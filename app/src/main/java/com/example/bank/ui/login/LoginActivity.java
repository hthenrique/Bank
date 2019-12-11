package com.example.bank.ui.login;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bank.Connection.MyReceiver;
import com.example.bank.R;

public class LoginActivity extends AppCompatActivity {
    LoginFragment loginFragment;
    private BroadcastReceiver MyReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyReceiver = new MyReceiver();
        broadcastIntent();

        if (null == savedInstanceState){
            loginFragment = new LoginFragment();
            initFragment(LoginFragment.newInstance());
        }
    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(MyReceiver);
    }

    private void initFragment(Fragment loginFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, loginFragment);
        transaction.commit();
    }

}
