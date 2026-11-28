#!/usr/bin/env python3
"""Audit every source/resource file named by a checked checklist row.

Scans whole referenced files so shared files cannot hide problems in a nested visual. Findings
retain the checklist rows that reference the source. Fixed theme/data/art palettes are documented
allowlist entries; non-semantic Material components are listed for review, and are accepted only
because the application root installs the Soft Dusk ColorScheme. Run:
  python3 tools/ui_audit.py --markdown tools/ui_audit_report.md --json tools/ui_audit_report.json
"""
from __future__ import annotations
import argparse, json, re, sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
CHECKLIST = ROOT / "SCREENS_CHECKLIST.md"
PALETTE_FILES = {
    "app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt",
    "app/src/main/java/com/mlmvpn/scanner/ui/home/Wallpapers.kt",
    "app/src/main/java/com/mlmvpn/scanner/ui/home/HomeDestinations.kt",
    "app/src/main/java/com/mlmvpn/scanner/ui/arena/ArenaRace.kt",
    "app/src/main/java/com/mlmvpn/scanner/store/StoreCatalog.kt",
    "app/src/main/res/values/colors.xml",
}
PATH_RE = re.compile(r"(?<![A-Za-z0-9_])(?:[A-Za-z0-9_.-]+/)+[A-Za-z0-9_.-]+\.(?:kt|java|xml|gradle|json|html|css)\b")
HEX_RE = re.compile(r"(?<![A-Za-z0-9_])#[0-9A-Fa-f]{6}(?:[0-9A-Fa-f]{2})?\b")
COLOR_INT_RE = re.compile(r"\bColor\s*\(\s*0[xX][0-9A-Fa-f]{6,8}\b")
RADIUS_RE = re.compile(r"\b(?:RoundedCornerShape|CutCornerShape|CornerSize)\s*\([^\n)]*?\b\d+(?:\.\d+)?\.dp")
FONT_SIZE_RE = re.compile(r"\b(?:fontSize|lineHeight)\s*=\s*\d+(?:\.\d+)?\.sp\b")
PHYSICAL_KT_RE = re.compile(
    r"\b(?:TextAlign|Alignment|AbsoluteAlignment)\.(?:Left|Right)\b|"
    r"\b(?:padding|absolutePadding)\s*\([^\n)]*\b(?:left|right)\s*=|"
    r"\b(?:left|right)\s*=\s*\d+(?:\.\d+)?\.dp"
)
PHYSICAL_XML_RE = re.compile(
    r"\bandroid:(?:paddingLeft|paddingRight|layout_marginLeft|layout_marginRight|"
    r"layout_toLeftOf|layout_toRightOf|layout_alignParentLeft|layout_alignParentRight)\b|"
    r"\bandroid:gravity=\"[^\"]*\b(?:left|right)\b"
)
# Resource references such as @font/vazirmatn are tokens, not literal family names.
LITERAL_FONT_RE = re.compile(
    r"\b(?:FontFamily\s*\(\s*['\"][^'\"]+['\"]|Typeface\.create\s*\(\s*['\"][^'\"]+['\"]|"
    r"fontFamily\s*=\s*['\"](?:serif|sans-serif|monospace|cursive|casual|fantasy)['\"]|"
    r"android:fontFamily=\"(?:serif|sans-serif|monospace|cursive|casual|fantasy)\")"
)
TOAST_RE = re.compile(r"\b(?:android\.widget\.)?Toast\.makeText\s*\(|\bToast\s*\(")
MATERIAL_SCHEME_RE = re.compile(r"\bMaterialTheme\.colorScheme\.[A-Za-z_]")
MATERIAL_CALL_RE = re.compile(r"\b(Button|TextButton|OutlinedButton|ElevatedButton|Switch|Card|Surface|TextField|OutlinedTextField)\s*\(")
# Explicitly reviewed platform/background fallback cases; new Toast locations fail by default.
TOAST_DEFERRALS = {
    "app/src/main/java/com/mlmvpn/core/aether/AetherScanService.kt": "service-start failure fallback",
    "app/src/main/java/com/mlmvpn/scanner/MainActivity.kt": "missing system permission/activity resolver fallback",
    "app/src/main/java/com/mlmvpn/scanner/lan/LanNotification.kt": "pre-Android-13 clipboard feedback; Android 13+ supplies system feedback",
    "app/src/main/java/com/mlmvpn/scanner/VpnTileService.kt": "system quick-settings tile action fallback; platform owns tile surface",
    "app/src/main/java/com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt": "one-shot ACTION_VIEW importer immediately launches app; no screen UI",
    "app/src/main/java/com/mlmvpn/scanner/widget/WidgetActionActivity.kt": "transparent one-shot widget trampoline outside Compose UI",
    "app/src/main/java/com/mlmvpn/scanner/backup/BackupStage.kt": "process-start restore result is emitted before Compose UI exists",
    "app/src/main/java/com/mlmvpn/scanner/engines/github/GtEngine.kt": "engine-level background operation has no Compose host callback",
}



