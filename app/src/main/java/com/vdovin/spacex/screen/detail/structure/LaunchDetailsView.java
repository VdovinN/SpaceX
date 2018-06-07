package com.vdovin.spacex.screen.detail.structure;

import android.util.Pair;

import com.google.android.youtube.player.YouTubePlayer;
import com.vdovin.spacex.base.BaseView;
import com.vdovin.spacex.database.model.SpaceX;

import io.reactivex.Observable;

public interface LaunchDetailsView extends BaseView {

    void backPressed();

    void setupView(SpaceX spaceX);

    void openWiki(String wikiLink);

    Observable<Pair<YouTubePlayer, Boolean>> initializeYoutubePlayer();

    void playVideo(String youtubeId, YouTubePlayer youTubePlayer, boolean wasRestored);

    Observable<Boolean> detectFullscreen();

    Observable<Object> launchImageClicked();

    Observable<Object> backButtonClicked();

    Observable<Object> linkClicked();
}
