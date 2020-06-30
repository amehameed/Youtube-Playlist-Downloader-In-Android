package com.example.fyp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyp.Database.Database

import com.example.fyp.R
import com.example.fyp.Recycler.Adapter.DownloaderAdapter
import com.example.fyp.Recycler.Adapter.FavouriteAdapter
import kotlinx.android.synthetic.main.fragment_download.*


class FavouriteFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(Database.getFavList().size==0)
        {
            list_recycler_view.visibility=View.GONE
        }
        // RecyclerView node initialized here
        list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = FavouriteAdapter(context, Database.getFavList())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    companion object {
        fun newInstance(): FavouriteFragment = FavouriteFragment()
    }

}
