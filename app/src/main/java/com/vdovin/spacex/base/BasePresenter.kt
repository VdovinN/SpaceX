package com.vdovin.spacex.base

interface BasePresenter<V : BaseView> {
    val view : V
    fun start()
    fun stop()
}