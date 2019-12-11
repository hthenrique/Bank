package com.example.bank.ui.transfer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bank.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class TransferFragment extends Fragment implements TransferContract.View {

    private TransferContract.UserActionsListener mActionsListener;
    private View root;
    private String balance;
    private String emailToTransfer;
    private EditText emailTo;
    private String valueToTransfer;
    private EditText valueTo;
    private Button sendBtn;

    public TransferFragment(){}

    static Fragment newInstance(){
        return new TransferFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new TransferPresenter(this, getContext());
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_transfer, container, false);
        Toolbar mToolbar = root.findViewById(R.id.toolbarTransfer);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(mToolbar);
        Objects.requireNonNull(((TransferActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((TransferActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        emailTo = root.findViewById(R.id.transferTo);
        emailToTransfer = emailTo.getText().toString();
        valueTo = root.findViewById(R.id.tranferValue);
        valueToTransfer = valueTo.getText().toString();
        sendBtn = root.findViewById(R.id.sendBtn);
        buttonClick();
        return root;
    }

    private void buttonClick(){
        sendBtn.setOnClickListener(v -> {
            emailToTransfer = emailTo.getText().toString();
            valueToTransfer = valueTo.getText().toString();
            emailTo.getText().clear();
            valueTo.getText().clear();
            if (emailToTransfer == null){
                Snackbar.make(root, "Check destination address", Snackbar.LENGTH_LONG).show();
            }
            if (emailToTransfer!= null){
                mActionsListener.loadUserDetails(emailToTransfer);
            }
            ((InputMethodManager) Objects.requireNonNull(Objects.requireNonNull(getContext())
                    .getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow(
                    sendBtn.getWindowToken(), 0);
        });
    }

    @Override
    public void setId(String id) {
        String idFrom = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getString("id");

        if (emailToTransfer != null){
            mActionsListener.checkTransferStatus(idFrom, id,valueToTransfer, balance);
        }
    }

    @Override
    public void setBalance(String balance) {
        this.balance = balance;

    }

    @Override
    public void transferStatus(boolean status) {
        if (!status){
            Snackbar.make(Objects.requireNonNull(getView()), "Transfer Fail", Snackbar.LENGTH_LONG).show();
        }if (status){
            Snackbar.make(Objects.requireNonNull(getView()), "Transferred with success", Snackbar.LENGTH_LONG).show();
        }
    }
}