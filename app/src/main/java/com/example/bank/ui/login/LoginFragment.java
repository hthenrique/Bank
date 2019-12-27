package com.example.bank.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import androidx.fragment.app.Fragment;

import com.example.bank.Model.GetUserModel;
import com.example.bank.R;
import com.example.bank.ui.home.HomeActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class LoginFragment extends Fragment implements LoginContract.View, View.OnFocusChangeListener {

    private LoginContract.UserActionsListener mActionsListener;
    private String emailUser;
    private EditText email;
    private String passwordUser;
    private EditText password;
    private Button button;

    Snackbar snackbar;
    View snackbarView;

    public LoginFragment(){}

    static Fragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new LoginPresenter(this,getContext());
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        email = root.findViewById(R.id.username);
        email.setOnFocusChangeListener(this);
        emailUser = email.getText().toString();

        password = root.findViewById(R.id.password);
        password.setOnFocusChangeListener(this);
        passwordUser = password.getText().toString();

        button = root.findViewById(R.id.goBtn);
        buttonClick();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void buttonClick(){
        button.setOnClickListener(v -> {
            emailUser = email.getText().toString();
            passwordUser = password.getText().toString();
            mActionsListener.loadUser(emailUser,passwordUser);
        });
    }

    @Override
    public void showStatus(boolean status) {
        if (!status){
            snackbar = Snackbar.make(getView(),
                    "Verify User and Password", Snackbar.LENGTH_LONG);
            snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.RED);
            snackbar.show();
        }
        if (status){
            Intent intent = new Intent(Objects.requireNonNull(getActivity()).getBaseContext(), HomeActivity.class);
            intent.putExtra("email", emailUser);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void showDetailsUi(GetUserModel user) {
        Intent intent = new Intent(Objects.requireNonNull(getActivity()).getBaseContext(), HomeActivity.class);
        intent.putExtra("email", user.email);
        intent.putExtra("id", user.id);
        intent.putExtra("name", user.name);
        intent.putExtra("profile", user.profile);
        intent.putExtra("balance", user.balance);
        startActivity(intent);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!v.hasFocus()) {
            InputMethodManager imm =  (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
