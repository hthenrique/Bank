package com.example.bank.ui.transfer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.bank.API.Retrofit.ServiceAPI;
import com.example.bank.API.Retrofit.ServiceApiImpl;

public class TransferPresenter implements TransferContract.UserActionsListener {
    private static final String TAG = "TransferPresenter";
    private final ServiceAPI mApi;
    private final TransferContract.View mTransferView;
    private Context context;

    TransferPresenter(TransferContract.View transferView, Context context){
        this.context = context;
        mApi = new ServiceApiImpl(context);
        mTransferView = transferView;
    }

    @Override
    public void loadUserDetails(String emailUser) {
        mApi.getUser(emailUser, userModel -> {
            mTransferView.setBalance(userModel.balance);
            mTransferView.setId(userModel.id);
        });
    }

    @Override
    public void checkTransferStatus(String id_user_from, String id_user_to, String value, String balance) {
        if (Integer.parseInt(value) <= Integer.parseInt(balance)){
            mApi.doTransfer(id_user_from,id_user_to,value, transferStatus ->{
                Log.e(TAG, "loadTransfer: " + transferStatus);
                mTransferView.transferStatus(transferStatus.status);
            });
        }else {
            Toast.makeText(context, "Verify your balance", Toast.LENGTH_SHORT).show();
        }

    }
}
