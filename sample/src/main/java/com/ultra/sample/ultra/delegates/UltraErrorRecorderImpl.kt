package com.ultra.sample.ultra.delegates

import com.typi.ultra.integration.recorder.UltraErrorRecorder

class UltraErrorRecorderImpl : UltraErrorRecorder {

    override fun recordError(throwable: Throwable) = Unit
}