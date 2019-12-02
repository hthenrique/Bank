package com.example.bank.API.Retrofit;

import com.example.bank.Model.LoginModel;
import com.example.bank.ui.login.LoginSearch;

public interface ServiceAPI {

    interface UserServiceCallBack<T>{
        void onLoaded(T user);
    }

    void checkUser(String email, String password, UserServiceCallBack<LoginSearch> callBack);

    //void getUser(String email, UserServiceCallBack<LoginModel> callBack);

}