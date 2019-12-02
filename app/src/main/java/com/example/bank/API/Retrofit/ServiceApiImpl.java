package com.example.bank.API.Retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.bank.ui.login.LoginSearch;
import com.example.bank.Model.LoginModel;

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
        Call<LoginSearch> callUserLogin = mRetrofit.isValidUser(email.trim(), password,"json");
        callUserLogin.enqueue(new Callback<LoginSearch>() {
            @Override
            public void onResponse(Call<LoginSearch> call, Response<LoginSearch> response) {
                try {
                    if (response.code()==200){
                        LoginSearch loginSearch = response.body();
                        callBack.onLoaded(loginSearch);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Verify user and password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginSearch> call, Throwable t) {
                Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
