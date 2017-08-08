package com.pandora.rxandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pandora.rxandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;


public class HelloFragment extends Fragment {
    @BindView(R.id.tv_display) TextView mTextView;

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_hello, container, false);
        mUnbinder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mUnbinder = null;
    }


    @OnClick(R.id.btn_hello)
    void start() {
        getObservable()
                .subscribe(getObserver());

        // case 1 : original
//        Observable.create(
//                new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> e) throws Exception {
//                        e.onNext("hello world!");
//                        e.onComplete();
//                    }
//                }).subscribe(getObserver());


        // case 2 : lambda
//        Observable.<String>create(s -> {
//            s.onNext("Hello, world!");
//            s.onComplete();
//        }).subscribe(o -> textView.setText(o));


        // case 3 : other Observable creator and reference method.
//        Observable.just("Hello, world!")
//                .subscribe(textView::setText);

    }

    private Observable<String> getObservable() {
        return Observable.just("Hello world!");
    }

    private Observer<? super String> getObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                mTextView.setText(R.string.hello);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


}
