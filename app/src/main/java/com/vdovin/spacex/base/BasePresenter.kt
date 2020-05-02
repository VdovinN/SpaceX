package com.vdovin.spacex.base

import kotlinx.coroutines.*

abstract class BasePresenter<V : BaseView> : CoroutineScope {
    protected lateinit var view: V
    private var job = SupervisorJob()
    private val handler = CoroutineExceptionHandler { _, throwable ->
        view.showError(throwable.message)
    }
    override val coroutineContext = Dispatchers.Main + job
    protected val backgroundContext = Dispatchers.IO

    fun initView(view : V) {
        this.view = view
        if(job.isCancelled) {
            job = SupervisorJob()
        }
        start()
    }

    protected abstract fun start()
    fun stop() {
        job.cancel()
    }

    fun launch(func: suspend CoroutineScope.() -> Unit) {
        launch(handler) {
            func.invoke(this)
        }
    }

}