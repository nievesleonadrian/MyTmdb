package com.desafio.mytmdb.factory

import com.desafio.mytmdb.common.mvi.ExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class FakeExecutionThread: ExecutionThread {

    override fun schedulerForObserving(): Scheduler = Schedulers.trampoline()

    override fun schedulerForSubscribing(): Scheduler = Schedulers.trampoline()

}