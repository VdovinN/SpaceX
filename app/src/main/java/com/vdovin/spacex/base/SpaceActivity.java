package com.vdovin.spacex.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.vdovin.spacex.R;
import com.vdovin.spacex.api.SpaceApi;
import com.vdovin.spacex.api.model.Links;
import com.vdovin.spacex.api.model.Rocket;
import com.vdovin.spacex.api.model.Space;
import com.vdovin.spacex.database.dao.SpaceXDao;
import com.vdovin.spacex.database.model.SpaceX;
import com.vdovin.spacex.screen.main.LaunchesFragment;
import com.vdovin.spacex.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
        setupStatusBarColor();

        Observable<List<Space>> allPastLaunches = api.getAllPastLaunches();

        allPastLaunches
                .flatMap(spaces -> {

                    List<SpaceX> spaceXList = new ArrayList<>();
                    for (Space space : spaces) {
                        Rocket rocket = space.getRocket();
                        Links links = space.getLinks();

                        String youtubeVideoId = "";

                        Matcher m = Pattern.compile("[?&;]v=([^?&;]+)").matcher(links.getVideoLink());
                        if (m.find()) {
                            youtubeVideoId = m.group(1);
                        }

                        SpaceX spaceX = new SpaceX(space.getFlightNumber(),
                                space.getMissionName(),
                                space.getDetails(),
                                DateUtil.convertTimestampToFormattedDate(space.getLaunchDateUnix() * 1000),
                                rocket.getRocketName(),
                                rocket.getSecondStage().getPayloads().get(0).getPayloadMassKg(),
                                links.getWikipedia(),
                                links.getVideoLink(),
                                youtubeVideoId);


                        spaceXList.add(spaceX);
                    }

                    dao.insert(spaceXList);

                    return Observable.just(1);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> showLaunches());

    }

    private void setupStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.generalGray));
        }
    }

    private void showLaunches() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.main_container, LaunchesFragment.newInstance())
                .commit();
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
