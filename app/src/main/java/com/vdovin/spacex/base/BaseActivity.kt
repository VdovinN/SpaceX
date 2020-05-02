package com.vdovin.spacex.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BasePresenter<*>> : AppCompatActivity() {
    abstract val presenter : P

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}