package com.example.fyp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainSettingsFragment()).commit();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    public static class MainSettingsFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);
//            bindSummaryValue(findPreference("enable_wifi"));
            bindSummaryValue(findPreference("max_download_tasks"));
          //  bindSummaryValue(findPreference("fast_download"));
            bindSummaryValue(findPreference("online_video_quality"));
            bindSummaryValue(findPreference("play_videos_with"));

        }
    }
    public void retreiveData()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext() );
    }

    private static void bindSummaryValue(Preference preference)
    {

        preference.setOnPreferenceChangeListener(listener);
        listener.onPreferenceChange(preference, PreferenceManager.
                getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(),""));
    }


    private static  Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            String stringvalue = newValue.toString();
            if(preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringvalue);
                preference.setSummary(index > 0 ? listPreference.getEntries()[index] : null);
            }

          else if(preference instanceof SwitchPreference)
            {
                preference.setSummary(stringvalue);

            }
          else if (preference instanceof CheckBoxPreference)
          {
              preference.setSummary(stringvalue);

          }
            return true;
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case  android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

