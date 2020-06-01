package com.example.videodownloader.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.videodownloader.R;
import com.example.videodownloader.WebViewActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    View v;
    ImageView mYoutube ,mFacebook, mInstagram;
    // SendMessage SM;
    String utube = "https://www.youtube.com", fb= "https://www.facebook.com" , insta= "https://www.instagram.com" ;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mYoutube = getView().findViewById(R.id.iv_youtube);
        mFacebook = getView().findViewById(R.id.iv_facebook);
        mInstagram =  getView().findViewById(R.id.iv_instagram);

        mYoutube.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                Intent mainIntent = new Intent(getActivity(), WebViewActivity.class);
                mainIntent.putExtra("url",utube);
                mainIntent.putExtra("platform","youtube");
                getActivity().startActivity(mainIntent);
            }
        });


        mFacebook.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        WebViewActivity.class);
                mainIntent.putExtra("url",fb);
                mainIntent.putExtra("platform","facebook");
                getActivity().startActivity(mainIntent);
            }
        });

        mInstagram.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        WebViewActivity.class);
                // SM.sendData("uRl");
                mainIntent.putExtra("url",insta);
                mainIntent.putExtra("platform","instagram");
                getActivity().startActivity(mainIntent);
            }
        });



    }

}
