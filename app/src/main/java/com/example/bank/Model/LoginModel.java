package com.example.bank.Model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    public LoginModel(){
    }

}