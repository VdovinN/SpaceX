package com.vdovin.spacex.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.vdovin.spacex.R;
import com.vdovin.spacex.activity.structure.SpacePresenter;
import com.vdovin.spacex.activity.structure.SpaceView;
import com.vdovin.spacex.base.BaseActivity;
import com.vdovin.spacex.screen.detail.LaunchDetailsFragment;
import com.vdovin.spacex.screen.main.LaunchesFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class SpaceActivity extends BaseActivity implements HasSupportFragmentInjector, SpaceView {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    SpacePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.takeView(this);
    }

    @Override
    public void onBackPressed() {
        LaunchDetailsFragment launchDetailsFragment = (LaunchDetailsFragment) getSupportFragmentManager().findFragmentByTag(LaunchDetailsFragment.class.getName());
        if (launchDetailsFragment != null && launchDetailsFragment.isVisible()) {
            if (launchDetailsFragment.isFullscreen()) {
                launchDetailsFragment.exitFullScreen();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showLaunches() {
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
