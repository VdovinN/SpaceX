package com.vdovin.spacex.main

import android.os.Bundle
import com.vdovin.spacex.R
import com.vdovin.spacex.base.BaseActivity
import com.vdovin.spacex.base.BasePresenter
import org.koin.android.ext.android.inject
import java.util.logging.Logger

class MainActivity : BaseActivity<MainView>(), MainView {

    override val presenter: BasePresenter<MainView> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun showLaunches() {
        //TODO open launches fragment
    }

    override fun showError(message: String?) {
        Logger.getLogger(javaClass.name).warning(message)
    }

    override val view: MainView = this
}