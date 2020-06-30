package com.example.fyp;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.Status;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;


public class InstagramDownload_Fragment extends Fragment  {
    private static View view;
    private static ListView_Adapter adapter;
    private static ListView listView;
    ArrayList<String> u;
    ArrayList<String> t;
    private static String dirPath;
    Button buttonOne,buttonCancelOne;
    TextView textViewProgressOne;
    ProgressBar progressBarOne;
    int length;
    long id1,id2,id3,id4,id5,id6,id7,id8,id9,id10;
    //Action Mode for toolbar
    int pp;
    int i1,i2,i3,i4,i5,i6,i7,i8,i9,i10;
    private ActionMode mActionMode;
    ArrayList<String> listname;
    ArrayList<String> listtext;
    private static ArrayList<Item_Model> item_models;
    private static ProgressDialog pDialog;
    Button  buttonTwo, buttonThree, buttonFour,
            buttonFive, buttonSix, buttonSeven, buttonEight,
            buttonNine, buttonTen, buttonEleven, buttonTwelve,
            buttonThirteen, buttonFourteen, buttonFifteen,
            buttonCancelTwo, buttonCancelThree,
            buttonCancelFour, buttonCancelFive, buttonCancelSix,
            buttonCancelSeven, buttonCancelEight, buttonCancelNine,
            buttonCancelTen, buttonCancelEleven, buttonCancelTwelve,
            buttonCancelThirteen, buttonCancelFourteen, buttonCancelFifteen;

    TextView  textViewProgressTwo, textViewProgressThree,
            textViewProgressFour, textViewProgressFive, textViewProgressSix,
            textViewProgressSeven, textViewProgressEight, textViewProgressNine,
            textViewProgressTen, textViewProgressEleven, textViewProgressTwelve,
            textViewProgressThirteen, textViewProgressFourteen, textViewProgressFifteen;

    TextView txtView1,txtView2,txtView3,txtView4,txtView5,txtView6,txtView7,txtView8,txtView9,txtView10;

    ProgressBar  progressBarTwo, progressBarThree,
            progressBarFour, progressBarFive, progressBarSix,
            progressBarSeven, progressBarEight, progressBarNine,
            progressBarTen, progressBarEleven, progressBarTwelve,
            progressBarThirteen, progressBarFourteen, progressBarFifteen;

    int downloadIdOne, downloadIdTwo, downloadIdThree, downloadIdFour,
            downloadIdFive, downloadIdSix, downloadIdSeven,
            downloadIdEight, downloadIdNine, downloadIdTen,
            downloadIdEleven, downloadIdTwelve, downloadIdThirteen,
            downloadIdFourteen, downloadIdFifteen;
    RelativeLayout r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;

     DownloadManager downloadManager10;
     DownloadManager.Request request10;
    DownloadManager downloadManager1;
    DownloadManager.Request request1;

