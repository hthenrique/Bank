package com.example.bank.ui.extract;

import android.content.Context;

import com.example.bank.API.Retrofit.ServiceAPI;
import com.example.bank.API.Retrofit.ServiceApiImpl;

public class ExtractPresenter implements ExtractContract.Presenter {
    private final ServiceAPI mApi;
    private final ExtractContract.View mExtractView;


    ExtractPresenter(ExtractContract.View extractView, Context context) {
        mApi = new ServiceApiImpl(context);
        mExtractView = extractView;
    }

    @Override
    public void loadExtract(String id_user) {
        mExtractView.setLoading(true);
        mApi.getExtract(id_user, extractModel ->{
            mExtractView.setLoading(false);
            mExtractView.showExtract(extractModel);
        });
    }
}
