package com.vdovin.spacex.screen.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;
import com.vdovin.spacex.R;
import com.vdovin.spacex.database.model.SpaceX;
import com.vdovin.spacex.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class LaunchesAdapter extends RecyclerView.Adapter<LaunchesAdapter.ViewHolder> {

    private Context context;
    private List<SpaceX> spaceList;

    PublishSubject<SpaceX> itemClicked = PublishSubject.create();

    public LaunchesAdapter(Context context, List<SpaceX> spaceList) {
        this.context = context;
        this.spaceList = spaceList;
    }

    public Observable<SpaceX> getItemClickedObservable() {
        return itemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.launch_item_layout, parent, false), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpaceX space = spaceList.get(position);
        holder.bind(space);
    }

    @Override
    public int getItemCount() {
        return spaceList.size();
    }

    public void swap(List<SpaceX> spaces) {
        spaceList = new ArrayList<>(spaces);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.launch_name_text_view)
        TextView launchNameTextView;
        @BindView(R.id.launch_date_text_view)
        TextView launchDateTextView;
        @BindView(R.id.launch_image_view)
        ImageView launchImageView;

        private ViewGroup parent;

        public ViewHolder(View itemView, ViewGroup parent) {
            super(itemView);
            this.parent = parent;
            ButterKnife.bind(this, itemView);
        }

        public void bind(SpaceX spaceX) {
            launchNameTextView.setText(spaceX.getMissionName());
            launchDateTextView.setText(spaceX.getLaunchDate());

            String path = Constants.YOUTUBE_IMG_BASE_URL + spaceX.getYoutubeVideoId() + Constants.YOUTUBE_IMG_END_URL;
            Picasso.get().load(path).into(launchImageView);
            RxView.clicks(itemView)
                    .throttleFirst(Constants.TIME_BETWEEN_CLICKS, TimeUnit.MILLISECONDS)
                    .takeUntil(RxView.detaches(parent))
                    .map(object -> spaceX)
                    .subscribe(itemClicked);
        }
    }
}
