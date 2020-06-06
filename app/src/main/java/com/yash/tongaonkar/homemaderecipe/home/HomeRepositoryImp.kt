package com.yash.tongaonkar.homemaderecipe.home

import com.yash.tongaonkar.homemaderecipe.models.Recipe
import javax.inject.Inject
import javax.inject.Singleton

//
// Created by Yash Tongaonkar on 06/06/20.
//

interface HomeRepository {
    fun getRecipes(): List<Recipe>
}

@Singleton
open class HomeRepositoryImp @Inject constructor(
    private val recipeDataSource: RecipeDataSource
) : HomeRepository {

    override fun getRecipes(): List<Recipe> = recipeDataSource.getRecipes()
}