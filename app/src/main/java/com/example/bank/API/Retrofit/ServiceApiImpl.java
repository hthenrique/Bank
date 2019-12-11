package com.example.bank.API.Retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.bank.Model.ExtractModel;
import com.example.bank.Model.GetUserModel;
import com.example.bank.Model.LoginStatus;
import com.example.bank.Model.TransferStatus;

import org.jetbrains.annotations.NotNull;

import java.util.List;

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
    public void checkUser(String email, String password, final UserServiceCallBack<LoginStatus> callBack) {
        Call<LoginStatus> callUserLogin = mRetrofit.isValidUser(email, password);
        callUserLogin.enqueue(new Callback<LoginStatus>() {
            @Override
            public void onResponse(@NotNull Call<LoginStatus> call, @NotNull Response<LoginStatus> response) {
                try {
                    if (response.code()==200){
                        LoginStatus loginStatus = response.body();
                        callBack.onLoaded(loginStatus);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Check User Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginStatus> call, @NotNull Throwable t) {
                Toast.makeText(context, "Failed to connect to server", Toast.LENGTH_SHORT).show();
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
            public void onFailure(@NotNull Call<GetUserModel> call, @NotNull Throwable t) {
                Toast.makeText(context, "Get User Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getExtract(String id_user, final UserServiceCallBack<List<ExtractModel>> callBack) {
        Call<List<ExtractModel>> callExtract = mRetrofit.getBankStatement(id_user);
        callExtract.enqueue(new Callback<List<ExtractModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<ExtractModel>> call, @NotNull Response<List<ExtractModel>> response) {
                try {
                    if (response.code()==200){
                        List<ExtractModel> extractModel = response.body();
                        callBack.onLoaded(extractModel);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Get Extract Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<ExtractModel>> call, @NotNull Throwable t) {
                Toast.makeText(context, "Get Extract Response Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void doTransfer(String id_user_from, String id_user_to, String value,
                           final UserServiceCallBack<TransferStatus> callBack) {
        Call<TransferStatus> callUserTransfer = mRetrofit.isValidTransfer(id_user_from,id_user_to,value);
        callUserTransfer.enqueue(new Callback<TransferStatus>() {
            @Override
            public void onResponse(@NotNull Call<TransferStatus> call, @NotNull Response<TransferStatus> response) {
                try {
                    if (response.code()==200){
                        TransferStatus transferStatus = response.body();
                        callBack.onLoaded(transferStatus);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Check user to transfer fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<TransferStatus> call, @NotNull Throwable t) {
            }
        });
    }
}
