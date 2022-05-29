package com.mpfaith.adminmp5.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.mpfaith.adminmp5.Adapter.ColorAdapter;
import com.mpfaith.adminmp5.Model.AllCategoryListModel;
import com.mpfaith.adminmp5.Model.AllSubCategoryListModel;
import com.mpfaith.adminmp5.Model.MainCategoryListModel;
import com.mpfaith.adminmp5.Model.ProductStockModel;
import com.mpfaith.adminmp5.Model.UpdateListProductModel;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ErrorConstants;
import com.mpfaith.adminmp5.utils.NetConnection;
import com.mpfaith.adminmp5.utils.ProgressDialogScreen;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import eu.amirs.JSON;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

 public class UpdateProductLisActivity extends AppCompatActivity {

    Toolbar toolbar;
    RestAPI api;
    ArrayList<String> meterSrArrayList;
    UserSharedPreferences userSharedPreferences;
    ArrayAdapter<String> meterSrNumAdapter;
    String str_brand,str_categoryMain,subcategory,productName;
    String str_category,str_category_id;
    EditText available_Quantity,product_brand,product_description,product_price,product_dist,color_edit,size_edit,product_Id,created_by;
    ImageView imageView,image_set;
    ListView listView;
    private static final int CAMERA_PIC_REQUEST = 1337;
    private static final int SELECT_IMAGE = 1338;
    private Bitmap bitmap;
    ArrayList<String>meterSrArrayList_sub;
    ArrayAdapter<String> meterSrNumAdapter_2;
    ArrayList<String> meterSrArrayList_2;
    String str_sub_ccate_id;
    ArrayList<String>meterSrArrayList_cate;
    ArrayList<String>meterSrArrayList_main;
    ArrayList<String>meterSrArrayList_cate_main_2;
    ArrayAdapter<String> meterSrNumAdapter_main;
    TextView  editText1,editText2,editText3;
    LinearLayout searchL1,searchL2,searchL3;
    String str_editText1,str_editText2,str_editText3;
    private Uri fileURI;
    Button submit_button;
    ImageView color_add;
    ColorAdapter adapter;
    String str_image_aa;
    String str_product_name,str_available_Quantity,str_product_brand,str_category_main_id,str_product_category, str_product_dist,str_product_price,str_product_description,str_product_id,str_cgst,str_sgst,str_commission,str_quantity,str_createdBy,str_sellQuantity,str_totalPrice;
    ProductStockModel model;
    ArrayList<ProductStockModel> color_list;
    ArrayList<String> size_list;
    TextView product_name,spp_text_1,cat_text_2,cat_text_3;
    TextInputLayout spp_text_lay,spp_text_lay_2,spp_text_lay_3;
    LinearLayout search1,search2,search3;
    String str_mainCategoryI_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product_lis);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Update Product List");

        str_mainCategoryI_d = getIntent().getStringExtra("mainCategoryId");
        str_product_id=getIntent().getStringExtra("productId");
        str_product_name=getIntent().getStringExtra("productName");
        str_product_brand=getIntent().getStringExtra("productBrand");
        str_product_price=getIntent().getStringExtra("productPrice");
        str_product_dist=getIntent().getStringExtra("discount");
        str_product_description=getIntent().getStringExtra("productDescription");


 // Assign variable...
        //editText1=findViewById(R.id.edit_ser1);
        submit_button=findViewById(R.id.submit_button);
        color_add=findViewById(R.id.color_add);
        color_edit=findViewById(R.id.color_edit);
        size_edit=findViewById(R.id.size_edit);
        product_name=findViewById(R.id.product_name);
        available_Quantity=findViewById(R.id.available_Quantity);
        product_brand=findViewById(R.id.product_brand);
        product_price=findViewById(R.id.product_price);
        product_dist=findViewById(R.id.product_dist);
        product_description=findViewById(R.id.product_description);
        spp_text_lay=findViewById(R.id.spp_text_lay);
        spp_text_lay_2=findViewById(R.id.spp_text_lay_2);
        spp_text_lay_3=findViewById(R.id.spp_text_lay_3);
        spp_text_1=findViewById(R.id.spp_text_1);
        cat_text_2=findViewById(R.id.cat_text_2);
        cat_text_3=findViewById(R.id.cat_text_3);

        search1=findViewById(R.id.search1);
        search2=findViewById(R.id.search2);
        search3=findViewById(R.id.search3);

//setText in all field......
//  editText1.setText(str_editText1);
        product_name.setText(str_product_name);
        product_brand.setText(str_product_brand);
        product_price.setText(str_product_price);
        product_dist.setText(str_product_dist);
        product_description.setText(str_product_description);

