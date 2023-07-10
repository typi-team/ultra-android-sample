package com.typi.ultra.sample.ultra

import com.typi.ultra.network.UltraNetworkDelegate

class UltraNetworkDelegateImpl : UltraNetworkDelegate {

    override fun getUrl(): String {
        return "some-url"
    }
}