package com.example.fyp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
//import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.bumptech.glide.Glide;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.example.fyp.Database.Database;
import com.example.fyp.Model.VideoModel;
import com.example.fyp.Util.Downloader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.RequiresApi;
//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

public class WebViewActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ImageView imageView,videoImage;
    EditText linkText;
    int len1;
    String selectedDownloadedurl="";
    String ylink;
    ArrayList<String> downURL=new ArrayList<>();
    ArrayList<String> vidTitle=new ArrayList<>();
         TextView tttxtfirst,textSecond,txtThird,txtFour,ttxtFive,txtSix,txtDOWNLOAD,txtPlay,txtAdd;
    LinearLayout downLayout;
    SparseArray<YtFile> videoFilesData;
    WebView webView;
    TextView videoTitletb;
    int progress;
    LinearLayout linearLayout,fablayout,videoLayout,playlistLayout,playlist_found;
    ListView playlistview;
    FloatingActionButton fab ;
    String URLl="";
    int length;
    Bundle extras=null;
    MenuItem mi;
    private static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 1;
   String flink,plink;
    private DownloadManager downloadManager;
    private long downloadReference;
    long downloadID;
    Button download,addinQueue;
    ImageView close,showPlaylist;
    DownloadManager downloadManager1;
    DownloadManager.Request request1;
    String vId;
    String Html;
    String imageUrl;
  //  String id;
  //  DataBaseHistoryTable dataBaseHistoryTable;
   // private DatabaseReference mDatabase;
//  ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.ListView,StringArray);
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        fab = findViewById(R.id.fab);
//        txtAdd=findViewById(R.id.btnAddVideo);
//        txtDOWNLOAD=findViewById(R.id.btnDownloadVideoBtn);
//        txtPlay=findViewById(R.id.btnDownloadVideoBtn);
//        txtFour=findViewById(R.id.txtFourth);
//        tttxtfirst=findViewById(R.id.txtfirst);
//        textSecond=findViewById(R.id.txtSecond);
//        txtThird=findViewById(R.id.ttxtTThired);
//        ttxtFive=findViewById(R.id.txtFift);
//        txtSix=findViewById(R.id.txtSixth);
        downLayout=findViewById(R.id.formatsLayout);
//        try {
//            Document document = Jsoup.connect("https://m.youtube.com/watch?pbjreload=101&v=tQ4m4zD7BBA&list=PLoSWVnSA9vG_4nlMUZHUyrjLyZ0Qcp5VL").get();
//            Log.e("Jsoup",document.title());
//            Log.e("Jsoup", String.valueOf(document.select("span")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        progressBar = findViewById(R.id.my_progress_bar);
        imageView = findViewById(R.id.my_img_view);
        videoImage = findViewById(R.id.videoImage);
        webView = findViewById(R.id.web_view);
        PRDownloader.initialize(getApplicationContext());
        linearLayout = findViewById(R.id.my_linear_layout);
        playlist_found = findViewById(R.id.playlist_foundlayout);
        download=findViewById(R.id.download);
        addinQueue=findViewById(R.id.downloadlater);
        close=findViewById(R.id.closebtn);
        showPlaylist=findViewById(R.id.showlist);
        videoLayout= findViewById(R.id.videoLayout);
        playlistLayout= findViewById(R.id.playlist_Layout);
        playlistview=findViewById(R.id.playlist_listview);
//        swipeRefreshLayout = findViewById(R.id.my_swipe_refresh);
        linkText = findViewById(R.id.txtLink);
        fablayout=findViewById(R.id.fabLAyout);
        videoTitletb=findViewById(R.id.videoTitle);
