package com.vdovin.spacex.screen.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vdovin.spacex.R;
import com.vdovin.spacex.api.model.Space;
import com.vdovin.spacex.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaunchesAdapter extends RecyclerView.Adapter<LaunchesAdapter.ViewHolder> {

    private Context context;
    private List<Space> spaceList;

    public LaunchesAdapter(Context context, List<Space> spaceList) {
        this.context = context;
        this.spaceList = spaceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.launch_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Space space = spaceList.get(position);
        holder.bind(space);
    }

    @Override
    public int getItemCount() {
        return spaceList.size();
    }

    public void swap(List<Space> spaces) {
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Space space) {
            launchNameTextView.setText(space.getMissionName());
            launchDateTextView.setText(DateUtil.convertTimestampToFormattedDate(space.getLaunchDateUnix()));

        }
    }
}
