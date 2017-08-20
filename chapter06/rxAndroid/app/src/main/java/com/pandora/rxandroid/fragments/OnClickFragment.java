package com.pandora.rxandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.jakewharton.rxbinding2.view.RxView;
import com.pandora.rxandroid.R;
import com.pandora.rxandroid.logs.LogAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;


public class OnClickFragment extends Fragment {
    public static final String TAG = OnClickFragment.class.getSimpleName();
    private static final int SEVEN = 7;
    @BindView(R.id.lv_log)
    ListView mLogView;
    @BindView(R.id.btn_click_observer)
    Button mButton;
    @BindView(R.id.btn_click_observer_lambda)
    Button mButtonLambda;
    @BindView(R.id.btn_click_observer_binding)
    Button mButtonBinding;
    @BindView(R.id.btn_click_observer_extra)
    Button mButtonExtra;
    private Unbinder mUnbinder;
    private LogAdapter mLogAdapter;
    private List<String> mLogs;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_on_click, container, false);
        mUnbinder = ButterKnife.bind(this, layout);
        setupLogger();
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getClickEventObservable()
                .map(s -> "clicked")
                .subscribe(getObserver());

        getClickEventObservableWithLambda()
                .map(s -> "clicked lambda")
                .subscribe(this::log);

        getClickEventObservableWithRxBinding()
                .subscribe(this::log);

        getClickEventObservableExtra()
                .map(local -> SEVEN)
                .flatMap(this::compareNumbers)
                .subscribe(this::log);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    private Observable<View> getClickEventObservable() {
        return Observable.create(new ObservableOnSubscribe<View>() {
            @Override
            public void subscribe(ObservableEmitter<View> e) throws Exception {
                mButton.setOnClickListener(e::onNext);
            }
        });
    }

    private Observable<View> getClickEventObservableWithLambda() {
        return Observable.create(s -> mButtonLambda.setOnClickListener(s::onNext));
    }

    private Observable<String> getClickEventObservableWithRxBinding() {
        return RxView.clicks(mButtonBinding)
                .map(s -> "Clicked Rxbinding");
    }

    private Observable<View> getClickEventObservableExtra() {
        return Observable.create(s -> mButtonExtra.setOnClickListener(s::onNext));
    }


    private Observable<String> compareNumbers(int input) {
        return Observable.just(input)
                .flatMap( in -> {
                    Random random = new Random();
                    int data = random.nextInt(10);
                    return Observable.just("local : " + in, "remote : " + data, "result = " + (in == data));
                });
    }


    private DisposableObserver<? super String> getObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) { log(s); }

            @Override
            public void onError(Throwable e) { log(e.getMessage()); }

            @Override
            public void onComplete() { log("complete"); }
        };
    }
    //        getClickEventObservable().subscribe();
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
