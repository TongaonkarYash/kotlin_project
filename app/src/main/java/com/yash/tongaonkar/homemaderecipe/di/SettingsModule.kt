package com.yash.tongaonkar.homemaderecipe.di

import androidx.lifecycle.ViewModel
import com.yash.tongaonkar.homemaderecipe.settings.SettingsFragment
import com.yash.tongaonkar.homemaderecipe.settings.SettingsViewModel
import com.yash.tongaonkar.homemaderecipe.settings.ThemeSettingDialogFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class SettingsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSettingsFragment(): SettingsFragment

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsFragmentViewModel(viewModel: SettingsViewModel): ViewModel

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeThemeSettingFragment(): ThemeSettingDialogFragment
}
