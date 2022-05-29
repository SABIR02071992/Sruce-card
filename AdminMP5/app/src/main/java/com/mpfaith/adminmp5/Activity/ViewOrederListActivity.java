package com.mpfaith.adminmp5.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mpfaith.adminmp5.Model.DeliveryAddressModel;
import com.mpfaith.adminmp5.Model.DeliveryAddressModelPayload;
import com.mpfaith.adminmp5.Model.ProductOrderModel;
import com.mpfaith.adminmp5.Model.ProductorderModelPayload;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ConstantVariable;
import com.mpfaith.adminmp5.utils.ErrorConstants;
import com.mpfaith.adminmp5.utils.NetConnection;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrederListActivity extends AppCompatActivity {
Toolbar toolbar;
TextView productName,productBrand,productQuantity,productPaymentMode,productPrice,productDiscount, mobile,productTotalPrice,productDeliveryAddress;
String str_productName,str_product_brand,str_product_quantity,str_product_paymentMode,str_product_price,
        str_product_discount ,str_product_totalprice,str_image;

    List<DeliveryAddressModelPayload> list;
    RestAPI api;
    UserSharedPreferences userSharedPreferences;
    ConstantVariable constantVariable;
    ImageView restImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_oreder_list);
        intView();
        deliverAddress();
    }


    private void intView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("View OrderList");
        constantVariable = new ConstantVariable();
// get data throw intent..coming from Order Adapter
        str_productName=getIntent().getStringExtra("productName");
        str_product_brand=getIntent().getStringExtra("productBrand");
        str_product_quantity=getIntent().getStringExtra("sellQuantity");
        str_product_paymentMode=getIntent().getStringExtra("paymentMode");
        str_product_price=getIntent().getStringExtra("productPrice");
        str_product_discount=getIntent().getStringExtra("discount");
        str_product_totalprice=getIntent().getStringExtra("totalPrice");
        str_image=getIntent().getStringExtra("image");
        api = APIClient.getClient().create(RestAPI.class);
        productName=findViewById(R.id.product_name);
        productBrand=findViewById(R.id.product_brand);
        productQuantity=findViewById(R.id.product_quantity);
        productPaymentMode=findViewById(R.id.payment_mode);
        productPrice=findViewById(R.id.product_price);
        productDiscount=findViewById(R.id.product_discount);
        productTotalPrice=findViewById(R.id.total_price);
        productDeliveryAddress=findViewById(R.id.product_deliveryAddress);
        restImage=findViewById(R.id.restImage);
        mobile=findViewById(R.id.mobile);
  // set data in TextView
        productName.setText(str_productName);
        productBrand.setText(str_product_brand);
        productQuantity.setText(str_product_quantity);
        productPaymentMode.setText(str_product_paymentMode);
        productPrice.setText(str_product_price);
        productDiscount.setText(str_product_discount+"%");
        productTotalPrice.setText(str_product_totalprice);
        if(str_image !=null){
            Picasso.get().load(constantVariable.BASE_URL+"productImages/"+str_image).into(restImage);

        }
    }
    private void deliverAddress() {
        if (NetConnection.isNetworkAvailable(this)){
            getDeliveryAddress();

        }else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDeliveryAddress() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("requestFor","BYADDID");
        requestBody.put("addressId","1");

        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload", requestBody);

       Call<DeliveryAddressModel>call=api.getAddress(requestBody1);
       call.enqueue(new Callback<DeliveryAddressModel>() {
           @Override
           public void onResponse(Call<DeliveryAddressModel> call, Response<DeliveryAddressModel> response) {
               if (response.isSuccessful()) {
                   DeliveryAddressModel allmodel = response.body();

                   if (allmodel.getResponseCode().equals(200)) {
                       for(int i=0;i<allmodel.getListPayload().size();i++){
                           String fullName= allmodel.getListPayload().get(i).getFullName();
                           String mobileNumber=allmodel.getListPayload().get(i).getMobileNumber();
                           String pinCode=allmodel.getListPayload().get(i).getPinCode();
                           String houseNo=allmodel.getListPayload().get(i).getHouseNo();
                           String area=allmodel.getListPayload().get(i).getArea();
                           String landmarks=allmodel.getListPayload().get(i).getLandmarks();
                           String city=allmodel.getListPayload().get(i).getCity();
                           String state=allmodel.getListPayload().get(i).getState();

                           productDeliveryAddress.setText(fullName + '\n' +houseNo +"  "+area+landmarks+"  "+city+"  "+state+" -"+pinCode);
                           mobile.setText(mobileNumber);
                       }

                   }else {
                       NetConnection.showMessage(ViewOrederListActivity.this, ""+allmodel.getStatus());

                   }
               }else {
                   Toast.makeText(ViewOrederListActivity.this, "", Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onFailure(Call<DeliveryAddressModel> call, Throwable t) {
               Toast.makeText(ViewOrederListActivity.this, "Error Connection", Toast.LENGTH_SHORT).show();

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