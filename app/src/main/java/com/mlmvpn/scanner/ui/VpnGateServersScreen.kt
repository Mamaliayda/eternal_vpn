package com.mlmvpn.scanner.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mlmvpn.scanner.engines.vpngate.SoftEtherProbe
import com.mlmvpn.scanner.engines.vpngate.VpnGateServer
import com.mlmvpn.scanner.ui.home.frostedGlass
import com.mlmvpn.scanner.ui.settings.IosAlert
import com.mlmvpn.scanner.ui.settings.IosAlertAction
import com.mlmvpn.scanner.ui.settings.IosScreen
import com.mlmvpn.scanner.ui.theme.DuskPill
import com.mlmvpn.scanner.ui.theme.DuskTheme
import com.mlmvpn.scanner.ui.tunnel.CountryLabel
import com.mlmvpn.scanner.R
import com.mlmvpn.scanner.utils.S

// =================================================================================================
// The gateway's one list of servers.
//
// This replaces TWO screens that were both called «انتخاب سرور»: the main list's picker, and the
// second step of the «سرورهای بیشتر» browser. They showed the same servers out of two different
// collections, each with its own row, its own copy of the ping and handshake tests, its own delete
// button and its own idea of where the controls go -- and reaching the second one meant going four
// levels deep from the home screen, through a screen that closed itself on the way.
//
// The distinction they encoded is real and worth keeping, but it is a SCOPE, not a screen:
//
//   «فهرست من»  = live + kept - hidden -- the servers the connect button chooses from
//   «آرشیو»     = everything the app has ever seen, up to MAX_POOL
//
// So it is a pair of chips at the top, and the archive's one unique verb -- promoting servers into
// the main list -- becomes the bulk-edit mode that iOS puts in the navigation bar's trailing slot.
// Everything else (search, filter, sort, both tests, delete) is now written once and works the
// same in both scopes.
// =================================================================================================

enum class GatewayScope { MINE, ARCHIVE }

enum class GatewaySort(val label: String, val detail: String) {
    VERIFIED(
        S(R.string.tested_first),
        S(R.string.servers_that_passed_the_real_test_come),
    ),
    OFFICIAL(
        S(R.string.official_first),
        S(R.string.servers_running_on_the_service_s_own),
    ),
    PING(
        S(R.string.fastest),
        S(R.string.lowest_latency_as_measured_from_your_own),
    ),
    SCORE(
        S(R.string.score),
        S(R.string.the_score_vpn_gate_itself_gives_based),
    ),
    SPEED(
        S(R.string.bandwidth),
        S(R.string.the_server_s_advertised_speed_note_vpn),
    ),
    SESSIONS(
        S(R.string.users),
        S(R.string.number_of_active_sessions_busier_means_more),
    ),
    COUNTRY(
        S(R.string.country),
        S(R.string.alphabetically_by_country_code_and_within_each),
    ),
}

/**
 * Everything the list screen remembers.
 *
 * Hoisted out of the screen on purpose. In the old picker the sort mode and the country filter
 * were `remember`ed inside the dialog, so stepping into the browser and back reset both -- and
 * since the browser was reached by CLOSING the picker, that happened on the most common path
 * through the feature. Held here, the state belongs to the gateway rather than to whichever page
 * is currently drawn.
 */
class GatewayListState {
    var scope by mutableStateOf(GatewayScope.MINE)
    var sort by mutableStateOf(GatewaySort.VERIFIED)

    /** Empty means every country. A set, because the archive browser could always pick several. */
    var countries by mutableStateOf<Set<String>>(emptySet())
    var query by mutableStateOf("")

    /** iOS's "Edit" mode: rows grow checkboxes and the bar gains the bulk verbs. */
    var selecting by mutableStateOf(false)
    var checked by mutableStateOf<Set<String>>(emptySet())

    fun clearSelection() {
        selecting = false
        checked = emptySet()
    }
}

