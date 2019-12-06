package com.example.bank.ui.login;

import com.example.bank.Model.GetUserModel;

public interface LoginContract {

    interface View{
        boolean showStatus(boolean status);
        void showDetailsUi (GetUserModel user);
    }

    interface UserActionsListener{
        void loadUser(String emailUser, String passwordUser);
    }
}
