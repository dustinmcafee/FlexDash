package com.dustin.flexdash

import java.net.NetworkInterface

object VpnDetector {
    const val LAN_HOST = "192.168.1.175"
    const val VPN_HOST = "10.8.0.6"
    private const val VPN_PREFIX = "10.8.0."

    fun isOnVpn(): Boolean {
        return try {
            NetworkInterface.getNetworkInterfaces()
                ?.toList()
                ?.any { iface ->
                    iface.isUp && iface.inetAddresses
                        ?.toList()
                        ?.any { addr -> addr.hostAddress?.startsWith(VPN_PREFIX) == true }
                        ?: false
                } ?: false
        } catch (e: Exception) {
            false
        }
    }

    fun getHost(): String = if (isOnVpn()) VPN_HOST else LAN_HOST

    fun buildServiceUrl(port: Int): String = "http://${getHost()}:$port"
}
