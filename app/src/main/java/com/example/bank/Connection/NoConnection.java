package com.example.bank.Connection;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bank.R;
import com.google.android.material.snackbar.Snackbar;

public class NoConnection extends AppCompatActivity implements View.OnClickListener {
    View snackbarView;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);


        if (NetworkUtil.getConnectivityStatus(this)) {
            snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "Connected",
                    Snackbar.LENGTH_LONG);
            snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.GREEN);
            snackbar.show();
        }else {
            snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "Verify your connection", Snackbar.LENGTH_LONG);
            snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.RED);
            snackbar.show();
        }

        Button tryAgainButton = findViewById(R.id.tryAgainButton);
        tryAgainButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tryAgainButton) {
            if (NetworkUtil.getConnectivityStatus(this)) {
                onBackPressed();
            } else {
                snackbar = Snackbar.make(v,
                        "Please verify your connection",
                        Snackbar.LENGTH_LONG);
                snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.RED);
                snackbar.show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
