package com.example.bank.API.Retrofit;

import com.example.bank.Model.StatementModel;
import com.example.bank.Model.GetUserModel;
import com.example.bank.Model.LoginStatus;
import com.example.bank.Model.TransferStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitEndPoint {

    //Login
    @FormUrlEncoded
    @POST("./api/check-login")
    Call<LoginStatus> isValidUser
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
    Call <List<StatementModel>> getBankStatement
            (@Field("id_user") String id_user);

    //Transfer
    @FormUrlEncoded
    @POST("./api/transfer")
    Call <TransferStatus> isValidTransfer
            (@Field("id_user_from") String id_user_from,
             @Field("id_user_to") String id_user_to,
             @Field("value") String value);
}
