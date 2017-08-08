package com.pandora.rxandroid.volley;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class LocalVolley {

    private static RequestQueue sRequestQueue;

    private LocalVolley() { }

    public static void init(Context context) {
        sRequestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue() {
        if (sRequestQueue != null) {
            return sRequestQueue;
        } else {
            throw new IllegalStateException("Not inited");
        }
    }
}
