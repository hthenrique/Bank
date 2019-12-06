package com.example.bank.ui.extract;

import com.example.bank.Model.ExtractModel;

import java.util.ArrayList;
import java.util.List;

public interface ExtractContract {

    interface View{
        void setLoading(boolean isActive);
        void showExtract(ArrayList<List<ExtractModel>> extract);
    }

    interface Presenter{
        void loadExtract (String id_user);
    }
}
