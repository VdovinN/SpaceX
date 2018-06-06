package com.vdovin.spacex.screen.detail.structure;

import com.vdovin.spacex.base.BaseView;

import io.reactivex.Observable;

public interface LaunchDetailsView extends BaseView {

    void backPressed();

    void openWiki();

    Observable<Object> backButtonClicked();

    Observable<Object> linkClicked();
}
