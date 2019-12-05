package com.example.bank.ui.home;

import android.content.Context;

import com.example.bank.API.Retrofit.ServiceAPI;
import com.example.bank.API.Retrofit.ServiceApiImpl;

public class HomePresenter implements HomeContract.Presenter {

    private final ServiceAPI mApi;
    private final HomeContract.View mDetailsView;

    Context context;

    HomePresenter(HomeContract.View detailsView, Context context){
        this.context = context;
        mApi = new ServiceApiImpl(context);
        mDetailsView = detailsView;
    }



    @Override
    public void loadUserDetails(String emailUser) {
        //mApi.getUser(emailUser, mDetailsView::showDetails);
        mApi.getUser(emailUser, mDetailsView::showDetails);
    }
}