@Composable
fun VpnGateServersScreen(
    state: GatewayListState,
    /** live + kept - hidden: what the connect button chooses from. */
    mine: List<VpnGateServer>,
    /** The whole archive. */
    archive: List<VpnGateServer>,
    kept: Set<String>,
    pings: Map<String, Int>,
    handshakes: Map<String, SoftEtherProbe.Result>,
    selectedHost: String?,
    loading: Boolean,
    sweepRunning: Boolean,
    snack: String?,
    onPick: (VpnGateServer) -> Unit,
    onOpenDetail: (VpnGateServer) -> Unit,
    onOpenCountries: () -> Unit,
    onOpenSort: () -> Unit,
    onOpenHelp: () -> Unit,
    onRefresh: () -> Unit,
    onPingAll: (List<VpnGateServer>) -> Unit,
    onProbeAll: (List<VpnGateServer>) -> Unit,
    onKeep: (Set<String>) -> Unit,
    /** Scope-aware: bans from the main list, or purges from the archive. See the call site. */
    onRemove: (List<String>) -> Unit,
    onBack: () -> Unit,
) {
    var confirmRemove by remember { mutableStateOf<List<String>?>(null) }
    val locale = LocalConfiguration.current.locales[0]

    val pool = if (state.scope == GatewayScope.MINE) mine else archive

    // Search matches the localised name, the raw name VPN Gate publishes, the code and the
    // hostname alike -- people type «آلمان», "Germany", "DE" and "vpn847263841" and all four are
    // things they have actually seen on this screen.
    val visible = remember(pool, state.sort, state.countries, state.query, pings, handshakes, locale) {
        val q = state.query.trim()
        pool.asSequence()
            .filter { state.countries.isEmpty() || it.countryShort in state.countries }
            .filter {
                q.isBlank() ||
                    CountryLabel.name(it.countryShort, locale).contains(q, ignoreCase = true) ||
                    it.countryLong.contains(q, ignoreCase = true) ||
                    it.countryShort.contains(q, ignoreCase = true) ||
                    it.hostName.contains(q, ignoreCase = true)
            }
            .sortedWith(sorter(state.sort, pings, handshakes))
            .toList()
    }

    // "Dead" means a test actually condemned the server, never merely untested -- otherwise the
    // first tap would wipe most of the list. Counts BOTH result kinds: whichever test the user
    // ran is the one holding the evidence.
    val dead = remember(visible, pings, handshakes) {
        visible.filter {
            handshakes[it.hostName] is SoftEtherProbe.Result.Failed ||
                (pings[it.hostName] ?: 0) < 0
        }.map { it.hostName }
    }

    val healthy = remember(visible, pings, handshakes) {
        val verified = visible.filter { handshakes[it.hostName] is SoftEtherProbe.Result.Ok }
        verified.ifEmpty { visible.filter { (pings[it.hostName] ?: -1) > 0 } }.map { it.hostName }
    }

    IosScreen(
        title = S(R.string.servers_4),
        onBack = { if (state.selecting) state.clearSelection() else onBack() },
        backLabel = if (state.selecting) null else S(R.string.gateway),
        scrollable = false,
        trailing = {
            Text(
                if (state.selecting) S(R.string.done) else S(R.string.select),
                color = DuskTheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (state.selecting) FontWeight.SemiBold else FontWeight.Normal,
                modifier = Modifier
                    .clip(DuskTheme.controlShape)
                    .clickable {
                        if (state.selecting) state.clearSelection() else state.selecting = true
                    }
                    .padding(horizontal = DuskTheme.space1_5, vertical = DuskTheme.space1),
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchField(
                    query = state.query,
                    onChange = { state.query = it },
                    placeholder = S(R.string.search_a_country_or_server_name),
                )

                ScopeChips(
                    scope = state.scope,
                    mineCount = mine.size,
                    archiveCount = archive.size,
                    onSelect = { state.scope = it; state.clearSelection() },
                )

                Spacer(Modifier.height(DuskTheme.space2))

                GatewayToolbar(
                    busy = sweepRunning,
                    refreshing = loading,
                    filterActive = state.countries.isNotEmpty(),
                    sortActive = state.sort != GatewaySort.VERIFIED,
                    onPing = { onPingAll(visible) },
                    onProbe = { onProbeAll(visible) },
                    onCountry = onOpenCountries,
                    onSort = onOpenSort,
                    onRefresh = onRefresh,
                    onHelp = onOpenHelp,
                )

                Spacer(Modifier.height(DuskTheme.space1_5))

                VpnGateSweepStrip(modifier = Modifier.padding(horizontal = DuskTheme.space4, vertical = DuskTheme.microSpace))

                ListSummary(
                    loading = loading,
                    shown = visible.size,
                    total = pool.size,
                    sort = state.sort,
                    countries = state.countries,
                    onClearCountries = { state.countries = emptySet() },
                )

                if (visible.isEmpty()) {
                    // Weighted, so an empty list occupies the same box a full one would. Without
                    // it the column collapses upward and the explanation lands under the toolbar
                    // instead of where the rows were.
                    Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                        GatewayEmptyState(
                            title = emptyTitle(state, loading),
                            body = emptyBody(state, loading, mine.size, archive.size),
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = DuskTheme.space4)
                            .frostedGlass(DuskTheme.cardShape),
                        // Room for the floating bar, so the last row is never stuck under it.
                        contentPadding = PaddingValues(bottom = 88.dp),
                    ) {
                        items(visible, key = { it.hostName }) { server ->
                            GatewayServerRow(
                                server = server,
                                ping = pings[server.hostName],
                                handshake = handshakes[server.hostName],
                                mark = when {
                                    state.selecting -> RowMark.Check(server.hostName in state.checked)
                                    state.scope == GatewayScope.ARCHIVE && server.hostName in kept ->
                                        RowMark.Kept
                                    server.hostName == selectedHost -> RowMark.Selected
                                    else -> RowMark.None
                                },
                                onClick = {
                                    if (state.selecting) {
                                        state.checked =
                                            if (server.hostName in state.checked) state.checked - server.hostName
                                            else state.checked + server.hostName
                                    } else {
                                        onPick(server)
                                    }
                                },
                                onLongClick = { onOpenDetail(server) },
                                showSeparator = server != visible.last(),
                            )
                        }
                    }
                }
            }

            // ---- the floating bar --------------------------------------------------------
            //
            // Glass with the page's own bottom inset under it, not the black gradient the old
            // browser painted: a 94%-black band over the wallpaper is the one surface in the app
            // that is not made of the same material as everything around it.
            BottomBar(
                selecting = state.selecting,
                checkedCount = state.checked.size,
                healthyCount = healthy.size,
                deadCount = dead.size,
                scope = state.scope,
                sweepRunning = sweepRunning,
                snack = snack,
                onCheckHealthy = { state.checked = healthy.toSet() },
                onKeep = {
                    onKeep(state.checked)
                    state.clearSelection()
                },
                onRemoveChecked = { confirmRemove = state.checked.toList() },
                onRemoveDead = { confirmRemove = dead },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }

    // Deleting in bulk used to happen on the tap, with no confirmation anywhere -- one press on a
    // list of three hundred and they were gone, with no undo and nothing that said how many.
    val doomed = confirmRemove
    if (doomed != null) {
        IosAlert(
            title = S(R.string.delete_3) + faCount(doomed.size) + S(R.string.servers_5),
            message = if (state.scope == GatewayScope.MINE) {
                S(R.string.they_are_removed_from_your_list_and) +
                    S(R.string.you_can_bring_them_back_later_with)
            } else {
                S(R.string.they_are_removed_from_the_archive_if) +
                    S(R.string.as_new_servers)
            },
            onDismiss = { confirmRemove = null },
            actions = listOf(
                IosAlertAction(S(R.string.cancel), onClick = { confirmRemove = null }),
                IosAlertAction(
                    S(R.string.delete),
                    onClick = {
                        onRemove(doomed)
                        state.clearSelection()
                        confirmRemove = null
                    },
                    destructive = true,
                ),
            ),
        )
    }
}

// =================================================================================================
// Pieces
// =================================================================================================

/** The same field the region picker uses, so search looks the same wherever the app offers it. */
@Composable
private fun SearchField(query: String, onChange: (String) -> Unit, placeholder: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DuskTheme.space4, vertical = DuskTheme.space2)
            .clip(DuskTheme.controlShape)
            .background(DuskTheme.white.copy(alpha = 0.10f))
            .heightIn(min = 36.dp)
            .padding(horizontal = DuskTheme.space2_5),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = null,
            tint = DuskTheme.muted,
            modifier = Modifier.size(17.dp),
        )
        Spacer(Modifier.width(7.dp))
        BasicTextField(
            value = query,
            onValueChange = onChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = DuskTheme.onSurface),
            cursorBrush = SolidColor(DuskTheme.primary),
            modifier = Modifier.weight(1f),
            decorationBox = { inner ->
                Box {
                    if (query.isEmpty()) {
                        Text(placeholder, color = DuskTheme.muted, style = MaterialTheme.typography.bodyLarge)
                    }
                    inner()
                }
            },
        )
        if (query.isNotEmpty()) {
            Icon(
                Icons.Default.Close,
                contentDescription = S(R.string.clear_2),
                tint = DuskTheme.muted,
                modifier = Modifier
                    .size(26.dp)
                    .clip(DuskTheme.controlShape)
                    .clickable { onChange("") }
                    .padding(5.dp),
            )
        }
    }
}

