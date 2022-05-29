package com.mpfaith.adminmp5.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import com.mpfaith.adminmp5.R;

public class WelcomeActivity extends AppCompatActivity {
    public static boolean tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = isTablet(WelcomeActivity.this);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();

            }
        },2000);
    }
    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

}