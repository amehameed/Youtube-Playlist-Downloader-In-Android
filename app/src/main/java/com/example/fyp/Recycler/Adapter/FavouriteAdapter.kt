package com.example.fyp.Recycler.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp.Model.VideoModel
import com.example.fyp.Recycler.ViewHolder.DownloadViewHolder
import com.example.fyp.Recycler.ViewHolder.FavouriteViewHolder


class FavouriteAdapter(context: Context, private val list: List<VideoModel>)
    : RecyclerView.Adapter<FavouriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavouriteViewHolder(parent.context,inflater, parent)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val data: VideoModel = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

}