/**
 * «فهرست من» / «آرشیو».
 *
 * The same chip the V2Ray screen uses for its folders, because it is the same idea: two views of
 * one collection, not two places.
 */
@Composable
private fun ScopeChips(
    scope: GatewayScope,
    mineCount: Int,
    archiveCount: Int,
    onSelect: (GatewayScope) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DuskTheme.space4)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(DuskTheme.space2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ScopeChip(S(R.string.my_list), mineCount, scope == GatewayScope.MINE) { onSelect(GatewayScope.MINE) }
        ScopeChip(S(R.string.archive), archiveCount, scope == GatewayScope.ARCHIVE) { onSelect(GatewayScope.ARCHIVE) }
    }
}

@Composable
private fun ScopeChip(label: String, count: Int, selected: Boolean, onClick: () -> Unit) {
    DuskPill(
        text = "$label · ${faCount(count)}",
        selected = selected,
        onClick = onClick,
        compact = true,
    )
}

/** What the list is showing right now, and a way out of whatever is narrowing it. */
@Composable
private fun ListSummary(
    loading: Boolean,
    shown: Int,
    total: Int,
    sort: GatewaySort,
    countries: Set<String>,
    onClearCountries: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp, vertical = DuskTheme.space2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = when {
                loading -> S(R.string.fetching_the_list)
                shown == total -> faCount(total) + S(R.string.servers_2) + sort.label
                else -> faCount(shown) + S(R.string.of) + faCount(total) + S(R.string.servers_2) + sort.label
            },
            color = DuskTheme.muted,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f),
        )
        if (countries.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .clip(DuskTheme.controlShape)
                    .background(DuskTheme.success.copy(alpha = 0.16f))
                    .clickable(onClick = onClearCountries)
                    .padding(horizontal = DuskTheme.space2, vertical = DuskTheme.space1),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    if (countries.size == 1) getNodeFlagEmoji(countries.first())
                    else faCount(countries.size) + S(R.string.countries_2),
                    color = DuskTheme.onSurface,
                    style = MaterialTheme.typography.labelSmall,
                )
                Spacer(Modifier.width(DuskTheme.space1))
                Icon(
                    Icons.Default.Close,
                    contentDescription = S(R.string.clear_the_country_filter),
                    tint = DuskTheme.muted,
                    modifier = Modifier.size(12.dp),
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    selecting: Boolean,
    checkedCount: Int,
    healthyCount: Int,
    deadCount: Int,
    scope: GatewayScope,
    sweepRunning: Boolean,
    snack: String?,
    onCheckHealthy: () -> Unit,
    onKeep: () -> Unit,
    onRemoveChecked: () -> Unit,
    onRemoveDead: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Nothing to say and nothing to do: draw nothing at all rather than an empty bar taking up
    // the bottom of every list.
    val hasWork = selecting || (deadCount > 0 && !sweepRunning) || snack != null
    AnimatedVisibility(visible = hasWork, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = DuskTheme.space4,
                    end = DuskTheme.space4,
                    bottom = LocalSystemBottomPadding.current + DuskTheme.space3,
                ),
            verticalArrangement = Arrangement.spacedBy(DuskTheme.space2),
        ) {
            if (snack != null) GatewaySnack(snack)

            if (selecting) {
                Row(horizontalArrangement = Arrangement.spacedBy(DuskTheme.space2)) {
                    GatewayBarButton(
                        onClick = onCheckHealthy,
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Bolt,
                        label = S(R.string.working_2),
                        enabled = healthyCount > 0,
                    )
                    if (scope == GatewayScope.ARCHIVE) {
                        GatewayBarButton(
                            onClick = onKeep,
                            modifier = Modifier.weight(1f),
                            label = if (checkedCount == 0) S(R.string.add)
                                    else S(R.string.add_2) + faCount(checkedCount) + ")",
                            enabled = checkedCount > 0,
                            accent = DuskTheme.primary,
                        )
                    }
                    GatewayBarButton(
                        onClick = onRemoveChecked,
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.DeleteSweep,
                        label = if (checkedCount == 0) S(R.string.delete)
                                else S(R.string.delete_4) + faCount(checkedCount) + ")",
                        enabled = checkedCount > 0,
                        accent = if (checkedCount > 0) DuskTheme.error else null,
                    )
                }
            } else if (deadCount > 0 && !sweepRunning) {
                GatewayBarButton(
                    onClick = onRemoveDead,
                    modifier = Modifier.fillMaxWidth(),
                    icon = Icons.Default.DeleteSweep,
                    // Count in the label: this deletes in bulk, so the tap must not be blind.
                    label = S(R.string.delete_3) + faCount(deadCount) + S(R.string.dead_servers),
                    accent = DuskTheme.error,
                )
            }
        }
    }
}

