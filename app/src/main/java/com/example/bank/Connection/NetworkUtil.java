package com.example.bank.Connection;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class NetworkUtil {
    static String getConnectivityStatusString(Context context){

        String status = null;
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "";
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "";
                return status;
            }
        } else {
            status = "No internet is available";
            Intent intent = new Intent(context, NoConnection.class);
            context.startActivity(intent);
            return status;
        }
        return status;
    }


}
