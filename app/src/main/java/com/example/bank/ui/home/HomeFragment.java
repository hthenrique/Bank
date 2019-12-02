package com.example.bank.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bank.R;
import com.example.bank.ui.extract.ExtractActivity;
import com.example.bank.ui.extract.ExtractFragment;
import com.example.bank.ui.login.LoginActivity;
import com.example.bank.ui.transfer.TransferActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    public HomeFragment(){}

    public static Fragment newInstance(){
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button extractButton = root.findViewById(R.id.extractButton);
        extractButton.setOnClickListener(this);
        Button transferButton = root.findViewById(R.id.transferButton);
        transferButton.setOnClickListener(this);
        Button exitButton = root.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.extractButton:
                Intent extract = new Intent(getActivity(), ExtractActivity.class);
                startActivity(extract);
                break;
            case R.id.transferButton:
                Intent transfer = new Intent(getActivity(), TransferActivity.class);
                startActivity(transfer);
                break;
            case  R.id.exitButton:
                Intent exit = new Intent(getActivity(), LoginActivity.class);
                startActivity(exit);
                getActivity().finish();
                break;
        }
    }
}