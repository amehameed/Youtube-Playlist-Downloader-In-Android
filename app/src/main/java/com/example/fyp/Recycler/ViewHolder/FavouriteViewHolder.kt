package com.example.fyp.Recycler.ViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.downloader.database.DownloadModel
import com.example.fyp.Database.Database
import com.example.fyp.Model.VideoModel
import com.example.fyp.R

class FavouriteViewHolder(context: Context, inflater: LayoutInflater, parent: ViewGroup) :
RecyclerView.ViewHolder(inflater.inflate(R.layout.favourite_item_view, parent, false)) {
    private var mTitleView: TextView? = null
    private var mVideoSize: TextView? = null
    private var mProgressLayout: LinearLayout? = null
    private var mImageButton: ImageView? = null
    private var mDownloadText: TextView? = null
    private lateinit var ParentContext:Context


    init {
        mTitleView = itemView.findViewById(R.id.item_title)
        mVideoSize = itemView.findViewById(R.id.item_downloadsize)
        mProgressLayout = itemView.findViewById(R.id.progresslayout)
        mImageButton = itemView.findViewById(R.id.favbtn)
        ParentContext=context
    }

    fun bind(download: VideoModel) {
        mTitleView?.text = download.getName()
        mVideoSize?.text = download.getSize()
        mImageButton?.setOnClickListener(View.OnClickListener {

            Database.RemoveFav(download)


//            mProgressLayout?.visibility ?:  View.VISIBLE

        })
    }

}