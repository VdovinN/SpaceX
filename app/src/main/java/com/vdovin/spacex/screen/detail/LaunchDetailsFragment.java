package com.vdovin.spacex.screen.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;
import com.vdovin.spacex.R;
import com.vdovin.spacex.base.BaseFragment;
import com.vdovin.spacex.database.model.SpaceX;
import com.vdovin.spacex.screen.detail.structure.LaunchDetailsPresenter;
import com.vdovin.spacex.screen.detail.structure.LaunchDetailsView;
import com.vdovin.spacex.util.Constants;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;

public class LaunchDetailsFragment extends BaseFragment implements LaunchDetailsView {

    @Inject
    LaunchDetailsPresenter presenter;

    @BindView(R.id.back_button)
    ImageButton backButton;
    @BindView(R.id.launch_title_text_view)
    TextView launchTitleTextView;
    @BindView(R.id.launch_image_view)
    ImageView launchImageView;
    @BindView(R.id.launch_date_text_view)
    TextView launchDateTextView;
    @BindView(R.id.launch_details_text_view)
    TextView launchDetailsTextView;
    @BindView(R.id.launch_rocket_name_text_view)
    TextView launchRocketNameTextView;
    @BindView(R.id.launch_payload_mass_text_view)
    TextView launchPayloadMassTextView;
    @BindView(R.id.launch_wiki_link_text_view)
    TextView launchWikiLinkTextView;


    private YouTubePlayerSupportFragment youTubePlayerFragment;

    private YouTubePlayer player;

    public static LaunchDetailsFragment newInstance(SpaceX spaceX) {

        Bundle args = new Bundle();
        args.putSerializable(Constants.SPACE_X_KEY, spaceX);
        LaunchDetailsFragment fragment = new LaunchDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.launch_detail_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();

        SpaceX spaceX = null;

        if (getArguments() != null) {
            spaceX = (SpaceX) getArguments().getSerializable(Constants.SPACE_X_KEY);
        }

        presenter.setSpaceX(spaceX);

        presenter.takeView(this);
    }


    @Override
    public void backPressed() {
        Objects.requireNonNull(getActivity()).onBackPressed();
    }

    @Override
    public void setupView(SpaceX spaceX) {
        if (spaceX != null) {
            launchTitleTextView.setText(spaceX.getMissionName());
            launchDateTextView.setText(spaceX.getLaunchDate());
            launchDetailsTextView.setText(spaceX.getDetails());
            String rocketName = getString(R.string.rocket_name) + ": " + spaceX.getRocketName();
            launchRocketNameTextView.setText(rocketName);
            if (spaceX.getPayloadMass() != null) {
                String payloadMass = getString(R.string.payload_mass) + ": " + spaceX.getPayloadMass().intValue() + getString(R.string.kg);
                launchPayloadMassTextView.setText(payloadMass);
            } else {
                launchPayloadMassTextView.setVisibility(View.GONE);
            }

            String youtubeId = spaceX.getYoutubeVideoId();
            if (youtubeId != null) {
                String path = Constants.YOUTUBE_IMG_BASE_URL + youtubeId + Constants.YOUTUBE_IMG_END_URL;
                Picasso.get().load(path).into(launchImageView);
            }
        }
    }

    @Override
    public void openWiki(String wikiLink) {
        if (wikiLink != null) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(wikiLink));
            startActivity(browserIntent);
        }
    }

    @Override
    public Observable<Pair<YouTubePlayer, Boolean>> initializeYoutubePlayer() {
        return Observable.create(emitter -> youTubePlayerFragment.initialize(Constants.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                emitter.onNext(new Pair<>(youTubePlayer, b));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        }));
    }

    @Override
    public void playVideo(String youtubeId, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            player = youTubePlayer;
            launchImageView.setVisibility(View.GONE);
            player.loadVideo(youtubeId);
            player.play();
        }
    }

    @Override
    public Observable<Boolean> detectFullscreen() {
        return Observable.create(emitter -> player.setOnFullscreenListener(emitter::onNext));
    }

    @Override
    public Observable<Object> launchImageClicked() {
        return RxView.clicks(launchImageView).throttleFirst(Constants.TIME_BETWEEN_CLICKS, TimeUnit.MILLISECONDS);
    }

    @Override
    public Observable<Object> backButtonClicked() {
        return RxView.clicks(backButton).throttleFirst(Constants.TIME_BETWEEN_CLICKS, TimeUnit.MILLISECONDS);
    }

    @Override
    public Observable<Object> linkClicked() {
        return RxView.clicks(launchWikiLinkTextView).throttleFirst(Constants.TIME_BETWEEN_CLICKS, TimeUnit.MILLISECONDS);
    }

    public boolean isFullscreen() {
        return presenter.isFullScreen();
    }

    public void exitFullScreen() {
        if (player != null) {
            player.setFullscreen(false);
        }
    }

}
