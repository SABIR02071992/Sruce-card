package com.mpfaith.adminmp5.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mpfaith.adminmp5.Adapter.AllProductListAdapter;
import com.mpfaith.adminmp5.Model.AllProductListByUserIdModel;
import com.mpfaith.adminmp5.Model.AllProductListByUserIdModelListPayload;
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

public class ProductListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<AllProductListByUserIdModelListPayload> list;
    AllProductListAdapter adapter;
    RestAPI api;
    Toolbar toolbar;
    ImageView no_item;
    UserSharedPreferences userSharedPreferences;
    String str_category_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        init();
    }
    private void init(){

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("All Product List");
        no_item=findViewById(R.id.no_item);
        api = APIClient.getClient().create(RestAPI.class);
        userSharedPreferences = new UserSharedPreferences(this);
        recyclerView = findViewById(R.id.recy_catord);// get the reference of ViewFlipper
        GridLayoutManager layoutManager = new GridLayoutManager(ProductListActivity.this, 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList();

            if (NetConnection.isNetworkAvailable(ProductListActivity.this)) {
                getProductlist();
            }else {
                Toast.makeText(ProductListActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();

            }
    }
    private void getProductlist() {
        adapter = new AllProductListAdapter(ProductListActivity.this, list);
        recyclerView.setAdapter(adapter);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("createdBy",userSharedPreferences.getLoginID());

        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload", requestBody);

        Call<AllProductListByUserIdModel> call = api.getAllProductListByUserId(requestBody1);
        call.enqueue(new Callback<AllProductListByUserIdModel>() {
            @Override
            public void onResponse(@NonNull Call<AllProductListByUserIdModel> call, @NonNull Response<AllProductListByUserIdModel> response) {

                if (response.isSuccessful()) {
                    AllProductListByUserIdModel allmodel = response.body();
                    list.clear();
                    if (allmodel.getResponseCode().equals(200)) {

                        list.addAll(allmodel.getListPayload());

                    }else {
                        NetConnection.showMessage(ProductListActivity.this, ""+allmodel.getStatus());

                    }
                    if (list.size() == 0) {
                        no_item.setVisibility(View.VISIBLE);

                    } else {

                        no_item.setVisibility(View.GONE);
                    }

                    adapter.notifyDataSetChanged();


                } else {
                    Toast.makeText(ProductListActivity.this, response.message(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<AllProductListByUserIdModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(ProductListActivity.this, ErrorConstants.Fail_message);
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