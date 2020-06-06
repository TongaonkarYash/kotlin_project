package com.yash.tongaonkar.homemaderecipe.di

import androidx.lifecycle.ViewModel
import com.yash.tongaonkar.homemaderecipe.home.HomeFragment
import com.yash.tongaonkar.homemaderecipe.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

//
// Created by Yash Tongaonkar on 06/06/20.
//
@Module
internal abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel: HomeViewModel): ViewModel
}