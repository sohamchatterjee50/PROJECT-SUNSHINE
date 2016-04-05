package com.example.sohamchatterjee.weather_21;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.preference.PreferenceManager;
import android.util.Log;

public class MainActivity extends AppCompatActivity {



    private final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity( new Intent(this,SettingsActivity.class));
            return true;
        }

        if (id == R.id.action_map) {
                       openPreferredLocationInMap();
                        return true;
                    }

        return super.onOptionsItemSelected(item);
    }


    private void openPreferredLocationInMap() {
                SharedPreferences sharedPrefs =
                        PreferenceManager.getDefaultSharedPreferences(this);
               String location = sharedPrefs.getString(
                         getString(R.string.pref_location_key),
                         getString(R.string.pref_location_default));

                // Using the URI scheme for showing a location found on a map.  This super-handy
                         // intent can is detailed in the "Common Intents" page of Android's developer site:
                                // http://developer.android.com/guide/components/intents-common.html#Maps
                                               Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                                .appendQueryParameter("q", location)
                                .build();

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(geoLocation);

                        if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
                    }
            }












}
