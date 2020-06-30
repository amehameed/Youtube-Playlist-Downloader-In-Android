package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.fyp.Activity.HomeActivity;
import com.example.fyp.Activity.HomeActivity2;
import com.example.fyp.Database.Database;
import com.example.fyp.Util.Downloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import is.arontibo.library.ElasticDownloadView;

public class SplashScreen extends AppCompatActivity {
    int SPLASH_TIME = 5000;
    ElasticDownloadView mElasticDownloadView;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);
        mElasticDownloadView=findViewById(R.id.elastic_download_view);
        mElasticDownloadView.startIntro();
        Database.GenerateDummyData();
        Downloader.initDownloading(getApplicationContext());
//        Content c=new Content();
//        c.execute();
        mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i=i+2) {
                    final int currentProgressCount = i;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    i=i+10;
//                  Update the value background thread to UI thread
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mElasticDownloadView.setProgress(currentProgressCount);
                        }
                    });
                }

                Intent mySuperIntent = new Intent(getApplicationContext(), HomeActivity2.class);
                mySuperIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mySuperIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mySuperIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(mySuperIntent);
            }
        }).start();
    }
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://firebase.google.com/";
                //Connect to the website
//                Document document = Jsoup.connect(url).get();
//
//                //Get the logo source of the website
//                Element img = document.select("img").first();
//                // Locate the src attribute
//                String imgSrc = img.absUrl("src");
//                // Download image from URL
//                InputStream input = new java.net.URL(imgSrc).openStream();
//                // Decode Bitmap
//                Bitmap bitmap = BitmapFactory.decodeStream(input);

                //Get the title of the website
//               String title = document.title();
                Document  document = Jsoup.connect("https://www.youtube.com/watch?v=wMuYiLby3-s&list=PLoSWVnSA9vG_4nlMUZHUyrjLyZ0Qcp5VL&index=4")
                        .userAgent("Mozilla/5.0 (Windows NT 6.1)")
//                        ..userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.facebook.com")
                        .get();
                Log.e("Jsoup",document.title());
                Log.e("Jsoup", String.valueOf(document.select("span").select("link").get(2).absUrl("href")));
                Elements links = document.select("a[href]");
                ArrayList<String> list=new ArrayList<>();
                int max=0;
                for (Element link : links) {
                    // get the value from href attribute
                    System.out.println("\nLink : " + link.attr("href"));
//                    System.out.println("Text : " + link.text());

                    if(link.attr("href").contains("index"))
                    {
                        String slink=link.attr("href");
                        Log.e("Index","\nLink : " +link.attr("href"));
                        System.out.println("\nLink index : " +link.attr("href").indexOf("index")+6);
                        System.out.println("\nLink : " +(link.attr("href").length()-1));
                        Log.e("Index",(link.attr("href").indexOf("index")+6)+"");
                        Log.e("Index",(link.attr("href").length()-1)+"");

                        String s1=link.attr("href").substring(link.attr("href").indexOf("index")+6,link.attr("href").length()-1);
                        if(max<Integer.parseInt(s1))
                        {
                            list.add(link.attr("href"));
                            max=Integer.parseInt(link.attr("href").substring(link.attr("href").indexOf("index")+6,(link.attr("href").length()-1)));
                        }

                    }

                }
                Log.e("Link","Exit");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

//            imageView.setImageBitmap(bitmap);
//            textView.setText(title);
//            progressDialog.dismiss();
        }
    }
}
