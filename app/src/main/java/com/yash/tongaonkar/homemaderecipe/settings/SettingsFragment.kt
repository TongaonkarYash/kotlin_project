package com.yash.tongaonkar.homemaderecipe.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePaddingRelative
import androidx.lifecycle.ViewModelProvider
import com.yash.tongaonkar.homemaderecipe.MainNavigationFragment
import com.yash.tongaonkar.homemaderecipe.databinding.FragmentSettingsBinding
import com.yash.tongaonkar.homemaderecipe.result.EventObserver
import com.yash.tongaonkar.homemaderecipe.utils.doOnApplyWindowInsets
import com.yash.tongaonkar.homemaderecipe.utils.viewModelProvider
import javax.inject.Inject

//
// Created by Yash Tongaonkar on 03/06/20.
//
class SettingsFragment : MainNavigationFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = viewModelProvider(viewModelFactory)

        viewModel.navigateToThemeSelector.observe(this, EventObserver {
            ThemeSettingDialogFragment.newInstance()
                .show(requireFragmentManager(), null)
        })

        val binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.settingsScroll.doOnApplyWindowInsets { v, insets, padding ->
            v.updatePaddingRelative(bottom = padding.bottom + insets.systemWindowInsetBottom)
        }
        return binding.root
    }
}