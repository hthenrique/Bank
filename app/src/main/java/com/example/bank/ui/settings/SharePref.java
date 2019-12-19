package com.example.bank.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {
    SharedPreferences mSharedPref;
    public SharePref(Context context){
        mSharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }

    public boolean loadNightModeState(){
        boolean state = mSharedPref.getBoolean("NightMode",false);
        return state;
    }
}
