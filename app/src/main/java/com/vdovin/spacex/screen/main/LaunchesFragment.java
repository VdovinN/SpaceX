package com.vdovin.spacex.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vdovin.spacex.R;
import com.vdovin.spacex.database.model.SpaceX;
import com.vdovin.spacex.screen.detail.LaunchDetailsFragment;
import com.vdovin.spacex.screen.main.adapter.LaunchesAdapter;
import com.vdovin.spacex.screen.main.structure.LaunchesPresenter;
import com.vdovin.spacex.screen.main.structure.LaunchesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;

public class LaunchesFragment extends Fragment implements LaunchesView {

    @Inject
    LaunchesPresenter presenter;

    @BindView(R.id.launches_recycler_view)
    RecyclerView launchesRecyclerView;

    private LaunchesAdapter launchesAdapter;

    public static LaunchesFragment newInstance() {
        return new LaunchesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.launches_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        launchesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        launchesAdapter = new LaunchesAdapter(getContext(), new ArrayList<>());
        launchesRecyclerView.setAdapter(launchesAdapter);

        presenter.takeView(this);
    }

    @Override
    public void displayLaunches(List<SpaceX> launches) {
        launchesAdapter.swap(launches);
    }

    @Override
    public void openDetails(SpaceX spaceX) {
        FragmentManager manager = getFragmentManager();
        if (manager != null) {
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.main_container, LaunchDetailsFragment.newInstance(spaceX), LaunchDetailsFragment.class.getName())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public Observable<SpaceX> launchClicked() {
        return launchesAdapter.getItemClickedObservable();
    }
}
