package com.example.fyp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

public class WebViewInstaActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ImageView imageView;
//    EditText linkText;
    String ylink;
    TextView tttxtfirst,textSecond,txtThird,txtFour,ttxtFive,txtSix,txtDOWNLOAD,txtPlay,txtAdd;
    LinearLayout downLayout,fablayout;
  //  RelativeLayout relativeLayout;
    WebView webView;
    ImageView imgFirst,imgSecond,imThird,imgFourth,imgFive,imgSixth;
    LinearLayout linearLayout;
//    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab ;
    String URLl="";
    Bundle extras=null;
    MenuItem mi;
    private static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 1;
    ArrayList<String> downURL=new ArrayList<>();
    ArrayList<String> vidTitle=new ArrayList<>();
    private DownloadManager downloadManager;
    private long downloadReference;

  //  String id;
  //  DataBaseHistoryTable dataBaseHistoryTable;
   // private DatabaseReference mDatabase;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_insta);
        fab = findViewById(R.id.fab_insta);
        txtAdd=findViewById(R.id.btnAddVideo_insta);
        txtDOWNLOAD=findViewById(R.id.btnDownloadVideoBtn_insta);
        txtPlay=findViewById(R.id.btnDownloadVideoBtn_insta);
        txtFour=findViewById(R.id.txtFourth_insta);
        tttxtfirst=findViewById(R.id.txtfirst_insta);
        textSecond=findViewById(R.id.txtSecond_insta);
        txtThird=findViewById(R.id.ttxtTThired_insta);
        ttxtFive=findViewById(R.id.txtFift_insta);
        txtSix=findViewById(R.id.txtSixth_insta);
        downLayout=findViewById(R.id.formatsLayout_insta);
        progressBar = findViewById(R.id.my_progress_bar_insta);
        imageView = findViewById(R.id.my_img_view_insta);
        webView = findViewById(R.id.web_ins);
        linearLayout = findViewById(R.id.my_linear_layout_insta);
//        swipeRefreshLayout = findViewById(R.id.my_swipe_refresh);
//        linkText = findViewById(R.id.txtLink_insta);
        fablayout=findViewById(R.id.fabLAyout_insta);
        imgFirst=findViewById(R.id.imgfIrst_insta);
        imgSecond=findViewById(R.id.imSEcond_insta);
        imThird=findViewById(R.id.imgThhird_insta);
        imgFourth=findViewById(R.id.imgFourth_insta);
        imgFive=findViewById(R.id.imgFifth_insta);
        imgSixth=findViewById(R.id.imgSixth_insta);
//
        extras = getIntent().getExtras();
        URLl = extras.getString("url");
        addHistory(URLl);
        webView.loadUrl(URLl);
        webView.getSettings().setJavaScriptEnabled(true); webView.getSettings().setUseWideViewPort(true);
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
                                         Toast.makeText(WebViewInstaActivity.this, url, Toast.LENGTH_SHORT).show();
                                         fablayout.setVisibility(View.VISIBLE);

                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         linearLayout.setVisibility(View.GONE);
//                                         swipeRefreshLayout.setRefreshing(false);
                                         super.onPageFinished(view, url);



                                     }



                                 });

//                                     }
//                                 });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);

               ylink = webView.getUrl();
//               if(ylink.contains("%")) {

//               }
                Toast.makeText(WebViewInstaActivity.this, ylink, Toast.LENGTH_SHORT).show();
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(ylink);
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("text label", ylink);
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


            }
        });



        progressBar.setMax(100);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(URLl.equals("https://www.youtube.com")){

                    downloadVideo1(fab);
//                }

//      linkText.setVisibility(View.VISIBLE);
            }
        });
//
    }

        public void downloadVideo1 (View view){
        //get the download URL
//



        YouTubeUriExtractor ytEx = new YouTubeUriExtractor(this) {
            @Override
            public void onUrisAvailable(String videoID, String videoTitle, SparseArray<YtFile> ytFiles) {
                if (ytFiles != null) {
                    int itag = 22;
                    //This is the download URL
                    String downloadURL = ytFiles.get(itag).getUrl();
                    Log.e("download URL :", downloadURL);
                    downURL.add(downloadURL);
                    vidTitle.add(videoTitle);

                }

            }
        };
//            String vId=  getVideoId(ylink);
            String flink="https://www.youtube.com/watch?v=nUtrGRn8NtI";
        ytEx.execute(flink);
    }

        private ProgressDialog pDialog;



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
                startActivity(new Intent(WebViewInstaActivity.this, HistoryActivity.class));
                break;
            case R.id.downloads:
                Intent i=  new Intent(WebViewInstaActivity.this, DownloadActivity.class);
                i.putExtra("videoTitle",vidTitle);
                i.putExtra("media","instagram");
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
        Toast.makeText(this, uRl, Toast.LENGTH_LONG).show();
      /*  if(uRl.equals("https://www.youtube.com"))
        {
            loadHistory();
            activity = "youTube";
        }
        else if(uRl.equals("https://www.facebook.com"))
        {
            loadHistory();
            activity = "facebook";
        }
        else  if(uRl.equals("https://www.instagram.com"))
        {
            loadHistory();
            activity = "instagram";
        }*/
     //   mDatabase = FirebaseDatabase.getInstance().getReference(activity);
//
     //   id = mDatabase.push().getKey();
    //    dataBaseHistoryTable = new DataBaseHistoryTable(uRl);
       // mDatabase.setValue(dataBaseHistoryTable);

        Toast.makeText(this, "Data Stored", Toast.LENGTH_LONG).show();

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
// linkText.setText("");
// linkText.setVisibility(View.GONE);
    }

    private void loadHistory() {
      /*  mDatabase = FirebaseDatabase.getInstance().getReference().child(activity);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                while (dataSnapshot.child(uRl) != null) {
                    String s = dataSnapshot.child("url").getValue().toString();
                    intent.putExtra("URL", s);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
     // HistoryActivity ha = new HistoryActivity();
    //  ha.setText(uRl);
        Toast.makeText(this, "Data load", Toast.LENGTH_LONG).show();

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
        try
        {
            String mBaseFolderPath = Environment
                    .getExternalStorageDirectory()
                    + File.separator
                    + "FacebookVideos" + File.separator;
            if (!new File(mBaseFolderPath).exists())
            {
                new File(mBaseFolderPath).mkdir();
            }
            String mFilePath = "file://" + mBaseFolderPath + "/" + vidID + ".mp4";

            Uri downloadUri = Uri.parse(vidData);
            DownloadManager.Request req = new DownloadManager.Request(downloadUri);
            req.setDestinationUri(Uri.parse(mFilePath));
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            DownloadManager dm = (DownloadManager) getSystemService(getApplicationContext().DOWNLOAD_SERVICE);
            dm.enqueue(req);
            Toast.makeText(this, "Download Started", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Download Failed: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
