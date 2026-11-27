package com.mlmvpn.scanner.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlmvpn.scanner.engines.freeconfig.FreeConfigEngine
import com.mlmvpn.scanner.ui.home.frostedGlass
import com.mlmvpn.scanner.models.VpnNode
import com.mlmvpn.scanner.ui.settings.IosScreen
import com.mlmvpn.scanner.ui.settings.SettingsActionRow
import com.mlmvpn.scanner.ui.settings.SettingsFooter
import com.mlmvpn.scanner.ui.settings.SettingsGroup
import com.mlmvpn.scanner.ui.settings.SettingsRow
import com.mlmvpn.scanner.ui.settings.SettingsSectionHeader
import com.mlmvpn.scanner.ui.settings.SettingsSlider
import com.mlmvpn.scanner.ui.theme.DuskCard
import com.mlmvpn.scanner.ui.theme.DuskTheme
import kotlinx.coroutines.launch
import com.mlmvpn.scanner.R
import com.mlmvpn.scanner.utils.S

// =================================================================================================
// Free configs, in three steps.
//
// The flow was written to live inside the old Add-Node dialog, which supplied the title bar and
// the insets. When Add-Node became a pushed page, this kept its old assumption: every step was a
// bare `Column(fillMaxSize)` with no navigation bar, no system insets and no scrolling, so the
// content ran under the status bar at the top and under the gesture bar at the bottom, and the
// long lists had nowhere to go.
//
// Each step is its own [IosScreen] now -- own bar, own title, own back -- so the three of them
// read as one flow you move through rather than as one screen whose contents keep changing. The
// controls are the app's: grouped rows for the categories, the settings slider for the count, and
// action rows instead of the Material buttons and the solid-fill chips.
// =================================================================================================

private enum class WizardStep { CHECKING, PICK_POOL, PICK_COUNT, FETCHING, RESULTS, DONE, FAILED }

/**
 * Check what is available → pick a category → pick how many → fetch and real-connection-test that
 * many → import the working ones → hand the user over to V2Ray to connect.
 *
 * That last step is new, and it is the whole reason this flow needed reworking when it stopped
 * being a page inside Add-Node. There, importing was the end of the story: the user was already
 * inside V2Ray, so dropping them back on the node list put them exactly where the configs had
 * landed. As its own destination on the home screen it is a different app, and "۴۲ configs
 * imported" followed by a bounce to the home grid leaves the user holding configs with no idea
 * where they went or how to use them.
 */
