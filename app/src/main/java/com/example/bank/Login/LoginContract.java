package com.example.bank.Login;

public interface LoginContract {

    interface View{
    }

    interface UserActionsListener{

        void loadUser(String email, String password);
    }
}
