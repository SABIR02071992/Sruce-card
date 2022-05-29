package com.mpfaith.adminmp5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mpfaith.adminmp5.Activity.ViewOrederListActivity;
import com.mpfaith.adminmp5.Model.AllProductListByUserIdModelListPayload;
import com.mpfaith.adminmp5.Model.ProductOrderModel;
import com.mpfaith.adminmp5.Model.ProductorderModelPayload;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ConstantVariable;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    List<ProductorderModelPayload>vendorModalClasses;
    RestAPI api;
    String str_id;
    String date_s;

    ConstantVariable constantVariable;
    public OrderAdapter(Context context,List<ProductorderModelPayload>vendorModalClasses){
        this.context=context;
        this.vendorModalClasses=vendorModalClasses;
        constantVariable = new ConstantVariable();
        api = APIClient.getClient().create(RestAPI.class);
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_lay, parent, false);
       return new OrderAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ProductorderModelPayload model_ads = vendorModalClasses.get(position);
        Date date1=new Date(model_ads.getCreatedAt());
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy | hh:mm:ss a");
        date_s=dateFormat.format(date1);
        str_id = String.valueOf(model_ads.getStoreId());
        holder.date.setText(date_s);
        holder.product_name.setText(model_ads.getProductName());
        holder.product_brand.setText(model_ads.getProductBrand());
        holder.quantity.setText(String.valueOf(model_ads.getSellQuantity()));
        holder.payment_mode.setText(model_ads.getPaymentMode());


        if(model_ads.getProductImage() !=null){
            Picasso.get().load(constantVariable.BASE_URL+"productImages/"+model_ads.getProductImage()).into(holder.restImage);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewOrederListActivity.class);
                intent.putExtra("sellId",String.valueOf(model_ads.getSellId()));
                intent.putExtra("productName",String.valueOf(model_ads.getProductName()));
                intent.putExtra("productBrand",model_ads.getProductBrand());
                intent.putExtra("sellQuantity",String.valueOf(model_ads.getSellQuantity()));
                intent.putExtra("productPrice",String.valueOf(model_ads.getProductPrice()));
                intent.putExtra("discount", String.valueOf(model_ads.getDiscount()));
                intent.putExtra("totalPrice",String.valueOf(model_ads.getTotalPrice()));
                intent.putExtra("storeId", model_ads.getStoreId());
                intent.putExtra("paymentMode", model_ads.getPaymentMode());
                intent.putExtra("addressId", String.valueOf(model_ads.getAddressId()));
                intent.putExtra("status", model_ads.getStatus());
                intent.putExtra("image",model_ads.getProductImage());
                context.startActivity(intent);
            }
        });

  holder.img_dot.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                }
                return false;
            }
        });
        popupMenu.inflate(R.menu.listorder_menu);
        popupMenu.show();

    }
   });

    }



    @Override
    public int getItemCount() {
        return vendorModalClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name,product_brand,quantity,payment_mode,date;
        ImageView img_dot,restImage;
        CardView cardView;
        Button button_dd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            product_brand=itemView.findViewById(R.id.product_brand);
            quantity=itemView.findViewById(R.id.quantity);
            payment_mode=itemView.findViewById(R.id.payment_mode);
//            cardView=itemView.findViewById(R.id.card_view);
            img_dot=itemView.findViewById(R.id.img_dot);
            date=itemView.findViewById(R.id.date_set);
           restImage=itemView.findViewById(R.id.restImage);
           // cardView=itemView.findViewById(R.id.cardView);
          //  button_dd=itemView.findViewById(R.id.button_dd);
        }

    }

}
