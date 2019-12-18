package com.example.bank.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatementModel {

    @SerializedName("id")
    public String id;
    @SerializedName("id_from")
    public String id_from;
    @SerializedName("id_to")
    public String id_to;
    @SerializedName("value")
    public String value;
    @SerializedName("data")
    public String data;

    public StatementModel(){
    }
}
