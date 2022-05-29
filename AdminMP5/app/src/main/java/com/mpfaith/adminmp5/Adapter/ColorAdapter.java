package com.mpfaith.adminmp5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpfaith.adminmp5.Model.AllProductListByUserIdModelListPayload;
import com.mpfaith.adminmp5.Model.ProductStockModel;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.utils.ConstantVariable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.VendorViewHolder>{

    Context context;
    ArrayList<ProductStockModel> vendorModalClasses;

    String list_id;
    String member_type;
    String date_s;

    ConstantVariable constantVariable;
    public ColorAdapter(Context context, ArrayList<ProductStockModel>vendorModalClasses) {
        this.context = context;
        this.vendorModalClasses = vendorModalClasses;
        constantVariable = new ConstantVariable();
    }





    public class VendorViewHolder extends RecyclerView.ViewHolder {
        TextView color_name;
        ImageView delete;

        public VendorViewHolder(@NonNull View itemView) {
            super(itemView);
            color_name = itemView.findViewById(R.id.color_name);
            delete = itemView.findViewById(R.id.delete);



        }
    }

    @NonNull
    @Override
    public ColorAdapter.VendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.color_layout, parent, false);
        return new ColorAdapter.VendorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ColorAdapter.VendorViewHolder holder, final int position) {
        final ProductStockModel model= vendorModalClasses.get(position);

        holder.color_name.setText(model.getProduct_color()+",   "+model.getProduct_size()+",  "+model.getAvailableQuantity());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendorModalClasses.remove(vendorModalClasses.get(position));

                notifyDataSetChanged();
            }
        });

    }
    @Override
    public int getItemCount() {
        return vendorModalClasses.size();
    }



}

