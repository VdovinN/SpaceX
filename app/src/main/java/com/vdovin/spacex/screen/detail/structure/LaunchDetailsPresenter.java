package com.vdovin.spacex.screen.detail.structure;

import com.vdovin.spacex.base.BasePresenter;
import com.vdovin.spacex.database.model.SpaceX;

import io.reactivex.disposables.Disposable;

public class LaunchDetailsPresenter extends BasePresenter<LaunchDetailsView> {

    private SpaceX spaceX;

    public boolean fullScreen;//Fix it

    public LaunchDetailsPresenter() {
    }

    public void setSpaceX(SpaceX spaceX) {
        this.spaceX = spaceX;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        getView().setupView(spaceX);
        disposables.add(back());
        disposables.add(openLink());
        disposables.add(play());
    }

    private Disposable back() {
        return getView().backButtonClicked().subscribe(o -> getView().backPressed());
    }

    private Disposable openLink() {
        return getView().linkClicked().subscribe(o -> getView().openWiki(spaceX.getWikipediaLink()));
    }

    private Disposable play() {
        return getView().launchImageClicked().flatMap(o -> getView().initializeYoutubePlayer()).subscribe(pair -> {
            fullScreen = pair.second;
            getView().playVideo(spaceX.getYoutubeVideoId(), pair.first, pair.second);
            disposables.add(detectFullScreen());
        });
    }

    private Disposable detectFullScreen() {
        return getView().detectFullscreen().subscribe(isFullscreen -> fullScreen = isFullscreen);
    }

}
