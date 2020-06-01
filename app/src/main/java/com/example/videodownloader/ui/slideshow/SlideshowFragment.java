package com.example.videodownloader.ui.slideshow;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.videodownloader.R;

import java.lang.reflect.Method;

import static android.content.ContentValues.TAG;

public class SlideshowFragment extends Fragment implements View.OnClickListener {

    private SlideshowViewModel slideshowViewModel;
    private WifiManager manager;
    private CheckBox wifi,data,mobile,settings;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        manager= (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi= root.findViewById(R.id.checkbox_wifi);
        wifi.setOnClickListener(this);
        data= root.findViewById(R.id.checkbox_data);
        data.setOnClickListener(this);
        mobile= root.findViewById(R.id.checkbox_MobileData);
        mobile.setOnClickListener(this);
        settings= root.findViewById(R.id.checkbox_Settings);
        settings.setOnClickListener(this);





        return root;
    }



    @Override
    public void onClick(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_wifi:
                if (checked){
                    manager.setWifiEnabled(true);
                }
                // Put some meat on the sandwich
                else
                    manager.setWifiEnabled(false);// Remove the meat
                break;
            case R.id.checkbox_data:
                if (checked){

                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(
                            "com.android.settings",
                            "com.android.settings.Settings$DataUsageSummaryActivity"));
                    startActivity(intent);

                }
                // Cheese me
                else
                    // I'm lactose intolerant
                    break;
                // TODO: Veggie sandwich

            case R.id.checkbox_MobileData:
                if (checked){

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                    startActivity(intent);

                }
                // Cheese me
                else
                    // I'm lactose intolerant
                    break;
                // TODO: Veggie sandwich


            case R.id.checkbox_Settings:
                if (checked){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                    intent.setData(uri);
                    getActivity().startActivity(intent);

                }
                // Cheese me
                else
                    // I'm lactose intolerant
                    break;
                // TODO: Veggie sandwich
        }
    }

}
