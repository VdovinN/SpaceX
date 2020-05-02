package com.vdovin.spacex.screen.main

import com.vdovin.spacex.base.BasePresenter
import com.vdovin.spacex.database.dao.SpaceXDao
import kotlinx.coroutines.withContext

class LaunchesPresenter(private val dao: SpaceXDao) : BasePresenter<LaunchesView>() {
    override fun start() {
        launch {
            val launches = withContext(backgroundContext) { dao.allLaunches }
            view.displayLaunches(launches)
            view.launchClicked{ spaceX -> view.openDetails(spaceX) }
        }
    }
}