package com.vdovin.spacex.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseView> : AppCompatActivity(), BaseView {
    abstract val view: V
    abstract val presenter: BasePresenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initView(view)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}