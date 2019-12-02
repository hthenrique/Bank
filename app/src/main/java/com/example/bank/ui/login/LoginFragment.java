package com.example.bank.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bank.ui.home.HomeActivity;
import com.example.bank.R;

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.UserActionsListener mActionsListener;
    private String emailUser;
    private String passwordUser;
    public LoginFragment(){}

    public static Fragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        final EditText email = root.findViewById(R.id.username);
        emailUser = email.getText().toString();

        final EditText password = root.findViewById(R.id.password);
        passwordUser = password.getText().toString();

        Button button = root.findViewById(R.id.goBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("henrique.teixeira@evosystems.com.br")
                        && password.getText().toString().equals("123456")){
                Intent intent = new Intent(getActivity().getBaseContext(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
                }else {
                    Toast.makeText(getActivity(), "Verify username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mActionsListener == null){
            Toast.makeText(getActivity(), "Welcome", Toast.LENGTH_SHORT).show();
        }else{
            mActionsListener.loadUser(emailUser,passwordUser);
        }

    }
}
