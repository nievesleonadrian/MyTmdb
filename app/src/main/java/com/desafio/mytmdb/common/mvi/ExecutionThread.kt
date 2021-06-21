package com.desafio.mytmdb.common.mvi

import io.reactivex.Scheduler


interface ExecutionThread {
    fun schedulerForObserving(): Scheduler
    fun schedulerForSubscribing(): Scheduler
}