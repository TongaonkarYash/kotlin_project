package com.yash.tongaonkar.homemaderecipe.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.yash.tongaonkar.homemaderecipe.R
import com.yash.tongaonkar.homemaderecipe.home.FireBaseRecipeDataSource
import com.yash.tongaonkar.homemaderecipe.home.HomeRepository
import com.yash.tongaonkar.homemaderecipe.home.HomeRepositoryImp
import com.yash.tongaonkar.homemaderecipe.home.RecipeDataSource
import com.yash.tongaonkar.homemaderecipe.utils.DefaultTimeProvider
import com.yash.tongaonkar.homemaderecipe.utils.TimeProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//
// Created by Yash Tongaonkar on 06/06/20.
//
@Module
class FireBaseModule {


    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        val firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        return firestore
    }

    @Singleton
    @Provides
    fun provideFirebaseFunctions(): FirebaseFunctions {
        return FirebaseFunctions.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseRemoteConfigSettings(): FirebaseRemoteConfigSettings {
        return FirebaseRemoteConfigSettings.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideFirebaseRemoteConfig(
        configSettings: FirebaseRemoteConfigSettings
    ): FirebaseRemoteConfig {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setConfigSettingsAsync(configSettings)
        return remoteConfig
    }

    @Singleton
    @Provides
    fun provideRecipeDataSource(firestore: FirebaseFirestore): RecipeDataSource {
        return FireBaseRecipeDataSource(firestore)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        dataSource: RecipeDataSource
    ): HomeRepository {
        return HomeRepositoryImp(dataSource)
    }

    @Singleton
    @Provides
    fun provideTimeProvider(): TimeProvider {
        return DefaultTimeProvider
    }
}