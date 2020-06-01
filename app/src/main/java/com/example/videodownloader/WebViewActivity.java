package com.example.videodownloader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;
import cz.msebera.android.httpclient.Header;
import io.paperdb.Paper;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

public class WebViewActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ImageView imageView;
  //  RelativeLayout relativeLayout;
    WebView webView;
    LinearLayout linearLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    String URLl="";
    Bundle extras=null;
    MenuItem mi;
    ProgressDialog loadingBar;
    String WritePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    String ReadPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
    String platform="";
    final String fbVideoRegex = "http[s]?:\\/\\/((www.)|(web.)|(m.)|(mobile.))?facebook.com\\/([A-Za-z0-9_\\.]+)\\/videos\\/([0-9]+)\\/?";
    final String fbPostRegex = "http[s]?:\\/\\/((www\\.)|(web\\.))?facebook\\.com\\/([A-Za-z0-9_\\.]+)\\/posts\\/([0-9]+)\\/?(.*)?";
    final String mfbVideoRegex = "http[s]?:\\/\\/((m\\.)|(mobile\\.)|(mbasic\\.))?facebook\\.com\\/story.php(.*)?";
    final String igURLPattern = "http[s]?:\\/\\/(www.)?instagram.com\\/p\\/([A-Za-z0-9_\\-]+)(\\/?)(\\??(?:&?[^=&]*=[^=&]*)*)?";
    final String igTVURLPattern = "http[s]?:\\/\\/(www.)?instagram.com\\/tv\\/([A-Za-z0-9_\\-]+)(\\/?)(\\??(?:&?[^=&]*=[^=&]*)*)?";
    private final String dataRegex = "<script type=\"text\\/javascript\">window._sharedData = (.*);<\\/script>";
    private final Pattern pattern = Pattern.compile(dataRegex);
    private JSONObject data;
    private JSONArray postPages;
    private List<InstagramPost> posts = new ArrayList<>();
    private String error = "";
    Context context;
    DownloadManager dm;
    private DownloadManager downloadManager;
    private long downloadReference;

  //  String id;
  //  DataBaseHistoryTable dataBaseHistoryTable;
   // private DatabaseReference mDatabase;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progressBar = findViewById(R.id.my_progress_bar);
        imageView = findViewById(R.id.my_img_view);
        webView = findViewById(R.id.web_view);
        linearLayout = findViewById(R.id.my_linear_layout);
        swipeRefreshLayout = findViewById(R.id.my_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.red,
                R.color.orange, R.color.pink, R.color.yellow, R.color.black,
                R.color.cyan);

        extras = getIntent().getExtras();
       URLl= extras.getString("url");
       platform=extras.getString("platform");
       context=this;
       dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Paper.init(context);


        webView.loadUrl(URLl);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                linearLayout.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                linearLayout.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);

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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });



        if (ActivityCompat.checkSelfPermission(this, WritePermission) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, ReadPermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{WritePermission, ReadPermission}, 1);
        }




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

               return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("JavascriptInterface")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.download:



                if (URLl.contains("http") && platform.contains("youtube")) {
                    YouTubeVideoDownloadF(18);
                }
                else if (platform.contains("facebook") ) {
                    final String fbURL = webView.getOriginalUrl();
                    addHistory(fbURL);
                    Pattern fbPattern = null;
                    boolean isMobileURL = false;
                    boolean isPostURL = false;
                    if (Pattern.matches(fbVideoRegex, fbURL)) {
                        fbPattern = Pattern.compile(fbVideoRegex);
                    } else if (Pattern.matches(mfbVideoRegex, fbURL)) {
                        isMobileURL = true;
                        fbPattern = Pattern.compile(mfbVideoRegex);
                    } else if (Pattern.matches(fbPostRegex, fbURL)) {
                        isMobileURL = true;
                        isPostURL = true;
                        fbPattern = Pattern.compile(fbPostRegex);
                    } else {
                        Toast.makeText(this, "Invalid Facebook Video URL", Toast.LENGTH_LONG).show();

                    }

                    if (isMobileURL) {
                        String storyFBID = "", id = "";
                        if (isPostURL) {
                            Matcher fbMatcher = fbPattern.matcher(fbURL);
                            if (fbMatcher.matches()) {
                                id = fbMatcher.group(4);
                                storyFBID = fbMatcher.group(5);
                            }
                        } else {
                            ArrayList<String> requiredParameters = new ArrayList<String>();
                            requiredParameters.add("story_fbid");
                            requiredParameters.add("id");
                            UrlQuerySanitizer sanitizer = new UrlQuerySanitizer(fbURL);
                            if (!(sanitizer.getParameterSet().containsAll(requiredParameters))) {
                                Toast.makeText(this, "Some Parameters are missing from the URL.", Toast.LENGTH_LONG).show();

                            }
                            storyFBID = sanitizer.getValue("story_fbid");
                            id = sanitizer.getValue("id");
                        }
                        Toast.makeText(this, "User ID: " + id +  " Story FBID: " + storyFBID , Toast.LENGTH_LONG).show();
                        getMobileFacebookVideo(storyFBID, id);

                    }
                    Matcher fbMatcher = fbPattern.matcher(fbURL);
                    String username = "";
                    String videoId = "";
                    if (fbMatcher.matches()) {
                        username = fbMatcher.group(4);
                        videoId = fbMatcher.group(5);
                        Toast.makeText(this, "Username: " + username + " Video ID: " + videoId, Toast.LENGTH_LONG).show();
                        getFacebookVideos(username, videoId);
                    } else {
                        Toast.makeText(this, "Invalid Facebook Video URL", Toast.LENGTH_LONG).show();

                    }
                }
                else if(platform.contains("insta") ){
                    String igURL=webView.getOriginalUrl();
                    addHistory(igURL);
                    Pattern igPattern = null;
                    if(Pattern.matches(igURLPattern, igURL)) {
                        igPattern = Pattern.compile(igURLPattern);
                    } else if (Pattern.matches(igTVURLPattern, igURL)) {
                        igPattern = Pattern.compile(igTVURLPattern);
                    }
                    else {
                        Toast.makeText(this, "Invalid Instagram URL", Toast.LENGTH_LONG).show();
                    }
                    Matcher igMatcher = igPattern.matcher(igURL);
                    String postId = "";
                    if(igMatcher.matches()) {
                        postId = igMatcher.group(2);
                        Toast.makeText(this, "Post ID: " + postId , Toast.LENGTH_LONG).show();
                        //getInstagramPost(postId);
                        fetchInstagramData(postId);
                    } else {
                        Toast.makeText(this, "Invalid Instagram Post URL", Toast.LENGTH_LONG).show();

                    }
                }
                else Toast.makeText(this,URLl, Toast.LENGTH_LONG).show();

                break;
            case R.id.quality1:
                if (URLl.contains("http") && platform.contains("youtube")) {
                    YouTubeVideoDownloadF(18);
                }
                else if (platform.contains("facebook")|| platform.contains("instagram")) {
                    Toast.makeText(this,"quality control doesnt exist for facebook and instagram", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(this,"enter url first", Toast.LENGTH_LONG).show();

                break;

            case R.id.quality2:
                if (URLl.contains("http") && platform.contains("youtube")) {
                    YouTubeVideoDownloadF(22);
                }
                else if (platform.contains("facebook")|| platform.contains("instagram")) {
                    Toast.makeText(this,"quality control doesnt exist for facebook and instagram", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(this,"Enter URL First", Toast.LENGTH_LONG).show();

                break;

            case R.id.quality3:
                if (URLl.contains("http") && platform.contains("youtube")) {
                    YouTubeVideoDownloadF(140);
                }
                else if (platform.contains("facebook")|| platform.contains("instagram")) {
                    Toast.makeText(this,"quality control doesnt exist for facebook and instagram", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(this,"Enter URL First", Toast.LENGTH_LONG).show();

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
                startActivity(new Intent(WebViewActivity.this, DownloadActivity.class));


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
        List<String> keys=Paper.book().getAllKeys();

        if(keys.size()==0){
            Paper.book().write("index",1);
        }
        int index=Paper.book().read("index");
        Toast.makeText(this, index+"", Toast.LENGTH_LONG).show();
        Paper.book().write(("url"+index).toString(),uRl);
        Paper.book().write("index",index+1);


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

    //methods for handling youtube videos download
    public void YouTubeVideoDownloadF(int iTag){

        if (ActivityCompat.checkSelfPermission(this, WritePermission) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, ReadPermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{WritePermission, ReadPermission}, 1);
        } else {

            YTDownload(iTag);
        }
    }
    public void YTDownload(final int itag) {

        String VideoURLDownload=webView.getUrl();
        addHistory(VideoURLDownload);

        @SuppressLint("StaticFieldLeak") YouTubeUriExtractor youTubeUriExtractor = new YouTubeUriExtractor(this) {
            @Override
            public void onUrisAvailable(String videoId, final String videoTitle, SparseArray<YtFile> ytFiles) {
                if ((ytFiles != null)) {
                    String downloadURL = ytFiles.get(itag).getUrl();
                    Log.e("Download URL: ", downloadURL);
                    if(itag==18 || itag == 22 || itag==140) {
                        String mp4=".mp4";
                        DownloadManagingF(downloadURL, videoTitle,mp4);
                    }

                } else Toast.makeText(WebViewActivity.this, "Error With URL", Toast.LENGTH_LONG).show();
            }
        };
        youTubeUriExtractor.execute(VideoURLDownload);
    }
    public void DownloadManagingF(String downloadURL, String videoTitle, String extentiondwn){
        if (downloadURL != null) {
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadURL));
            request.setTitle(videoTitle);
            request.setTitle("Youtube Video Downloader");
            request.setDescription("Downloading Video ");
            request.setAllowedOverMetered(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir("/Download/YouTube-Downloader/", videoTitle + extentiondwn);
            if (downloadManager != null) {
                Toast.makeText(getApplicationContext(),"Downloading...", Toast.LENGTH_SHORT).show();
                downloadManager.enqueue(request);
            }
            BroadcastReceiver onComplete = new BroadcastReceiver() {
                public void onReceive(Context ctxt, Intent intent) {
                    loadingBar.dismiss();
                    Toast.makeText(getApplicationContext(),"Download Completed", Toast.LENGTH_SHORT).show();

                    Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory() + "/Download/YouTube-Downloader/");
                    Intent intentop = new Intent(Intent.ACTION_VIEW);
                    intentop.setDataAndType(selectedUri, "resource/folder");

                    if (intentop.resolveActivityInfo(getPackageManager(), 0) != null)
                    {
                        startActivity(intentop);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Saved on: Download/YouTube-Downloader", Toast.LENGTH_LONG).show();
                        //restartApp();
                    }
                    unregisterReceiver(this);
                    finish();
                }
            };
            registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        }
    }

    //methods for handling facebook videos download
    private void getFacebookVideos(String username, String videoId) {
        String fetchURL = "fb";
        RequestParams rp = new RequestParams();
        rp.add("videoId", videoId);
        rp.add("user", username);
        HttpUtils.get(fetchURL, rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String error = response.optString("error");
                if (!(error.equalsIgnoreCase(""))) {
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                    return;
                }
                String lowURL = "";
                String highURL = "";
                JSONObject low = response.optJSONObject("low");
                JSONObject high = response.optJSONObject("high");
                if (!(low.optString("url", "").equalsIgnoreCase(""))) {
                    lowURL = low.optString("url");
                }
                if (!(high.optString("url", "").equalsIgnoreCase(""))) {
                    highURL = high.optString("url");
                }
                if (lowURL.isEmpty() && highURL.isEmpty()) {
                    new AlertDialog.Builder(context)
                            .setTitle("Cannot Find Video")
                            .setMessage("Either this URL is invalid or the Video you are trying to download is private or has been removed")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Nothing to do just dismiss.
                                }
                            }).show();
                } else {
                    String title = response.optString("title", "");
                    String description = response.optString("description", "");
                    String thumbnail = response.optString("thumbnail", "");

                    if (!lowURL.isEmpty()) {
                        final String finalLowURL = lowURL;

                        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(finalLowURL))
                                .setTitle("Facebook Video Downloader")
                                .setDescription("Downloading Video in Normal Quality")
                                .setAllowedOverMetered(true)
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setDestinationInExternalPublicDir("/Download/Facebook-Downloader/", title + description);
                        dm.enqueue(req);


                    }
                    if (!highURL.isEmpty()) {
                        final String finalHighURL = highURL;

                        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(finalHighURL))
                                .setTitle("Facebook Video Downloader")
                                .setDescription("Downloading Video in High Quality")
                                .setAllowedOverMetered(true)
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setDestinationInExternalPublicDir("/Download/Facebook-Downloader/", title + description);
                        dm.enqueue(req);

                    }

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast.makeText(context, "Network Error: Cannot fetch video.", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getMobileFacebookVideo(String storyFBID, String id) {
        String fetchURL = "mfb";
        RequestParams rp = new RequestParams();
        rp.add("story_fbid", storyFBID);
        rp.add("id", id);
        HttpUtils.get(fetchURL, rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String error = response.optString("error");
                if (!(error.equalsIgnoreCase(""))) {
                    new AlertDialog.Builder(context)
                            .setTitle("Cannot Find Video")
                            .setMessage(error)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Nothing to do just dismiss.
                                }
                            }).show();
                    return;
                }
                String videoURL = response.optString("video", "");
                if (videoURL.equalsIgnoreCase("")) {
                    new AlertDialog.Builder(context)
                            .setTitle("Cannot Find Video")
                            .setMessage("Either this URL is invalid or the Video you are trying to download is private or has been removed")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Nothing to do just dismiss.
                                }
                            }).show();
                } else {

                    String title = response.optString("title", "");
                    String description = response.optString("description", "");
                    String thumbnail = response.optString("thumbnail", "");



                    final String finalVideoURL = videoURL;

                            DownloadManager.Request req = new DownloadManager.Request(Uri.parse(finalVideoURL))
                                    .setTitle("Facebook Video Downloader")
                                    .setDescription("Downloading Video in Normal Quality")
                                    .setAllowedOverMetered(true)
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                    .setDestinationInExternalPublicDir("/Download/Facebook-Downloader/", title + description);
                            dm.enqueue(req);

                        }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast.makeText(context, "Network Error: Cannot fetch video.", Toast.LENGTH_LONG).show();
            }
        });
    }

    //methods for handling instagram videos download
    private void getInstagramPost(String postId) {
        String fetchURL = "ig";
        RequestParams rp = new RequestParams();
        rp.add("postId", postId);
        HttpUtils.get(fetchURL, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String error = response.optString("error", null);
                if(error != null) {
                    error = error.isEmpty() ? "Cannot fetch post" : error;
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                    return;
                }

                JSONArray media = response.optJSONArray("media");
                ArrayList<InstagramPost> posts = new ArrayList<>();
                for(int i=0; i<media.length(); i++) {
                    try {
                        InstagramPost post = new InstagramPost(media.getJSONObject(i));
                        if(post.getPostType().equalsIgnoreCase("IMAGE")) {
                            posts.add(new InstagramImage(media.getJSONObject(i)));
                        } else {
                            posts.add(new InstagramVideo(media.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        // Skip this index
                    }

                }


                InstagramPost post = posts.get(0);
                final String thumbURL, mainURL;
                if(post.getPostType() == "IMAGE") {
                    thumbURL = mainURL = ((InstagramImage)post).getImageURL();
                } else {
                    thumbURL = ((InstagramVideo)post).getThumbnailURL();
                    mainURL = ((InstagramVideo) post).getVideoURL();
                }

                DownloadManager.Request req = new DownloadManager.Request(Uri.parse(mainURL))
                        .setTitle("Instagram Download")
                        .setAllowedOverMetered(true)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir("/Download/Instagram-Downloader/", response.optString("title", "Untitled") + response.optString("description", "No Description"));
                DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(req);
                Toast.makeText(context, "Download Started", Toast.LENGTH_LONG).show();

                Toast.makeText(context, "Total Post Fetched: " + posts.size(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast.makeText(context, "Network Error: Cannot fetch post.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchInstagramData(String postId) {
        posts.clear();
        RequestQueue queue = Volley.newRequestQueue(this);
        String postURL = "https://www.instagram.com/p/" + postId;
        StringRequest getPageContent = new StringRequest(postURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Got Response", Toast.LENGTH_LONG).show();
                final Matcher matcher = pattern.matcher(response);
                if (matcher.find()) {
                    try {
                        data = new JSONObject(matcher.group(1));
                    } catch (Exception ex) {
                        error = "Exception: " + ex.getMessage();
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (Objects.requireNonNull(data.optJSONObject("entry_data")).optJSONArray("PostPage") == null) {
                        error = "Captcha required by Instagram due to excess requests. Please wait and retry later.";
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                        return;
                    } else {

                        postPages = Objects.requireNonNull(data.optJSONObject("entry_data")).optJSONArray("PostPage");
                    }
                    try {
                        assert postPages != null;
                        String typename = postPages.optJSONObject(0).optJSONObject("graphql").optJSONObject("shortcode_media").optString("__typename");
                        if (typename.equalsIgnoreCase("GraphImage")) {
                            InstagramImage postImage = new InstagramImage(new JSONObject("{image: \"" + postPages.optJSONObject(0).optJSONObject("graphql").optJSONObject("shortcode_media").optString("display_url") + "\"}"));
                            posts.add(postImage);
                        } else if (typename.equalsIgnoreCase("GraphSidecar")) {
                            JSONArray postsArray = postPages.optJSONObject(0).optJSONObject("graphql").optJSONObject("shortcode_media").optJSONObject("edge_sidecar_to_children").optJSONArray("edges");
                            for (int i = 0; i < postsArray.length(); i++) {
                                JSONObject p = postsArray.optJSONObject(i);
                                Log.d("ERROR", p.toString(2));
                                String imgURL = p.optJSONObject("node").optString("display_url");
                                String vidURL = p.optJSONObject("node").optString("video_url");
                                if(vidURL == null || vidURL == "") {
                                    posts.add(new InstagramImage(new JSONObject("{image: \""+ imgURL +"\"}")));
                                } else {
                                    posts.add(new InstagramVideo(new JSONObject("{image: \""+ imgURL +"\", video:\""+ vidURL +"\"}")));
                                }
                            }
                        } else if (typename.equalsIgnoreCase("GraphVideo")) {
                            String imgURL = postPages.optJSONObject(0).optJSONObject("graphql").optJSONObject("shortcode_media").optString("display_url");
                            String vidURL = postPages.optJSONObject(0).optJSONObject("graphql").optJSONObject("shortcode_media").optString("video_url");
                            posts.add(new InstagramVideo(new JSONObject("{image: \""+ imgURL +"\", video:\""+ vidURL +"\"}")));
                        }
                        Document doc = Jsoup.parse(response, "https://instagram.com");
                        String title = doc.select("title").first().html();
                        String description = postPages.optJSONObject(0).optJSONObject("graphql").optJSONObject("shortcode_media").optJSONObject("edge_media_to_caption").optJSONArray("edges").optJSONObject(0).optJSONObject("node").optString("text", "No Description");

                        Toast.makeText(context, "Total Pages: " + posts.size(), Toast.LENGTH_LONG).show();
                        InstagramPost post = posts.get(0);
                        final String thumbURL, mainURL;
                        if(post.getPostType() == "IMAGE") {
                            thumbURL = mainURL = ((InstagramImage)post).getImageURL();
                        } else {
                            thumbURL = ((InstagramVideo)post).getThumbnailURL();
                            mainURL = ((InstagramVideo) post).getVideoURL();
                        }

                        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(mainURL))
                                .setTitle("Instagram Download")
                                .setAllowedOverMetered(true)
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setDestinationInExternalPublicDir("/Download/Instagram-Downloader/", title + description);
                        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
                        downloadManager.enqueue(req);
                        Toast.makeText(context, "Download Started", Toast.LENGTH_LONG).show();

                        Toast.makeText(context, "Total Post Fetched: " + posts.size(), Toast.LENGTH_LONG).show();


                    } catch (Exception ex) {
                        error = "Invalid JSON: " + ex.getMessage();
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                        ex.printStackTrace();
                        return;
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError err) {
                error = err.getMessage();
                Toast.makeText(context, error, Toast.LENGTH_LONG).show();
            }
        });
        queue.add(getPageContent);
        Toast.makeText(context, "Request Added to Queue", Toast.LENGTH_SHORT).show();
    }
    public void openFolder(View view)
    {
        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

    }
}
