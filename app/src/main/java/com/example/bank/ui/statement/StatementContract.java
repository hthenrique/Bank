package com.example.bank.ui.statement;

import com.example.bank.Model.StatementModel;

import java.util.List;

public interface StatementContract {

    interface View{
        void setLoading(boolean isActive);
        void showStatement(List<StatementModel> extract);
    }

    interface Presenter{
        void loadStatement (String id_user);
    }
}
