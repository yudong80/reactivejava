package com.pandora.rxandroid.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.pandora.rxandroid.R;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TimerActivity extends AppCompatActivity {

    private final static String TAG = TimerActivity.class.getSimpleName();

    Unbinder mUnbinder;
    @BindView(R.id.textView)
    TextView mTextView;

    private int DELAY = 0;
    private int PERIOD = 1000;
    private int count;


    //TimerTask
    private Timer mTimer;

    public void timerStart() {
        count = 0;
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // Do something worthwhile
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(String.valueOf(count++));
                    }
                });
            }
        }, DELAY, PERIOD);
    }

    public void timerStop() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }


    //CountDownTimer
    private static final int MILLISINFUTURE = 11 * 1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;

    CountDownTimer mCountDownTimer;

    private void initCountDownTask() {
        mCountDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long l) {
                mTextView.setText(String.valueOf(count--));
            }

            @Override
            public void onFinish() {
                mTextView.setText("Finish .");
            }
        };
    }

    public void countDownTimerStart() {
        count = 10;
        mCountDownTimer.start();
    }

    public void countDownTimerStop() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }


    //Handler
    private Handler mHandler;
    private Runnable timer = new Runnable() {
        public void run() {
            // write here whaterver you want to repeat
            mTextView.setText(String.valueOf(count++));
            mHandler.postDelayed(this, 1000);
        }
    };

    private void initHandler() {
        mHandler = new Handler();
    }


    public void handerStart() {
        count = 0;
        mHandler.postDelayed(timer, 0);
    }

    public void handlerStop() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }


    public void stop() {
        timerStop();
        countDownTimerStop();
        handlerStop();
    }

    @OnClick(R.id.button)
    void timerTask() {
        stop();
        timerStart();
    }

    @OnClick(R.id.button2)
    void countDownTask() {
        stop();
        countDownTimerStart();
    }

    @OnClick(R.id.button3)
    void handler() {
        stop();
        handerStart();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mUnbinder = ButterKnife.bind(this);

        initCountDownTask();
        initHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        stop();
    }
}
