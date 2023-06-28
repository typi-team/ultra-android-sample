package com.typi.ultra.sample.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

inline fun <reified T> Context.createIntent(): Intent = Intent(this, T::class.java)

fun Intent.startIntent(context: Context, bundle: Bundle? = null) {
    context.startActivity(this, bundle)
}

fun Intent.startAndClose(activity: Activity) {
    startIntent(activity)
    activity.finish()
}