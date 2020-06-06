package com.yash.tongaonkar.homemaderecipe.di

import com.yash.tongaonkar.homemaderecipe.AppLauncherActivity
import com.yash.tongaonkar.homemaderecipe.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector()
    internal abstract fun launcherActivity(): AppLauncherActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [SettingsModule::class,
            MainActivityModule::class,
            HomeModule::class]
    )
    internal abstract fun mainActivity(): MainActivity

}
