package com.example.bank.API.Retrofit;

import com.example.bank.Model.ExtractModel;
import com.example.bank.Model.GetUserModel;
import com.example.bank.Model.LoginSearch;

import java.util.List;

import retrofit2.http.POST;

public interface ServiceAPI {

    interface UserServiceCallBack<T>{
        void onLoaded(T user);
    }

    void checkUser(String email, String password, UserServiceCallBack<LoginSearch> callBack);

    @POST("/api/get-user")
    void getUser(String email, UserServiceCallBack<GetUserModel> callBack);

    void getExtract(String id_user, UserServiceCallBack<List<ExtractModel>> callBack);

}