package com.example.bank.Connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.material.snackbar.Snackbar;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!NetworkUtil.getConnectivityStatus(context)){
            Intent noConnection = new Intent(context, NoConnection.class);
            noConnection.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            noConnection.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(noConnection);
        }
    }
}
