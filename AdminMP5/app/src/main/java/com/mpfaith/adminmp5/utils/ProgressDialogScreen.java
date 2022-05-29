package com.mpfaith.adminmp5.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogScreen {
    private Context context;
    private static ProgressDialogScreen mProgressDialog;
    private static ProgressDialog progressDialog;

    private ProgressDialogScreen(Context context){
        this.context=context;
    }

    public static void showProgressDialog (Context context) {
        if (mProgressDialog == null ) {
            mProgressDialog = new ProgressDialogScreen(context);
            progressDialog=new ProgressDialog(context);
            progressDialog.setMessage("Please wait");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);

        }
        try {
            progressDialog.show();
        }catch (Exception e){

        }
    }

    public static void hideProgressDialog () {
        if (mProgressDialog != null && progressDialog != null && progressDialog.isShowing()) {
            //    progressDialog.hide();
//            progressDialog.dismiss();
//            progressDialog=null;
            progressDialog.dismiss();
            progressDialog.cancel();
            progressDialog = null;
            mProgressDialog = null;
        }
    }

}