@Composable
fun FreeConfigWizard(
    onImport: (List<VpnNode>) -> Unit,
    onClose: () -> Unit,
    /**
     * Take the user to V2Ray, aimed at the config passed in.
     *
     * Navigation, not connection. The connect path -- VPN consent, then the service intent --
     * belongs to [NodesTab] and stays there; what this does is leave the user one tap away from
     * it instead of one search plus one tap.
     */
    onGoToNodes: (VpnNode?) -> Unit,
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val scope = rememberCoroutineScope()
    var step by remember { mutableStateOf(WizardStep.CHECKING) }
    var catalog by remember { mutableStateOf<FreeConfigEngine.Catalog?>(null) }
    var pool by remember { mutableStateOf<FreeConfigEngine.Pool?>(null) }
    var candidates by remember { mutableStateOf<List<String>>(emptyList()) }
    var desiredCount by remember { mutableStateOf(100) }
    var progress by remember { mutableStateOf(FreeConfigEngine.TestProgress(0, 0, 0, 0)) }
    var results by remember { mutableStateOf<List<VpnNode>>(emptyList()) }
    var newResults by remember { mutableStateOf<List<VpnNode>>(emptyList()) }
    var alreadyOwnedCount by remember { mutableStateOf(0) }
    var stopRequested by remember { mutableStateOf(false) }

    // Live, so importing on this visit updates the "you already have N" row behind the flow.
    val savedNodes by com.mlmvpn.scanner.data.NodeManager(context).nodesFlow.collectAsState()
    val ownedCount = remember(savedNodes) {
        savedNodes.count { it.groupTitle == FreeConfigEngine.GROUP_NAME }
    }

    // Two separate loads, on purpose. The catalog is a small index that says which categories
    // exist and how big they are, so it can be shown immediately; downloading a category's
    // several-thousand-line list only happens once the user has chosen one. Fetching all of
    // them up front would mean waiting on megabytes to display four numbers.
    fun startCheck() {
        step = WizardStep.CHECKING
        pool = null
        scope.launch {
            val loaded = try { FreeConfigEngine.fetchCatalog(force = true) } catch (e: Exception) { null }
            catalog = loaded
            step = if (loaded == null || loaded.pools.isEmpty()) WizardStep.FAILED else WizardStep.PICK_POOL
        }
    }

    fun choosePool(selected: FreeConfigEngine.Pool) {
        pool = selected
        step = WizardStep.CHECKING
        scope.launch {
            val found = try { FreeConfigEngine.fetchCandidates(selected) } catch (e: Exception) { emptyList() }
            candidates = found
            desiredCount = minOf(100, found.size).coerceAtLeast(if (found.isEmpty()) 0 else 1)
            step = if (found.isEmpty()) WizardStep.FAILED else WizardStep.PICK_COUNT
        }
    }

    LaunchedEffect(Unit) { startCheck() }

    when (step) {
        WizardStep.CHECKING -> BusyStep(
            title = S(R.string.free_configs),
            onBack = onClose,
            icon = Icons.Default.TravelExplore,
            headline = S(R.string.checking_the_sources),
            body = S(R.string.just_a_moment_while_we_find_out),
        )

        WizardStep.FAILED -> BusyStep(
            title = S(R.string.free_configs),
            onBack = onClose,
            icon = Icons.Default.CloudOff,
            headline = S(R.string.could_not_fetch_the_sources),
            body = S(R.string.the_public_sources_were_not_reachable_check),
            spinner = false,
            actionLabel = S(R.string.try_again_r2),
            actionIcon = Icons.Default.Refresh,
            onAction = { startCheck() },
        )

        WizardStep.PICK_POOL -> PickPoolStep(
            catalog = catalog,
            onPick = { choosePool(it) },
            onBack = onClose,
            ownedCount = ownedCount,
            onGoToNodes = { onGoToNodes(null) },
        )

        WizardStep.PICK_COUNT -> PickCountStep(
            available = candidates.size,
            poolTitle = pool?.title.orEmpty(),
            poolWhy = pool?.why.orEmpty(),
            onBack = { step = WizardStep.PICK_POOL },
            desiredCount = desiredCount,
            onCountChange = { desiredCount = it },
            onConfirm = {
                step = WizardStep.FETCHING
                stopRequested = false
                scope.launch {
                    val working = FreeConfigEngine.collectWorking(
                        context = context,
                        candidates = candidates,
                        targetCount = desiredCount,
                        shouldStop = { stopRequested },
                    ) { p -> progress = p }
                    results = working

                    // Compare against what the user already has saved (by real server identity,
                    // not our randomized "mlmvpnNNNN" name) so a re-fetch of an already-owned
                    // server doesn't get imported as a duplicate.
                    // Off the main thread: a read of every saved node and a parse of each of them.
                    val (fresh, owned) = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Default) {
                        val existingUris = com.mlmvpn.scanner.data.NodeManager(context).nodes.map { it.uri }
                        FreeConfigEngine.splitAlreadyOwned(working, existingUris)
                    }
                    newResults = fresh
                    alreadyOwnedCount = owned.size

                    step = WizardStep.RESULTS
                }
            },
        )

        WizardStep.FETCHING -> FetchingStep(
            progress = progress,
            stopping = stopRequested,
            onStopHere = { stopRequested = true },
            onBack = { step = WizardStep.PICK_COUNT },
        )

        WizardStep.RESULTS -> ResultsStep(
            results = results,
            newCount = newResults.size,
            alreadyOwnedCount = alreadyOwnedCount,
            onTransfer = {
                onImport(newResults)
                step = WizardStep.DONE
            },
            onBack = onClose,
        )

        WizardStep.DONE -> DoneStep(
            imported = newResults.size,
            alreadyOwnedCount = alreadyOwnedCount,
            // The first config that passed the real test. Not "the fastest" -- the funnel returns
            // them in the order they finished, and no per-node latency is recorded -- so it is
            // presented as "one of them is selected for you", which is what it actually is.
            firstNode = newResults.firstOrNull() ?: results.firstOrNull(),
            onGoToNodes = onGoToNodes,
            onMore = { startCheck() },
            onClose = onClose,
        )
    }
}

