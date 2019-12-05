package com.example.bank.API.Retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.bank.Model.GetUserModel;
import com.example.bank.ui.login.LoginSearch;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceApiImpl implements ServiceAPI {

    private RetrofitEndPoint mRetrofit;
    private Context context;

    public ServiceApiImpl(Context context){
        this.context = context;
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndPoint.class);
    }

    @Override
    public void checkUser(String email, String password, final UserServiceCallBack<LoginSearch> callBack) {
        Call<LoginSearch> callUserLogin = mRetrofit.isValidUser(email, password);
        callUserLogin.enqueue(new Callback<LoginSearch>() {
            @Override
            public void onResponse(Call<LoginSearch> call, Response<LoginSearch> response) {
                try {
                    if (response.code()==200){
                        LoginSearch loginSearch = response.body();
                        callBack.onLoaded(loginSearch);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Check User Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginSearch> call, Throwable t) {
                Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void getUser(String email, final UserServiceCallBack<GetUserModel> callBack) {
        Call<GetUserModel> callUser = mRetrofit.getUser(email);
        callUser.enqueue(new Callback<GetUserModel>() {
            @Override
            public void onResponse(@NotNull Call<GetUserModel> call, @NotNull Response<GetUserModel> response) {
                try {
                    if (response.code()==200){
                        GetUserModel getUserModel = response.body();
                        callBack.onLoaded(getUserModel);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Get User Fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NotNull Call<GetUserModel> call, Throwable t) {
                Toast.makeText(context, "Get Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
