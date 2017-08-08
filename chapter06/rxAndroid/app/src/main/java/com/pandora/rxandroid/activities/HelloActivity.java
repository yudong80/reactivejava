package com.pandora.rxandroid.activities;


import android.os.Bundle;
import android.widget.TextView;

import com.pandora.rxandroid.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subscribers.DisposableSubscriber;

public class HelloActivity extends RxAppCompatActivity {
    public static final String TAG = HelloActivity.class.getSimpleName();

    @BindView(R.id.tv_hello) TextView textView;

    private Disposable mDisposable;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        mUnbinder = ButterKnife.bind(this);

        DisposableObserver<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                textView.setText(s);
            }
            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        };

//        DisposableSubscriber<String> subscriber = new DisposableSubscriber<String>() {
//            @Override
//            public void onNext(String s) {
//                textView.setText(s);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

        mDisposable = Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    e.onNext("hello world!");
                    e.onComplete();
                }
            }).subscribeWith(observer);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
