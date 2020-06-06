package com.yash.tongaonkar.homemaderecipe.theme

import androidx.lifecycle.LiveData
import com.yash.tongaonkar.homemaderecipe.models.Theme
import com.yash.tongaonkar.homemaderecipe.result.Result
import com.yash.tongaonkar.homemaderecipe.settings.GetThemeUseCase
import com.yash.tongaonkar.homemaderecipe.settings.ObserveThemeModeUseCase
import com.yash.tongaonkar.homemaderecipe.utils.map
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

interface ThemedActivityDelegate {

    val theme: LiveData<Theme>
    val currentTheme: Theme
}

class ThemedActivityDelegateImpl @Inject constructor(
    private val observeThemeUseCase: ObserveThemeModeUseCase,
    private val getThemeUseCase: GetThemeUseCase
) : ThemedActivityDelegate {
    override val theme: LiveData<Theme> by lazy(NONE) {
        observeThemeUseCase.observe().map {
            if (it is Result.Success) it.data else Theme.SYSTEM
        }
    }

    override val currentTheme: Theme
        get() = getThemeUseCase.executeNow(Unit).let {
            if (it is Result.Success) it.data else Theme.SYSTEM
        }

    init {
        observeThemeUseCase.execute(Unit)
    }
}
