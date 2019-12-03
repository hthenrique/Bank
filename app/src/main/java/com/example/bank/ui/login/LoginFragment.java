package com.example.bank.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bank.ui.home.HomeActivity;
import com.example.bank.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.UserActionsListener mActionsListener;
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

        final EditText email = root.findViewById(R.id.username);
        emailUser = email.getText().toString();

        final EditText password = root.findViewById(R.id.password);
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
        if (mActionsListener == null){
            Snackbar.make(getView(), "Welcome", Snackbar.LENGTH_LONG).show();
        }else{
            mActionsListener.loadUser(emailUser,passwordUser);
        }
    }


    @Override
    public boolean showStatus(boolean status) {

        if (status == false){
            Snackbar.make(root, "Verify User and Password", Snackbar.LENGTH_LONG).show();
        }

        if (status == true){
            Intent intent = new Intent(getActivity().getBaseContext(), HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        return status;
    }
}
