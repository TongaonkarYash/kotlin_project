package com.yash.tongaonkar.homemaderecipe.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yash.tongaonkar.homemaderecipe.models.Recipe
import com.yash.tongaonkar.homemaderecipe.result.Result
import com.yash.tongaonkar.homemaderecipe.result.successOr
import com.yash.tongaonkar.homemaderecipe.utils.LoadingIndicator
import com.yash.tongaonkar.homemaderecipe.utils.RecipesEmpty
import com.yash.tongaonkar.homemaderecipe.utils.map
import javax.inject.Inject

//
// Created by Yash Tongaonkar on 06/06/20.
//
class HomeViewModel @Inject constructor(
    loadRecipesUseCase: LoadRecipesUseCase,
    timeProvider: com.yash.tongaonkar.homemaderecipe.utils.TimeProvider
) : ViewModel() {

    private val loadRecipesResult = MutableLiveData<Result<List<Recipe>>>()

    val feed: LiveData<List<Any>>

    init {
        loadRecipesUseCase(timeProvider.now(), loadRecipesResult)

        val recipes: LiveData<List<Any>> = loadRecipesResult.map {
            if (it is Result.Loading) {
                listOf(LoadingIndicator)
            } else {
                val items = it.successOr(emptyList())
                if (items.isNotEmpty()) items else listOf(RecipesEmpty)
            }
        }

        feed = recipes

    }
}