package com.example.fyp.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.widget.Toast;

import com.example.fyp.Model.VideoModel;
import com.example.fyp.R;
import com.example.fyp.Recycler.Adapter.DownloaderAdapter;

import java.util.ArrayList;

public class Database {
    public static ArrayList<VideoModel>  videoList= new ArrayList<VideoModel>();
    public static ArrayList<String>  downloadingList= new ArrayList<>();
    public static ArrayList<String>  favlist= new ArrayList<>();
    private static int queue=0;
    public static ArrayList<VideoModel> getFavList()
    {
        ArrayList<VideoModel> favList=new ArrayList<>();
        for(int i=0;i<favlist.size();i++)
        {
            for(int j=0;j<videoList.size();j++)
            {
                if(favlist.get(i).equals(videoList.get(j).getId()))
                {
                    favList.add(videoList.get(j));
                }

            }
        }
        return favList;
    }
    public static ArrayList<VideoModel> getCompleted()
    {
        ArrayList<VideoModel> favList=new ArrayList<>();
        for(int i=0;i<favlist.size();i++)
        {
            for(int j=0;j<videoList.size();j++)
            {
                if(favlist.get(i).equals(videoList.get(j).getId())&& videoList.get(j).isCompleted()==true)
                {
                    favList.add(videoList.get(j));
                }

            }
        }
        return favList;
    }
    public static boolean AddDownloadQue(VideoModel v, Context context)
    {
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if(queue<Integer.parseInt(prefs.getString("max_download_tasks","3")))
        {
            videoList.add(v);
            downloadingList.add(v.getId());
            queue+=1;
            Toast.makeText(context,"Video Added in a Queue.",Toast.LENGTH_LONG);
            return true;
        }
        else
        {

            Toast.makeText(context,"Video Failed to added in a Queue.",Toast.LENGTH_LONG);
            return false;
        }

    }

    public static boolean StartDownloadQue(VideoModel v,int id, Context context)
    {
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if(queue<Integer.parseInt(prefs.getString("max_download_tasks","3")))
        {
            v.setDownloadid(id);
            videoList.add(v);
            downloadingList.add(v.getId());
            queue+=1;
            Toast.makeText(context,"Video Added in a Queue.",Toast.LENGTH_LONG);
            return true;
        }
        else
        {

            Toast.makeText(context,"Video Failed to added in a Queue.",Toast.LENGTH_LONG);
            return false;
        }

    }
    public static ArrayList<VideoModel> downloadList()
    {
        ArrayList<VideoModel> downloadList=new ArrayList<>();
        for(int i=0;i<downloadingList.size();i++)
        {
            for(int j=0;j<videoList.size();j++)
            {
                if(downloadingList.get(i).equals(videoList.get(j).getId()) && videoList.get(j).isCompleted()==false)
                {
                    downloadList.add(videoList.get(j));
                }
            }

        }
        return downloadList;
    }
    public static ArrayList<VideoModel> downloadList(int tag)
    {
        ArrayList<VideoModel> downloadList=new ArrayList<>();
        for(int i=0;i<downloadingList.size();i++)
        {
            for(int j=0;j<videoList.size();j++)
            {
                if(downloadingList.get(i).equals(videoList.get(j).getId()) && videoList.get(j).isCompleted()==false && videoList.get(j).getStatuscode()==tag)
                {
                    downloadList.add(videoList.get(j));
                }
            }

        }
        return downloadList;
    }
    public static void GenerateDummyData()
    {
//        videoList.add(new VideoModel("123","Testing",false, "https://i.ytimg.com/vi/aS__9RbCyHg/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBkmJmmk3oyScR57vyWPl_KaznIqA",0));
//
//        videoList.add(new VideoModel("1232","Testing2",false, "https://i.ytimg.com/vi/aS__9RbCyHg/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBkmJmmk3oyScR57vyWPl_KaznIqA",0));
//        downloadingList.add("123");
//        favlist.add("1232");
    }
    public static ArrayList<VideoModel> getVideoList()
    {
        return videoList;
    }
    public static void RemoveFav(VideoModel v)
    {
        for(int i=0;i<favlist.size();i++)
        {
            if(v.getId()==favlist.get(i))
            {
                favlist.remove(i);
            }
        }

    }
    public static VideoModel getVideo(String id)
    {
        for(int i=videoList.size()-1;i>=0;i--)
        {
            if(videoList.get(i).getId().equals(id))
            {
              return videoList.get(i);
            }
        }
        return null;
    }

    public static class MainSettingsFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);
//            bindSummaryValue(findPreference("enable_wifi"));
            bindSummaryValue(findPreference("max_download_tasks"));
            //  bindSummaryValue(findPreference("fast_download"));
            bindSummaryValue(findPreference("online_video_quality"));
            bindSummaryValue(findPreference("play_videos_with"));

        }
    }
    private static void bindSummaryValue(Preference preference)
    {

        preference.setOnPreferenceChangeListener(listener);
        listener.onPreferenceChange(preference, PreferenceManager.
                getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(),""));
    }
    private static  Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            String stringvalue = newValue.toString();
            if(preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringvalue);
                preference.setSummary(index > 0 ? listPreference.getEntries()[index] : null);
            }

            else if(preference instanceof SwitchPreference)
            {
                preference.setSummary(stringvalue);

            }
            else if (preference instanceof CheckBoxPreference)
            {
                preference.setSummary(stringvalue);

            }
            return true;
        }
    };


}
