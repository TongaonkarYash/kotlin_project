package com.yash.tongaonkar.homemaderecipe.settings

import androidx.core.os.BuildCompat
import com.yash.tongaonkar.homemaderecipe.models.Theme
import com.yash.tongaonkar.homemaderecipe.models.themeFromStorageKey
import com.yash.tongaonkar.homemaderecipe.pref.PreferenceStorage
import com.yash.tongaonkar.homemaderecipe.utils.UseCase
import javax.inject.Inject

open class GetThemeUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : UseCase<Unit, Theme>() {
    override fun execute(parameters: Unit): Theme {
        preferenceStorage.selectedTheme?.let { key ->
            return themeFromStorageKey(key)
        }
        // If we get here, we don't currently have a theme set, so we need to provide a default
        return when {
            BuildCompat.isAtLeastQ() -> Theme.SYSTEM
            else -> Theme.BATTERY_SAVER
        }
    }
}