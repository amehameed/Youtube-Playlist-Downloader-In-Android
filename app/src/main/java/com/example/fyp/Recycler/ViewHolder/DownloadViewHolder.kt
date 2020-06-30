package com.example.fyp.Recycler.ViewHolder

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.Status
import com.example.fyp.Fragment.DownloadFragment
import com.example.fyp.Model.VideoModel
import com.example.fyp.R
import com.example.fyp.Util.Downloader
import com.example.fyp.Utils
import java.io.File

class DownloadViewHolder(context: Context, inflater: LayoutInflater, parent: ViewGroup) :
RecyclerView.ViewHolder(inflater.inflate(R.layout.download_item_view, parent, false)) {
    private var mTitleView: TextView? = null
    private var mVideoSize: TextView? = null
    private var mProgressLayout: LinearLayout? = null
    private var mprogress: ProgressBar?=null
    private var mImageButton: ImageView? = null
    private var mPauseButton: ImageView? = null
    private var mCloseButton: ImageView? = null
    private var mItemImage: ImageView? = null
    private var mDownloadText: TextView? = null
    private lateinit var ParentContext:Context
    private lateinit var fragment:DownloadFragment


    init {
        mTitleView = itemView.findViewById(R.id.item_title)
        mItemImage = itemView.findViewById(R.id.item_image)
        mVideoSize = itemView.findViewById(R.id.item_downloadsize)
        mProgressLayout = itemView.findViewById(R.id.progresslayout)
        mImageButton = itemView.findViewById(R.id.downloadbtn)
        mprogress = itemView.findViewById(R.id.progressBar1)
        mPauseButton = itemView.findViewById(R.id.pausebtn)
        mCloseButton = itemView.findViewById(R.id.closebtn)
        mDownloadText = itemView.findViewById(R.id.item_downloaded)
        ParentContext=context
        this.fragment =fragment
    }

    fun bind(download: VideoModel) {
        mTitleView?.text = download.getName()
        mVideoSize?.text = download.getSize()
        if(download.statuscode==0)
        {
            mItemImage!!.background=ContextCompat.getDrawable(ParentContext, R.drawable.img_youtube);
        }
        else if (download.statuscode==1)
        {
            mItemImage!!.background=ContextCompat.getDrawable(ParentContext, R.drawable.img_facebook);
        }
        else if(download.statuscode==2)
        {
            mItemImage!!.background=ContextCompat.getDrawable(ParentContext, R.drawable.img_instagram);
        }
//        mImageButton?.setOnClickListener(View.OnClickListener {
//                mProgressLayout?.visibility = if (mProgressLayout?.visibility == View.VISIBLE){
//                    View.INVISIBLE
//                } else{
//                    View.VISIBLE
//                }
//
////            mProgressLayout?.visibility ?:  View.VISIBLE
//            if(mProgressLayout?.visibility==View.VISIBLE)
//            {
//                mImageButton!!.setTag("Pasue")
//                mImageButton!!.visibility=View.GONE
////                mImageButton!!.background= ContextCompat.getDrawable(ParentContext, R.mipmap.ic_pause)
//
//            }
//          else
//            {
//                mImageButton!!.setTag("Downloading")
//                mImageButton!!.background= ContextCompat.getDrawable(ParentContext, R.mipmap.ic_newdownload)
//            }
//        })

        mImageButton?.setOnClickListener(View.OnClickListener {
//            int currentID=Database.getDownloadID(download);
            mProgressLayout?.visibility = if (mProgressLayout?.visibility == View.VISIBLE){View.INVISIBLE    } else{ View.VISIBLE     }
            mImageButton?.visibility=View.GONE
            mPauseButton?.visibility=View.VISIBLE
            if(download.downloadid!=0)
            {

                if (Status.RUNNING == PRDownloader.getStatus(download.downloadid)) {
                    PRDownloader.pause(download.downloadid)
                    return@OnClickListener
                }
//            buttonOne.setEnabled(false)
//            mprogress?.setIndeterminate(true)

                if (Status.PAUSED == PRDownloader.getStatus(download.downloadid)) {
                    PRDownloader.resume(download.downloadid)
                    return@OnClickListener
                }

            }
            else{
                val storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/YoutubeVideos"
                val f = File(storagePath)
                if (!f.exists()) {
                    f.mkdir()
                }
                //                String storagepATH= Utils.getRootDirPath(getContext());
                val dirPath = Utils.getRootDirPath(ParentContext)
                download.downloadid = PRDownloader.download(download.url, dirPath, "youtube video")
                        .build()
                        .setOnStartOrResumeListener {
                            // buttonOne.setEnabled(true)
                            // buttonOne.setText(R.string.pause)
                            // buttonCancelOne.setEnabled(true)

                            val request1= DownloadManager.Request(Uri.parse(download.url))
                            request1.setTitle(download.name)
                            request1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, download.name + ".mp4")
                            request1.allowScanningByMediaScanner()
                            request1.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                            var newdownloadid = Downloader.downloadManager.enqueue(request1)
                        }
                        .setOnPauseListener {
//                        buttonOne.setText("resume")
                            Toast.makeText(ParentContext, "Video is Paused Now.", Toast.LENGTH_LONG)
                        }
                        .setOnCancelListener {
//                        buttonOne.setText("start")
//                        buttonCancelOne.setEnabled(false)
//                            context.UpdateUI
                            mprogress?.setProgress(0)
                            download.downloadid = 0
                        }
                        .setOnProgressListener { progress ->
                            val progressPercent = progress.currentBytes * 100 / progress.totalBytes
                            mprogress?.setProgress(progressPercent.toInt())
                            mVideoSize?.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes))
                            mprogress?.setIndeterminate(false)
                        }
                        .start(object : OnDownloadListener {
                            override fun onDownloadComplete() {
//                            buttonOne.setEnabled(false)
//                            buttonCancelOne.setEnabled(false)
//                            buttonOne.setText("completed")
                                download.isCompleted=true
                                fragment.UpdateUI()

                            }

                            override fun onError(error: Error) {
//                            buttonOne.setText("start")
                                Toast.makeText(ParentContext, "some_error_occurred" + " " + "1", Toast.LENGTH_SHORT).show()
                                mVideoSize?.setText("")
                                mprogress?.setProgress(0)
                                download.downloadid = 0
//                            buttonCancelOne.setEnabled(false)
//                            mprogress?.setIndeterminate(false)
//                            buttonOne.setEnabled(true)
                            }
                        })
            }

        })
        mPauseButton?.setOnClickListener(View.OnClickListener {
            PRDownloader.pause(download.downloadid)
            mImageButton?.visibility=View.VISIBLE
            mPauseButton?.visibility=View.GONE
        })

        mCloseButton?.setOnClickListener(View.OnClickListener {
            PRDownloader.cancel(download.downloadid)
            Downloader.downloadManager.remove(download.downloadid.toLong())
        })
    }

}