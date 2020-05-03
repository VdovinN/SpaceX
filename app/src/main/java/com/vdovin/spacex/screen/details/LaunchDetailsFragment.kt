package com.vdovin.spacex.screen.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import com.squareup.picasso.Picasso
import com.vdovin.spacex.R
import com.vdovin.spacex.base.BaseFragment
import com.vdovin.spacex.base.BasePresenter
import com.vdovin.spacex.util.Constants.SPACE_X_KEY
import com.vdovin.spacex.util.Constants.YOUTUBE_API_KEY
import com.vdovin.spacex.util.Constants.YOUTUBE_IMG_BASE_URL
import com.vdovin.spacex.util.Constants.YOUTUBE_IMG_END_URL
import com.vdovin.spacex.util.showSnackBar
import kotlinx.android.synthetic.main.launch_detail_layout.*
import kotlinx.android.synthetic.main.launches_detail_toolbar_layout.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named


class LaunchDetailsFragment : BaseFragment<LaunchDetailsView>(), LaunchDetailsView {
    override val view: LaunchDetailsView = this
    override val presenter: BasePresenter<LaunchDetailsView> by inject(named(TAG))

    private var youTubePlayerFragment: YouTubePlayerSupportFragmentX? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flightNumber = arguments?.get(SPACE_X_KEY) as Long
        (presenter as LaunchDetailsPresenter).flightNumber = flightNumber
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.launch_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        youTubePlayerFragment = YouTubePlayerSupportFragmentX.newInstance()
        youTubePlayerFragment?.let {
            val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.youtube_fragment, it).commit()
        }
    }

    override fun backPressed() {
        activity?.onBackPressed()
    }

    override fun backButtonClicked(backClickListener: () -> Unit) {
        back_button.setOnClickListener { backClickListener.invoke() }
    }

    override fun setTitle(title: String) {
        launch_title_text_view.text = title
    }

    override fun setDate(date: String) {
        launch_date_text_view.text = date
    }

    override fun setDetails(details: String) {
        launch_details_text_view.text = details
    }

    override fun setRocketName(rocketName: String) {
        val name = "${getString(R.string.rocket_name)}: $rocketName"
        launch_rocket_name_text_view.text = name
    }

    override fun setPayloadMass(payloadMass: Double) {
        val mass = getString(R.string.payload_mass) + ": " + payloadMass.toInt() + getString(R.string.kg)
        launch_payload_mass_text_view.text = mass
    }

    override fun setPayloadMassVisibility(visibility: Int) {
        launch_payload_mass_text_view.visibility = visibility
    }

    override fun setLaunchImageVisibility(visibility: Int) {
        launch_image_view.visibility = visibility
    }

    override fun setImage(imgSource: String) {
        val path = "$YOUTUBE_IMG_BASE_URL$imgSource$YOUTUBE_IMG_END_URL"
        Picasso.get().load(path).into(launch_image_view)
    }

    override fun launchImageClicked(launchImageListener: (YouTubePlayer, Boolean) -> Unit) {
        launch_image_view.setOnClickListener {
            youTubePlayerFragment?.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youtubePlayer: YouTubePlayer, fullScreen: Boolean) {
                    launchImageListener.invoke(youtubePlayer, fullScreen)
                }

                override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                    parent_view.showSnackBar(getString(R.string.error_message, getString(R.string.youtube_error)))
                }
            })
        }
    }

    override fun linkClicked(linkListener: () -> Unit) {
        launch_wiki_link_text_view.setOnClickListener { linkListener.invoke() }
    }

    override fun openWiki(wikiLink: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wikiLink))
        startActivity(browserIntent)
    }

    override fun showError(message: String?) {
        parent_view.showSnackBar(getString(R.string.error_message, message))
    }

    fun isFullScreen() = (presenter as LaunchDetailsPresenter).fullscreen
    fun exitFullScreen() {
        (presenter as LaunchDetailsPresenter).exitFullScreen()
    }

    companion object {
        const val TAG = "launch_detail_fragment"
        fun newInstance(flightNumber: Long) = LaunchDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(SPACE_X_KEY, flightNumber)
            }
        }
    }
}