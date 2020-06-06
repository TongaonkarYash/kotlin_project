package com.yash.tongaonkar.homemaderecipe.utils

import org.threeten.bp.Instant

interface TimeProvider {
    fun now(): org.threeten.bp.Instant
}

object DefaultTimeProvider : TimeProvider {
    private var delegate: TimeProvider = WallclockTimeProvider

    fun setDelegate(newDelegate: TimeProvider?) {
        delegate = newDelegate ?: WallclockTimeProvider
    }

    override fun now(): Instant {
        return delegate.now()
    }
}

internal object WallclockTimeProvider : TimeProvider {
    override fun now(): Instant {
        return Instant.now()
    }
}
