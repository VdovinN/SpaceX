package com.vdovin.spacex.base

import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BasePresenter<*>> : Fragment() {
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