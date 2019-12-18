package com.example.bank.ui.home;

import android.content.Context;

import com.example.bank.API.Retrofit.ServiceAPI;
import com.example.bank.API.Retrofit.ServiceApiImpl;

public class HomePresenter implements HomeContract.Presenter {

    private final ServiceAPI mApi;
    private final HomeContract.View mDetailsView;

    HomePresenter(HomeContract.View detailsView, Context context){
        mApi = new ServiceApiImpl(context);
        mDetailsView = detailsView;
    }

    @Override
    public void loadUserDetails(String emailUser) {
        mApi.getUser(emailUser, userModel -> {
            mDetailsView.setLoading(false);
                    mDetailsView.setId(userModel.id);
                    mDetailsView.showDetails(userModel);
                    mDetailsView.setBalance(userModel.balance);
                });
    }
}
