package com.mpfaith.adminmp5.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mpfaith.adminmp5.Adapter.OrderAdapter;
import com.mpfaith.adminmp5.Model.ProductOrderModel;
import com.mpfaith.adminmp5.Model.ProductorderModelPayload;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ErrorConstants;
import com.mpfaith.adminmp5.utils.NetConnection;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class ProductOrderManagementActivity extends AppCompatActivity {
    Toolbar toolbar;
    RestAPI api;
    UserSharedPreferences userSharedPreferences;
    RecyclerView recy_catord;
    List<ProductorderModelPayload> list;
    OrderAdapter adapter;
    ImageView no_item;
    TextView today,yesterday,all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_order_management);

        intView();
    }

    private void intView() {
        no_item=findViewById(R.id.no_item);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Order List");
        today=findViewById(R.id.today);
        yesterday=findViewById(R.id.yesterday);
        all=findViewById(R.id.all);
        api = APIClient.getClient().create(RestAPI.class);
        userSharedPreferences = new UserSharedPreferences(this);
        recy_catord = findViewById(R.id.recy_catord);// get the reference of ViewFlipper
        GridLayoutManager layoutManager = new GridLayoutManager(ProductOrderManagementActivity.this, 1, LinearLayoutManager.VERTICAL, false);
        recy_catord.setLayoutManager(layoutManager);

        list = new ArrayList();
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (NetConnection.isNetworkAvailable(ProductOrderManagementActivity.this)){
            getProductlist();

        }else {
            Toast.makeText(ProductOrderManagementActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();

        }
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void getProductlist() {
        adapter = new OrderAdapter(ProductOrderManagementActivity.this, list);
        recy_catord.setAdapter(adapter);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("requestFor","MERCHANT");
        requestBody.put("storeId",userSharedPreferences.getLoginID());

        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload", requestBody);

        Call<ProductOrderModel> call = api.getAllOrderList(requestBody1);
        call.enqueue(new Callback<ProductOrderModel>() {
            @Override
            public void onResponse(Call<ProductOrderModel> call, Response<ProductOrderModel> response) {
                if (response.isSuccessful()) {
                    ProductOrderModel allmodel = response.body();
                    list.clear();
                    if (allmodel.getResponseCode().equals(200)) {

                        list.addAll(allmodel.getListPayload());

                    }else {
                        NetConnection.showMessage(ProductOrderManagementActivity.this, ""+allmodel.getStatus());

                    }
                    if (list.size() == 0) {
                        no_item.setVisibility(View.VISIBLE);

                    } else {

                       no_item.setVisibility(View.GONE);
                    }

                    adapter.notifyDataSetChanged();


                }else{
                    Toast.makeText(ProductOrderManagementActivity.this, "", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductOrderModel> call, Throwable t) {
                NetConnection.showMessage(ProductOrderManagementActivity.this, ErrorConstants.Fail_message);


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