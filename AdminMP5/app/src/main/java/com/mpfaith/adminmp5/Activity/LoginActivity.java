package com.mpfaith.adminmp5.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mpfaith.adminmp5.Model.MerchantLoginModel;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ErrorConstants;
import com.mpfaith.adminmp5.utils.NetConnection;
import com.mpfaith.adminmp5.utils.ProgressDialogScreen;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView register;
    UserSharedPreferences userSharedPreferences;
    RestAPI api;
    Button login_butto;
    EditText password,mobile;
    TextView forgot_g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init(){

        password= findViewById(R.id.password);
        mobile= findViewById(R.id.mobile_no);
        register=findViewById(R.id.register);
        login_butto= findViewById(R.id.login_button);
        userSharedPreferences = new UserSharedPreferences(this);
        api = APIClient.getClient().create(RestAPI .class);

        if(userSharedPreferences.isLogin()) {
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }




        login_butto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String strmobile = mobile.getText().toString().trim();
                String strpassword= password.getText().toString().trim();


                if (NetConnection.isNetworkAvailable(LoginActivity.this)) {
                    if(isValidMobile(strmobile)){
                        if(!strpassword.isEmpty()){
                            login(strmobile, strpassword);
                        }else {
                            Toast.makeText(LoginActivity.this, "Enter the Mobile and Password", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "Enter the valid Mobile ", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Toast.makeText(LoginActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();
                }


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }
    private void login(String strmobile, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("loginId", strmobile);
        requestBody.put("password", password);
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(LoginActivity.this);
        Call<MerchantLoginModel> call = api.getMerchantLogin(requestBody1);
        call.enqueue(new Callback<MerchantLoginModel>() {
            @Override
            public void onResponse(@NonNull Call<MerchantLoginModel> call, @NonNull Response<MerchantLoginModel> response) {
                ProgressDialogScreen.hideProgressDialog();
                if (response.isSuccessful() ) {
                    if (response.body().getPayload() != null) {
                        MerchantLoginModel login= response.body();
                        if(login.getResponseCode().equals(200)){
                            if(login.getPayload().getRespCode().equals(200)){
                                userSharedPreferences.storeData(login);
                                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                                finish();
                                Toast.makeText(LoginActivity.this, login.getPayload().getRespMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                NetConnection.showMessage(LoginActivity.this, ""+login.getPayload().getRespMessage());
                            }


                        }else {
                            NetConnection.showMessage(LoginActivity.this, ""+login.getStatus());

                        }

                    }else {
                        //body
                        NetConnection.showMessage(LoginActivity.this, ErrorConstants.JSONDATA_NULL);

                    }

                } else {

                    //response
                    NetConnection.showMessage(LoginActivity.this, ErrorConstants.RESPONSE_NULL);
                }

            }

            @Override
            public void onFailure(@NonNull Call<MerchantLoginModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(LoginActivity.this, ErrorConstants.Fail_message);

                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }


    public static boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 10 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
                // txtPhone.setError("Not Valid Number");
            } else {
                check = android.util.Patterns.PHONE.matcher(phone).matches();
            }
        } else {
            check = false;
        }
        return check;
    }


//        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
//
//            }
//        });



}