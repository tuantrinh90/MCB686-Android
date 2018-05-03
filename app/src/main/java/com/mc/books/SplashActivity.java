package com.mc.books;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.auth0.android.jwt.JWT;
import com.mc.common.activities.AloneFragmentActivity;
import com.mc.common.activities.BaseAppCompatActivity;
import com.mc.books.fragments.account.signin.SignInFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by HungND on 3/2/18.
 */

public class SplashActivity extends BaseAppCompatActivity {


    @BindView(R.id.progress)
    GifImageView progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AloneFragmentActivity.with(getAppContext())
                        .start(SignInFragment.class);
                finish();
            }
        }, 2000);

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}