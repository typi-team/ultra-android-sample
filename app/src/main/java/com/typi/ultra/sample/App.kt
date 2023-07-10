package com.typi.ultra.sample

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.typi.ultra.BuildConfig
import com.typi.ultra.UltraApi
import com.typi.ultra.UltraComponentHolder
import com.typi.ultra.UltraDependencies
import com.typi.ultra.UltraScreenDelegate
import com.typi.ultra.auth.api.UltraAuthDelegate
import com.typi.ultra.auth.api.UltraAuthProvider
import com.typi.ultra.core.integration.BaseDependencyHolder
import com.typi.ultra.core.integration.BaseFeatureDependencies
import com.typi.ultra.core.integration.DependencyHolder
import com.typi.ultra.network.UltraNetworkDelegate
import com.typi.ultra.push.api.UltraPushDelegate
import com.typi.ultra.push.api.UltraPushProvider
import com.typi.ultra.sample.core.di.KoinManager
import com.typi.ultra.sample.push.PushManager
import com.typi.ultra.uikit.UltraThemeDelegate
import org.koin.android.ext.android.inject
import timber.log.Timber

class App : Application() {

    private val pushManager: PushManager by inject()

    private val authDelegate: UltraAuthDelegate by inject()
    private val pushDelegate: UltraPushDelegate by inject()
    private val themeDelegate: UltraThemeDelegate by inject()
    private val screenDelegate: UltraScreenDelegate by inject()
    private val networkDelegate: UltraNetworkDelegate by inject()

    private val ultraApi: UltraApi by lazy { UltraComponentHolder.get() }

    override fun onCreate() {
        super.onCreate()
        UltraComponentHolder.dependencyProvider = {
            class UltraComponentDependencyHolder(
                override val block: (
                    BaseDependencyHolder<UltraDependencies>,
                ) -> UltraDependencies,
            ) : DependencyHolder<UltraDependencies>()

            UltraComponentDependencyHolder { dependencies ->
                object : UltraDependencies {
                    override val context: Context = this@App
                    override val authDelegate: UltraAuthDelegate
                        get() = this@App.authDelegate
                    override val pushDelegate: UltraPushDelegate
                        get() = this@App.pushDelegate
                    override val themeDelegate: UltraThemeDelegate
                        get() = this@App.themeDelegate
                    override val screenDelegate: UltraScreenDelegate
                        get() = this@App.screenDelegate
                    override val networkDelegate: UltraNetworkDelegate
                        get() = this@App.networkDelegate
                    override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies> = dependencies
                }
            }.dependencies
        }

        KoinManager.init(
            context = this,
            appDependencies = object : AppDependencies {
                override val authProvider: UltraAuthProvider
                    get() = ultraApi.authProvider
                override val pushProvider: UltraPushProvider
                    get() = ultraApi.pushProvider
            }
        )

        ProcessLifecycleOwner.get().lifecycle.addObserver(ultraApi.lifecycleObserver)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        themeDelegate.setDarkMode(false)
        pushManager.createNotificationChannel()
    }

    override fun onTerminate() {
        ProcessLifecycleOwner.get().lifecycle.removeObserver(ultraApi.lifecycleObserver)
        super.onTerminate()
    }
}