//        imgFirst=findViewById(R.id.imgfIrst);
//        imgSecond=findViewById(R.id.imSEcond);
//        imThird=findViewById(R.id.imgThhird);
//        imgFourth=findViewById(R.id.imgFourth);
//        imgFive=findViewById(R.id.imgFifth);
//        imgSixth=findViewById(R.id.imgSixth);
        addinQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isque=Database.AddDownloadQue(new VideoModel(flink,videoTitletb.getText().toString(),false,selectedDownloadedurl,0),getApplicationContext());
                downLayout.setVisibility(View.GONE);
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/YoutubeVideos";
//                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getApplicationContext());
//// ...Irrelevant code for customizing the buttons and title
//                AlertDialog alertDialog = dialogBuilder.create();
////                LayoutInflater inflater = getApplication().getLayoutInflater();
//                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//                alertDialog.setContentView(inflater.inflate(R.layout.custom_dailog, null));
//                Button yes=(Button)findViewById(R.id.btn_yes);
//                Button no=(Button)findViewById(R.id.btn_no);
//                yes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        VideoModel video=Database.getVideo(vId);
//                        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(video.getUrl()));
//                        req.setTitle(videoTitletb.getText());
//                        req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,  video.getName()+".mp4");
//                        req.allowScanningByMediaScanner();
//                        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
//                        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                        DownloadManager dm = Downloader.downloadManager;
//                        dm.enqueue(req);
//                        downLayout.setVisibility(View.GONE);
//                        Toast.makeText(getApplicationContext(),"Video started Downloading",Toast.LENGTH_LONG);
//                    }
//                });
//                no.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getApplicationContext(),"Video Added in the Queue",Toast.LENGTH_LONG);
//                    }
//                });
//                alertDialog.show();

                VideoModel video=Database.getVideo(vId);
                DownloadManager.Request req = new DownloadManager.Request(Uri.parse(video.getUrl()));
                req.setTitle(videoTitletb.getText());
                req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,  video.getName()+".mp4");
                req.allowScanningByMediaScanner();
                req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager dm =  downloadManager= (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                dm.enqueue(req);
                downLayout.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Video started Downloading",Toast.LENGTH_LONG);

//                Downloader downloader = Downloader.getInstance(getApplicationContext())
//                        .setUrl(selectedDownloadedurl)
//                        .setAllowedOverRoaming(true)
//                        .setVisibleInDownloadsUi(true)
//                        .setDestinationDir(storagePath, videoTitletb.getText().toString())
//                        .setNotificationTitle(videoTitletb.getText().toString())
//                        .setNotificationVisibility(1)
//                        .setKeptAllDownload(true);
//                downloader.start();

                //sto

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLayout.setVisibility(View.GONE);
            }
        });
        //code to share video from youtube and play and download in app

        extras = getIntent().getExtras();
        Bundle extras1 = getIntent().getExtras();
        if(extras1!=null) {
            String link = extras1.getString(Intent.EXTRA_TEXT);
            webView.loadUrl("https://www.youtube.com");
//            webView.loadUrl("https://m.youtube.com/watch?pbjreload=101&v=tQ4m4zD7BBA&list=PLoSWVnSA9vG_4nlMUZHUyrjLyZ0Qcp5VL");
            ylink=link;
            webView.loadUrl(ylink);
//            Toast.makeText(this, link, Toast.LENGTH_SHORT).show();
//
        }
        URLl = extras.getString("url");
//        URLl = "https://m.youtube.com/watch?pbjreload=101&v=tQ4m4zD7BBA&list=PLoSWVnSA9vG_4nlMUZHUyrjLyZ0Qcp5VL";
        addHistory(URLl);
        webView.loadUrl(URLl);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.addJavascriptInterface(this, "FBDownloader");
        webView.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public void onPageCommitVisible(WebView view, String url) {
                                         super.onPageCommitVisible(view, url);
                                         


                                     }


                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         linearLayout.setVisibility(View.VISIBLE);
                                         super.onPageStarted(view, url, favicon);
//                                         Toast.makeText(WebViewActivity.this, url, Toast.LENGTH_SHORT).show();


                                     }

                                     @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         linearLayout.setVisibility(View.GONE);
                                         super.onPageFinished(view, url);
                                         webView.evaluateJavascript(
                                                 "(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
                                                 new ValueCallback<String>() {
                                                     @Override
                                                     public void onReceiveValue(String html) {
                                                         Log.d("HTML", html);
                                                         // code here
                                                         Html=html;
                                                     }
                                                 });

                                     }


                                 });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                URLl=webView.getUrl();
                ylink = webView.getUrl();
               //to show download button when vido is played
               if(ylink.contains("/watch?v=") || ylink.contains("&v=") || ylink.contains("?v=")){
                   fablayout.setVisibility(View.VISIBLE);
                   getVideoImage videoImage=new getVideoImage();
                   videoImage.execute();
               }
               else
               {
                   fablayout.setVisibility(View.GONE);
               }
               //copy link in clipboard
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(ylink);
                } else {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("text label", ylink);
                    clipboard.setPrimaryClip(clip);
                }


            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);


                getSupportActionBar().setTitle(title);


            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                imageView.setImageBitmap(icon);
//                fablayout.setVisibility(View.VISIBLE);


            }
        });



        progressBar.setMax(100);
        // initialize downloader for downloading
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadVideo1(txtDOWNLOAD);

            }
        });
