package com.mpfaith.adminmp5.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;


public class NetConnection {

    @SuppressLint("MissingPermission")
    static public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return (connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null) != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
    public static void showMessage(Context context,String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}