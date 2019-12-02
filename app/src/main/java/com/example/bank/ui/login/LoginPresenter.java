package com.example.bank.ui.login;

import android.content.Context;

import com.example.bank.API.Retrofit.ServiceAPI;
import com.example.bank.API.Retrofit.ServiceApiImpl;

public class LoginPresenter implements LoginContract.UserActionsListener {
    private final ServiceAPI mApi;
    private final LoginContract.View mloginView;
    Context context;

    public LoginPresenter(LoginContract.View loginView){
        mApi = new ServiceApiImpl(context);
        mloginView = loginView;
    }

    @Override
    public void loadUser(String email, String password) {
    }
}