//        txtDOWNLOAD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                downloadVideo1(txtDOWNLOAD);
//                downLayout.setVisibility(View.GONE);
//            }
//        });
//        tttxtfirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                imgFirst.setImageResource(R.drawable.ic_check_circle_black_24dp);
//                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//            }
//        });
//        textSecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                imgSecond.setImageResource(R.drawable.ic_check_circle_black_24dp);
//                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//            }
//        });
//        txtThird.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imThird.setImageResource(R.drawable.ic_check_circle_black_24dp);
//                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//            }
//        });
//        txtFour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imgFourth.setImageResource(R.drawable.ic_check_circle_black_24dp);
//                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//            }
//        });
//        ttxtFive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imgFive.setImageResource(R.drawable.ic_check_circle_black_24dp);
//                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//            }
//        });
//        txtSix.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imgSixth.setImageResource(R.drawable.ic_check_circle_black_24dp);
//                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
//            }
//        });
    }
    private class getVideoImage extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

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
                Document  document = Jsoup.connect(URLl)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1)")
//                        ..userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.facebook.com")
                        .get();
                try {
                    imageUrl=String.valueOf(document.select("span").select("link").get(2).absUrl("href"));
                }
                catch(Exception e)
                {

                }

//                Log.e("Jsoup",document.title());
//                Log.e("Jsoup", String.valueOf(document.select("span").select("link").get(2).absUrl("href")));
//                Elements links = document.select("a[href]");
//                ArrayList<String> list=new ArrayList<>();
//                int max=0;
//                for (Element link : links) {
//                    // get the value from href attribute
//                    System.out.println("\nLink : " + link.attr("href"));
////                    System.out.println("Text : " + link.text());
//
//                    if(link.attr("href").contains("index"))
//                    {
//                        String slink=link.attr("href");
//                        Log.e("Index","\nLink : " +link.attr("href"));
//                        System.out.println("\nLink index : " +link.attr("href").indexOf("index")+6);
//                        System.out.println("\nLink : " +(link.attr("href").length()-1));
//                        Log.e("Index",(link.attr("href").indexOf("index")+6)+"");
//                        Log.e("Index",(link.attr("href").length()-1)+"");
//
//                        String s1=link.attr("href").substring(link.attr("href").indexOf("index")+6,link.attr("href").length()-1);
//                        if(max<Integer.parseInt(s1))
//                        {
//                            list.add(link.attr("href"));
//                            max=Integer.parseInt(link.attr("href").substring(link.attr("href").indexOf("index")+6,(link.attr("href").length()-1)));
//                        }
//
//                    }
//
//                }
//                Log.e("Link","Exit");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e("Image",imageUrl);
            Glide.with(getApplicationContext()).load(imageUrl).into(videoImage);
//            imageView.setImageBitmap(bitmap);
//            textView.setText(title);
//            progressDialog.dismiss();
        }
    }
    private class Content extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.show();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> list=new ArrayList<>();
            try {
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
                Document  document = Jsoup.connect(URLl)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1)")
//                        ..userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.facebook.com")
                        .get();
                Log.e("Jsoup",document.title());
                Log.e("Jsoup", String.valueOf(document.select("span").select("link").get(2).absUrl("href")));
                Elements links = document.select("a[href]");

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