/** A step with nothing to do on it: a glyph, a sentence, and either a spinner or one action. */
@Composable
private fun BusyStep(
    title: String,
    onBack: () -> Unit,
    icon: ImageVector,
    headline: String,
    body: String,
    spinner: Boolean = true,
    actionLabel: String? = null,
    actionIcon: ImageVector = Icons.Default.Refresh,
    onAction: (() -> Unit)? = null,
) {
    IosScreen(title = title, onBack = onBack, backLabel = S(R.string.add_r2)) {
        Spacer(Modifier.height(60.dp))
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space6),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(DuskTheme.muted.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icon, contentDescription = null, tint = DuskTheme.muted, modifier = Modifier.size(34.dp))
            }
            Spacer(Modifier.height(DuskTheme.space4))
            Text(headline, color = DuskTheme.onSurface, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(DuskTheme.space2))
            Text(
                body,
                color = DuskTheme.muted,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
            )
            if (spinner) {
                Spacer(Modifier.height(22.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = DuskTheme.muted,
                    strokeWidth = 2.dp,
                )
            }
        }

        if (actionLabel != null && onAction != null) {
            Spacer(Modifier.height(DuskTheme.space5))
            SettingsGroup {
                SettingsActionRow(actionLabel, actionIcon, onClick = onAction)
            }
        }

        Spacer(Modifier.height(DuskTheme.space5))
    }
}

/**
 * Which category to draw from.
 *
 * The aggregator publishes the same servers several times over, pre-sorted by how each did in its
 * own testing, and the difference between those lists is the difference between a sweep that finds
 * working configs in seconds and one that grinds through thousands of dead ones. The live count
 * sits on the trailing edge of each row, where a settings row puts its value: "verified" being a
 * tenth the size of "all" is exactly the trade the user is being asked to make.
 */
@Composable
private fun PickPoolStep(
    catalog: FreeConfigEngine.Catalog?,
    onPick: (FreeConfigEngine.Pool) -> Unit,
    onBack: () -> Unit,
    /** How many free configs the user already holds, so a return visit is not a dead end. */
    ownedCount: Int,
    onGoToNodes: () -> Unit,
) {
    IosScreen(title = S(R.string.free_configs), onBack = onBack, backLabel = S(R.string.home_r2)) {
        Spacer(Modifier.height(DuskTheme.space3))

        // The second-visit path. As a page inside Add-Node this was unnecessary -- the user was
        // standing in the node list and could see what they already had. Opened from the home
        // screen it is the first question they have: "didn't I already do this?"
        if (ownedCount > 0) {
            SettingsGroup {
                SettingsRow(
                    title = S(R.string.the_free_configs_you_already_have),
                    subtitle = S(R.string.in_v2ray_the_folder, FreeConfigEngine.GROUP_NAME),
                    icon = Icons.Default.Bolt,
                    tint = DuskTheme.success,
                    value = faCount(ownedCount),
                    onClick = onGoToNodes,
                )
            }
            SettingsFooter(
                S(R.string.if_they_still_connect_there_is_no) +
                    S(R.string.a_fresh_batch_below)
            )
        }

        SettingsSectionHeader(S(R.string.which_batch_should_we_take_configs_from))
        SettingsGroup {
            catalog?.pools.orEmpty().forEachIndexed { index, p ->
                if (index > 0) Separator()
                SettingsRow(
                    title = p.title,
                    subtitle = p.why.takeIf { it.isNotBlank() },
                    icon = Icons.Default.Bolt,
                    tint = if (index == 0) DuskTheme.success else DuskTheme.disabled,
                    // A negative count means "not known until it is downloaded" -- a dash rather
                    // than a zero, which would read as an empty category.
                    value = if (p.count >= 0) faCount(p.count) else "—",
                    onClick = { onPick(p) },
                )
            }
        }
        SettingsFooter(
            S(R.string.the_higher_batches_are_smaller_but_more) +
                S(R.string.configs_it_holds_right_now)
        )

        catalog?.let { c ->
            val bits = buildList {
                c.intervalMinutes?.let { add(S(R.string.refreshed_every_minutes, faCount(it))) }
                if (c.healthySources != null && c.totalSources != null) {
                    add(S(R.string.of_sources_healthy, faCount(c.healthySources!!), faCount(c.totalSources!!)))
                }
            }
            if (bits.isNotEmpty()) {
                SettingsFooter(bits.joinToString(" · "))
            }
        }

        Spacer(Modifier.height(DuskTheme.space5))
    }
}

