package com.vdovin.spacex.screen.main.structure;

import com.vdovin.spacex.base.BaseView;
import com.vdovin.spacex.database.model.SpaceX;

import java.util.List;

public interface LaunchesView extends BaseView {

    void displayLaunches(List<SpaceX> launches);

}
