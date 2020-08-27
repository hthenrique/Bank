package com.example.bank.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.bank.R;
import com.example.bank.ui.home.HomeActivity;
import com.example.bank.ui.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler splashThread = new Handler();
        splashThread.postDelayed(this::showLogin,2000);

        SharedPreferences sharedPreferences = getSharedPreferences("darkMode", MODE_PRIVATE);
            sharedPreferences.getBoolean("keyDarkMode",false);
            if (sharedPreferences.getBoolean("keyDarkMode",false)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

    }

    private void showLogin(){
        SharedPreferences preferences = getSharedPreferences( "login" , Context.MODE_PRIVATE);
        preferences.getBoolean("isUserLogin",false);
        if (preferences.contains("isUserLogin")){
            String email = preferences.getString("email", "");
            Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
            intent.putExtra("email", email);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            Intent start = new Intent(this, LoginActivity.class);
            startActivity(start);
            finish();
        }
    }
}