/** How many to fetch, and from where. */
@Composable
private fun PickCountStep(
    available: Int,
    poolTitle: String,
    poolWhy: String,
    onBack: () -> Unit,
    desiredCount: Int,
    onCountChange: (Int) -> Unit,
    onConfirm: () -> Unit,
) {
    IosScreen(title = S(R.string.count), onBack = onBack, backLabel = S(R.string.batches)) {
        Spacer(Modifier.height(DuskTheme.space3))

        if (poolTitle.isNotBlank()) {
            SettingsSectionHeader(S(R.string.selected_batch))
            SettingsGroup {
                SettingsRow(
                    title = poolTitle,
                    subtitle = poolWhy.takeIf { it.isNotBlank() },
                    icon = Icons.Default.Bolt,
                    tint = DuskTheme.success,
                    value = S(R.string.change),
                    onClick = onBack,
                )
            }
        }

        SettingsSectionHeader(S(R.string.how_many_configs_do_you_want))
        SettingsSlider(
            title = S(R.string.count),
            value = desiredCount.coerceIn(1, available.coerceAtLeast(1)),
            onValueChange = { onCountChange(it.coerceAtLeast(1)) },
            valueLabel = faCount(desiredCount),
            range = 1f..available.toFloat().coerceAtLeast(1f),
        )

        Spacer(Modifier.height(DuskTheme.space3))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space4),
            horizontalArrangement = Arrangement.spacedBy(DuskTheme.space2),
        ) {
            listOf(50, 100, 300, available).filter { it in 1..available }.distinct().forEach { quick ->
                QuickPickChip(
                    value = quick,
                    selected = desiredCount == quick,
                    modifier = Modifier.weight(1f),
                ) { onCountChange(quick) }
            }
        }

        SettingsFooter(
            S(R.string.this_batch_holds_configs_right_now_each, faCount(available)) +
                S(R.string.connection_so_a_larger_number_means_a)
        )

        Spacer(Modifier.height(DuskTheme.space3))
        SettingsGroup {
            SettingsActionRow(
                label = S(R.string.fetch_configs, faCount(desiredCount)),
                icon = Icons.Default.Download,
                tint = DuskTheme.success,
                enabled = available > 0,
                onClick = onConfirm,
            )
        }

        Spacer(Modifier.height(DuskTheme.space5))
    }
}

@Composable
private fun QuickPickChip(
    value: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(DuskTheme.glyphShape)
            // Tinted glass with white type, like every other selectable chip in the app -- this
            // was a solid accent slab with dark text on it.
            .background(
                if (selected) DuskTheme.white.copy(alpha = 0.20f) else DuskTheme.white.copy(alpha = 0.07f)
            )
            .clickable(onClick = onClick)
            .padding(vertical = DuskTheme.space3),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            faCount(value),
            color = if (selected) DuskTheme.onSurface else DuskTheme.muted,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        )
    }
}

