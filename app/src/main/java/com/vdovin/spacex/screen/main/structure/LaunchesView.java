package com.vdovin.spacex.screen.main.structure;

import com.vdovin.spacex.base.BaseView;
import com.vdovin.spacex.database.model.SpaceX;

import java.util.List;

import io.reactivex.Observable;

public interface LaunchesView extends BaseView {

    void displayLaunches(List<SpaceX> launches);

    void openDetails(SpaceX spaceX);

    Observable<SpaceX> launchClicked();
}
