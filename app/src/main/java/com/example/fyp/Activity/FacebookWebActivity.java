package com.example.fyp.Activity;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.Database.Database;
import com.example.fyp.Model.VideoModel;
import com.example.fyp.R;
import com.example.fyp.Util.Downloader;

import java.io.File;

public class FacebookWebActivity extends AppCompatActivity {

    public String target_url = "http://www.facebook.com";
    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_web);

        webView = (WebView) findViewById(R.id.webview);
       // WebSettings webSettings = webView.getSettings();
        //webView.loadUrl("http://www.facebook.com");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.addJavascriptInterface(this, "FBDownloader");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
               /* if (swipeLayout.isRefreshing())
                {
                    swipeLayout.setRefreshing(false);
                }*/

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
            public void onLoadResource(WebView view, String url)
            {
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

        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        CookieSyncManager.getInstance().startSync();

        webView.loadUrl(target_url);
    }
    private String m_Text = "";
    @JavascriptInterface
    public void processVideo(final String vidData, final String vidID)
    {
        try
        {
            String mBaseFolderPath = android.os.Environment
                    .getExternalStorageDirectory()
                    + File.separator
                    + "FacebookVideos" + File.separator;
            if (!new File(mBaseFolderPath).exists())
            {
                new File(mBaseFolderPath).mkdir();
            }
            String mFilePath = "file://" + mBaseFolderPath + "/" + vidID + ".mp4";

//            Uri downloadUri = Uri.parse(vidData);
//            DownloadManager.Request req = new DownloadManager.Request(downloadUri);
//            req.setDestinationUri(Uri.parse(mFilePath));
//            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//            DownloadManager dm = (DownloadManager) getSystemService(getApplicationContext().DOWNLOAD_SERVICE);
//            dm.enqueue(req);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Video Title ");

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text = input.getText().toString();
                    VideoModel video=new VideoModel(vidID,m_Text,false,vidData,1);
                    boolean isque=Database.AddDownloadQue(video,getApplicationContext());
                    if(isque)
                    {
                        Toast.makeText(getApplication(),"Video Added in a Downloaed Que",Toast.LENGTH_SHORT);
                        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(video.getUrl()));
                        req.setTitle(m_Text);
                        req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,  video.getName()+".mp4");
                        req.allowScanningByMediaScanner();
                        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        DownloadManager dm = Downloader.downloadManager;
                        dm.enqueue(req);
                    }
                    else
                    {
                        Toast.makeText(getApplication(),"Video Downloading Que is Full",Toast.LENGTH_SHORT);
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();


        }
        catch (Exception e)
        {
            Toast.makeText(this, "Download Failed: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    @JavascriptInterface
    public void VideoFound(final String vidData, final String vidID)
    {
        Toast.makeText(this,"Video Available for Download ="+vidID,Toast.LENGTH_SHORT);
    }
}
