package com.example.bank.Connection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bank.R;
import com.google.android.material.snackbar.Snackbar;

public class NoConnection extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);

        Snackbar.make(findViewById(android.R.id.content),
                "Verify your connection", Snackbar.LENGTH_LONG).show();

        Button tryAgainButton = findViewById(R.id.tryAgainButton);
        tryAgainButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tryAgainButton) {
            if (NetworkUtil.getConnectivityStatus(this)) {
                onBackPressed();
            } else {
                Snackbar.make(findViewById(android.R.id.content),
                        "Please verify your connection",
                        Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
