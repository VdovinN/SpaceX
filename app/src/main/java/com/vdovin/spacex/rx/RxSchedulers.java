package com.vdovin.spacex.rx;


import io.reactivex.Scheduler;

public interface RxSchedulers {

    Scheduler androidUI();

    Scheduler io();

    Scheduler computation();

    Scheduler network();

    Scheduler trampoline();
}
