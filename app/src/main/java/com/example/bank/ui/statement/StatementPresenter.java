package com.example.bank.ui.statement;

import android.content.Context;

import com.example.bank.API.Retrofit.ServiceAPI;
import com.example.bank.API.Retrofit.ServiceApiImpl;

public class StatementPresenter implements StatementContract.Presenter {
    private final ServiceAPI mApi;
    private final StatementContract.View mStatementView;


    StatementPresenter(StatementContract.View extractView, Context context) {
        mApi = new ServiceApiImpl(context);
        mStatementView = extractView;
    }

    @Override
    public void loadStatement(String id_user) {
        mStatementView.setLoading(true);
        mApi.getStatement(id_user, statementModel ->{
            mStatementView.setLoading(false);
            mStatementView.showStatement(statementModel);
        });
    }
}
