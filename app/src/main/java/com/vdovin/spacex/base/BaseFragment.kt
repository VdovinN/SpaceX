package com.vdovin.spacex.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : BaseView> : Fragment(), BaseView {
    abstract val view : V
    lateinit var presenter : BasePresenter<V>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initView(this.view)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}