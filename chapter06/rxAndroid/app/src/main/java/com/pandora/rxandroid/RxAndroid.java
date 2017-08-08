package com.pandora.rxandroid;

import android.app.Application;

import com.pandora.rxandroid.volley.LocalVolley;


public class RxAndroid extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LocalVolley.init(getApplicationContext());
    }
}
