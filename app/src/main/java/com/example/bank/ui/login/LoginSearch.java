package com.example.bank.ui.login;

import com.example.bank.Model.LoginModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginSearch {
    @SerializedName("Search")
    public List<LoginModel> loginUser;
}
