package com.ultra.sample

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.typi.ultra.integration.UltraApi
import com.typi.ultra.integration.UltraComponentHolder
import com.typi.ultra.integration.UltraDependencies
import com.typi.ultra.integration.UltraInitializer
import com.typi.ultra.integration.auth.UltraAuthDelegate
import com.typi.ultra.integration.auth.UltraAuthProvider
import com.typi.ultra.integration.base.BaseDependencyHolder
import com.typi.ultra.integration.base.BaseFeatureDependencies
import com.typi.ultra.integration.base.DependencyHolder
import com.typi.ultra.integration.cache.UltraCacheProvider
import com.typi.ultra.integration.localise.UltraLocaliseDelegate
import com.typi.ultra.integration.logs.UltraFileProvider
import com.typi.ultra.integration.message.UltraMessageDelegate
import com.typi.ultra.integration.message.UltraMessageProvider
import com.typi.ultra.integration.navigation.UltraNavigator
import com.typi.ultra.integration.push.UltraPushProvider
import com.typi.ultra.integration.recorder.UltraErrorRecorder
import com.typi.ultra.integration.settings.UltraSettingsDelegate
import com.typi.ultra.integration.support.UltraSupportDelegate
import com.typi.ultra.integration.theme.UltraThemeDelegate
import com.typi.ultra.integration.toggle.UltraFeatureToggle
import com.typi.ultra.integration.user.UltraUserDelegate
import com.typi.ultra.integration.user.UltraUserProvider
import com.ultra.sample.core.di.KoinManager
import com.ultra.sample.push.PushManager
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperApplicationDelegate
import org.koin.android.ext.android.inject
import timber.log.Timber

class App : Application() {

    private val localeAppDelegate = LocaleHelperApplicationDelegate()

    private val pushManager: PushManager by inject()

    private val settingsDelegate: UltraSettingsDelegate by inject()
    private val authDelegate: UltraAuthDelegate by inject()
    private val themeDelegate: UltraThemeDelegate by inject()
    private val featureToggle: UltraFeatureToggle by inject()
    private val messageDelegate: UltraMessageDelegate by inject()
    private val userDelegate: UltraUserDelegate by inject()
    private val supportDelegate: UltraSupportDelegate by inject()
    private val errorRecorder: UltraErrorRecorder by inject()
    private val localiseDelegate: UltraLocaliseDelegate by inject()

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
                    override val messageDelegate: UltraMessageDelegate
                        get() = this@App.messageDelegate
                    override val userDelegate: UltraUserDelegate
                        get() = this@App.userDelegate
                    override val supportDelegate: UltraSupportDelegate
                        get() = this@App.supportDelegate
                    override val errorRecorder: UltraErrorRecorder
                        get() = this@App.errorRecorder
                    override val localiseDelegate: UltraLocaliseDelegate
                        get() = this@App.localiseDelegate
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
                    get() = ultraApi.navigator
                override val cacheProvider: UltraCacheProvider
                    get() = ultraApi.cacheProvider
                override val messageProvider: UltraMessageProvider
                    get() = ultraApi.messageProvider
                override val initializer: UltraInitializer
                    get() = ultraApi.initializer
                override val fileProvider: UltraFileProvider
                    get() = ultraApi.fileProvider
                override val userProvider: UltraUserProvider
                    get() = ultraApi.userProvider
            }
        )

        ultraApi.initializer.init()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        themeDelegate.setDarkMode(false)
        pushManager.createNotificationChannel()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeAppDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeAppDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context =
        LocaleHelper.onAttach(super.getApplicationContext())
}