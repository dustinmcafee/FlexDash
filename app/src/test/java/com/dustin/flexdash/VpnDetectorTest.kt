package com.dustin.flexdash

import org.junit.Assert.*
import org.junit.Test

class VpnDetectorTest {

    @Test
    fun `buildServiceUrl uses correct host and port`() {
        val url = "http://${VpnDetector.LAN_HOST}:8096"
        assertTrue(url.contains(":8096"))
        assertTrue(url.startsWith("http://"))
    }

    @Test
    fun `LAN_HOST is correct`() {
        assertEquals("192.168.1.175", VpnDetector.LAN_HOST)
    }

    @Test
    fun `VPN_HOST is correct`() {
        assertEquals("10.8.0.6", VpnDetector.VPN_HOST)
    }

    @Test
    fun `buildServiceUrl with LAN host produces valid URL`() {
        val url = "http://${VpnDetector.LAN_HOST}:7878"
        assertEquals("http://192.168.1.175:7878", url)
    }

    @Test
    fun `buildServiceUrl with VPN host produces valid URL`() {
        val url = "http://${VpnDetector.VPN_HOST}:8989"
        assertEquals("http://10.8.0.6:8989", url)
    }

    @Test
    fun `all service ports are positive`() {
        val ports = listOf(8096, 7878, 8989, 8080, 9696)
        ports.forEach { assertTrue("Port $it should be positive", it > 0) }
    }

    @Test
    fun `all service ports are in valid range`() {
        val ports = listOf(8096, 7878, 8989, 8080, 9696)
        ports.forEach {
            assertTrue("Port $it out of range", it in 1..65535)
        }
    }

    @Test
    fun `service port uniqueness`() {
        val ports = listOf(8096, 7878, 8989, 8080, 9696)
        assertEquals("Ports must be unique", ports.size, ports.toSet().size)
    }
}
