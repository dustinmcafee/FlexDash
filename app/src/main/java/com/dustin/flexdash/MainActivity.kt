package com.dustin.flexdash

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.dustin.flexdash.databinding.ActivityMainBinding

data class Service(val name: String, val port: Int, val emoji: String, val description: String)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val services = listOf(
        Service("Jellyfin",    8096, "🎬", "Stream movies & TV"),
        Service("Radarr",      7878, "🎥", "Find & download movies"),
        Service("Sonarr",      8989, "📺", "Find & download TV shows"),
        Service("qBittorrent", 8080, "⬇️", "Download client"),
        Service("Prowlarr",    9696, "🔍", "Indexer manager"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupServiceButtons()
        updateVpnStatus()
    }

    override fun onResume() {
        super.onResume()
        updateVpnStatus()
    }

    private fun isOnVpn(): Boolean = VpnDetector.isOnVpn()

    private fun getHost(): String = VpnDetector.getHost()

    private fun updateVpnStatus() {
        val onVpn = isOnVpn()
        val host = getHost()
        binding.vpnStatus.text = if (onVpn)
            "🔒 VPN connected — using $host"
        else
            "🏠 Local network — using $host"
        binding.vpnStatus.setTextColor(
            ContextCompat.getColor(this, if (onVpn) R.color.vpn_on else R.color.vpn_off)
        )
    }

    private fun setupServiceButtons() {
        val container = binding.servicesContainer
        container.removeAllViews()

        services.forEachIndexed { index, service ->
            val card = layoutInflater.inflate(R.layout.service_card, container, false)
            card.findViewById<TextView>(R.id.serviceName).text = "${service.emoji} ${service.name}"
            card.findViewById<TextView>(R.id.serviceDesc).text = service.description
            card.setOnClickListener {
                updateVpnStatus()
                openService(service)
            }
            container.addView(card)
            // Give the D-pad a starting point on Android TV
            if (index == 0) card.requestFocus()
        }
    }

    private fun openService(service: Service) {
        val url = "http://${getHost()}:${service.port}"
        val colorScheme = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ContextCompat.getColor(this, R.color.toolbar_color))
            .build()
        CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(colorScheme)
            .setShowTitle(true)
            .setUrlBarHidingEnabled(true)
            .build()
            .launchUrl(this, Uri.parse(url))
    }
}
