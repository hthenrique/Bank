package com.example.bank.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.bank.API.Retrofit.ServiceAPI;
import com.example.bank.API.Retrofit.ServiceApiImpl;

public class LoginPresenter implements LoginContract.UserActionsListener {
    private final ServiceAPI mApi;
    private final LoginContract.View mLoginView;
    private Context context;

    private static final String TAG = "LoginPresenter";

    LoginPresenter(LoginContract.View loginView, Context context){
        this.context = context;
        mApi = new ServiceApiImpl(this.context);
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
