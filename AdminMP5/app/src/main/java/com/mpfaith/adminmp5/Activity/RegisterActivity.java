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

import com.mpfaith.adminmp5.Model.RegisterMerchantModel;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ErrorConstants;
import com.mpfaith.adminmp5.utils.NetConnection;
import com.mpfaith.adminmp5.utils.ProgressDialogScreen;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mpfaith.adminmp5.Activity.LoginActivity.isValidMobile;

public class RegisterActivity extends AppCompatActivity {
    TextView register;
    UserSharedPreferences userSharedPreferences;
    RestAPI api;
    Button sign_button;
    String str_mobile;
    EditText password, mobile, name_f, email_id, lastname,mobile_no,shop_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        str_mobile = getIntent().getStringExtra("mobile");
        name_f = findViewById(R.id.name_f);
        lastname = findViewById(R.id.lastname);
        email_id = findViewById(R.id.email_id);
        password = findViewById(R.id.password);
        mobile_no = findViewById(R.id.mobile_no);
        shop_name = findViewById(R.id.shop_name);
        register = findViewById(R.id.register);
        sign_button = findViewById(R.id.sign_button);
        userSharedPreferences = new UserSharedPreferences(this);
        api = APIClient.getClient().create(RestAPI.class);


        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_name_f = name_f.getText().toString().trim();
                String str_lastname = lastname.getText().toString().trim();
                String str_passwordd = password.getText().toString().trim();

                String str_mobile_no = mobile_no.getText().toString().trim();
                String str_email_id = email_id.getText().toString().trim();
                String str_shop_name = shop_name.getText().toString().trim();


                if (NetConnection.isNetworkAvailable(RegisterActivity.this)) {
                    if(isValidMobile(str_mobile_no)){
                        if (!str_passwordd.isEmpty()&&!str_name_f.isEmpty()&&!str_lastname.isEmpty()&&!str_email_id.isEmpty()&&!str_shop_name.isEmpty()) {

                                getRegister(str_name_f, str_lastname, str_passwordd, str_mobile_no, str_email_id,str_shop_name);


                        } else {
                            Toast.makeText(RegisterActivity.this, "Add the field", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Enter the valid Mobile ", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Toast.makeText(RegisterActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }



    private void getRegister(String first_name, String last_name, String password, String strmobile,String email_id, String str_shop_name) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("firstName", first_name);
        requestBody.put("lastName", last_name);
        requestBody.put("emailid", email_id);
        requestBody.put("mobileNumber", strmobile);
        requestBody.put("password", password);
        requestBody.put("shopName", str_shop_name);
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload", requestBody);
        ProgressDialogScreen.showProgressDialog(RegisterActivity.this);
        Call<RegisterMerchantModel> call = api.getRegisterMerchant(requestBody1);
        call.enqueue(new Callback<RegisterMerchantModel>() {
            @Override
            public void onResponse(@NonNull Call<RegisterMerchantModel> call, @NonNull Response<RegisterMerchantModel> response) {
                ProgressDialogScreen.hideProgressDialog();
                if (response.isSuccessful()) {
                    if (response.body().getPayload() != null) {
                        RegisterMerchantModel registerCustomerModel = response.body();
                        if (registerCustomerModel.getResponseCode().equals(200)) {
                            if (registerCustomerModel.getPayload().getRespCode().equals(200)) {

                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                                Toast.makeText(RegisterActivity.this, registerCustomerModel.getPayload().getRespMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                NetConnection.showMessage(RegisterActivity.this, "" + registerCustomerModel.getPayload().getRespMessage());
                            }


                        } else {
                            NetConnection.showMessage(RegisterActivity.this, "" + registerCustomerModel.getStatus());

                        }

                    } else {
                        //body
                        NetConnection.showMessage(RegisterActivity.this, ErrorConstants.JSONDATA_NULL);

                    }

                } else {

                    //response
                    NetConnection.showMessage(RegisterActivity.this, ErrorConstants.RESPONSE_NULL);
                }

            }

            @Override
            public void onFailure(@NonNull Call<RegisterMerchantModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(RegisterActivity.this, ErrorConstants.Fail_message);

                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}



