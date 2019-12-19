package com.example.bank.ui.settings;

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
import com.example.bank.ui.statement.StatementActivity;

import java.util.Objects;

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Toolbar mToolbar = root.findViewById(R.id.toolbarSettings);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        Objects.requireNonNull(((SettingsActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((SettingsActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        Switch switchDarkMode = root.findViewById(R.id.switchDarkMode);
        switchDarkMode.setOnCheckedChangeListener(this);
        return root;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
