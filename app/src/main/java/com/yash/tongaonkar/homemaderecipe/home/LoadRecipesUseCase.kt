package com.yash.tongaonkar.homemaderecipe.home

import com.yash.tongaonkar.homemaderecipe.models.Recipe
import com.yash.tongaonkar.homemaderecipe.utils.UseCase
import org.threeten.bp.Instant
import javax.inject.Inject

//
// Created by Yash Tongaonkar on 06/06/20.
//
open class LoadRecipesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) : UseCase<Instant, List<Recipe>>() {

    override fun execute(parameters: Instant): List<Recipe> {
        return homeRepository.getRecipes()
    }
}