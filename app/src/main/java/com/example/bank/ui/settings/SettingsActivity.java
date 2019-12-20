package com.example.bank.ui.settings;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bank.Connection.MyReceiver;
import com.example.bank.Connection.NetworkUtil;
import com.example.bank.R;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private BroadcastReceiver MyReceiver = null;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (!NetworkUtil.getConnectivityStatus(Objects.requireNonNull(this))) {
            MyReceiver = new MyReceiver();
            broadcastIntent();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
    }

    private void broadcastIntent() {
        this.registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    public void onPause() {
        super.onPause();
    }

}