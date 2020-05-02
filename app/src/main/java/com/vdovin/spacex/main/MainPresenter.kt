package com.vdovin.spacex.main

import com.vdovin.spacex.api.SpaceApi

import com.vdovin.spacex.base.BasePresenter
import com.vdovin.spacex.database.dao.SpaceXDao
import com.vdovin.spacex.util.ConnectionHelper
import com.vdovin.spacex.util.convertToDatabaseModel
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent


class MainPresenter(
        private val api: SpaceApi,
        private val dao: SpaceXDao,
        private val connectionHelper: ConnectionHelper
) : BasePresenter<MainView>(), KoinComponent {

    override fun start() {
        launch {
            if (connectionHelper.isOnline()) {
                withContext(backgroundContext){
                    val spaces = api.allPastLaunches()
                    dao.insert(spaces.map { space -> space.convertToDatabaseModel() })
                    println(dao.allLaunches)
                }
            }
            view.showLaunches()
        }
    }

}