    DownloadManager downloadManager2;
    DownloadManager.Request request2;
    DownloadManager downloadManager3;
    DownloadManager.Request request3;
    DownloadManager downloadManager4;
    DownloadManager.Request request4;
    DownloadManager downloadManager5;
    DownloadManager.Request request5;
    DownloadManager downloadManager6;
    DownloadManager.Request request6;
    DownloadManager downloadManager7;
    DownloadManager.Request request7;
    DownloadManager downloadManager8;
    DownloadManager.Request request8;
    DownloadManager downloadManager9;
    DownloadManager.Request request9;
    public InstagramDownload_Fragment() {
    }

//    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.instagram_download_fragment, container, false);
//        populateListView();
//        buttonOne = view.findViewById(R.id.buttonOneinsta);
//        buttonCancelOne = view.findViewById(R.id.buttonCancelOneinsta);
//        textViewProgressOne = view.findViewById(R.id.textViewProgressOneinsta);
//        progressBarOne =view. findViewById(R.id.progressBarOneinsta);
//
//        DownloadActivity activity = (DownloadActivity) getActivity();
//        String media=(activity.getMyfileProg()==null?"":activity.getMyfileProg());
//        if(media.equals("instagram")) {
//            u = activity.getStringUrlInsta();
//            t=activity.getStringTitleInsta();
//        }
//        buttonOne = view.findViewById(R.id.buttonOneinsta);
//        buttonTwo = view.findViewById(R.id.buttonTwoinsta);
//        buttonThree = view.findViewById(R.id.buttonThreeinsta);
//        buttonFour = view.findViewById(R.id.buttonFourinsta);
//        buttonFive = view.findViewById(R.id.buttonFiveinsta);
//        buttonSix = view.findViewById(R.id.buttonSixinsta);
//        buttonSeven = view.findViewById(R.id.buttonSeveninsta);
//        buttonEight = view.findViewById(R.id.buttonEightinsta);
//        buttonNine =view. findViewById(R.id.buttonNineinsta);
//        buttonTen = view.findViewById(R.id.buttonTeninsta);
//
//        buttonCancelOne = view.findViewById(R.id.buttonCancelOneinsta);
//        buttonCancelTwo = view.findViewById(R.id.buttonCancelTwoinsta);
//        buttonCancelThree = view.findViewById(R.id.buttonCancelThreeinsta);
//        buttonCancelFour = view.findViewById(R.id.buttonCancelFourinsta);
//        buttonCancelFive = view.findViewById(R.id.buttonCancelFiveinsta);
//        buttonCancelSix = view.findViewById(R.id.buttonCancelSixinsta);
//        buttonCancelSeven = view.findViewById(R.id.buttonCancelSeveninsta);
//        buttonCancelEight = view.findViewById(R.id.buttonCancelEightinsta);
//        buttonCancelNine = view.findViewById(R.id.buttonCancelNineinsta);
//        buttonCancelTen = view.findViewById(R.id.buttonCancelTeninsta);
//
//        r1=view.findViewById(R.id.relative1insta);
//        r2=view.findViewById(R.id.relative2insta);
//        r3=view.findViewById(R.id.relative3insta);
//        r4=view.findViewById(R.id.relative4insta);
//        r5=view.findViewById(R.id.relative5insta);
//        r6=view.findViewById(R.id.relative6insta);
//        r7=view.findViewById(R.id.relative7insta);
//        r8=view.findViewById(R.id.relative8insta);
//        r9=view.findViewById(R.id.relative9insta);
//        r10=view.findViewById(R.id.relative10insta);
//
//        txtView1=view.findViewById(R.id.txt1insta);
//        txtView2=view.findViewById(R.id.txt2insta);
//        txtView3=view.findViewById(R.id.txt3insta);
//        txtView4=view.findViewById(R.id.txt4insta);
//        txtView5=view.findViewById(R.id.txt5insta);
//        txtView6=view.findViewById(R.id.txt6insta);
//        txtView7=view.findViewById(R.id.txt7insta);
//        txtView8=view.findViewById(R.id.txt8insta);
//        txtView9=view.findViewById(R.id.txt9insta);
//        txtView10=view.findViewById(R.id.txt10insta);
//
//        textViewProgressOne = view.findViewById(R.id.textViewProgressOneinsta);
//        textViewProgressTwo = view.findViewById(R.id.textViewProgressTwoinsta);
//        textViewProgressThree = view.findViewById(R.id.textViewProgressThreeinsta);
//        textViewProgressFour =view. findViewById(R.id.textViewProgressFourinsta);
//        textViewProgressFive = view.findViewById(R.id.textViewProgressFiveinsta);
//        textViewProgressSix = view.findViewById(R.id.textViewProgressSixinsta);
//        textViewProgressSeven = view.findViewById(R.id.textViewProgressSeveninsta);
//        textViewProgressEight = view.findViewById(R.id.textViewProgressEightinsta);
//        textViewProgressNine = view.findViewById(R.id.textViewProgressNineinsta);
//        textViewProgressTen = view.findViewById(R.id.textViewProgressTeninsta);
//
//        progressBarOne = view.findViewById(R.id.progressBarOneinsta);
//        progressBarTwo = view.findViewById(R.id.progressBarTwoinsta);
//        progressBarThree = view.findViewById(R.id.progressBarThreeinsta);
//        progressBarFour = view.findViewById(R.id.progressBarFourinsta);
//        progressBarFive = view.findViewById(R.id.progressBarFiveinsta);
//        progressBarSix = view. findViewById(R.id.progressBarSixinsta);
//        progressBarSeven = view.findViewById(R.id.progressBarSeveninsta);
//        progressBarEight = view.findViewById(R.id.progressBarEightinsta);
//        progressBarNine = view.findViewById(R.id.progressBarNineinsta);
//        progressBarTen = view.findViewById(R.id.progressBarTeninsta);
        if(u!=null){
        for (int i=0;i<u.size();i++) {
            if (i == 0) {
                i1 = 0;
                request1 = new DownloadManager.Request(Uri.parse(u.get(0)));
                request1.setTitle(u.get(0));
                request1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(0) + ".mp4");
                downloadManager1 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request1.allowScanningByMediaScanner();
                request1.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                onClickListenerOne();
                txtView1.setText(t.get(0));
            }
            if (i == 1) {
                request2 = new DownloadManager.Request(Uri.parse(u.get(1)));
                request2.setTitle(u.get(1));
                request2.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(1) + ".mp4");
                downloadManager2 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request2.allowScanningByMediaScanner();
                request2.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                r2.setVisibility(View.VISIBLE);
                i2 = 1;
                onClickListenerTwo();
                txtView2.setText(t.get(1));
            }
            if (i == 2) {

                r3.setVisibility(View.VISIBLE);
                request3 = new DownloadManager.Request(Uri.parse(u.get(2)));
                request3.setTitle(u.get(2));
                request3.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(2) + ".mp4");
                downloadManager3 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request3.allowScanningByMediaScanner();
                request3.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                onClickListenerThree();
                txtView3.setText(t.get(2));
            }
            if (i == 3) {
                r4.setVisibility(View.VISIBLE);
                request4 = new DownloadManager.Request(Uri.parse(u.get(3)));
                request4.setTitle(u.get(3));
                request4.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, u.get(3) + ".mp4");
                downloadManager4 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request4.allowScanningByMediaScanner();
                request4.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                onClickListenerFour();
                txtView4.setText(t.get(3));
            }
            if (i == 4) {
                r5.setVisibility(View.VISIBLE);
                request5 = new DownloadManager.Request(Uri.parse(u.get(4)));
                request5.setTitle(u.get(4));
                request5.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(4) + ".mp4");
                downloadManager5 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request5.allowScanningByMediaScanner();
                request5.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                txtView5.setText(t.get(4));
                onClickListenerFive();
            }
            if (i == 5) {
                r6.setVisibility(View.VISIBLE);
                request6 = new DownloadManager.Request(Uri.parse(u.get(5)));
                request6.setTitle(u.get(5));
                request6.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(5) + ".mp4");
                downloadManager6 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request6.allowScanningByMediaScanner();
                request6.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                txtView6.setText(t.get(5));
                onClickListenerSix();
            }
            if (i == 6) {
                r7.setVisibility(View.VISIBLE);
                request7 = new DownloadManager.Request(Uri.parse(u.get(6)));
                request7.setTitle(u.get(6));
                request7.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(6) + ".mp4");
                downloadManager7 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request7.allowScanningByMediaScanner();
                request7.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                onClickListenerSeven();
                txtView7.setText(t.get(6));
            }
            if (i == 7) {
                r8.setVisibility(View.VISIBLE);
                request8 = new DownloadManager.Request(Uri.parse(u.get(7)));
                request8.setTitle(u.get(7));
                request8.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(7) + ".mp4");
                downloadManager8 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request8.allowScanningByMediaScanner();
                request8.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                onClickListenerEight();
                txtView8.setText(t.get(7));
            }
            if (i == 8) {
                r9.setVisibility(View.VISIBLE);
                request9 = new DownloadManager.Request(Uri.parse(u.get(8)));
                request9.setTitle(u.get(8));
                request9.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(8) + ".mp4");
                downloadManager9 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request9.allowScanningByMediaScanner();
                request9.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                onClickListenerNine();
                txtView9.setText(t.get(8));
            }
            if (i == 9) {
                r10.setVisibility(View.VISIBLE);
                request10 = new DownloadManager.Request(Uri.parse(u.get(9)));
                request10.setTitle(u.get(9));
                request10.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, t.get(9) + ".mp4");
                downloadManager10 = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                request10.allowScanningByMediaScanner();
                request10.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                onClickListenerTen();
                txtView10.setText(t.get(9));
            }
        }
        }
