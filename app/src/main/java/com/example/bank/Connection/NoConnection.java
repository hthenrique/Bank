package com.example.bank.Connection;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bank.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class NoConnection extends AppCompatActivity {

    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
        snackbar = Snackbar.make(findViewById(android.R.id.content), "Verify your connection",
                Snackbar.LENGTH_LONG);
        snackbar.show();
        Toolbar noConnectionToolbar = findViewById(R.id.noConnectionToolbar);
        setSupportActionBar(noConnectionToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
