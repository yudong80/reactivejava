package com.pandora.rxandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pandora.rxandroid.R;
import com.pandora.rxandroid.logs.LogAdapter;
import com.pandora.rxandroid.square.Contributor;
import com.pandora.rxandroid.square.GitHubServiceApi;
import com.pandora.rxandroid.square.RestfulAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OkHttpFragment extends Fragment {

    private static final String sName = "jungjoonpark-pandora";
    private static final String sRepo = "rxAndroid";

    @BindView(R.id.ohf_lv_log)
    ListView mLogView;

    private Unbinder mUnbinder;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_okhttp, container, false);

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
        mCompositeDisposable.clear();
    }

    @OnClick(R.id.ohf_btn_retrofit)
    void getRetrofit() {
        startRetrofit();
    }

    @OnClick(R.id.ohf_btn_get_retrofit_okhttp)
    void getOkHttp() {
        startOkHttp();
    }

    @OnClick(R.id.ohf_btn_get_retrofit_okhttp_rx)
    void getRx() {
        startRx();
    }


    /**
     * retrofit + okHttp( Call의 내부 )
     */
    private void startRetrofit() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getSimpleApi();
        Call<List<Contributor>> call = service.getCallContributors(sName, sRepo);
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    List<Contributor> contributors = response.body();
                    for (Contributor c : contributors) {
                        log(c.toString());
                    }
                } else {
                    log("not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }


    /**
     * retrofit + okHttp
     */
    private void startOkHttp() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getServiceApi();
        Call<List<Contributor>> call = service.getCallContributors(sName, sRepo);

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    List<Contributor> contributors = response.body();
                    for (Contributor c : contributors) {
                        log(c.toString());
                    }
                } else {
                    log("not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }


    /**
     * retrofit + okHttp + rxJava
     */
    private void startRx() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getServiceApi();
        Observable<List<Contributor>> observable = service.getObContributors(sName, sRepo);

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Contributor>>() {
                    @Override
                    public void onNext(List<Contributor> contributors) {
                        for (Contributor c : contributors) {
                            log(c.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        log(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        log("complete");
                    }
                })


        );
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
