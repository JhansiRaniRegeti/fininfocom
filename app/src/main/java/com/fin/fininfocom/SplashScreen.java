package com.fin.fininfocom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.fin.fininfocom.utils.InternetConnection;
import com.fin.fininfocom.utils.MySharedPreferences;

public class SplashScreen extends AppCompatActivity {
    ImageView splash_image;
    MySharedPreferences mySharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        if (!InternetConnection.isNetworkAvailable(this)) {
            InternetConnection.networkDialog(this);
            return;
        }
        splash_image = findViewById(R.id.splash_image);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        splash_image.startAnimation(animation1);

        mySharedPreferences = new MySharedPreferences(SplashScreen.this);
        callSplash();
    }

    private void callSplash() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (mySharedPreferences.getLoginStatus()) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();

                }else {
                    Intent intent= new Intent(SplashScreen.this, LoginScreen.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2200);
    }
}