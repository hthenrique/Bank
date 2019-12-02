package com.example.bank.ui.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private int status;

    @SerializedName("msg")
    private String message;

    private String user_id;
    private String user_email;
}
