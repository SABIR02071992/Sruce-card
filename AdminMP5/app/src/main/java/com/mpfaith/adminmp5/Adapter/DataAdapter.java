package com.mpfaith.adminmp5.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.mpfaith.adminmp5.R;

import java.io.IOException;
import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter {
    ArrayList<Uri> imagesUriArrayList;
    Activity mainActivity;
    ImageView imageview;
    Bitmap bitmap = null;
    Context context;
    public DataAdapter(Context context, Activity addNewVehicleActivity, ArrayList imagesUriArrayList) {
        super(context, R.layout.listview,imagesUriArrayList);
        this.imagesUriArrayList = imagesUriArrayList;
        this.mainActivity = addNewVehicleActivity;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview, parent, false);
            imageview = (ImageView) convertView.findViewById(R.id.imageView);
        }

        Uri selectedImage = imagesUriArrayList.get(position);

        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),selectedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageview.setImageBitmap(bitmap);

        return  convertView;
    }}
