package com.example.bank.ui.settings;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.example.bank.Connection.MyReceiver;
import com.example.bank.Connection.NetworkUtil;
import com.example.bank.R;
import com.example.bank.ui.home.HomeActivity;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {
    private Switch switchDarkMode;

    private BroadcastReceiver MyReceiver = null;
    private String email;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (!NetworkUtil.getConnectivityStatus(Objects.requireNonNull(this))) {
            MyReceiver = new MyReceiver();
            broadcastIntent();
        }

        SharedPreferences sharedPrefs = getSharedPreferences("darkMode", MODE_PRIVATE);


        email = this.getIntent().getExtras().getString("email");

        Toolbar mToolbar = findViewById(R.id.toolbarSettings);
        this.setSupportActionBar(mToolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        switchDarkMode = findViewById(R.id.switchDarkMode);
        switchDarkMode.setChecked(sharedPrefs.getBoolean("keyDarkMode", false));
        switchDarkMode.setOnClickListener(v -> {
            if (switchDarkMode.isChecked()){
                switchDarkMode.setChecked(true);
                Toast.makeText(SettingsActivity.this, "Dark Mode on", Toast.LENGTH_SHORT).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                SharedPreferences.Editor editor = getSharedPreferences("darkMode", MODE_PRIVATE).edit();
                editor.putBoolean("keyDarkMode", true);
                editor.commit();
            }else {
                switchDarkMode.setChecked(false);
                Toast.makeText(SettingsActivity.this, "Dark Mode off", Toast.LENGTH_SHORT).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                SharedPreferences.Editor editor = getSharedPreferences("darkMode", MODE_PRIVATE).edit();
                editor.putBoolean("keyDarkMode", false);
                editor.commit();
            }
        });
    }

    private void broadcastIntent() {
        this.registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("email", email);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}