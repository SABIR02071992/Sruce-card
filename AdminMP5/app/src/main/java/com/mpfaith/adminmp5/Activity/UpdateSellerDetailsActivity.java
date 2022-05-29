package com.mpfaith.adminmp5.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mpfaith.adminmp5.Model.UpdateMerchantModel;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.NetConnection;
import com.mpfaith.adminmp5.utils.ProgressDialogScreen;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSellerDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    RestAPI api;
    UserSharedPreferences userSharedPreferences;
    Button submit_button_in;
    EditText first_name,last_name,email_id,gst,sellerAddress,pinCode,city,state,pan_card;
    String str_first_name,str_last_name,str_email_id,str_gst,str_sellerAddress,str_pinCode,str_city,str_state,str_pan_card;
    String str_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        init();
    }
    public void init(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(" Update ");
        api = APIClient.getClient().create(RestAPI.class);
        userSharedPreferences = new UserSharedPreferences(this);
        submit_button_in=findViewById(R.id.update_button);
//        str_house_no = getIntent().getStringExtra("house_no");
//        str_id = getIntent().getStringExtra("id");
//        str_street_no = getIntent().getStringExtra("street_no");
//        str_area_u = getIntent().getStringExtra("area");
//        str_land_mark = getIntent().getStringExtra("land_mark");
//        str_pin_code= getIntent().getStringExtra("pin_code");
//        str_city_u = getIntent().getStringExtra("city");
//        str_state_u = getIntent().getStringExtra("state");



//        first_name =findViewById(R.id.first_name);
//        last_name =findViewById(R.id.last_name);
//        email_id =findViewById(R.id.email_id);
        gst=findViewById(R.id.gst);
        sellerAddress=findViewById(R.id.sellerAddress);
        pinCode=findViewById(R.id.pinCode);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        pan_card=findViewById(R.id.pan_card);

//        hone_no.setText(str_house_no);
//        streetNo.setText(str_street_no);
//        area.setText(str_area_u);
//        landMark.setText(str_land_mark);
//        pinCode.setText(str_pin_code);
//        city.setText(str_city_u);
//        state.setText(str_state_u);
        submit_button_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                str_first_name = first_name.getText().toString().trim();
//                str_last_name = last_name.getText().toString().trim();
//                str_email_id = email_id.getText().toString().trim();
                str_gst = gst.getText().toString().trim();
                str_sellerAddress=sellerAddress.getText().toString().trim();
                str_pinCode = pinCode.getText().toString().trim();
                str_city = city.getText().toString().trim();
                str_state = state.getText().toString().trim();
                str_pan_card = pan_card.getText().toString().trim();
                if (NetConnection.isNetworkAvailable(UpdateSellerDetailsActivity.this)) {

                    if(!str_gst.isEmpty()&&!str_sellerAddress.isEmpty()&&!str_pinCode.isEmpty()&&!str_city.isEmpty()&&!str_state.isEmpty()&&!str_pan_card.isEmpty()){


                        getaddAddress(str_gst,str_sellerAddress,str_pinCode, str_city,str_state,str_pan_card);



                    }else {
                        Toast.makeText(UpdateSellerDetailsActivity.this, "Fill the field ", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Toast.makeText(UpdateSellerDetailsActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    private void getaddAddress(String  str_gst,String  str_sellerAddress,String str_pinCode,String str_city,String str_state,String str_pan_card) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("loginId",userSharedPreferences.getLoginID());
        requestBody.put("sellerAddress",str_sellerAddress);
        requestBody.put("sellerCity", str_city);
        requestBody.put("sellerState", str_state);
        requestBody.put("pinCode", str_pinCode);
        requestBody.put("sellerGst", str_gst);
        requestBody.put("sellerPan", str_pan_card);


        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(UpdateSellerDetailsActivity.this);
        Call<UpdateMerchantModel> call = api.getUpdateMerchant(requestBody1);

        call.enqueue(new Callback<UpdateMerchantModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateMerchantModel> call, @NonNull Response<UpdateMerchantModel> response) {
                ProgressDialogScreen.hideProgressDialog();

                if (response.isSuccessful() ) {
                    UpdateMerchantModel addmodel= response.body();
                    if(addmodel.getPayload().getRespCode()!=null){
                        if(addmodel.getPayload().getRespCode().equals(200)){
                            Intent intent=new Intent(UpdateSellerDetailsActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }else {
                        Toast.makeText(UpdateSellerDetailsActivity.this, "null " + response.message(), Toast.LENGTH_LONG).show();

                    }



                    Toast.makeText(UpdateSellerDetailsActivity.this, addmodel.getPayload().getRespMessage(), Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(UpdateSellerDetailsActivity.this, "ERROR : 101 " + response.message(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<UpdateMerchantModel> call, @NonNull Throwable t) {


                Toast.makeText(UpdateSellerDetailsActivity.this, "server error" ,Toast.LENGTH_LONG).show();

                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:

                return super.onOptionsItemSelected(item);
        }
        return false;
    }

}