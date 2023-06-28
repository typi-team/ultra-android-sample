package com.typi.ultra.sample.ultra

import com.typi.ultra.push.api.UltraPushDelegate
import com.typi.ultra.sample.R

class UltraPushDelegateImpl : UltraPushDelegate {

    override val notificationSmallIcon: Int = R.drawable.ic_push_notification

    override val notificationColor: Int = R.color.ultra_ic_launcher_background
}