package com.example.bank.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.UserActionsListener mActionsListener;
    private String emailUser;
    private EditText email;
    private String passwordUser;
    private EditText password;
    private Button button;

    public LoginFragment(){}

    static Fragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new LoginPresenter(this,getContext());
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        email = root.findViewById(R.id.username);
        emailUser = email.getText().toString();
        password = root.findViewById(R.id.password);
        passwordUser = password.getText().toString();
        button = root.findViewById(R.id.goBtn);
        buttonClick();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
            Snackbar.make(Objects.requireNonNull(getView()), "Welcome", Snackbar.LENGTH_LONG).show();
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
            Snackbar.make(Objects.requireNonNull(getView()), "Verify User and Password", Snackbar.LENGTH_LONG).show();
        }
        if (status){
            Intent intent = new Intent(Objects.requireNonNull(getActivity()).getBaseContext(), HomeActivity.class);
            intent.putExtra("email", emailUser);
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
}
