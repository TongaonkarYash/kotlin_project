package com.yash.tongaonkar.homemaderecipe.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePaddingRelative
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.yash.tongaonkar.homemaderecipe.MainNavigationFragment
import com.yash.tongaonkar.homemaderecipe.databinding.FragmentHomeBinding
import com.yash.tongaonkar.homemaderecipe.models.Recipe
import com.yash.tongaonkar.homemaderecipe.utils.doOnApplyWindowInsets
import com.yash.tongaonkar.homemaderecipe.utils.viewModelProvider
import javax.inject.Inject

//
// Created by Yash Tongaonkar on 06/06/20.
//
class HomeFragment : MainNavigationFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    private var adapter: RecipeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = viewModelProvider(viewModelFactory)
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.relativeLayoutHome.doOnApplyWindowInsets { v, insets, padding ->
            v.updatePaddingRelative(bottom = padding.bottom + insets.systemWindowInsetBottom)
        }

        homeViewModel.feed.observe(this, Observer {
            showItems(binding.recyclerViewRecipe, it)
        })

        return binding.root
    }

    private fun showItems(recyclerViewRecipe: RecyclerView, recipes: List<Any>?) {
        if (adapter == null) {
            adapter = RecipeAdapter(recipes)
        }
        if (recyclerViewRecipe.adapter == null) {
            recyclerViewRecipe.adapter = adapter
        }
        (recyclerViewRecipe.adapter as RecipeAdapter).submitList(recipes ?: emptyList())
    }
}