package com.example.fyp.Recycler.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp.Fragment.DownloadFragment
import com.example.fyp.Model.VideoModel
import com.example.fyp.Recycler.ViewHolder.DownloadViewHolder


class DownloaderAdapter(context: Context, private val list: List<VideoModel>)
    : RecyclerView.Adapter<DownloadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DownloadViewHolder(parent.context,inflater, parent)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        val data: VideoModel = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

}