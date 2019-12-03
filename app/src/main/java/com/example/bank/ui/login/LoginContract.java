package com.example.bank.ui.login;

public interface LoginContract {

    interface View{
        boolean showStatus(boolean status);
    }

    interface UserActionsListener{
        void loadUser(String emailUser, String passwordUser);
    }
}
