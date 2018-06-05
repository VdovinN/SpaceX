package com.vdovin.spacex.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vdovin.spacex.R;

public class LaunchesFragment extends Fragment {

    public static LaunchesFragment newInstance() {

        Bundle args = new Bundle();

        LaunchesFragment fragment = new LaunchesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.launches_layout, container, false);
    }
}
