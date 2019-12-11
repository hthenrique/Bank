package com.example.bank.ui.extract;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bank.Connection.MyReceiver;
import com.example.bank.Connection.NetworkUtil;
import com.example.bank.R;

import java.util.Objects;

public class ExtractActivity extends AppCompatActivity {
    ExtractFragment extractFragment;
    private BroadcastReceiver MyReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extract);
        extractFragment = new ExtractFragment();
        initFragment(ExtractFragment.newInstance());

        if (NetworkUtil.getConnectivityStatus(Objects.requireNonNull(this))){
            MyReceiver = new MyReceiver();
            broadcastIntent();
        }if (null == savedInstanceState){
            extractFragment = new ExtractFragment();
            initFragment(ExtractFragment.newInstance());
        }
    }
    private void broadcastIntent() {
        this.registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    public void onPause() {
        super.onPause();
        //this.unregisterReceiver(MyReceiver);
    }

    private void initFragment(Fragment extractFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content_extract, extractFragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
