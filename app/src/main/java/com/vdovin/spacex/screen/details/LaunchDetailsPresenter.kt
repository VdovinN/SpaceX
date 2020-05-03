package com.vdovin.spacex.screen.details

import android.view.View.GONE
import com.google.android.youtube.player.YouTubePlayer
import com.vdovin.spacex.base.BasePresenter
import com.vdovin.spacex.database.dao.SpaceXDao
import kotlinx.coroutines.withContext

class LaunchDetailsPresenter(private val dao: SpaceXDao) : BasePresenter<LaunchDetailsView>() {

    var flightNumber: Long? = null

    var fullscreen = false

    private var player: YouTubePlayer? = null

    override fun start() {
        flightNumber?.let {
            launch {
                val space = withContext(backgroundContext) { dao.getLaunchByFlightNumber(it) }
                space.missionName?.let { title -> view.setTitle(title) }
                space.launchDate?.let { date -> view.setDate(date) }
                space.details?.let { detail -> view.setDetails(detail) }
                space.rocketName?.let { name -> view.setRocketName(name) }
                space.payloadMass?.let { mass -> view.setPayloadMass(mass) }
                        ?: view.setPayloadMassVisibility(GONE)
                space.youtubeVideoId?.let { img ->
                    view.setImage(img)
                    view.launchImageClicked { youTubePlayer, wasRestored ->
                        if (!wasRestored) {
                            player = youTubePlayer
                            view.setLaunchImageVisibility(GONE)
                            player?.let {
                                it.loadVideo(img)
                                it.play()
                            }

                            youTubePlayer.setOnFullscreenListener {
                                fullscreen = it
                            }
                        }
                    }
                }

                view.backButtonClicked { view.backPressed() }
                space.wikipediaLink?.let { link -> view.linkClicked {  view.openWiki(link) } }
            }
        }
    }

    fun exitFullScreen(){
        player?.let {
            it.setFullscreen(false)
        }
    }
}