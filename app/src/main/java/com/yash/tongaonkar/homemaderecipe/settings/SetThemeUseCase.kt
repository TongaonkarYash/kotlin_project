package com.yash.tongaonkar.homemaderecipe.settings

import com.yash.tongaonkar.homemaderecipe.models.Theme
import com.yash.tongaonkar.homemaderecipe.pref.PreferenceStorage
import com.yash.tongaonkar.homemaderecipe.utils.UseCase
import javax.inject.Inject

open class SetThemeUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : UseCase<Theme, Unit>() {
    override fun execute(parameters: Theme) {
        preferenceStorage.selectedTheme = parameters.storageKey
    }
}