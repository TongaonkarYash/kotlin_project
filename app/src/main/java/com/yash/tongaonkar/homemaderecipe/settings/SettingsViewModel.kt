package com.yash.tongaonkar.homemaderecipe.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yash.tongaonkar.homemaderecipe.models.Theme
import com.yash.tongaonkar.homemaderecipe.result.Event
import com.yash.tongaonkar.homemaderecipe.result.Result.Success
import com.yash.tongaonkar.homemaderecipe.utils.map
import javax.inject.Inject

//
// Created by Yash Tongaonkar on 06/06/20.
//
class SettingsViewModel @Inject constructor(
    val setThemeUseCase: SetThemeUseCase,
    getThemeUseCase: GetThemeUseCase,
    getAvailableThemesUseCase: GetAvailableThemesUseCase
) : ViewModel() {

    private val themeResult =
        MutableLiveData<com.yash.tongaonkar.homemaderecipe.result.Result<Theme>>()
    val theme: LiveData<Theme>

    // Theme setting
    private val availableThemesResult =
        MutableLiveData<com.yash.tongaonkar.homemaderecipe.result.Result<List<Theme>>>()
    val availableThemes: LiveData<List<Theme>>

    private val _navigateToThemeSelector = MutableLiveData<Event<Unit>>()
    val navigateToThemeSelector: LiveData<Event<Unit>>
        get() = _navigateToThemeSelector


    init {
        getThemeUseCase(Unit, themeResult)
        theme = themeResult.map {
            (it as? Success<Theme>)?.data ?: Theme.SYSTEM
        }

        getAvailableThemesUseCase(Unit, availableThemesResult)
        availableThemes = availableThemesResult.map {
            (it as? Success<List<Theme>>)?.data ?: emptyList()
        }
    }

    fun setTheme(theme: Theme) {
        setThemeUseCase(theme)
    }

    fun onThemeSettingClicked() {
        _navigateToThemeSelector.value = Event(Unit)
    }
}