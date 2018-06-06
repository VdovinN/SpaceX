package com.vdovin.spacex.screen.main.structure;

import com.vdovin.spacex.base.BasePresenter;
import com.vdovin.spacex.database.dao.SpaceXDao;
import com.vdovin.spacex.rx.RxSchedulers;

import io.reactivex.disposables.Disposable;

public class LaunchesPresenter extends BasePresenter<LaunchesView> {

    private SpaceXDao spaceXDao;
    private RxSchedulers rxSchedulers;

    public LaunchesPresenter(SpaceXDao spaceXDao, RxSchedulers rxSchedulers) {
        this.spaceXDao = spaceXDao;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        disposables.add(loadAllLaunches());
        disposables.add(launchClicked());
    }

    private Disposable loadAllLaunches() {
        return spaceXDao.getAllLaunches()
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(launches -> getView().displayLaunches(launches));
    }

    private Disposable launchClicked() {
        return getView().launchClicked().subscribe(spaceX -> getView().openDetails(spaceX));
    }

}
