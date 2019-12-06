package com.example.bank.API.Retrofit;

import com.example.bank.Model.ExtractModel;
import com.example.bank.Model.GetUserModel;
import com.example.bank.Model.LoginSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitEndPoint {

    //Login
    @FormUrlEncoded
    @POST("./api/check-login")
    Call<LoginSearch> isValidUser
            (@Field("email") String email,
             @Field("password") String password);

    //User Data
    @FormUrlEncoded
    @POST("./api/get-user")
    Call<GetUserModel> getUser
            (@Field("email") String email);

    //Extract
    @FormUrlEncoded
    @POST("./api/get-bank-statement")
    Call<List<ExtractModel>> getBankStatement
            (@Field("id_user") String id_user);
}
