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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Util.Downloader;
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

//import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
public class WebViewFacebookActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ImageView imageView;
    EditText linkText;
    String ylink;
    Uri downloadUri,dUri;
    ArrayList<String> url=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();
  //  RelativeLayout relativeLayout;
  LinearLayout downLayout,fablayout;
  TextView tttxtfirst,textSecond,txtThird,txtFour,ttxtFive,txtSix,txtDOWNLOAD,txtPlay,txtAdd;
    WebView webView;
    Uri videodATA;
    String VIDEOiD;
    LinearLayout linearLayout;
//    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab ;
    String URLl="";
    Bundle extras=null;
    MenuItem mi;
    private static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 1;

    ImageView imgFirst,imgSecond,imThird,imgFourth,imgFive,imgSixth;
    private DownloadManager downloadManager;
    private long downloadReference;
    DownloadManager.Request req;

  //  String id;
  //  DataBaseHistoryTable dataBaseHistoryTable;
   // private DatabaseReference mDatabase;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_facebook);
        fab = findViewById(R.id.fab_facebook);
        progressBar = findViewById(R.id.my_progress_bar_facebook);
        imageView = findViewById(R.id.my_img_view_facebook);
        webView = findViewById(R.id.web_view_facebook);
        linearLayout = findViewById(R.id.my_linear_layout_facebook);
//        swipeRefreshLayout = findViewById(R.id.my_swipe_refresh_facebook);
        linkText = findViewById(R.id.txtLinkfacebook);
        txtAdd=findViewById(R.id.btnAddVideoFB);
        txtDOWNLOAD=findViewById(R.id.btnDownloadVideoBtnFB);
        txtPlay=findViewById(R.id.btnDownloadVideoBtnFB);
        txtFour=findViewById(R.id.txtFourthFB);
        tttxtfirst=findViewById(R.id.txtfirstFB);
        textSecond=findViewById(R.id.txtSecondFB);
        txtThird=findViewById(R.id.ttxtTThiredFB);
        ttxtFive=findViewById(R.id.txtFiftFB);
        txtSix=findViewById(R.id.txtSixthFB);
        downLayout=findViewById(R.id.formatsLayoutFB);
        fablayout=findViewById(R.id.fabLAyoutFB);
        imgFirst=findViewById(R.id.imgfIrstFB);
        imgSecond=findViewById(R.id.imSEcondFB);
        imThird=findViewById(R.id.imgThhirdFB);
        imgFourth=findViewById(R.id.imgFourthFB);
        imgFive=findViewById(R.id.imgFifthFB);
        imgSixth=findViewById(R.id.imgSixthFB);

//        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.red,
//                R.color.orange, R.color.pink, R.color.yellow, R.color.black,
//                R.color.cyan);

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
                                         Toast.makeText(WebViewFacebookActivity.this, url, Toast.LENGTH_SHORT).show();


                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         linearLayout.setVisibility(View.GONE);
//                                         swipeRefreshLayout.setRefreshing(false);
                                         super.onPageFinished(view, url);
//                                         fab.setVisibility(View.VISIBLE);

                                         webView.loadUrl("javascript:(function() { "
                                                 + "var el = document.querySelectorAll('div[data-sigil]');"
                                                 + "for(var i=0;i<el.length; i++)"
                                                 + "{"
                                                 + "var sigil = el[i].dataset.sigil;"
                                                 + "if(sigil.indexOf('inlineVideo') > -1){"
                                                 + "delete el[i].dataset.sigil;"
                                                 + "var jsonData = JSON.parse(el[i].dataset.store);"
                                                 + "el[i].setAttribute('onClick', 'FBDownloader.processVideo(\"'+jsonData['src']+'\");');"
                                                 + "}" + "}" + "})()");


                                     }

                                     @Override
                                     public void onLoadResource(WebView view, String url) {
                                         webView.loadUrl("javascript:(function prepareVideo() { "
                                                 + "var el = document.querySelectorAll('div[data-sigil]');"
                                                 + "for(var i=0;i<el.length; i++)"
                                                 + "{"
                                                 + "var sigil = el[i].dataset.sigil;"
                                                 + "if(sigil.indexOf('inlineVideo') > -1){"
                                                 + "delete el[i].dataset.sigil;"
                                                 + "console.log(i);"
                                                 + "var jsonData = JSON.parse(el[i].dataset.store);"
                                                 + "el[i].setAttribute('onClick', 'FBDownloader.processVideo(\"'+jsonData['src']+'\",\"'+jsonData['videoID']+'\");');"
                                                 + "}" + "}" + "})()");
                                         webView.loadUrl("javascript:( window.onload=prepareVideo;"
                                                 + ")()");
                                     }
                                 });


        CookieSyncManager.createInstance(WebViewFacebookActivity.this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            CookieSyncManager.getInstance().

                    startSync();

//                                     }
//                                 });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);

               ylink = webView.getUrl();
               if(ylink.contains("%")){
                   fablayout.setVisibility(View.VISIBLE);
               }
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


//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                webView.reload();
//
//
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(URLl.equals("https://www.youtube.com")){
                downLayout.setVisibility(View.VISIBLE);
                    downloadVideo1(fab);
//                }

//      linkText.setVisibility(View.VISIBLE);
            }
        });
        txtDOWNLOAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                downloadVideo1(txtDOWNLOAD);
                downLayout.setVisibility(View.GONE);
            }
        });
        tttxtfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgFirst.setImageResource(R.drawable.ic_check_circle_black_24dp);
                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
        });
        textSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgSecond.setImageResource(R.drawable.ic_check_circle_black_24dp);
                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
        });
        txtThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imThird.setImageResource(R.drawable.ic_check_circle_black_24dp);
                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
        });
        txtFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFourth.setImageResource(R.drawable.ic_check_circle_black_24dp);
                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
        });
        ttxtFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFive.setImageResource(R.drawable.ic_check_circle_black_24dp);
                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSixth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
        });
        txtSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSixth.setImageResource(R.drawable.ic_check_circle_black_24dp);
                imgFirst.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgSecond.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imThird.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFourth.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                imgFive.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
        });

        linkText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                 ylink=linkText.getText().toString();


            }
        });
    }

        public void downloadVideo1 (View view){

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
                startActivity(new Intent(WebViewFacebookActivity.this, HistoryActivity.class));
                break;
            case R.id.downloads:
                Intent i=  new Intent(WebViewFacebookActivity.this, DownloadActivity.class);
                i.putExtra("videoTitle",title);
                i.putExtra("media","facebook");
                i.putExtra("url",url);
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

            downloadUri = Uri.parse(vidData);
            String d= String.valueOf(downloadUri);
            url.add(d);
            title.add(vidID);
            Toast.makeText(this, vidID, Toast.LENGTH_LONG).show();
            videodATA=downloadUri;VIDEOiD=vidID;
//            DownloadManager.Request req = new DownloadManager.Request(downloadUri);
//            req.setTitle()
//            req.setDestinationUri(Uri.parse(mFilePath));
//            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//            DownloadManager dm = Downloader.downloadManager;
//            dm.enqueue(req);
//            Toast.makeText(this, "Download Started", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Download Failed: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }




}
