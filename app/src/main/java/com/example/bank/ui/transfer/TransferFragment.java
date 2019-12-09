package com.example.bank.ui.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bank.Model.GetUserModel;
import com.example.bank.R;
import com.example.bank.ui.home.HomeActivity;
import com.google.android.material.snackbar.Snackbar;

public class TransferFragment extends Fragment implements TransferContract.View {

    TransferContract.UserActionsListener mActionsListener;
    View root;
    String idTo;
    String emailToTransfer;
    String valueToTransfer;

    public TransferFragment(){}

    public static Fragment newInstance(){
        return new TransferFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new TransferPresenter(this, getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_transfer, container, false);
        Toolbar mToolbar = root.findViewById(R.id.toolbarTransfer);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((TransferActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((TransferActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        EditText emailTo = root.findViewById(R.id.transferTo);
        emailToTransfer = emailTo.getText().toString();
        EditText valueTo = root.findViewById(R.id.tranferValue);
        valueToTransfer = valueTo.getText().toString();

        Button sendBtn = root.findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(v -> {
            emailToTransfer = emailTo.getText().toString();
            valueToTransfer = valueTo.getText().toString();
            mActionsListener.loadUserDetails(emailToTransfer);
            emailTo.getText().clear();
            valueTo.getText().clear();
        });
        return root;
    }

    @Override
    public void setId(String id) {
        idTo = id;
        String idFrom = getActivity().getIntent().getExtras().getString("id");

        if (emailToTransfer != null){
            mActionsListener.checkTransferStatus(idFrom,idTo,valueToTransfer);
        }
    }

    @Override
    public boolean transferStatus(boolean status) {
        if (!status){
            Snackbar.make(root, "Transfer Fail", Snackbar.LENGTH_LONG).show();
        }if (status){
            Snackbar.make(root, "Transferred with success", Snackbar.LENGTH_LONG).show();
        }
        return status;
    }
}