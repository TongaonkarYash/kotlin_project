package com.yash.tongaonkar.homemaderecipe.utils

import androidx.lifecycle.MediatorLiveData
import com.yash.tongaonkar.homemaderecipe.result.Result

abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<Result<R>>()
    open fun observe(): MediatorLiveData<Result<R>> {
        return result
    }

    abstract fun execute(parameters: P)
}
