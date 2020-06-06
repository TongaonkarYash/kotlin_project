package com.yash.tongaonkar.homemaderecipe.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yash.tongaonkar.homemaderecipe.R
import com.yash.tongaonkar.homemaderecipe.databinding.ItemEmptyRecipeBinding
import com.yash.tongaonkar.homemaderecipe.databinding.ItemRecipeHolderBinding
import com.yash.tongaonkar.homemaderecipe.models.Recipe
import com.yash.tongaonkar.homemaderecipe.utils.RecipesEmpty


//
// Created by Yash Tongaonkar on 06/06/20.
//
class RecipeAdapter(private val recipeList: List<Any>?) :
    ListAdapter<Any, RecipeAdapter.RecipeViewHolders>(RecipeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolders {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_recipe_holder -> RecipeViewHolders.RecipeItemCardHolder(
                ItemRecipeHolderBinding.inflate(inflater, parent, false)
            )
            R.layout.item_empty_recipe -> RecipeViewHolders.RecipeEmptyItemCardHolder(
                ItemEmptyRecipeBinding.inflate(inflater, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolders, position: Int) {
        if (holder is RecipeViewHolders.RecipeItemCardHolder) {
            val recipe = recipeList?.get(position) as Recipe
            holder.binding.recipe = recipe
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = recipeList?.get(position)
        return if (item is RecipesEmpty) R.layout.item_empty_recipe else R.layout.item_recipe_holder
    }

    sealed class RecipeViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class RecipeItemCardHolder(
            val binding: ItemRecipeHolderBinding
        ) : RecipeViewHolders(binding.root)

        class RecipeEmptyItemCardHolder(
            val binding: ItemEmptyRecipeBinding
        ) : RecipeViewHolders(binding.root)
    }

    override fun getItemCount(): Int {
        return recipeList?.size ?: 0
    }

    internal object RecipeDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return true
        }

        @SuppressLint("DiffUtilEquals")
        // Workaround of https://issuetracker.google.com/issues/122928037
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return true
        }
    }
}