package com.mpfaith.adminmp5.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mpfaith.adminmp5.Model.MerchantLoginModel;


public class UserSharedPreferences {


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public UserSharedPreferences(Context context) {
        preferences = context.getSharedPreferences(ConstantVariable.UserSharedPref.USER_FILE,
                Context.MODE_PRIVATE);
    }

    public String getMemberType() {
        return preferences.getString(ConstantVariable.UserSharedPref.MEMBER_TYPE, "");
    }

    public String geturl() {
        return preferences.getString(ConstantVariable.UserSharedPref.URL, "");
    }

    public String getLoginID() {
        return preferences.getString(ConstantVariable.UserSharedPref.LOGIN_ID, "");
    }
    public String getFirstName() {
        return preferences.getString(ConstantVariable.UserSharedPref.FIRST_NAME, "");
    }
    public String getLastName() {
        return preferences.getString(ConstantVariable.UserSharedPref.LAST_NAME, "");
    }


    public boolean isLogin() {
        return preferences.getBoolean(ConstantVariable.UserSharedPref.IS_LOGIN, false);
    }

    public void resetLogin() {
        editor = preferences.edit();

        editor.putBoolean(ConstantVariable.UserSharedPref.IS_LOGIN, false);
        editor.apply();
    }
//    public void storeDataLogin(Login login) {
//
//        editor.putString(ConstantVariable.UserSharedPref.USER_ID, login.getPayload().getUserId());
//
//    }

    public void storeData(MerchantLoginModel loginResult) {
        editor = preferences.edit();
        editor.putString(ConstantVariable.UserSharedPref.FIRST_NAME, loginResult.getPayload().getFirstName());
        editor.putString(ConstantVariable.UserSharedPref.LAST_NAME, loginResult.getPayload().getLastName());
        editor.putString(ConstantVariable.UserSharedPref.LOGIN_ID, loginResult.getPayload().getLoginId());
        editor.putBoolean(ConstantVariable.UserSharedPref.IS_LOGIN, true);
        editor.apply();
    }



}

