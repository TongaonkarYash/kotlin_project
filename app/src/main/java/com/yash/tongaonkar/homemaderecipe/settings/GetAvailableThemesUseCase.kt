package com.yash.tongaonkar.homemaderecipe.settings

import androidx.core.os.BuildCompat
import com.yash.tongaonkar.homemaderecipe.models.Theme
import com.yash.tongaonkar.homemaderecipe.scheduler.SyncScheduler
import com.yash.tongaonkar.homemaderecipe.utils.UseCase
import javax.inject.Inject

class GetAvailableThemesUseCase @Inject constructor() : UseCase<Unit, List<Theme>>() {
    init {
        taskScheduler = SyncScheduler
    }

    override fun execute(parameters: Unit): List<Theme> = when {
        BuildCompat.isAtLeastQ() -> {
            listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM)
        }
        else -> {
            listOf(Theme.LIGHT, Theme.DARK, Theme.BATTERY_SAVER)
        }
    }
}