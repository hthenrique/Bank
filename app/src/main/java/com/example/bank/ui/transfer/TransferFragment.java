package com.example.bank.ui.transfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.bank.R;

public class TransferFragment extends Fragment {

    private TransferViewModel sendViewModel;

    public TransferFragment(){}

    public static Fragment newInstance(){
        return new TransferFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(TransferViewModel.class);
        View root = inflater.inflate(R.layout.fragment_transfer, container, false);
        Toolbar mToolbar = root.findViewById(R.id.toolbarTransfer);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((TransferActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((TransferActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        return root;
    }
}