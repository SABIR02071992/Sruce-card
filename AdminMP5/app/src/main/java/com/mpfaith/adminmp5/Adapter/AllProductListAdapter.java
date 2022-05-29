package com.mpfaith.adminmp5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpfaith.adminmp5.Activity.UpdateProductLisActivity;
import com.mpfaith.adminmp5.Model.ActiveProductModel;
import com.mpfaith.adminmp5.Model.AllProductListByUserIdModelListPayload;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ConstantVariable;
import com.mpfaith.adminmp5.utils.ProgressDialogScreen;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductListAdapter extends RecyclerView.Adapter<AllProductListAdapter.VendorViewHolder>{

    Context context;
    List<AllProductListByUserIdModelListPayload> vendorModalClasses;
    RestAPI api;
    String list_id;
    String member_type;
    String date_s;
    String str_id;

    UserSharedPreferences userSharedPreferences;

    ConstantVariable constantVariable;
    public AllProductListAdapter(Context context, List<AllProductListByUserIdModelListPayload> vendorModalClasses) {
        this.context = context;
        this.vendorModalClasses = vendorModalClasses;
        constantVariable = new ConstantVariable();
        api = APIClient.getClient().create(RestAPI.class);
    }



    public class VendorViewHolder extends RecyclerView.ViewHolder {
        TextView name_brand,product,status,price,offer;
        ImageView rest_Image;
        Button edit,marks_sold,delete;

        public VendorViewHolder(@NonNull View itemView) {
            super(itemView);
            name_brand = itemView.findViewById(R.id.name_brand);
            rest_Image = itemView.findViewById(R.id.restImage);
            status = itemView.findViewById(R.id.status);
            price = itemView.findViewById(R.id.price);
            offer = itemView.findViewById(R.id.offer);
            product = itemView.findViewById(R.id.product);
            edit=itemView.findViewById(R.id.edit);
            marks_sold=itemView.findViewById(R.id.marks_sold);
            delete=itemView.findViewById(R.id.delete);


        }
    }

    @NonNull
    @Override
    public AllProductListAdapter.VendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_lay, parent, false);
        return new AllProductListAdapter.VendorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllProductListAdapter.VendorViewHolder holder, final int position) {
        final AllProductListByUserIdModelListPayload model_ads = vendorModalClasses.get(position);
        str_id = String.valueOf(model_ads.getProductId());
        holder.status.setText(model_ads.getProductStatus());
        holder.name_brand.setText(model_ads.getProductBrand());
        holder.product.setText(model_ads.getProductName());
        holder.offer.setText(model_ads.getDiscount()+"%");
        holder.price.setText(String.valueOf(model_ads.getProductPrice()));

        if(model_ads.getProductImage() !=null){
            Picasso.get().load(constantVariable.BASE_URL+"productImages/"+model_ads.getProductImage()).into(holder.rest_Image);

        }
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdateProductLisActivity.class);
                intent.putExtra("productId",String.valueOf(model_ads.getProductId()));
                intent.putExtra("mainCategoryId",String.valueOf(model_ads.getCategoryId()));
//                intent.putExtra("mainCategoryId",String.valueOf(model_ads.getCategoryId()));
                intent.putExtra("productName",model_ads.getProductName());
                intent.putExtra("productBrand",model_ads.getProductBrand());
                intent.putExtra("productPrice",String.valueOf(model_ads.getProductPrice()));
                intent.putExtra("discount",String.valueOf(model_ads.getDiscount()));
                intent.putExtra("productDescription",model_ads.getProductDescription());
                intent.putExtra("createdBy",model_ads.getCreatedBy());
                context.startActivity(intent);
            }
        });
        if (holder.status.getText().toString().equalsIgnoreCase("inactive")) {
            holder.marks_sold.setTextColor(Color.parseColor("#F55625"));
            holder.marks_sold.setBackground(context.getResources().getDrawable(R.drawable.button_shapebtnred));
            holder.marks_sold.setText("Inactive");

        } else {

            holder.marks_sold.setBackground(context.getResources().getDrawable(R.drawable.shapebtn));
            holder.marks_sold.setTextColor(Color.parseColor("#2EAC33"));
            holder.marks_sold.setText("Active");
        }
        holder.marks_sold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = holder.marks_sold.getText().toString().trim();
                final AllProductListByUserIdModelListPayload tecResponse = vendorModalClasses.get(position);
                if (status.equals("Activate")) {

                    String status_s = "INACTIVE";
                    sendToapi(String.valueOf(tecResponse.getProductId()), status_s, holder);
                } else {
                    String status_s = "ACTIVE";
                    sendToapi(String.valueOf(tecResponse.getProductId()), status_s, holder);
                }
            }
        });


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, AllProductListActivity.class);
//                intent.putExtra("category_id",String.valueOf(model_ads.getCategoryId()));
//                context.startActivity(intent);
//            }
//        });

    }

    private void sendToapi(String id, String status_s, final VendorViewHolder holder_h) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", id);
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(context);
        Call<ActiveProductModel> call = api.getActiceDeactive(requestBody1);
        call.enqueue(new Callback<ActiveProductModel>() {
            @Override
            public void onResponse(Call<ActiveProductModel> call, Response<ActiveProductModel> response) {
                ProgressDialogScreen.hideProgressDialog();
                if (response.isSuccessful() ) {
                    ActiveProductModel login= response.body();
                    if (login.getResponseCode().equals(200)) {
                        if (holder_h.status.getText().toString().equalsIgnoreCase("inactive")) {
                            holder_h.status.setText("ACTIVE");
                            Toast.makeText(context, "Active Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            holder_h.status.setText("INACTIVE");
                            Toast.makeText(context, "Inactive Successfully ", Toast.LENGTH_SHORT).show();

                        }
                        if (holder_h.status.getText().toString().equalsIgnoreCase("inactive")) {
                            holder_h.marks_sold.setBackground(context.getResources().getDrawable(R.drawable.button_shapebtnred));
                            holder_h.marks_sold.setTextColor(Color.parseColor("#F55625"));
                            holder_h.marks_sold.setText("Inactive");
                        } else {
                            holder_h.marks_sold.setBackground(context.getResources().getDrawable(R.drawable.shapebtn));
                            holder_h.marks_sold.setTextColor(Color.parseColor("#2EAC33"));
                            holder_h.marks_sold.setText("Active");
                        }
                        // Toast.makeText(context, login.getPayload().getRespMesg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "ERROR : 101 " + response.message(), Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<ActiveProductModel> call, Throwable t) {
                Toast.makeText(context, "server error " , Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return vendorModalClasses.size();
    }



}



