package com.pandora.rxandroid.square;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jungjoon on 2017. 2. 26..
 */

public class RestfulAdapter {

    private static final String BASE_URI = "https://api.github.com/";

    private RestfulAdapter() { }
    private static class Singleton {
        private static final RestfulAdapter instance = new RestfulAdapter();
    }

    public static RestfulAdapter getInstance() {
        return Singleton.instance;
    }

    public GitHubServiceApi getServiceApi() {

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URI)
                .build();

        return retrofit.create(GitHubServiceApi.class);
    }

    public GitHubServiceApi getSimpleApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GitHubServiceApi.class);
    }
}
