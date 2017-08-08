package com.pandora.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pandora.rxandroid.fragments.MainFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new MainFragment(), MainFragment.TAG)
                    .commit();

        }
    }
}
