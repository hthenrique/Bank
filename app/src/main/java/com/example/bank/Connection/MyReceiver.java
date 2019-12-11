package com.example.bank.Connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean status = NetworkUtil.getConnectivityStatusString(context);
        if(!status) {
            Intent noConnection = new Intent(context, NoConnection.class);
            context.startActivity(noConnection);
        }
    }
}
