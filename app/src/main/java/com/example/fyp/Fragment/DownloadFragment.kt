package com.example.fyp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.example.fyp.Database.Database
import com.example.fyp.R
import com.example.fyp.Recycler.Adapter.DownloaderAdapter
import kotlinx.android.synthetic.main.fragment_download.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [DownloadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DownloadFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var view = null
    lateinit var adapter: DownloaderAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
    inflater.inflate(R.layout.fragment_download, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build()
        PRDownloader.initialize(context, config)
        if(Database.getVideoList().size==0)
        {
            list_recycler_view.visibility=View.GONE
        }
        // RecyclerView node initialized here
        list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = DownloaderAdapter(context, Database.getVideoList())


        }
    }
    public fun UpdateUI()
    {
        adapter.notifyDataSetChanged()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    companion object {
        fun newInstance(): DownloadFragment = DownloadFragment()
    }

}