//        listView.setVisibility(View.INVISIBLE);
        api = APIClient.getClient().create(RestAPI.class);
        userSharedPreferences = new UserSharedPreferences(this);
        if (NetConnection.isNetworkAvailable(UpdateProductLisActivity.this)) {
            getMainCategoryList(str_mainCategoryI_d);

        }else {
            Toast.makeText(UpdateProductLisActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();
        }
        spp_text_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.setVisibility(View.GONE);
                searchL1.setVisibility(View.VISIBLE);
            }
        });
        cat_text_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText2.setVisibility(View.GONE);
                searchL2.setVisibility(View.VISIBLE);
            }
        });
        cat_text_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText2.setVisibility(View.GONE);
                searchL2.setVisibility(View.VISIBLE);
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_product_name = product_name.getText().toString().trim();
                str_product_brand = product_brand.getText().toString().trim();
                str_product_dist = product_dist.getText().toString().trim();
                str_product_price = product_price.getText().toString().trim();
                str_product_description = product_description.getText().toString().trim();

                if (NetConnection.isNetworkAvailable(UpdateProductLisActivity.this)) {

                    getAddUpdateApi(str_sub_ccate_id,str_available_Quantity,str_product_id,str_product_name,str_product_brand,str_product_dist,str_product_price,str_product_description);

                }else{
                    Toast.makeText(UpdateProductLisActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        SearchableSpinner searchableSpinner = (SearchableSpinner)findViewById(R.id.searchable_category);
        meterSrArrayList = new ArrayList<>();
        meterSrArrayList_cate = new ArrayList<>();
        meterSrNumAdapter = new ArrayAdapter<String>(UpdateProductLisActivity.this, R.layout.support_simple_spinner_dropdown_item, meterSrArrayList);
        searchableSpinner.setAdapter(meterSrNumAdapter);
        searchableSpinner.setPrompt("Categorie");
        searchableSpinner.setPositiveButton("Ok");
        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_category_id = meterSrArrayList_cate.get(position);
                if(str_category_id !=null){

                    getSubCategoryList(str_category_id);

                }else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        SearchableSpinner searchableSpinner_main = (SearchableSpinner)findViewById(R.id.searchable_main_category);
        meterSrArrayList_main = new ArrayList<>();
        meterSrArrayList_cate_main_2 = new ArrayList<>();
        meterSrNumAdapter_main = new ArrayAdapter<String>(UpdateProductLisActivity.this, R.layout.support_simple_spinner_dropdown_item, meterSrArrayList_main);
        searchableSpinner_main.setAdapter(meterSrNumAdapter_main);
        searchableSpinner_main.setTitle("Select Item");
        searchableSpinner_main.setPositiveButton("Ok");

        searchableSpinner_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_category_main_id = meterSrArrayList_cate_main_2.get(position);
                if(str_category_main_id !=null){
                    if (NetConnection.isNetworkAvailable(UpdateProductLisActivity.this)) {
                        getCategoryList(str_category_main_id);

                    }else {
                        Toast.makeText(UpdateProductLisActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        SearchableSpinner searchableSpinner_2 = (SearchableSpinner)findViewById(R.id.sub_searchable_category);
        meterSrArrayList_2 = new ArrayList<>();
        meterSrArrayList_sub = new ArrayList<>();
        meterSrNumAdapter_2 = new ArrayAdapter<String>(UpdateProductLisActivity.this, R.layout.support_simple_spinner_dropdown_item, meterSrArrayList_2);
        searchableSpinner_2.setAdapter(meterSrNumAdapter_2);
        searchableSpinner_2.setTitle("Select Item");
        searchableSpinner_2.setPositiveButton("Ok");

        searchableSpinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_sub_ccate_id = meterSrArrayList_sub.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }
    private void getMainCategoryList(final String str_mer_id) {

        Map<String, String> requestBody = new HashMap<>();
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload", requestBody);
        Call<MainCategoryListModel> call = api.getMainCatList(requestBody1);
        call.enqueue(new Callback<MainCategoryListModel>() {
            @Override
            public void onResponse(@NonNull Call<MainCategoryListModel> call, @NonNull Response<MainCategoryListModel> response) {

                if (response.isSuccessful()) {
                    MainCategoryListModel listModel = response.body();

                    if (listModel.getResponseCode().equals(200)) {


                        for (int j = 0; j < listModel.getListPayload().size(); j++) {
                          meterSrArrayList_main.add(listModel.getListPayload().get(j).getMainCategoryName());
                          meterSrArrayList_cate_main_2.add(String.valueOf(listModel.getListPayload().get(j).getMainCategoryId()));

                            if(String.valueOf(listModel.getListPayload().get(j).getMainCategoryId()).equals(str_mainCategoryI_d)){
                                spp_text_1.setText(listModel.getListPayload().get(j).getMainCategoryName());
                            }


                        }

                    }
                    else {
                        NetConnection.showMessage(UpdateProductLisActivity.this, ""+listModel.getStatus());
                    }




                } else {
                    Toast.makeText(UpdateProductLisActivity.this, response.message(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MainCategoryListModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(UpdateProductLisActivity.this, ErrorConstants.Fail_message);
            }

        });

    }

    private void getCategoryList(String str_category_main_id){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("mainCategoryId", str_category_main_id);
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(UpdateProductLisActivity.this);
        Call<AllCategoryListModel> call = api.getAllCategoryList(requestBody1);
        call.enqueue(new Callback<AllCategoryListModel>() {
            @Override
            public void onResponse(@NonNull Call<AllCategoryListModel> call, @NonNull Response<AllCategoryListModel> response) {
                ProgressDialogScreen.hideProgressDialog();
                if (response.isSuccessful() ) {
                    if (response.body().getListPayload() != null) {
                        AllCategoryListModel category_model= response.body();
                        meterSrArrayList.clear();
                        if(category_model != null &category_model.getResponseCode().equals(200)){


                            for (int j = 0; j < category_model.getListPayload().size(); j++) {
                                meterSrArrayList.add(category_model.getListPayload().get(j).getCategoryName());
                                meterSrArrayList_cate.add(String.valueOf (category_model.getListPayload().get(j).getCategoryId()));


                            }

                        }else {
                            NetConnection.showMessage(UpdateProductLisActivity.this, ""+category_model.getStatus());

                        }

                    }else {
                        //body
                        NetConnection.showMessage(UpdateProductLisActivity.this, ErrorConstants.JSONDATA_NULL);

                    }

                } else {

                    //response
                    NetConnection.showMessage(UpdateProductLisActivity.this, ErrorConstants.RESPONSE_NULL);
                }

            }

            @Override
            public void onFailure(@NonNull Call<AllCategoryListModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(UpdateProductLisActivity.this, ErrorConstants.Fail_message);

                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getSubCategoryList(String str_category_id){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("requestFor", "BYCATID");
        requestBody.put("categoryId", str_category_id);
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(UpdateProductLisActivity.this);
        Call<AllSubCategoryListModel> call = api.getAllSubCategoryList(requestBody1);
        call.enqueue(new Callback<AllSubCategoryListModel>() {
            @Override
            public void onResponse(@NonNull Call<AllSubCategoryListModel> call, @NonNull Response<AllSubCategoryListModel> response) {
                ProgressDialogScreen.hideProgressDialog();
                if (response.isSuccessful() ) {
                    if (response.body().getListPayload() != null) {
                        AllSubCategoryListModel category_model= response.body();
                        meterSrArrayList_2.clear();
                        if(category_model != null &category_model.getResponseCode().equals(200)){
                            for (int j = 0; j < category_model.getListPayload().size(); j++) {
                                meterSrArrayList_2.add(category_model.getListPayload().get(j).getSubCategoryName());
                                meterSrArrayList_sub.add(String.valueOf(category_model.getListPayload().get(j).getSubCategoryId()));

                            }

                        }else {
                            NetConnection.showMessage(UpdateProductLisActivity.this, ""+category_model.getStatus());

                        }

                    }else {
                        //body
                        NetConnection.showMessage(UpdateProductLisActivity.this, ErrorConstants.JSONDATA_NULL);

                    }

                } else {

                    //response
                    NetConnection.showMessage(UpdateProductLisActivity.this, ErrorConstants.RESPONSE_NULL);
                }

            }

            @Override
            public void onFailure(@NonNull Call<AllSubCategoryListModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(UpdateProductLisActivity.this, ErrorConstants.Fail_message);

                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getAddUpdateApi(String str_sub_ccate_id,String str_available_Quantity,String str_product_id, String str_product_name, String str_product_brand, String str_product_dist, String str_product_price, String str_product_description) {

        Map<String,String>requestBody=new HashMap<>();
        requestBody.put("productId",str_product_id);
        requestBody.put("subCategoryId",str_sub_ccate_id);
        requestBody.put("productName",str_product_name);
        requestBody.put("productBrand",str_product_brand);
        requestBody.put("productPrice",str_product_price);
        requestBody.put("discount",str_product_dist);
        requestBody.put("productDescription",str_product_description);
        requestBody.put("createdBy",userSharedPreferences.getLoginID());


        Map<String,Map>requestBody1=new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(UpdateProductLisActivity.this);
        Call<UpdateListProductModel>call=api.getUpdateProduct(requestBody1);
        call.enqueue(new Callback<UpdateListProductModel>() {
            @Override
            public void onResponse(Call<UpdateListProductModel> call, Response<UpdateListProductModel> response) {
                ProgressDialogScreen.hideProgressDialog();
                if(response.isSuccessful()){
                    UpdateListProductModel updateListProductModel=response.body();
                    if (updateListProductModel.getPayload().getRespCode()!=null){
                        if (updateListProductModel.getPayload().getRespCode().equals(200)){

                            Intent intent=new Intent(UpdateProductLisActivity.this, ProductListActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(UpdateProductLisActivity.this, "null " + response.message(), Toast.LENGTH_LONG).show();

                        }

                    }else {
                        Toast.makeText(UpdateProductLisActivity.this, "ERROR : 101 " + response.message(), Toast.LENGTH_LONG).show();


                    }

                }

            }

            @Override
            public void onFailure(Call<UpdateListProductModel> call, Throwable t) {
                Toast.makeText(UpdateProductLisActivity.this, "server error" ,Toast.LENGTH_LONG).show();


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