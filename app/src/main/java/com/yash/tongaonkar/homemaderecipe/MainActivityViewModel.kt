package com.yash.tongaonkar.homemaderecipe

import android.content.Context
import androidx.lifecycle.ViewModel
import com.yash.tongaonkar.homemaderecipe.theme.ThemedActivityDelegate
import javax.inject.Inject

//
// Created by Yash Tongaonkar on 06/06/20.
//
class MainActivityViewModel @Inject constructor(
    themedActivityDelegate: ThemedActivityDelegate,
    context: Context
) : ViewModel(), ThemedActivityDelegate by themedActivityDelegate