//

       WebViewActivity nn=new WebViewActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dirPath = Utils.getRootDirPath(getContext());



    }

    public void onClickListenerOne() {

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Status.RUNNING == PRDownloader.getStatus(pp)) {
                    PRDownloader.pause(pp);
                    return;
                }

                buttonOne.setEnabled(false);
                progressBarOne.setIndeterminate(true);
                progressBarOne.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(pp)) {
                    PRDownloader.resume(pp);
                    return;
                }
                String storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/YoutubeVideos";
                File f = new File(storagePath);
                if (!f.exists()) {
                    f.mkdir();
                }
//                String storagepATH= Utils.getRootDirPath(getContext());



                        pp = PRDownloader.download(u.get(0), dirPath, "youtube video")
                                .build()
                                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                                    @Override
                                    public void onStartOrResume() {
                                        progressBarOne.setIndeterminate(false);
                                        buttonOne.setEnabled(true);
                                        buttonOne.setText(R.string.pause);
                                        buttonCancelOne.setEnabled(true);

                   id1= downloadManager1.enqueue(request1);
                                    }
                                })
                                .setOnPauseListener(new OnPauseListener() {
                                    @Override
                                    public void onPause() {
                                        buttonOne.setText("resume");
                                    }
                                })
                                .setOnCancelListener(new OnCancelListener() {
                                    @Override
                                    public void onCancel() {
                                        buttonOne.setText("start");
                                        buttonCancelOne.setEnabled(false);
                                        progressBarOne.setProgress(0);
                                        textViewProgressOne.setText("");
                                        pp = 0;
                                        progressBarOne.setIndeterminate(false);
                                    }
                                })
                                .setOnProgressListener(new OnProgressListener() {
                                    @Override
                                    public void onProgress(Progress progress) {
                                        long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                        progressBarOne.setProgress((int) progressPercent);
                                        textViewProgressOne.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                                        progressBarOne.setIndeterminate(false);
                                    }
                                })
                                .start(new OnDownloadListener() {
                                    @Override
                                    public void onDownloadComplete() {
                                        buttonOne.setEnabled(false);
                                        buttonCancelOne.setEnabled(false);
                                        buttonOne.setText("completed");
                                    }

                                    @Override
                                    public void onError(Error error) {
                                        buttonOne.setText("start");
                                        Toast.makeText(getContext(), "some_error_occurred" + " " + "1", Toast.LENGTH_SHORT).show();
                                        textViewProgressOne.setText("");
                                        progressBarOne.setProgress(0);
                                        pp = 0;
                                        buttonCancelOne.setEnabled(false);
                                        progressBarOne.setIndeterminate(false);
                                        buttonOne.setEnabled(true);
                                    }
                                });
                    }


        });

        buttonCancelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(pp);
                downloadManager1.remove(id1);
            }
        });
    }

    private void init() {

    }


    public void onClickListenerTwo() {
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdTwo)) {
                    PRDownloader.pause(downloadIdTwo);
                    return;
                }

                buttonTwo.setEnabled(false);
                progressBarTwo.setIndeterminate(true);
                progressBarTwo.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdTwo)) {
                    PRDownloader.resume(downloadIdTwo);
                    return;
                }

                downloadIdTwo = PRDownloader.download(u.get(1), dirPath, "wechat.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarTwo.setIndeterminate(false);
                                buttonTwo.setEnabled(true);
                                buttonTwo.setText(R.string.pause);
                                buttonCancelTwo.setEnabled(true);
                                buttonCancelTwo.setText(R.string.cancel);
                                id2= downloadManager2.enqueue(request2);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonTwo.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdTwo = 0;
                                buttonTwo.setText(R.string.start);
                                buttonCancelTwo.setEnabled(false);
                                progressBarTwo.setProgress(0);
                                textViewProgressTwo.setText("");
                                progressBarTwo.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarTwo.setProgress((int) progressPercent);
                                textViewProgressTwo.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonTwo.setEnabled(false);
                                buttonCancelTwo.setEnabled(false);
                                buttonTwo.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonTwo.setText(R.string.start);
                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "2", Toast.LENGTH_SHORT).show();
                                textViewProgressTwo.setText("");
                                progressBarTwo.setProgress(0);
                                downloadIdTwo = 0;
                                buttonCancelTwo.setEnabled(false);
                                progressBarTwo.setIndeterminate(false);
                                buttonTwo.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdTwo);
                downloadManager2.remove(id2);
            }
        });
    }

    public void onClickListenerThree() {
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdThree)) {
                    PRDownloader.pause(downloadIdThree);
                    return;
                }

                buttonThree.setEnabled(false);
                progressBarThree.setIndeterminate(true);
                progressBarThree.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdThree)) {
                    PRDownloader.resume(downloadIdThree);
                    return;
                }
                downloadIdThree = PRDownloader.download(u.get(2), dirPath, "instagram.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarThree.setIndeterminate(false);
                                buttonThree.setEnabled(true);
                                buttonThree.setText(R.string.pause);
                                buttonCancelThree.setEnabled(true);
                                buttonCancelThree.setText(R.string.cancel);
                                id3= downloadManager3.enqueue(request3);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonThree.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdThree = 0;
                                buttonThree.setText(R.string.start);
                                buttonCancelThree.setEnabled(false);
                                progressBarThree.setProgress(0);
                                textViewProgressThree.setText("");
                                progressBarThree.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarThree.setProgress((int) progressPercent);
                                textViewProgressThree.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonThree.setEnabled(false);
                                buttonCancelThree.setEnabled(false);
                                buttonThree.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonThree.setText(R.string.start);
                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "3", Toast.LENGTH_SHORT).show();
                                textViewProgressThree.setText("");
                                progressBarThree.setProgress(0);
                                downloadIdThree = 0;
                                buttonCancelThree.setEnabled(false);
                                progressBarThree.setIndeterminate(false);
                                buttonThree.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdThree);
                downloadManager3.remove(id3);
            }
        });
    }

    public void onClickListenerFour() {
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdFour)) {
                    PRDownloader.pause(downloadIdFour);
                    return;
                }

                buttonFour.setEnabled(false);
                progressBarFour.setIndeterminate(true);
                progressBarFour.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdFour)) {
                    PRDownloader.resume(downloadIdFour);
                    return;
                }
                downloadIdFour = PRDownloader.download(u.get(3), dirPath, "flashlight.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarFour.setIndeterminate(false);
                                buttonFour.setEnabled(true);
                                buttonFour.setText(R.string.pause);
                                buttonCancelFour.setEnabled(true);
                                buttonCancelFour.setText(R.string.cancel);
                                id4= downloadManager4.enqueue(request4);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonFour.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdFour = 0;
                                buttonFour.setText(R.string.start);
                                buttonCancelFour.setEnabled(false);
                                progressBarFour.setProgress(0);
                                textViewProgressFour.setText("");
                                progressBarFour.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarFour.setProgress((int) progressPercent);
                                textViewProgressFour.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFour.setEnabled(false);
                                buttonCancelFour.setEnabled(false);
                                buttonFour.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFour.setText(R.string.start);
                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "4", Toast.LENGTH_SHORT).show();
                                textViewProgressFour.setText("");
                                progressBarFour.setProgress(0);
                                downloadIdFour = 0;
                                buttonCancelFour.setEnabled(false);
                                progressBarFour.setIndeterminate(false);
                                buttonFour.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdFour);
                downloadManager4.remove(id4);
            }
        });
    }

    public void onClickListenerFive() {
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdFive)) {
                    PRDownloader.pause(downloadIdFive);
                    return;
                }

                buttonFive.setEnabled(false);
                progressBarFive.setIndeterminate(true);
                progressBarFive.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdFive)) {
                    PRDownloader.resume(downloadIdFive);
                    return;
                }
                downloadIdFive = PRDownloader.download(u.get(4), dirPath, "screenrecorder.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarFive.setIndeterminate(false);
                                buttonFive.setEnabled(true);
                                buttonFive.setText(R.string.pause);
                                buttonCancelFive.setEnabled(true);
                                buttonCancelFive.setText(R.string.cancel);
                                id5= downloadManager5.enqueue(request5);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonFive.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdFive = 0;
                                buttonFive.setText(R.string.start);
                                buttonCancelFive.setEnabled(false);
                                progressBarFive.setProgress(0);
                                textViewProgressFive.setText("");
                                progressBarFive.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarFive.setProgress((int) progressPercent);
                                textViewProgressFive.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFive.setEnabled(false);
                                buttonCancelFive.setEnabled(false);
                                buttonFive.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFive.setText(R.string.start);
                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "5", Toast.LENGTH_SHORT).show();
                                textViewProgressFive.setText("");
                                progressBarFive.setProgress(0);
                                downloadIdFive = 0;
                                buttonCancelFive.setEnabled(false);
                                progressBarFive.setIndeterminate(false);
                                buttonFive.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdFive);
                downloadManager5.remove(id5);
            }
        });

    }

    public void onClickListenerSix() {
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdSix)) {
                    PRDownloader.pause(downloadIdSix);
                    return;
                }

                buttonSix.setEnabled(false);
                progressBarSix.setIndeterminate(true);
                progressBarSix.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdSix)) {
                    PRDownloader.resume(downloadIdSix);
                    return;
                }
                downloadIdSix = PRDownloader.download(u.get(5), dirPath, "callrecorder.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarSix.setIndeterminate(false);
                                buttonSix.setEnabled(true);
                                buttonSix.setText(R.string.pause);
                                buttonCancelSix.setEnabled(true);
                                buttonCancelSix.setText(R.string.cancel);
                                id6= downloadManager6.enqueue(request6);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonSix.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdSix = 0;
                                buttonSix.setText(R.string.start);
                                buttonCancelSix.setEnabled(false);
                                progressBarSix.setProgress(0);
                                textViewProgressSix.setText("");
                                progressBarSix.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarSix.setProgress((int) progressPercent);
                                textViewProgressSix.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonSix.setEnabled(false);
                                buttonCancelSix.setEnabled(false);
                                buttonSix.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonSix.setText(R.string.start);
//                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "6", Toast.LENGTH_SHORT).show();
                                textViewProgressSix.setText("");
                                progressBarSix.setProgress(0);
                                downloadIdSix = 0;
                                buttonCancelSix.setEnabled(false);
                                progressBarSix.setIndeterminate(false);
                                buttonSix.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdSix);
                downloadManager6.remove(id6);
            }
        });

    }

    public void onClickListenerSeven() {
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdSeven)) {
                    PRDownloader.pause(downloadIdSeven);
                    return;
                }

                buttonSeven.setEnabled(false);
                progressBarSeven.setIndeterminate(true);
                progressBarSeven.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdSeven)) {
                    PRDownloader.resume(downloadIdSeven);
                    return;
                }
                downloadIdSeven = PRDownloader.download(u.get(6), dirPath, "soundprofile.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarSeven.setIndeterminate(false);
                                buttonSeven.setEnabled(true);
                                buttonSeven.setText(R.string.pause);
                                buttonCancelSeven.setEnabled(true);
                                buttonCancelSeven.setText(R.string.cancel);
                                id7= downloadManager7.enqueue(request7);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonSeven.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdSeven = 0;
                                buttonSeven.setText(R.string.start);
                                buttonCancelSeven.setEnabled(false);
                                progressBarSeven.setProgress(0);
                                textViewProgressSeven.setText("");
                                progressBarSeven.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarSeven.setProgress((int) progressPercent);
                                textViewProgressSeven.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonSeven.setEnabled(false);
                                buttonCancelSeven.setEnabled(false);
                                buttonSeven.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonSeven.setText(R.string.start);
                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "7", Toast.LENGTH_SHORT).show();
                                textViewProgressSeven.setText("");
                                progressBarSeven.setProgress(0);
                                downloadIdSeven = 0;
                                buttonCancelSeven.setEnabled(false);
                                progressBarSeven.setIndeterminate(false);
                                buttonSeven.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdSeven);
                downloadManager7.remove(id7);
            }
        });

    }

    public void onClickListenerEight() {
        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdEight)) {
                    PRDownloader.pause(downloadIdEight);
                    return;
                }

                buttonEight.setEnabled(false);
                progressBarEight.setIndeterminate(true);
                progressBarEight.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdEight)) {
                    PRDownloader.resume(downloadIdEight);
                    return;
                }
                downloadIdEight = PRDownloader.download(u.get(7), dirPath, "evernote.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarEight.setIndeterminate(false);
                                buttonEight.setEnabled(true);
                                buttonEight.setText(R.string.pause);
                                buttonCancelEight.setEnabled(true);
                                buttonCancelEight.setText(R.string.cancel);
                                id8= downloadManager8.enqueue(request8);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonEight.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdEight = 0;
                                buttonEight.setText(R.string.start);
                                buttonCancelEight.setEnabled(false);
                                progressBarEight.setProgress(0);
                                textViewProgressEight.setText("");
                                progressBarEight.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarEight.setProgress((int) progressPercent);
                                textViewProgressEight.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonEight.setEnabled(false);
                                buttonCancelEight.setEnabled(false);
                                buttonEight.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonEight.setText(R.string.start);
                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "8", Toast.LENGTH_SHORT).show();
                                textViewProgressEight.setText("");
                                progressBarEight.setProgress(0);
                                downloadIdEight = 0;
                                buttonCancelEight.setEnabled(false);
                                progressBarEight.setIndeterminate(false);
                                buttonEight.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdEight);
                downloadManager8.remove(id8);
            }
        });
    }

    public void onClickListenerNine() {
        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdNine)) {
                    PRDownloader.pause(downloadIdNine);
                    return;
                }

                buttonNine.setEnabled(false);
                progressBarNine.setIndeterminate(true);
                progressBarNine.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdNine)) {
                    PRDownloader.resume(downloadIdNine);
                    return;
                }
                downloadIdNine = PRDownloader.download(u.get(8), dirPath, "ucbrowser.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarNine.setIndeterminate(false);
                                buttonNine.setEnabled(true);
                                buttonNine.setText(R.string.pause);
                                buttonCancelNine.setEnabled(true);
                                buttonCancelNine.setText(R.string.cancel);
                                id9= downloadManager9.enqueue(request9);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonNine.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdNine = 0;
                                buttonNine.setText(R.string.start);
                                buttonCancelNine.setEnabled(false);
                                progressBarNine.setProgress(0);
                                textViewProgressNine.setText("");
                                progressBarNine.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarNine.setProgress((int) progressPercent);
                                textViewProgressNine.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonNine.setEnabled(false);
                                buttonCancelNine.setEnabled(false);
                                buttonNine.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonNine.setText(R.string.start);
                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "9", Toast.LENGTH_SHORT).show();
                                textViewProgressNine.setText("");
                                progressBarNine.setProgress(0);
                                downloadIdNine = 0;
                                buttonCancelNine.setEnabled(false);
                                progressBarNine.setIndeterminate(false);
                                buttonNine.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdNine);
                downloadManager9.remove(id9);
            }
        });
    }

    public void onClickListenerTen() {
        buttonTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdTen)) {
                    PRDownloader.pause(downloadIdTen);
                    return;
                }

                buttonTen.setEnabled(false);
                progressBarTen.setIndeterminate(true);
                progressBarTen.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdTen)) {
                    PRDownloader.resume(downloadIdTen);
                    return;
                }
                downloadIdTen = PRDownloader.download(u.get(2), dirPath, "barcodescanner.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarTen.setIndeterminate(false);
                                buttonTen.setEnabled(true);
                                buttonTen.setText(R.string.pause);
                                buttonCancelTen.setEnabled(true);
                                buttonCancelTen.setText(R.string.cancel);
                                id10= downloadManager10.enqueue(request10);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonTen.setText("resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdTen = 0;
                                buttonTen.setText(R.string.start);
                                buttonCancelTen.setEnabled(false);
                                progressBarTen.setProgress(0);
                                textViewProgressTen.setText("");
                                progressBarTen.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarTen.setProgress((int) progressPercent);
                                textViewProgressTen.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonTen.setEnabled(false);
                                buttonCancelTen.setEnabled(false);
                                buttonTen.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonTen.setText(R.string.start);
                                Toast.makeText(getContext(), getString(R.string.some_error_occurred) + " " + "10", Toast.LENGTH_SHORT).show();
                                textViewProgressTen.setText("");
                                progressBarTen.setProgress(0);
                                downloadIdTen = 0;
                                buttonCancelTen.setEnabled(false);
                                progressBarTen.setIndeterminate(false);
                                buttonTen.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdTen);
                downloadManager10.remove(id10);
            }
        });
    }


}
