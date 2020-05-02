package com.vdovin.spacex.main

import android.os.Bundle
import com.vdovin.spacex.R
import com.vdovin.spacex.base.BaseActivity
import com.vdovin.spacex.base.BasePresenter
import com.vdovin.spacex.screen.main.LaunchesFragment
import com.vdovin.spacex.util.add
import com.vdovin.spacex.util.showSnackBar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : BaseActivity<MainView>(), MainView {

    override val presenter: BasePresenter<MainView> by inject(named(TAG))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun showLaunches() {
        add(LaunchesFragment.newInstance(), R.id.main_container)
    }

    override fun showError(message: String?) {
        root_view.showSnackBar(getString(R.string.error_message, message))
    }

    override val view: MainView = this

    companion object{
        const val TAG = "main_activity"
    }
}