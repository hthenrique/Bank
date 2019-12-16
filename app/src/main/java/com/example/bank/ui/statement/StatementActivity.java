package com.example.bank.ui.statement;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bank.Connection.MyReceiver;
import com.example.bank.Connection.NetworkUtil;
import com.example.bank.R;

public class StatementActivity extends AppCompatActivity {
    StatementFragment statementFragment;
    private BroadcastReceiver MyReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        if (null == savedInstanceState){
            statementFragment = new StatementFragment();
            initFragment(StatementFragment.newInstance());
        }
        if (!NetworkUtil.getConnectivityStatus(this)) {
            MyReceiver = new MyReceiver();
            broadcastIntent();
        }else{
            MyReceiver = new MyReceiver();
        }

    }
    private void broadcastIntent() {
        this.registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    private void initFragment(Fragment statementFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content_statement, statementFragment);
        transaction.commit();
    }

}
