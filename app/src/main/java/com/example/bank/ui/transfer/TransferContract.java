package com.example.bank.ui.transfer;

public interface TransferContract {

    interface View{
        boolean transferStatus(boolean status);
        void setId(String id);
    }

    interface UserActionsListener{
        void loadUserDetails(String emailUser);
        void checkTransferStatus(String id_user_from, String id_user_to, String value);
    }
}
