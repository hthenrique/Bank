package com.example.bank.ui.home;

import androidx.annotation.NonNull;

import com.example.bank.Model.GetUserModel;

public interface HomeContract {

    interface View{
        //void showDetails(GetUserModel getUserModel);

        void showDetails(GetUserModel userModel);
    }

    interface Presenter{

        void loadUserDetails(String emailUser);
    }

}
