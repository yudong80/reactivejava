package com.pandora.rxandroid.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pandora.rxandroid.R;
import com.pandora.rxandroid.logs.LogAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SchedulerFragment extends Fragment {

    @BindView(R.id.lv_scheduler_log)
    ListView mLogView;

    Unbinder mUnbinder;
    private MyAsyncTask myAsyncTask;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_scheduler, container, false);

        mUnbinder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupLogger();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }



    @OnClick(R.id.btn_async_task)
    void onClickAync() {
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("hello", "async", "world");
    }

    @OnClick(R.id.btn_rx_task)
    void onClickRx() {
        Observable.just("Hello", "rx", "world")
                .reduce((x, y) -> x + " " + y)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.getMainLooper()))
                .subscribe(getObserver());
    }

    private MaybeObserver<String> getObserver() {
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                log(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder word = new StringBuilder();
            for (String s : params) {
                word.append(s).append(" ");
            }
            return word.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            log(result);
        }
    }

    // Log
    private LogAdapter mLogAdapter;
    private List<String> mLogs;

    private void log(String log) {
        mLogs.add(log);
        mLogAdapter.clear();
        mLogAdapter.addAll(mLogs);
    }

    private void setupLogger() {
        mLogs = new ArrayList<>();
        mLogAdapter = new LogAdapter(getActivity(), new ArrayList<>());
        mLogView.setAdapter(mLogAdapter);
    }
}