/** The sweep, with a way to stop early and keep what has been found. */
@Composable
private fun FetchingStep(
    progress: FreeConfigEngine.TestProgress,
    stopping: Boolean,
    onStopHere: () -> Unit,
    onBack: () -> Unit,
) {
    val fraction = if (progress.candidates == 0) 0f
    else (progress.tested.toFloat() / progress.candidates.toFloat()).coerceIn(0f, 1f)
    val animated by animateFloatAsState(targetValue = fraction, label = "fetchProgress")

    IosScreen(title = S(R.string.fetching_r2), onBack = onBack, backLabel = S(R.string.count)) {
        Spacer(Modifier.height(DuskTheme.space4))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DuskTheme.space4)
                .frostedGlass(DuskTheme.tileShape)
                .padding(18.dp),
        ) {
            Text(
                S(R.string.fetching_and_testing_configs),
                color = DuskTheme.onSurface,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(DuskTheme.space1 + 2.dp))
            Text(
                S(R.string.only_configs_that_actually_connected_are_added),
                color = DuskTheme.muted,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 20.sp,
            )
            Spacer(Modifier.height(DuskTheme.space4))
            LinearProgressIndicator(
                progress = animated,
                modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
                color = DuskTheme.success,
                trackColor = DuskTheme.white.copy(alpha = 0.14f),
            )
            Spacer(Modifier.height(DuskTheme.space3))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    S(R.string.tested_r2, faCount(progress.tested), faCount(progress.candidates)),
                    color = DuskTheme.muted,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    S(R.string.connected_r2, faCount(progress.working), faCount(progress.target)),
                    color = DuskTheme.success,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        Spacer(Modifier.height(DuskTheme.space4))
        SettingsGroup {
            SettingsActionRow(
                label = if (stopping) S(R.string.stopping)
                else S(R.string.this_many_is_enough, faCount(progress.working)),
                icon = Icons.Default.Stop,
                busy = stopping,
                enabled = progress.working > 0,
                onClick = onStopHere,
            )
        }
        SettingsFooter(
            S(R.string.stopping_is_not_immediate_the_batch_being) +
                S(R.string.was_found_is_kept)
        )

        Spacer(Modifier.height(DuskTheme.space5))
    }
}

/** What came back, and the one action that matters. */
@Composable
private fun ResultsStep(
    results: List<VpnNode>,
    newCount: Int,
    alreadyOwnedCount: Int,
    onTransfer: () -> Unit,
    onBack: () -> Unit,
) {
    IosScreen(title = S(R.string.result), onBack = onBack, backLabel = S(R.string.add_r2), scrollable = false) {
        Spacer(Modifier.height(DuskTheme.space3))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DuskTheme.space4)
                .frostedGlass(DuskTheme.chartShape)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Default.CheckCircle,
                contentDescription = null,
                tint = DuskTheme.success,
                modifier = Modifier.size(22.dp),
            )
            Spacer(Modifier.width(10.dp))
            Text(
                S(R.string.configs_connected_and_ready, faCount(results.size)),
                color = DuskTheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }

        if (alreadyOwnedCount > 0) {
            Spacer(Modifier.height(DuskTheme.space2 + 2.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = DuskTheme.space4)
                    .clip(DuskTheme.chartShape)
                    .background(DuskTheme.accent.copy(alpha = 0.12f))
                    .padding(14.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = DuskTheme.accent,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    S(R.string.you_already_have_of_these_so, faCount(alreadyOwnedCount)) +
                        S(R.string.new_configs_will_be_moved_across, faCount(newCount)),
                    color = DuskTheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 19.sp,
                )
            }
        }

        Spacer(Modifier.height(DuskTheme.space3))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = DuskTheme.space4)
                .frostedGlass(DuskTheme.tileShape),
            contentPadding = PaddingValues(vertical = 6.dp),
        ) {
            items(results) { node ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = DuskTheme.space3, vertical = DuskTheme.space2 + 1.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        node.name,
                        color = DuskTheme.onSurface,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f),
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(node.type.uppercase(), color = DuskTheme.muted, style = MaterialTheme.typography.labelSmall)
                }
            }
        }

        Spacer(Modifier.height(DuskTheme.space3))
        SettingsGroup {
            SettingsActionRow(
                label = if (alreadyOwnedCount > 0) {
                    S(R.string.move_new_configs_to_the_connection_page, faCount(newCount))
                } else {
                    S(R.string.move_to_the_connection_page)
                },
                icon = Icons.Default.Download,
                tint = DuskTheme.success,
                enabled = newCount > 0,
                onClick = onTransfer,
            )
        }
        if (newCount == 0) {
            SettingsFooter(S(R.string.you_already_have_all_of_these_configs))
        }

        Spacer(Modifier.height(DuskTheme.space4))
    }
}

