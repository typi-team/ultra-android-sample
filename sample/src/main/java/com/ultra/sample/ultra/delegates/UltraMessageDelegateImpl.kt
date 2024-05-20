package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.message.UltraMessageDelegate

class UltraMessageDelegateImpl : UltraMessageDelegate {

    override fun getMessageProperties(): Map<String, String> {
        return emptyMap()
    }
}