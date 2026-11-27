package com.mlmvpn.scanner.ui

import com.mlmvpn.scanner.ui.home.frostedGlass
import com.mlmvpn.scanner.ui.theme.*
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import java.util.Locale
import com.mlmvpn.scanner.utils.S
import com.mlmvpn.scanner.R

data class CountryInfo(
    val code: String,
    val name: String,
    val flag: String,
    val fileLength: Int
)

enum class IpStatus {
    UNKNOWN, TESTING, VALID, INVALID
}

data class ProxyIpModel(
    val ip: String,
    val port: String,
    var status: IpStatus = IpStatus.UNKNOWN
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixedIpScreen(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = LocalDuskSnackbarHostState.current
    
    val primaryColor = DuskTheme.primary
    val textColor = DuskTheme.onSurface
    val mutedColor = DuskTheme.muted
    val greenOk = DuskTheme.success
    val redError = DuskTheme.error

    var countries by remember { mutableStateOf<List<CountryInfo>>(emptyList()) }
    var selectedCountry by remember { mutableStateOf<CountryInfo?>(null) }
    var proxyIps by remember { mutableStateOf<List<ProxyIpModel>>(emptyList()) }
    var isTesting by remember { mutableStateOf(false) }
    var testingJob by remember { mutableStateOf<kotlinx.coroutines.Job?>(null) }

    // Load countries on init
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                val ipFiles = context.assets.list("ip") ?: emptyArray()
                val list = mutableListOf<CountryInfo>()
                for (fileName in ipFiles) {
                    if (fileName.endsWith(".txt")) {
                        val code = fileName.removeSuffix(".txt")
                        // Estimate lines by reading bytes (rough) or just keep file name
                        list.add(
                            CountryInfo(
                                code = code,
                                name = getLocalizedCountryName(code),
                                flag = getFlagEmoji(code),
                                fileLength = 0 // Optional: Count lines if needed
                            )
                        )
                    }
                }
                countries = list.sortedBy { it.name }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // The app's own navigation bar. Back here is two-level: inside a country it returns to the
    // country list, and only from the list does it leave the screen -- which is what the pushed
    // pages elsewhere in the app do too.
    com.mlmvpn.scanner.ui.settings.IosScreen(
        title = if (selectedCountry == null) {
            androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.fixed_ip_select_country)
        } else {
            "${selectedCountry!!.flag} ${selectedCountry!!.name}"
        },
        onBack = {
            if (selectedCountry != null) {
                testingJob?.cancel()
                testingJob = null
                selectedCountry = null
                proxyIps = emptyList()
                isTesting = false
            } else {
                testingJob?.cancel()
                onDismiss()
            }
        },
        backLabel = if (selectedCountry == null) S(R.string.home) else S(R.string.countries),
        scrollable = false,
    ) {
        run { val topPad = DuskTheme.space2
        Box(modifier = Modifier.fillMaxSize()) {
            if (selectedCountry == null) {
                // Country List
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = DuskTheme.space4,
                        end = DuskTheme.space4,
                        top = topPad,
                        bottom = com.mlmvpn.scanner.ui.LocalSystemBottomPadding.current + DuskTheme.space5,
                    ),
                    verticalArrangement = Arrangement.spacedBy(DuskTheme.space2)
                ) {
                    items(countries) { country ->
                        DuskCard(
                            modifier = Modifier.fillMaxWidth().clickable {
                                selectedCountry = country
                                loadIps(context, country.code) { ips -> proxyIps = ips }
                            },
                            shape = DuskTheme.chartShape,
                            contentPadding = PaddingValues(DuskTheme.space4),
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(country.flag, style = MaterialTheme.typography.titleLarge)
                                Spacer(modifier = Modifier.width(DuskTheme.space4))
                                Text(country.name, color = textColor, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = mutedColor)
                            }
                        }
                    }
                }

            } else {
                // IP list runs the full height of the Box; the test button floats over its
                // bottom edge rather than being stacked above it in a Column. As a Column child it
                // sat at the very top of the screen -- underneath the floating header, which is
                // laid over the same space -- so it was both in the wrong place and half hidden.
                LazyColumn(
                        contentPadding = PaddingValues(
                            start = DuskTheme.space4,
                            end = DuskTheme.space4,
                            top = topPad,
                            // The last row has to clear the floating button, not slide under it.
                            bottom = com.mlmvpn.scanner.ui.LocalSystemBottomPadding.current + DuskTheme.floatingActionClearance,
                        ),
                        verticalArrangement = Arrangement.spacedBy(DuskTheme.space2)
                    ) {
                        items(proxyIps) { model ->
                            val statusColor = when (model.status) {
                                IpStatus.VALID -> greenOk
                                IpStatus.INVALID -> redError
                                IpStatus.TESTING -> primaryColor
                                IpStatus.UNKNOWN -> mutedColor
                            }
                            val statusText = when (model.status) {
                                IpStatus.VALID -> androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.fixed_ip_status_valid)
                                IpStatus.INVALID -> androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.fixed_ip_status_invalid)
                                IpStatus.TESTING -> androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.fixed_ip_status_testing)
                                IpStatus.UNKNOWN -> androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.fixed_ip_status_unknown)
                            }

                            DuskCard(
                                modifier = Modifier.fillMaxWidth(),
                                shape = DuskTheme.chartShape,
                                contentPadding = PaddingValues(DuskTheme.space3),
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("${model.ip}:${model.port}", color = textColor, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(DuskTheme.space1))
                                    Text(statusText, color = statusColor, style = MaterialTheme.typography.bodySmall)
                                }

                                if (model.status == IpStatus.VALID) {
                                    IconButton(
                                        onClick = {
                                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                            val clip = ClipData.newPlainText("Proxy IP", model.ip)
                                            clipboard.setPrimaryClip(clip)
                                            scope.launch { snackbarHostState.showSnackbar(context.getString(com.mlmvpn.scanner.R.string.fixed_ip_copied)) }
                                        },
                                        modifier = Modifier.frostedGlass(DuskTheme.glyphShape)
                                    ) {
                                        Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = greenOk)
                                    }
                                }
                                }
                            }
                        }
                    }

                Button(
                    onClick = {
                        if (!isTesting) {
                            isTesting = true
                            testingJob?.cancel()
                            testingJob = scope.launch {
                                try {
                                    startTesting(proxyIps, onUpdate = { updatedList ->
                                        proxyIps = updatedList.toList()
                                    })
                                } finally {
                                    isTesting = false
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(
                            start = DuskTheme.space4,
                            end = DuskTheme.space4,
                            bottom = com.mlmvpn.scanner.ui.LocalSystemBottomPadding.current + DuskTheme.space4,
                        )
                        .height(DuskTheme.actionHeight),
                    enabled = !isTesting,
                    // Tinted glass with an accent rim and accent text -- the same primary action
                    // treatment as Save and Connect. It was a solid accent slab with BLACK text.
                    colors = iosButtonColors(primaryColor),
                    border = iosButtonBorder(primaryColor, enabled = !isTesting),
                    shape = DuskTheme.controlShape
                ) {
                    Icon(if (isTesting) Icons.Default.HourglassEmpty else Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(DuskTheme.space2))
                    Text(if (isTesting) androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.fixed_ip_testing) else androidx.compose.ui.res.stringResource(com.mlmvpn.scanner.R.string.fixed_ip_start_test), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
    }
}

private fun loadIps(context: Context, code: String, onLoaded: (List<ProxyIpModel>) -> Unit) {
    try {
        val inputStream = context.assets.open("ip/$code.txt")
        val lines = inputStream.bufferedReader().readLines()
        val ips = lines.mapNotNull { line ->
            val parts = line.trim().split(" ", ":")
            if (parts.size >= 2) {
                ProxyIpModel(ip = parts[0], port = parts[1])
            } else null
        }
        onLoaded(ips)
    } catch (e: Exception) {
        e.printStackTrace()
        onLoaded(emptyList())
    }
}

private suspend fun startTesting(ips: List<ProxyIpModel>, onUpdate: suspend (List<ProxyIpModel>) -> Unit) = kotlinx.coroutines.coroutineScope {
    val baseClient = OkHttpClient.Builder()
        .build()
        
    val semaphore = Semaphore(5) // Reduced to 5 concurrent tests to prevent network congestion
    val currentList = ips.toMutableList()

    val jobs = currentList.mapIndexed { index, model ->
        launch(Dispatchers.IO) {
            semaphore.withPermit {
                // Check if cancelled before proceeding
                kotlinx.coroutines.yield()
                
                // Update state to testing
                currentList[index] = model.copy(status = IpStatus.TESTING)
                withContext(Dispatchers.Main) { onUpdate(currentList) }

                val isValid = checkProxyIp(baseClient, model.ip, model.port)
                
                // Check if cancelled before updating final result
                kotlinx.coroutines.yield()
                
                // Update state to result
                currentList[index] = model.copy(status = if (isValid) IpStatus.VALID else IpStatus.INVALID)
                withContext(Dispatchers.Main) { onUpdate(currentList) }
            }
        }
    }
    jobs.forEach { it.join() }
}

private suspend fun checkProxyIp(baseClient: OkHttpClient, ip: String, port: String): Boolean = withContext(Dispatchers.IO) {
    var retries = 2
    while (retries >= 0) {
        // Cloudflare requires SNI for HTTPS. We use speed.cloudflare.com as Host and SNI,
        // and a custom Dns resolver to route the request to the specific Proxy IP.
        try {
            val isHttps = port == "443" || port == "8443" || port == "2053"
            val protocol = if (isHttps) "https" else "http"
            val host = "speed.cloudflare.com"
            val url = "$protocol://$host:$port/"
            
            // Clone the client with custom DNS and longer timeout
            val client = baseClient.newBuilder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .dns(object : Dns {
                    override fun lookup(hostname: String): List<InetAddress> {
                        if (hostname == host) {
                            return listOf(InetAddress.getByName(ip))
                        }
                        return Dns.SYSTEM.lookup(hostname)
                    }
                })
                .build()
                
            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0")
                .build()
                
            val response = client.newCall(request).execute()
            response.use { resp ->
                val serverHeader = resp.header("Server")?.lowercase(Locale.US) ?: ""
                // If it's Cloudflare, it's a valid proxy IP
                if (serverHeader.contains("cloudflare") || serverHeader.contains("envoy")) {
                    return@withContext true
                }
                // Fallback: Check if response code is 400 Bad Request or 403 Forbidden
                if (resp.code == 400 || resp.code == 403) {
                    return@withContext true
                }
                if (resp.isSuccessful) {
                    return@withContext true
                }
            }
        } catch (e: Exception) {
            // TCP timeout or other error
        }
        retries--
        if (retries >= 0) kotlinx.coroutines.delay(1000)
    }
    return@withContext false
}

private fun getLocalizedCountryName(countryCode: String): String {
    return Locale("", countryCode.uppercase(Locale.US)).getDisplayCountry(com.mlmvpn.scanner.utils.AppLocaleManager.getResolvedLocale())
}

private fun getFlagEmoji(countryCode: String): String {
    if (countryCode.length != 2) return ""
    val code = countryCode.uppercase(Locale.US)
    val flagOffset = 0x1F1E6
    val asciiOffset = 0x41
    val firstChar = Character.codePointAt(code, 0) - asciiOffset + flagOffset
    val secondChar = Character.codePointAt(code, 1) - asciiOffset + flagOffset
    return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
}

