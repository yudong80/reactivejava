package com.pandora.rxandroid.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pandora.rxandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;



public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getSimpleName();

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
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
    void demoHello() { startDemo(new HelloFragment()); }

    @OnClick(R.id.btn_scheduler)
    void demoSchedulers() { startDemo(new SchedulerFragment()); }

    @OnClick(R.id.btn_recycler_view)
    void demoClickRecycle() { startDemo(new RecyclerViewFragment());}

    @OnClick(R.id.btn_debounce)
    void demoDebounce() { startDemo(new DebounceFragment());}

    @OnClick(R.id.btn_polling)
    void demoPolling() { startDemo(new PollingFragment()); }

    @OnClick(R.id.btn_click_observer)
    void demoClickObserver() { startDemo(new OnClickFragment());}

    @OnClick(R.id.btn_debounce_search)
    void demoDebounceSearch() { startDemo(new DebounceSearchFragment()); }

    @OnClick(R.id.btn_volley)
    void demoVolley() { startDemo(new VolleyFragment()); }

    @OnClick(R.id.btn_okHttp)
    void demoOkHttp() { startDemo(new OkHttpFragment()); }


    private void startDemo(@NonNull Fragment fragment) {
        final String tag = fragment.getClass().getSimpleName();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }
}
