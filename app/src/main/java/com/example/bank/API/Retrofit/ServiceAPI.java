package com.example.bank.API.Retrofit;

import com.example.bank.Login.LoginSearch;
import com.example.bank.Model.LoginModel;

public interface ServiceAPI {

    interface UserServiceCallBack<T>{
        void onLoaded(LoginSearch user);
    }

    void isValidUser(String email, String password, UserServiceCallBack<LoginModel> callBack);

    //void getUser(String email, UserServiceCallBack<LoginModel> callBack);

}