//                        String s1=link.attr("href").substring(link.attr("href").indexOf("index")+6,link.attr("href").length()-1);
//                        if(max<Integer.parseInt(s1))
                        {
                            list.add(link.attr("href"));
//                            max=Integer.parseInt(link.attr("href").substring(link.attr("href").indexOf("index")+6,(link.attr("href").length()-1)));
                        }

                    }

                }
                Log.e("Link","Exit");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }


    }
        public void downloadVideo1 (View view){
        //get the download URL



            if(ylink.contains("&list"))
            {
                Toast.makeText(this,"Its Playlist",Toast.LENGTH_SHORT);
                playlist_found.setVisibility(View.VISIBLE);
                vId= ylink.trim().substring(ylink.trim().indexOf("v=")+2,ylink.trim().indexOf("&list"));
                flink = "https://www.youtube.com/watch?v=" + vId;
                Content content=new Content();
                ArrayList<String> playlist=content.doInBackground();
            }
            else {
                // get Video id from webview link and pass here
                vId=  getVideoId(ylink);
                flink = "https://www.youtube.com/watch?v=" + vId;
            }

//
        YouTubeUriExtractor ytEx = new YouTubeUriExtractor(this) {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onUrisAvailable(final String videoID, final String videoTitle, SparseArray<YtFile> ytFiles) {
                if (ytFiles != null) {
                    videoFilesData=ytFiles;
                    int itag = 22;
                    final ArrayList<String> list=new ArrayList<>();
                    //This is the downloadable URL
//                    String downloadURL = ytFiles.get(itag).getUrl();
                    final ArrayList<String > tags=new ArrayList<>();
                    tags.add("160");
                    tags.add("133");
                    tags.add("134");
                    tags.add("135");
                    tags.add("136");
                    tags.add("137");
                    ;
                    for(int i=0;i<tags.size();i++)
                    {
                        list.add(ytFiles.get(Integer.parseInt(tags.get(i))).getFormat().getHeight()+"   MP4");
                    }
//                    downURL.add(downloadURL);
                   vidTitle.add(videoTitle);
                    String storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/YoutubeVideos";
                File f = new File(storagePath);
                if (!f.exists()) {
                    f.mkdir();
                }
//
                progress=123;
//                    boolean isque=Database.AddDownloadQue(new VideoModel(videoID,videoTitle,false,downloadURL,0),getApplicationContext());
//                    if(isque)
//                    {
//                      Toast.makeText(getApplication(),"Video Added in a Downloaed Que",Toast.LENGTH_SHORT);
//                    }
//                    else
//                    {
//                        Toast.makeText(getApplication(),"Video Downloading Que is Full",Toast.LENGTH_SHORT);
//                    }
                    String[] mStringArray = new String[list.size()];
                    mStringArray = list.toArray(mStringArray);

                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, mStringArray);
                    ListView listView = (ListView) findViewById(R.id.videolist);
                    listView.setAdapter(adapter);
                    final SparseArray<YtFile> t=ytFiles;
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                             for(int i=0;i<parent.getChildCount();i++)
                             {
                                 View v=parent.getChildAt(i);
                                 if(v!=view) {
                                     v.setBackgroundColor(Color.WHITE);
                                     v.setTag("Unselected");
                                 }
                                 else
                                 {
                                     v.setBackgroundColor(getResources().getColor(R.color.red));
                                     v.setTag("Selected");
                                     download.setVisibility(View.VISIBLE);
                                     Database.AddDownloadQue(new VideoModel(videoID,videoTitle,false,t.get(Integer.parseInt(tags.get(position))).getUrl()),getApplicationContext());
                                    download.setText("Download "+t.get(Integer.parseInt(tags.get(position))).getFormat().getHeight());
                                    selectedDownloadedurl=t.get(Integer.parseInt(tags.get(position))).getUrl();
                                 }
                             }



                        }
                    });
                    videoTitletb.setText(videoTitle);
                    downLayout.setVisibility(View.VISIBLE);
                }

            }
        };

//
        ytEx.execute(flink);
    }







    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();


        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu
                .webview_menu, menu);
        mi = menu.findItem(R.id.download);
      //  mi.setVisible(false);

               return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()) {

            case R.id.download:

//                    new DownloadFile().execute(url);




                break;

            case R.id.backward:
                onBackPressed();
                break;

            case R.id.forward:
                onForwardPressed();
                break;

            case R.id.refresh:
                webView.reload();
                break;
            case R.id.history:
                startActivity(new Intent(WebViewActivity.this, HistoryActivity.class));
                break;
            case R.id.downloads:
                // put data for download section
              Intent i=  new Intent(WebViewActivity.this, DownloadActivity.class);
              i.putExtra("videoTitle",vidTitle);
              i.putExtra("media","youtube");
                i.putExtra("url",downURL);

                startActivity(i);


            default:



        }
        return super.onOptionsItemSelected(item);
    }


    private void onForwardPressed() {
        if (webView.canGoForward()) {
            webView.goForward();

        } else {
            Toast.makeText(this, "can't go Further!", Toast.LENGTH_SHORT).show();
        }
    }


    private void addHistory(String uRl) {

        if (!checkPermissionForReadExtertalStorage()) {
            try {
                requestPermissionForReadExtertalStorage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void back_btn_pressed(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
 linkText.setText("");
 linkText.setVisibility(View.GONE);
    }

    private void loadHistory() {

    }
    private final static String expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
    public static String getVideoId(String videoUrl) {
        if (videoUrl == null || videoUrl.trim().length() <= 0){
            return null;
        }
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(videoUrl);
        try {
            if (matcher.find())
                return matcher.group();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void requestPermissionForReadExtertalStorage() throws Exception {
        try {
            ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean checkPermissionForReadExtertalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            int result2 = this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            return (result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED);
        }
        return false;
    }
    @JavascriptInterface
    public void processVideo(final String vidData, final String vidID)
    {
        Toast.makeText(WebViewActivity.this, "process", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> getTitleVideo(){

        return  vidTitle;
    }
}