def resolve_ref(ref: str) -> Path | None:
    p = ref.strip("`.,;()")
    if p.startswith("app/"):
        q = ROOT / p
        return q if q.is_file() else None
    if p.startswith(("com/", "kittoku/")):
        for base in (ROOT / "app/src/main/java", ROOT / "app/src/main/kotlin"):
            q = base / p
            if q.is_file(): return q
    return None


def checked_rows():
    return [(n, line) for n, line in enumerate(CHECKLIST.read_text().splitlines(), 1) if re.match(r"^- \[x\]", line)]


def references(rows):
    refs: dict[Path, list[tuple[int, str]]] = defaultdict(list)
    missing=[]
    checklist_lines=CHECKLIST.read_text().splitlines()
    source_root=ROOT / "app/src/main/java"
    all_sources=[p for p in source_root.rglob("*") if p.suffix in {".kt", ".java", ".xml"}]
    symbols=defaultdict(list)
    for path in all_sources:
        if path.suffix in {".kt", ".java"}:
            for m in re.finditer(r"\b(?:class|object|interface|enum\s+class)\s+([A-Za-z_][A-Za-z0-9_]*)",path.read_text(errors="replace")):
                symbols[m.group(1)].append(path)
    for n,row in rows:
        current=next((line[3:].strip() for line in reversed(checklist_lines[:n]) if line.startswith("## ")),"")
        found=[]
        for raw in dict.fromkeys(PATH_RE.findall(row)):
            p=resolve_ref(raw)
            if p is not None: found.append(p)
            elif raw.startswith(("app/","com/")): missing.append({"row_line":n,"row":row,"reference":raw})
        # Only resolve unique class/object names, never generic function names; a literal path on
        # the row takes precedence. This avoids collisions such as countryName across packages.
        if not found:
            for token in re.findall(r"`([^`]+)`",row):
                name=token.rsplit(".",1)[-1]
                candidates=symbols.get(name,[])
                if len(candidates)==1: found.append(candidates[0])
        if not found:
            if n in range(9,13):
                found += [source_root/"com/mlmvpn/scanner/ui/theme/SoftDusk.kt", source_root/"com/mlmvpn/scanner/ui/theme/Theme.kt"]
            elif current == "Main destination registry":
                found.append(source_root/"com/mlmvpn/scanner/ui/home/HomeDestinations.kt")
            elif n == 1324:
                found += [source_root/"com/mlmvpn/scanner/MainActivity.kt", source_root/"com/mlmvpn/scanner/ui/theme/Theme.kt"]
            elif n == 1325:
                found += list((source_root/"com/mlmvpn/scanner").rglob("*Notification*.kt")) + list((source_root/"com/mlmvpn/scanner").rglob("*Tile*.kt"))
            elif n == 1326:
                found += [source_root/"com/mlmvpn/scanner/widget/MethodWidgets.kt", source_root/"com/mlmvpn/scanner/widget/studio/StudioWidgets.kt", source_root/"com/mlmvpn/scanner/ui/widgetstudio/WidgetStudioScreen.kt"]
            elif n == 1327:
                found += [source_root/"com/mlmvpn/scanner/ui/npv/NpvImportActivity.kt", source_root/"com/mlmvpn/scanner/ui/amnezia/AmzImportActivity.kt"]
            elif n == 1335:
                # Audit-control row describes this tool's outcome, not a screen/source file.
                continue
        found=[p for p in dict.fromkeys(found) if p.is_file()]
        if not found:
            missing.append({"row_line":n,"row":row,"reference":"no source reference or resolvable class"})
        for p in found: refs[p].append((n,row))
    return refs,missing


def is_comment(line: str, suffix: str) -> bool:
    stripped=line.lstrip()
    if suffix in (".kt", ".java", ".gradle"):
        return stripped.startswith(("//","/*","*","*/"))
    if suffix==".xml": return stripped.startswith("<!--")
    return False


def record(category,path,n,line,rows):
    return {"category":category,"file":path.relative_to(ROOT).as_posix(),"line":n,
            "text":line.strip()[:240],"checked_rows":sorted({rn for rn,_ in rows})}


