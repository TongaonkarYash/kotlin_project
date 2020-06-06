package com.yash.tongaonkar.homemaderecipe.settings

import com.yash.tongaonkar.homemaderecipe.models.Theme
import com.yash.tongaonkar.homemaderecipe.models.themeFromStorageKey
import com.yash.tongaonkar.homemaderecipe.pref.PreferenceStorage
import com.yash.tongaonkar.homemaderecipe.result.Result.Success
import com.yash.tongaonkar.homemaderecipe.utils.MediatorUseCase
import javax.inject.Inject

open class ObserveThemeModeUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : MediatorUseCase<Unit, Theme>() {
    override fun execute(parameters: Unit) {
        result.addSource(preferenceStorage.observableSelectedTheme) {
            result.postValue(Success(themeFromStorageKey(it)))
        }
    }
}