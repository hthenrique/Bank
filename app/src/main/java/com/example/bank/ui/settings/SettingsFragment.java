package com.example.bank.ui.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bank.R;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private Switch switchDarkMode;
    private boolean switchState;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Toolbar mToolbar = root.findViewById(R.id.toolbarSettings);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(mToolbar);
        Objects.requireNonNull(((SettingsActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((SettingsActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        switchDarkMode = root.findViewById(R.id.switchDarkMode);
        switchDarkMode.setOnCheckedChangeListener(this);
        switchDarkMode.setChecked(switchState);
        return root;
    }


    @SuppressLint("ApplySharedPref")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences prefs = getActivity().getSharedPreferences("darkMode", MODE_PRIVATE);
        isChecked = prefs.getBoolean("service_status", isChecked);

        if(isChecked == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            switchState = true;
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            switchState = false;
        }
    }
}
