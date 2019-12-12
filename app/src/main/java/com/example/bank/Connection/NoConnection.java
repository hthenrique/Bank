package com.example.bank.Connection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bank.R;
import com.example.bank.ui.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

public class NoConnection extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);

        Snackbar.make(findViewById(android.R.id.content),
                "Verify your connection", Snackbar.LENGTH_LONG).show();

        Toolbar noConnectionToolbar = findViewById(R.id.noConnectionToolbar);
        setSupportActionBar(noConnectionToolbar);

        Button exitButton = findViewById(R.id.exitButton2);
        exitButton.setOnClickListener(this);
        Button tryAgainButton = findViewById(R.id.tryAgainButton);
        tryAgainButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tryAgainButton:
                if (NetworkUtil.getConnectivityStatus(this)){
                    onBackPressed();
                }else {
                    Snackbar.make(findViewById(android.R.id.content),
                            "Please verify your connection",
                            Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.exitButton2:
                Intent exit = new Intent(this, LoginActivity.class);
                exit.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                exit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(exit);
                finish();
                break;
            default: break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
