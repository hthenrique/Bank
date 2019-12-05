package com.example.bank.Model;

import com.google.gson.annotations.SerializedName;

public class GetUserModel {

    @SerializedName("email")
    public String email;

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("profile")
    public String profile;

    @SerializedName("balance")
    public String balance;

    GetUserModel(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
