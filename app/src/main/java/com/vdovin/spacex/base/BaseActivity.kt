package com.vdovin.spacex.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vdovin.spacex.util.setupStatusBarColor

abstract class BaseActivity<V : BaseView> : AppCompatActivity(), BaseView {
    abstract val view: V
    abstract val presenter: BasePresenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupStatusBarColor()
        presenter.initView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }
}