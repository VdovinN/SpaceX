package com.vdovin.spacex.base;

import android.os.Bundle;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends BaseView> {

    protected final CompositeDisposable disposables = new CompositeDisposable();
    private WeakReference<V> view = null;

    private boolean loaded;

    public final void takeView(V view) {
        if (view == null) throw new NullPointerException("new view must not be null");

        if (this.view != null) dropView(this.view.get());

        this.view = new WeakReference<>(view);
        if (!loaded) {
            loaded = true;
            onLoad();
        }
    }

    public final void dropView(V view) {
        if (view == null) throw new NullPointerException("dropped view must not be null");
        loaded = false;
        this.view = null;
        onDestroy();
    }

    protected final V getView() {
        if (view == null)
            throw new NullPointerException("getView called when view is null. Ensure takeView(View view) is called first.");
        return view.get();
    }

    protected void onLoad() {
    }

    public void onRestore(@NonNull Bundle savedInstanceState) {
    }

    public void onSave(@NonNull Bundle outState) {
    }

    public void onDestroy() {
        disposables.dispose();
    }
}
