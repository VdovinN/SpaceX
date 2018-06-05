package com.vdovin.spacex.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.vdovin.spacex.R;
import com.vdovin.spacex.api.SpaceApi;
import com.vdovin.spacex.api.model.Links;
import com.vdovin.spacex.api.model.Rocket;
import com.vdovin.spacex.api.model.Space;
import com.vdovin.spacex.database.dao.SpaceXDao;
import com.vdovin.spacex.database.model.SpaceX;
import com.vdovin.spacex.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SpaceActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    SpaceApi api;

    @Inject
    SpaceXDao dao;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable<List<Space>> allPastLaunches = api.getAllPastLaunches();

        allPastLaunches
                .flatMap((Function<List<Space>, ObservableSource<List<SpaceX>>>) spaces -> {

                    List<SpaceX> spaceXList = new ArrayList<>();
                    for (Space space : spaces) {
                        Rocket rocket = space.getRocket();
                        Links links = space.getLinks();

                        SpaceX spaceX = new SpaceX(space.getFlightNumber(),
                                space.getMissionName(),
                                DateUtil.convertTimestampToFormattedDate(space.getLaunchDateUnix()),
                                rocket.getRocketName(),
                                rocket.getSecondStage().getPayloads().get(0).getPayloadMassKg(),
                                links.getWikipedia(),
                                links.getVideoLink());

                        spaceXList.add(spaceX);
                    }

                    dao.insert(spaceXList);

                    return dao.getAllLaunches().toObservable();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(spaces -> System.out.println(spaces));

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
