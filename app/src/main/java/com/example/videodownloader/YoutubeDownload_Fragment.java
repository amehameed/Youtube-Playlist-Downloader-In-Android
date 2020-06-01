package com.example.videodownloader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;


public class YoutubeDownload_Fragment extends Fragment {
    private Cursor videocursor;
    private static View view;
    private static ListView_Adapter adapter;
    private static ListView listView;
    public File[] files;
    //Action Mode for toolbar
    private ActionMode mActionMode;
    private static ArrayList<Item_Model> item_models;

    public YoutubeDownload_Fragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.youtube_download_fragment, container, false);
        populateListView();
        implementListViewClickListeners();
        return view;
    }


    //Populate ListView with dummy data
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void populateListView() {
        //final String destination = Environment.getExternalStorageDirectory()+"/Download/YouTube-Downloader/";
        String path = Environment.getExternalStorageDirectory().toString()+"/Download/Youtube-Downloader/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        listView =  view.findViewById(R.id.list_view);
        item_models = new ArrayList<>();
        if(directory.isDirectory()){
            files = directory.listFiles();
            Log.d("Files", "Size: "+ files.length);
            for (int i = 0; i < files.length; i++)
            {
                Log.d("Files", "FileName:" + files[i].getName());
            }

            for (int i = 0; i < files.length; i++)
                item_models.add(new Item_Model("Title: " + files[i].getName(), " URI:"+ files[i].getAbsolutePath()));

        }



        adapter = new ListView_Adapter(getActivity(), item_models);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    //Implement item click and long click over listview
    private void implementListViewClickListeners() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity().getApplication(),videoView.class);
                i.putExtra("url",files[position].getAbsolutePath().toString());
                startActivity(i);
                Toast.makeText(getActivity().getApplicationContext(),files[position].getAbsolutePath(), Toast.LENGTH_SHORT).show();
                //If ActionMode not null select item
                if (mActionMode != null)
                    onListItemSelect(position);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Select item on long click

                onListItemSelect(position);
                return true;
            }
        });
    }

    //List item select method
    private void onListItemSelect(int position) {


        adapter.toggleSelection(position);//Toggle the selection
        boolean hasCheckedItems = adapter.getSelectedCount() > 0;//Check if any items are already selected or not

        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(new Toolbar_ActionMode_Callback(getActivity(), null, adapter, item_models, true));
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();

        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(adapter
                    .getSelectedCount()) + " selected");


    }

    //Set action mode null after use
    public void setNullToActionMode() {
        if (mActionMode != null)
            mActionMode = null;
    }


    //Delete selected rows
    public void deleteRows() {
        SparseBooleanArray selected = adapter
                .getSelectedIds();//Get selected ids

        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //If current id is selected remove the item via key
                item_models.remove(selected.keyAt(i));
                adapter.notifyDataSetChanged();//notify adapter

            }
        }
        Toast.makeText(getActivity(), selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();//Show Toast
        mActionMode.finish();//Finish action mode after use

    }
}

class Video2 {
    public final Uri uri;
    public final String name;
    public final int size;

    public Video2(Uri uri, String name, int size) {
        this.uri = uri;
        this.name = name;
        this.size = size;
    }


}