/**
 * The handoff.
 *
 * The one screen this flow did not have, and the reason it could not simply be lifted out of
 * Add-Node: the configs are now in a different app from the one that uses them, and an import that
 * ends by returning the user to a grid of icons has taught them nothing about where the configs
 * went or what to do next.
 *
 * So it says all three things: what happened, where it landed, and the single next action -- with
 * the config already chosen on the other side, so "go to V2Ray" and "connect" are two taps rather
 * than a hunt through a folder the user has never opened.
 */
@Composable
private fun DoneStep(
    imported: Int,
    alreadyOwnedCount: Int,
    firstNode: VpnNode?,
    onGoToNodes: (VpnNode?) -> Unit,
    onMore: () -> Unit,
    onClose: () -> Unit,
) {
    IosScreen(title = S(R.string.ready_r2), onBack = onClose, backLabel = S(R.string.home_r2)) {
        Spacer(Modifier.height(DuskTheme.space5))

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = DuskTheme.space6),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                Icons.Default.CheckCircle,
                contentDescription = null,
                tint = DuskTheme.success,
                modifier = Modifier.size(52.dp),
            )
            Spacer(Modifier.height(DuskTheme.space3))
            Text(
                faCount(imported) + S(R.string.configs_added),
                color = DuskTheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(DuskTheme.space2))
            Text(
                S(R.string.all_of_them_passed_a_real_test) +
                    S(R.string.the) + com.mlmvpn.scanner.engines.freeconfig.FreeConfigEngine.GROUP_NAME +
                    S(R.string.folder_r2),
                color = DuskTheme.muted,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(Modifier.height(DuskTheme.space5))

        SettingsSectionHeader(S(R.string.next_step))
        SettingsGroup {
            SettingsActionRow(
                label = S(R.string.go_to_v2ray_and_connect),
                icon = Icons.Default.Bolt,
                tint = DuskTheme.primary,
            ) { onGoToNodes(firstNode) }
        }
        SettingsFooter(
            if (firstNode != null) {
                S(R.string.the_folder_opens_with) + firstNode.name + S(R.string.already_selected_just_tap_the_connect) +
                    S(R.string.button_if_it_is_slow_run_delay)
            } else {
                S(R.string.the_free_configs_folder_opens_pick_one)
            }
        )

        SettingsSectionHeader(S(R.string.or))
        SettingsGroup {
            SettingsActionRow(
                label = S(R.string.fetch_more_configs),
                icon = Icons.Default.TravelExplore,
                onClick = onMore,
            )
        }
        SettingsFooter(
            if (alreadyOwnedCount > 0) {
                faCount(alreadyOwnedCount) + S(R.string.configs_from_this_round_you_already_had) +
                    S(R.string.again_the_public_sources_refresh_every_15)
            } else {
                S(R.string.the_public_sources_refresh_every_15_minutes) +
                    S(R.string.back_here_and_take_more)
            }
        )

        Spacer(Modifier.height(40.dp))
    }
}
