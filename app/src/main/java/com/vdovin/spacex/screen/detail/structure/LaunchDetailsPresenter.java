package com.vdovin.spacex.screen.detail.structure;

import com.vdovin.spacex.base.BasePresenter;

import io.reactivex.disposables.Disposable;

public class LaunchDetailsPresenter extends BasePresenter<LaunchDetailsView> {

    public LaunchDetailsPresenter() {
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        disposables.add(back());
        disposables.add(openLink());
        disposables.add(play());
    }

    private Disposable back() {
        return getView().backButtonClicked().subscribe(o -> getView().backPressed());
    }

    private Disposable openLink() {
        return getView().linkClicked().subscribe(o -> getView().openWiki());
    }

    private Disposable play() {
        return getView().launchImageClicked().subscribe(o -> getView().playVideo());
    }

}