def audit_file(path, rows):
    rel=path.relative_to(ROOT).as_posix();lines=path.read_text(errors="replace").splitlines();out=[]
    documented_palette=(rel in PALETTE_FILES or (rel.startswith("app/src/main/res/drawable/ic_app_") and rel.endswith(".xml")))
    theme_token_file=rel=="app/src/main/java/com/mlmvpn/scanner/ui/theme/SoftDusk.kt"
    for n,line in enumerate(lines,1):
        if is_comment(line,path.suffix): continue
        if not documented_palette:
            if HEX_RE.search(line): out.append(record("hardcoded_hex",path,n,line,rows))
            if COLOR_INT_RE.search(line): out.append(record("color_0x",path,n,line,rows))
        if not theme_token_file:
            if RADIUS_RE.search(line): out.append(record("hardcoded_dp_radius",path,n,line,rows))
            if FONT_SIZE_RE.search(line): out.append(record("hardcoded_font_size",path,n,line,rows))
        if PHYSICAL_KT_RE.search(line): out.append(record("physical_left_right",path,n,line,rows))
        if PHYSICAL_XML_RE.search(line): out.append(record("physical_left_right_xml",path,n,line,rows))
        if LITERAL_FONT_RE.search(line): out.append(record("literal_font_name",path,n,line,rows))
        if TOAST_RE.search(line):
            category = "reviewed_toast_deferral" if rel in TOAST_DEFERRALS else "toast_call"
            item=record(category,path,n,line,rows)
            if category == "reviewed_toast_deferral": item["rationale"] = TOAST_DEFERRALS[rel]
            out.append(item)
        if MATERIAL_SCHEME_RE.search(line): out.append(record("material_color_scheme",path,n,line,rows))
        if MATERIAL_CALL_RE.search(line):
            # Review calls whose colors parameter is omitted. App Compose content is hosted beneath
            # MlmVpnTheme, which supplies Soft Dusk's ColorScheme; this category is surfaced for
            # manual confirmation and counted as reviewed, not as an unresolved hard-coded color.
            window="\n".join(lines[n-1:min(len(lines),n+30)])
            if "colors =" not in window and not any(token in window for token in ("iosButtonColors(","DuskCard(","DuskPill(")):
                out.append(record("material_default_colors_review",path,n,line,rows))
    return out


def main():
    ap=argparse.ArgumentParser();ap.add_argument("--markdown");ap.add_argument("--json",dest="json_path");args=ap.parse_args()
    rows=checked_rows();refs,missing=references(rows);findings=[]
    for p,linked in sorted(refs.items()): findings.extend(audit_file(p,linked))
    findings.sort(key=lambda x:(x["file"],x["line"],x["category"]))
    counts=defaultdict(int)
    for f in findings: counts[f["category"]]+=1
    reviewed=counts.pop("material_default_colors_review",0)
    reviewed_toasts=counts.pop("reviewed_toast_deferral",0)
    unresolved=sum(counts.values())+len(missing)
    data={"checked_rows":len(rows),"rows_with_references":len({n for r in refs.values() for n,_ in r}),
          "files_scanned":len(refs),"unresolved_references":missing,"unresolved_findings":unresolved,
          "reviewed_material_defaults":reviewed,"reviewed_toast_deferrals":reviewed_toasts,"finding_counts":dict(sorted(counts.items())),"findings":findings}
    print(f"Checked rows: {data['checked_rows']}; rows referencing source files: {data['rows_with_references']}; files scanned: {data['files_scanned']}")
    print(f"Unresolved findings: {unresolved}; Material defaults reviewed under Soft Dusk ColorScheme: {reviewed}; documented Toast deferrals: {reviewed_toasts}; unresolved references: {len(missing)}")
    for k,v in sorted(counts.items()): print(f"  {k}: {v}")
    for f in findings: print(f"{f['file']}:{f['line']}: [{f['category']}] rows={','.join(map(str,f['checked_rows']))} {f['text']}")
    for x in missing: print(f"CHECKLIST:{x['row_line']}: unresolved source ref {x['reference']}")
    if args.json_path: (ROOT/args.json_path).write_text(json.dumps(data,indent=2,ensure_ascii=False)+"\n")
    if args.markdown:
        md=["# Checked-row UI source audit","",f"- Checked rows: {len(rows)}",f"- Rows with source references: {data['rows_with_references']}",f"- Unique files scanned: {len(refs)}",f"- Unresolved findings: {unresolved}",f"- Material component defaults reviewed under the root Soft Dusk ColorScheme: {reviewed}",f"- Explicitly documented platform/background Toast deferrals: {reviewed_toasts}","","| Category | Unresolved hits |","|---|---:|"]
        md += [f"| `{k}` | {v} |" for k,v in sorted(counts.items())]
        md += ["","| File:line | Category | Finding | Checked rows |","|---|---|---|---|"]
        md += [f"| `{f['file']}:{f['line']}` | `{f['category']}` | `{f['text'].replace('|','&#124;')}` | {', '.join(map(str,f['checked_rows']))} |" for f in findings]
        md += ["","## Unresolved references",""]+[f"- Checklist line {x['row_line']}: `{x['reference']}`" for x in missing]
        (ROOT/args.markdown).write_text("\n".join(md)+"\n")
    return 1 if unresolved else 0

if __name__=="__main__": sys.exit(main())
