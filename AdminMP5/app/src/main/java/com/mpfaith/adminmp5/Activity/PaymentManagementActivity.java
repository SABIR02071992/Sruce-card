package com.mpfaith.adminmp5.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mpfaith.adminmp5.Model.PaymentWalletModel;
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

public class PaymentManagementActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView transfer_bank;
    Button button_transfer;
    String str_transferBank;
    RestAPI api;
    UserSharedPreferences userSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_management);
        intView();
        payMentWallet();
    }


    private void intView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Add Wallet");
        api = APIClient.getClient().create(RestAPI.class);
        userSharedPreferences = new UserSharedPreferences(this);

        transfer_bank=findViewById(R.id.transfer_bank);
        button_transfer=findViewById(R.id.button_transfer);

    }
    private void payMentWallet() {
        str_transferBank=transfer_bank.toString().trim();
        if (NetConnection.isNetworkAvailable(this)){
            getAddTransferBank(str_transferBank );

        }else {
            Toast.makeText(this, "No internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    private void getAddTransferBank(String str_transferBank) {
        Map<String,String>requestBody=new HashMap<>();
        requestBody.put("loginId",userSharedPreferences.getLoginID());

        Map<String,Map>requestBody1=new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(PaymentManagementActivity.this);
        Call<PaymentWalletModel>call=api.getMerchantWallet(requestBody1);
        call.enqueue(new Callback<PaymentWalletModel>() {
            @Override
            public void onResponse(Call<PaymentWalletModel> call, Response<PaymentWalletModel> response) {
                ProgressDialogScreen.hideProgressDialog();
               if (response.isSuccessful()){
                   PaymentWalletModel payment=response.body();
                   if (payment.getResponseCode().equals(200)){


                       Toast.makeText(PaymentManagementActivity.this, payment.getPayload().getRespMessage(), Toast.LENGTH_SHORT).show();

                   }else {
                       NetConnection.showMessage(PaymentManagementActivity.this, ""+payment.getStatus());

                   }
               }


            }

            @Override
            public void onFailure(Call<PaymentWalletModel> call, Throwable t) {
                NetConnection.showMessage(PaymentManagementActivity.this, ErrorConstants.Fail_message);


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