// =================================================================================================
// Ordering and empty states
// =================================================================================================

private fun sorter(
    sort: GatewaySort,
    pings: Map<String, Int>,
    handshakes: Map<String, SoftEtherProbe.Result>,
): Comparator<VpnGateServer> = when (sort) {
    // Servers proven to complete a handshake first, fastest of those on top. Untested rank above
    // ones that failed -- a failure is information, an absent test is not.
    GatewaySort.VERIFIED -> compareBy {
        when (val r = handshakes[it.hostName]) {
            is SoftEtherProbe.Result.Ok -> r.ms
            null -> 1_000_000
            else -> 2_000_000
        }
    }
    GatewaySort.OFFICIAL -> compareBy<VpnGateServer> { !it.isOfficialRelay }
        .thenByDescending { it.score }
    // Untested and unreachable servers sink to the bottom rather than masquerading as instant.
    GatewaySort.PING -> compareBy {
        val p = pings[it.hostName] ?: Int.MAX_VALUE - 1
        if (p <= 0) Int.MAX_VALUE else p
    }
    GatewaySort.SCORE -> compareByDescending { it.score }
    GatewaySort.SPEED -> compareByDescending { it.speedBps }
    GatewaySort.SESSIONS -> compareByDescending { it.numSessions }
    GatewaySort.COUNTRY -> compareBy({ it.countryShort }, { -it.score })
}

private fun emptyTitle(state: GatewayListState, loading: Boolean): String = when {
    loading -> S(R.string.fetching_the_list_2)
    state.query.isNotBlank() -> S(R.string.nothing_found)
    state.countries.isNotEmpty() -> S(R.string.there_are_no_servers_in_these_countries)
    state.scope == GatewayScope.ARCHIVE -> S(R.string.the_archive_is_empty)
    else -> S(R.string.the_list_is_empty)
}

private fun emptyBody(
    state: GatewayListState,
    loading: Boolean,
    mineCount: Int,
    archiveCount: Int,
): String = when {
    loading -> S(R.string.just_a_moment)
    state.query.isNotBlank() ->
        S(R.string.no_server_matched_clear_the_search_or, state.query)
    state.countries.isNotEmpty() ->
        S(R.string.clear_the_country_filter_or_look_in) + faCount(archiveCount) + S(R.string.servers_6)
    state.scope == GatewayScope.ARCHIVE ->
        S(R.string.the_archive_fills_up_on_every_refresh)
    mineCount == 0 ->
        S(R.string.you_have_no_servers_yet_tap_refresh)
    else ->
        S(R.string.every_server_in_your_list_has_been)
}
