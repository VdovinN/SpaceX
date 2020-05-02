package com.vdovin.spacex.screen.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vdovin.spacex.R
import com.vdovin.spacex.database.model.SpaceX
import com.vdovin.spacex.util.Constants.YOUTUBE_IMG_BASE_URL
import com.vdovin.spacex.util.Constants.YOUTUBE_IMG_END_URL
import kotlinx.android.synthetic.main.launch_item_layout.view.*
import java.util.*


class LaunchesAdapter(private val context: Context?, private var spaceList: List<SpaceX>) : RecyclerView.Adapter<LaunchesAdapter.ViewHolder?>() {
    /*var itemClicked: PublishSubject<SpaceX> = PublishSubject.create()
    val itemClickedObservable: Observable<SpaceX>
        get() = itemClicked*/

    var onItemClick: ((SpaceX) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.launch_item_layout, parent, false), parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val space = spaceList[position]
        holder.bind(space)
    }

    override fun getItemCount() = spaceList.size

    fun swap(spaces: List<SpaceX>) {
        spaceList = ArrayList(spaces)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, private val parent: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        fun bind(spaceX: SpaceX) {
            itemView.launch_name_text_view.text = spaceX.missionName
            itemView.launch_date_text_view.text = spaceX.launchDate
            val path = "$YOUTUBE_IMG_BASE_URL${spaceX.youtubeVideoId}$YOUTUBE_IMG_END_URL"
            Picasso.get().load(path).into(itemView.launch_image_view)
            itemView.setOnClickListener {
                onItemClick?.invoke(spaceList[adapterPosition])
            }
            /*RxView.clicks(itemView)
                    .throttleFirst(Constants.TIME_BETWEEN_CLICKS, TimeUnit.MILLISECONDS)
                    .takeUntil(RxView.detaches(parent))
                    .map({ `object` -> spaceX })
                    .subscribe(itemClicked)*/
        }
    }

}