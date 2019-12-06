package com.example.bank.ui.home;

import com.example.bank.Model.GetUserModel;

public interface HomeContract {

    interface View{
        void setId(String id);
        void showDetails(GetUserModel userModel);
    }

    interface Presenter{

        void loadUserDetails(String emailUser);
    }

}
