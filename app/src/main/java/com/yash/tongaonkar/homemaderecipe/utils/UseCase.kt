package com.yash.tongaonkar.homemaderecipe.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yash.tongaonkar.homemaderecipe.scheduler.DefaultScheduler
import com.yash.tongaonkar.homemaderecipe.scheduler.Scheduler

abstract class UseCase<in P, R> {
    protected var taskScheduler: Scheduler = DefaultScheduler

    operator fun invoke(parameters: P, result: MutableLiveData<com.yash.tongaonkar.homemaderecipe.result.Result<R>>) {
        try {
            taskScheduler.execute {
                try {
                    execute(parameters).let { useCaseResult ->
                        result.postValue(com.yash.tongaonkar.homemaderecipe.result.Result.Success(useCaseResult))
                    }
                } catch (e: Exception) {
                    result.postValue(com.yash.tongaonkar.homemaderecipe.result.Result.Error(e))
                }
            }
        } catch (e: Exception) {
            result.postValue(com.yash.tongaonkar.homemaderecipe.result.Result.Error(e))
        }
    }

    operator fun invoke(parameters: P): LiveData<com.yash.tongaonkar.homemaderecipe.result.Result<R>> {
        val liveCallback: MutableLiveData<com.yash.tongaonkar.homemaderecipe.result.Result<R>> = MutableLiveData()
        this(parameters, liveCallback)
        return liveCallback
    }

    fun executeNow(parameters: P): com.yash.tongaonkar.homemaderecipe.result.Result<R> {
        return try {
            com.yash.tongaonkar.homemaderecipe.result.Result.Success(execute(parameters))
        } catch (e: Exception) {
            com.yash.tongaonkar.homemaderecipe.result.Result.Error(e)
        }
    }


    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): R
}

operator fun <R> UseCase<Unit, R>.invoke(): LiveData<com.yash.tongaonkar.homemaderecipe.result.Result<R>> = this(Unit)
operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<com.yash.tongaonkar.homemaderecipe.result.Result<R>>) = this(Unit, result)
