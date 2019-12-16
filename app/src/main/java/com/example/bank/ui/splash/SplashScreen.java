package com.example.bank.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bank.R;
import com.example.bank.ui.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(this::showLogin,2000);
    }

    private void showLogin(){
        Intent start = new Intent(this, LoginActivity.class);
        startActivity(start);
        finish();
    }
}
