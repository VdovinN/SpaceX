package com.vdovin.spacex.screen.main

import com.vdovin.spacex.base.BaseView
import com.vdovin.spacex.database.model.SpaceX




interface LaunchesView : BaseView {

    fun displayLaunches(launches: List<SpaceX>)

    fun openDetails(spaceX: SpaceX)

    fun launchClicked(launchClickListener : (SpaceX) -> Unit)

}