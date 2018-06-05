package com.vdovin.spacex.rx;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppRxSchedulers implements RxSchedulers {

    private static final Executor NETWORK_EXECUTOR = Executors.newCachedThreadPool();
    private static final Scheduler NETWORK_SCHEDULER = Schedulers.from(NETWORK_EXECUTOR);

    @Override
    public Scheduler androidUI() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler network() {
        return NETWORK_SCHEDULER;
    }

    @Override
    public Scheduler trampoline() {
        return Schedulers.trampoline();
    }
}
