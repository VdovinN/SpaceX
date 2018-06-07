package com.vdovin.spacex.activity.structure;

import com.vdovin.spacex.api.SpaceApi;
import com.vdovin.spacex.api.model.Links;
import com.vdovin.spacex.api.model.Rocket;
import com.vdovin.spacex.api.model.Space;
import com.vdovin.spacex.base.BasePresenter;
import com.vdovin.spacex.database.dao.SpaceXDao;
import com.vdovin.spacex.database.model.SpaceX;
import com.vdovin.spacex.rx.RxSchedulers;
import com.vdovin.spacex.util.ConnectivityInfo;
import com.vdovin.spacex.util.DateUtil;
import com.vdovin.spacex.util.ParseUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SpacePresenter extends BasePresenter<SpaceView> {

    private SpaceApi api;
    private SpaceXDao dao;
    private ConnectivityInfo connectivityInfo;
    private RxSchedulers rxSchedulers;

    public SpacePresenter(SpaceApi api, SpaceXDao dao, ConnectivityInfo connectivityInfo, RxSchedulers rxSchedulers) {
        this.api = api;
        this.dao = dao;
        this.connectivityInfo = connectivityInfo;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        disposables.add(loadLaunches());
    }

    private Disposable loadLaunches() {
        return Observable.just(connectivityInfo.isOnline())
                .flatMap(isOnline -> {
                    if (isOnline) {
                        return api.getAllPastLaunches();
                    } else {
                        return Observable.just(new ArrayList<Space>());
                    }
                })
                .flatMap(spaces -> {
                    if (!spaces.isEmpty()) {
                        List<SpaceX> spaceXList = new ArrayList<>();
                        for (Space space : spaces) {
                            Rocket rocket = space.getRocket();
                            Links links = space.getLinks();

                            SpaceX spaceX = new SpaceX(space.getFlightNumber(),
                                    space.getMissionName(),
                                    space.getDetails(),
                                    DateUtil.convertTimestampToFormattedDate(space.getLaunchDateUnix() * 1000),
                                    rocket.getRocketName(),
                                    rocket.getSecondStage().getPayloads().get(0).getPayloadMassKg(),
                                    links.getWikipedia(),
                                    ParseUtil.getYoutubeId(links.getVideoLink()));

                            spaceXList.add(spaceX);
                        }

                        dao.insert(spaceXList);
                    }

                    return Observable.just(1);
                })
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(success -> getView().showLaunches());
    }
}
