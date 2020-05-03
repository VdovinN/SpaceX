package com.vdovin.spacex.screen.details

import com.google.android.youtube.player.YouTubePlayer
import com.vdovin.spacex.base.BaseView

interface LaunchDetailsView : BaseView {

    fun backPressed()

    fun backButtonClicked(backClickListener: () -> Unit)

    fun launchImageClicked(launchImageListener: (YouTubePlayer, Boolean) -> Unit)

    fun linkClicked(linkListener: () -> Unit)

    fun setTitle(title : String)

    fun setDate(date : String)

    fun setDetails(details: String)

    fun setRocketName(rocketName: String)

    fun setPayloadMass(payloadMass: Double)

    fun setPayloadMassVisibility(visibility : Int)

    fun setLaunchImageVisibility(visibility: Int)

    fun setImage(imgSource: String)

    fun openWiki(wikiLink: String)

}