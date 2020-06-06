package com.yash.tongaonkar.homemaderecipe.di

import androidx.lifecycle.ViewModel
import com.yash.tongaonkar.homemaderecipe.theme.ThemeViewModel
import com.yash.tongaonkar.homemaderecipe.theme.ThemedActivityDelegate
import com.yash.tongaonkar.homemaderecipe.theme.ThemedActivityDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@Suppress("UNUSED")
abstract class ThemedActivityDelegateModule {

    @Singleton
    @Binds
    abstract fun provideThemedActivityDelegate(
        impl: ThemedActivityDelegateImpl
    ): ThemedActivityDelegate

    @Binds
    @IntoMap
    @ViewModelKey(ThemeViewModel::class)
    abstract fun provideThemeViewModel(viewModel: ThemeViewModel): ViewModel
}
