package com.ultra.sample

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.typi.ultra.UltraApi
import com.typi.ultra.UltraComponentHolder
import com.typi.ultra.UltraDependencies
import com.typi.ultra.UltraFeatureToggle
import com.typi.ultra.auth.api.UltraAuthDelegate
import com.typi.ultra.auth.api.UltraAuthProvider
import com.typi.ultra.cache.api.UltraCacheProvider
import com.typi.ultra.core.integration.BaseDependencyHolder
import com.typi.ultra.core.integration.BaseFeatureDependencies
import com.typi.ultra.core.integration.DependencyHolder
import com.typi.ultra.navigation.UltraNavigator
import com.typi.ultra.push.api.UltraPushProvider
import com.typi.ultra.settings.UltraSettingsDelegate
import com.typi.ultra.uikit.UltraThemeDelegate
import com.typi.ultra.user.UltraUserDelegate
import com.ultra.sample.core.di.KoinManager
import com.ultra.sample.push.PushManager
import org.koin.android.ext.android.inject
import timber.log.Timber

class App : Application() {

    private val pushManager: PushManager by inject()

    private val settingsDelegate: UltraSettingsDelegate by inject()
    private val authDelegate: UltraAuthDelegate by inject()
    private val themeDelegate: UltraThemeDelegate by inject()
    private val featureToggle: UltraFeatureToggle by inject()
    private val userDelegate: UltraUserDelegate by inject()

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
                    override val settingsDelegate: UltraSettingsDelegate
                        get() = this@App.settingsDelegate
                    override val authDelegate: UltraAuthDelegate
                        get() = this@App.authDelegate
                    override val themeDelegate: UltraThemeDelegate
                        get() = this@App.themeDelegate
                    override val featureToggle: UltraFeatureToggle
                        get() = this@App.featureToggle
                    override val userDelegate: UltraUserDelegate
                        get() = this@App.userDelegate
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
                override val screenStarter: UltraNavigator
                    get() = ultraApi.screenStarter
                override val cacheProvider: UltraCacheProvider
                    get() = ultraApi.cacheProvider
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