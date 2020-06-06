package com.yash.tongaonkar.homemaderecipe

import android.app.Application
import com.yash.tongaonkar.homemaderecipe.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

//
// Created by Yash Tongaonkar on 31/05/20.
//
class MainApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}