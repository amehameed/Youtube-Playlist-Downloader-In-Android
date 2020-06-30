package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fyp.Activity.FacebookWebActivity;

public class FragmentHome extends Fragment {


    View v;
    ImageView mYoutube ,mFacebook, mInstagram;
   // SendMessage SM;
    String utube = "https://www.youtube.com", fb= "https://www.facebook.com" , insta= "https://www.instagram.com" ;


    public FragmentHome() {


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

                Intent mainIntent = new Intent(getActivity(),WebViewActivity.class);
                mainIntent.putExtra("url",utube);
                getActivity().startActivity(mainIntent);
            }
        });


        mFacebook.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        FacebookWebActivity.class);
                mainIntent.putExtra("url",fb);
                getActivity().startActivity(mainIntent);
            }
        });

        mInstagram.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        WebViewInstaActivity.class);
               // SM.sendData("uRl");
                mainIntent.putExtra("url",insta);
                getActivity().startActivity(mainIntent);
            }
        });



    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       v=inflater.inflate(R.layout.fragment_home , container,false);

    return v;
    }

/*

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
         //   SM= (SendMessage)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }
}

*/
}

