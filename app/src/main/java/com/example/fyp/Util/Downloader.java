package com.example.fyp.Util;

import android.app.DownloadManager;
import android.content.Context;

public class Downloader {
    public static DownloadManager downloadManager;
    public static DownloadManager.Request request;
    public static void initDownloading(Context context)
    {
        downloadManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }
    public void addRequest(DownloadManager.Request request)
    {

    }

}
