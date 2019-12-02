package com.example.bank.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bank.R;

public class LoginActivity extends AppCompatActivity {
    LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (null == savedInstanceState){
            loginFragment = new LoginFragment();
            initFragment(LoginFragment.newInstance());
        }
    }

    private void initFragment(Fragment loginFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, loginFragment);
        transaction.commit();
    }

}
