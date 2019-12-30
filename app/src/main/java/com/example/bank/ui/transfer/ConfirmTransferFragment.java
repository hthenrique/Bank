package com.example.bank.ui.transfer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bank.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class ConfirmTransferFragment extends Fragment implements TransferContract.View, View.OnFocusChangeListener {

    private TransferContract.UserActionsListener mActionsListener;
    private View root;
    private String tbalance;
    private String emailToTransfer;
    private String valueToTransfer;
    private Button sendBtn;
    String idFrom;
    String emailFrom;

    private Snackbar snackbar;
    private View snackbarView;

    public ConfirmTransferFragment(){}

    static Fragment newInstance(){
        return new ConfirmTransferFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new TransferPresenter(this, getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_transfer, container, false);

        tbalance = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getString("balance");
        valueToTransfer = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getString("valueTo");
        emailFrom = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getString("emailFrom");
        emailToTransfer = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getString("emailTo");
        valueToTransfer = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getString("valueTo");

        TextView emailTransfer = root.findViewById(R.id.emailTo);
        emailTransfer.setText(emailToTransfer);
        TextView valueTransfer = root.findViewById(R.id.valueToTransfer2);
        valueTransfer.setText(valueToTransfer);
        sendBtn = root.findViewById(R.id.sendBtn);
        buttonClick();
        return root;
    }

    private void buttonClick(){
        sendBtn.setOnClickListener(v -> {
            if (emailToTransfer == null){
                snackbar = Snackbar.make(getView(),
                        "Check destination address", Snackbar.LENGTH_LONG);
                snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.parseColor("#b30000"));
                snackbar.show();
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
        idFrom = Objects.requireNonNull(Objects.requireNonNull(getActivity())
                .getIntent().getExtras()).getString("id");

        if (emailToTransfer != null){
            if (!id.equals(idFrom)){
                mActionsListener.checkTransferStatus(idFrom, id,valueToTransfer, tbalance);
            }else {
                snackbar = Snackbar.make(getView(),
                        "Check destination address", Snackbar.LENGTH_LONG);
                snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.RED);
                snackbar.show();
            }
        }
    }

    @Override
    public void setBalance(String balance) {
        tbalance = balance;
    }

    @Override
    public void transferStatus(boolean status) {
        if (!status){
            snackbar = Snackbar.make(getView(),
                    "Transfer Fail", Snackbar.LENGTH_LONG);
            snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.RED);
            snackbar.show();
        }if (status){
            snackbar = Snackbar.make(getView(),
                    "Transferred with success", Snackbar.LENGTH_LONG);
            snackbarView = snackbar.getView();
            /*TextView tv = snackbarView.findViewById(R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);*/
            snackbarView.setBackgroundColor(Color.parseColor("#00b300"));
            snackbar.show();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!v.hasFocus()) {
            InputMethodManager imm =  (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
