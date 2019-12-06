package com.example.bank.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bank.Model.GetUserModel;
import com.example.bank.R;
import com.example.bank.ui.home.HomeActivity;
import com.example.bank.ui.home.HomeContract;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment implements LoginContract.View {

    LoginContract.UserActionsListener mActionsListener;
    private String emailUser;
    private String passwordUser;

    View root;

    public LoginFragment(){}

    public static Fragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new LoginPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);

        EditText email = root.findViewById(R.id.username);
        emailUser = email.getText().toString();

        EditText password = root.findViewById(R.id.password);
        passwordUser = password.getText().toString();

        Button button = root.findViewById(R.id.goBtn);
        button.setOnClickListener(v -> {
            emailUser = email.getText().toString();
            passwordUser = password.getText().toString();
            mActionsListener.loadUser(emailUser,passwordUser);
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
            Snackbar.make(getView(), "Welcome", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean showStatus(boolean status) {
        if (!status){
            Snackbar.make(root, "Verify User and Password", Snackbar.LENGTH_LONG).show();
        }
        if (status){
            Intent intent = new Intent(getActivity().getBaseContext(), HomeActivity.class);
            intent.putExtra("email", emailUser);
            startActivity(intent);
        }

        return status;
    }

    @Override
    public void showDetailsUi(GetUserModel user) {
        Intent intent = new Intent(getActivity().getBaseContext(), HomeActivity.class);
        intent.putExtra("email", user.email);
        intent.putExtra("id", user.id);
        intent.putExtra("name", user.name);
        intent.putExtra("profile", user.profile);
        intent.putExtra("balance", user.balance);
        startActivity(intent);
    }
}
