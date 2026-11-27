package com.mlmvpn.scanner.engines.edg

import com.mlmvpn.scanner.ui.home.frostedGlass
import com.mlmvpn.scanner.ui.theme.*
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mlmvpn.scanner.models.CloudAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import com.mlmvpn.scanner.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdgSettingsScreen(
    account: CloudAccount,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val notify = rememberDuskSnackbarAction()
    val coroutineScope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(true) }
    var isSaving by remember { mutableStateOf(false) }
    var loadError by remember { mutableStateOf<String?>(null) }

    // State for config.json properties
    var proxyIp by remember { mutableStateOf("") }

    // Original JSON to merge changes
    var originalConfigJson by remember { mutableStateOf(JSONObject()) }

    LaunchedEffect(Unit) {
        if (account.edgKvNamespaceId.isNullOrEmpty()) {
            loadError = context.getString(com.mlmvpn.scanner.R.string.edg_old_panel_error)
            isLoading = false
            return@LaunchedEffect
        }

        withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient.Builder().dns(com.mlmvpn.scanner.engines.cloud.WorkerRoute.dns()).protocols(com.mlmvpn.scanner.engines.cloud.WorkerRoute.HTTP1).socketFactory(com.mlmvpn.scanner.engines.cloud.WorkerRoute.cfSocketFactory()).proxySelector(com.mlmvpn.scanner.engines.cloud.CfTunnel.selector())
                    .build()
                val isCfat = com.mlmvpn.scanner.data.CloudAuth.useBearer(account)
                val authHeaders = Headers.Builder().apply {
                    if (isCfat) add("Authorization", "Bearer ${account.token}")
                    else {
                        add("X-Auth-Email", account.email)
                        add("X-Auth-Key", account.token)
                    }
                    add("Content-Type", "application/json")
                }.build()

                // Fetch config.json
                val getConfigReq = Request.Builder()
                    .url("https://api.cloudflare.com/client/v4/accounts/${account.accountId}/storage/kv/namespaces/${account.edgKvNamespaceId}/values/config.json")
                    .headers(authHeaders)
                    .get()
                    .build()

                val res = client.newCall(getConfigReq).execute()
                if (res.isSuccessful) {
                    val body = res.body?.string() ?: ""
                    try {
                        val json = JSONObject(body)
                        originalConfigJson = json
                        val fanDai = json.optJSONObject("反代")
                        if (fanDai != null) {
                            proxyIp = fanDai.optString("PROXYIP", "")
                            if (proxyIp == "auto") proxyIp = ""
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                withContext(Dispatchers.Main) {
                    isLoading = false
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    loadError = context.getString(com.mlmvpn.scanner.R.string.edg_connection_error, e.message)
                    isLoading = false
                }
            }
        }
    }

    Surface(
        // Transparent over the wallpaper; the Cloud tab that hosts this no longer pads it.
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(
                top = com.mlmvpn.scanner.ui.LocalContentTopInset.current,
                bottom = com.mlmvpn.scanner.ui.LocalSystemBottomPadding.current,
            )) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(DuskTheme.space4),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(DuskTheme.navIconSize)
                            .background(DuskTheme.primary.copy(alpha = 0.15f), ControlShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Tune,
                            contentDescription = null,
                            tint = DuskTheme.muted,
                            modifier = Modifier.size(DuskTheme.iconSize)
                        )
                    }
                    Spacer(modifier = Modifier.width(DuskTheme.space3))
                    Column {
                        Text(
                            androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.edg_settings_title),
                            color = DuskTheme.onSurface,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            account.name,
                            color = DuskTheme.muted,
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(max = 200.dp)
                        )
                    }
                }
                IconButton(onClick = onDismiss, enabled = !isSaving) {
                    Icon(Icons.Default.Close, contentDescription = androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.common_close), tint = DuskTheme.muted)
                }
            }

            Divider(color = DuskTheme.outline)

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = DuskTheme.primary)
                }
            } else if (loadError != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(DuskTheme.space6)) {
                        Icon(Icons.Default.ErrorOutline, contentDescription = null, tint = DuskTheme.error, modifier = Modifier.size(DuskTheme.navIconSize))
                        Spacer(modifier = Modifier.height(DuskTheme.space4))
                        Text(loadError!!, color = DuskTheme.muted, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            } else {
                Column(
                    modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(start = DuskTheme.space4, end = DuskTheme.space4, top = DuskTheme.space4, bottom = DuskTheme.space5),
                    verticalArrangement = Arrangement.spacedBy(DuskTheme.space5)
                ) {
                    // Proxy IP
                    Column(modifier = Modifier.fillMaxWidth().frostedGlass(ControlShape).border(1.dp, DuskTheme.outline, ControlShape).padding(DuskTheme.space4)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Dns, contentDescription = null, tint = DuskTheme.muted, modifier = Modifier.size(DuskTheme.iconSize))
                            Spacer(modifier = Modifier.width(DuskTheme.space2))
                            Text(androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.edg_proxy_ip_title), color = DuskTheme.onSurface, style = MaterialTheme.typography.titleMedium)
                        }
                        Spacer(modifier = Modifier.height(DuskTheme.space2))
                        Text(androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.edg_proxy_ip_desc), color = DuskTheme.muted, style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(DuskTheme.space3))
                        OutlinedTextField(
                            value = proxyIp,
                            onValueChange = { proxyIp = it },
                            placeholder = { Text(androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.edg_proxy_ip_hint), color = DuskTheme.muted) },
                            colors = iosFieldColors(),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        ,
        shape = ControlShape,)
                    }
                }

                // Save Button
                Box(modifier = Modifier.fillMaxWidth().frostedGlass(androidx.compose.ui.graphics.RectangleShape).padding(DuskTheme.space4)) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                isSaving = true
                                withContext(Dispatchers.IO) {
                                    try {
                                        val client = OkHttpClient.Builder().dns(com.mlmvpn.scanner.engines.cloud.WorkerRoute.dns()).protocols(com.mlmvpn.scanner.engines.cloud.WorkerRoute.HTTP1).socketFactory(com.mlmvpn.scanner.engines.cloud.WorkerRoute.cfSocketFactory()).proxySelector(com.mlmvpn.scanner.engines.cloud.CfTunnel.selector())
                                            .build()
                                        val isCfat = com.mlmvpn.scanner.data.CloudAuth.useBearer(account)
                                        val authHeaders = Headers.Builder().apply {
                                            if (isCfat) add("Authorization", "Bearer ${account.token}")
                                            else {
                                                add("X-Auth-Email", account.email)
                                                add("X-Auth-Key", account.token)
                                            }
                                            add("Content-Type", "application/json")
                                        }.build()

                                        // Update config.json
                                        val newConfig = JSONObject(originalConfigJson.toString())
                                        val fanDai = newConfig.optJSONObject("反代") ?: JSONObject()
                                        fanDai.put("PROXYIP", if (proxyIp.isNotBlank()) proxyIp else "auto")
                                        newConfig.put("反代", fanDai)

                                        val req1 = Request.Builder()
                                            .url("https://api.cloudflare.com/client/v4/accounts/${account.accountId}/storage/kv/namespaces/${account.edgKvNamespaceId}/values/config.json")
                                            .headers(authHeaders)
                                            .put(newConfig.toString().toRequestBody("application/json".toMediaTypeOrNull()))
                                            .build()
                                        client.newCall(req1).execute().use { response ->
                                            if (!response.isSuccessful) {
                                                throw IOException("HTTP ${response.code}")
                                            }
                                        }

                                        withContext(Dispatchers.Main) {
                                            isSaving = false
                                            notify(context.getString(com.mlmvpn.scanner.R.string.edg_settings_saved))
                                            onDismiss()
                                        }
                                    } catch (e: Exception) {
                                        withContext(Dispatchers.Main) {
                                            isSaving = false
                                            notify(context.getString(com.mlmvpn.scanner.R.string.edg_save_error, e.message))
                                        }
                                    }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        enabled = !isSaving,
                        colors = iosButtonColors(DuskTheme.primary),
                        border = iosButtonBorder(DuskTheme.primary, enabled = !isSaving)) {
                        if (isSaving) {
                            CircularProgressIndicator( modifier = Modifier.size(DuskTheme.space5))
                        } else {
                            Text(androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.edg_save_settings), color = DuskTheme.onSurface, style = MaterialTheme.typography.titleMedium)
                        }
                    }
                    Spacer(modifier = Modifier.height(DuskTheme.space5))
                }
            }
        }
    }
}
