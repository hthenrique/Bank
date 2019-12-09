package com.example.bank.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bank.API.Retrofit.ServiceAPI;
import com.example.bank.API.Retrofit.ServiceApiImpl;
import com.example.bank.Model.GetUserModel;

public class LoginPresenter implements LoginContract.UserActionsListener {
    private final ServiceAPI mApi;
    private final LoginContract.View mLoginView;
    Context context;

    private static final String TAG = "LoginPresenter";

    public LoginPresenter(LoginContract.View loginView){
        mApi = new ServiceApiImpl(context);
        mLoginView = loginView;
    }

    @Override
    public void loadUser(String emailUser, String passwordUser) {
        if (emailUser == null && passwordUser == null){
            Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show();
        }else {
            mApi.checkUser(emailUser, passwordUser, loginStatus -> {
                Log.e(TAG, "loadUser: " + loginStatus);
                mLoginView.showStatus(loginStatus.status);
            });
        }
    }
}
