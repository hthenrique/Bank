package com.example.bank.API.Retrofit;

import com.example.bank.ui.login.LoginResponse;
import com.example.bank.ui.login.LoginSearch;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitEndPoint {

    //Login
    @FormUrlEncoded
    @POST("/api/check-login")
    Call<LoginSearch> isValidUser
            (@Field("email") String email,
             @Field("password") String password);

    //User Data
    @FormUrlEncoded
    @POST("/api/get-user")
    Call<LoginResponse> getUser
            (@Field("id_user") String email,
             @Field("format") String format);
}
