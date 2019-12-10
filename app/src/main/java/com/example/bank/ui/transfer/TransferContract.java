package com.example.bank.ui.transfer;

public interface TransferContract {

    interface View{
        void transferStatus(boolean status);
        void setId(String id);
        void setBalance(String balance);
    }

    interface UserActionsListener{
        void loadUserDetails(String emailUser);
        void checkTransferStatus(String id_user_from, String id_user_to, String value, String balance);
    }
}
