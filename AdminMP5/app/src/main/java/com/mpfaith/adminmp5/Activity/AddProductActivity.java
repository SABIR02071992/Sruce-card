package com.mpfaith.adminmp5.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import com.mpfaith.adminmp5.Adapter.AllProductListAdapter;
import com.mpfaith.adminmp5.Adapter.ColorAdapter;
import com.mpfaith.adminmp5.Adapter.DataAdapter;
import com.mpfaith.adminmp5.Model.AddProductModel;
import com.mpfaith.adminmp5.Model.AllCategoryListModel;
import com.mpfaith.adminmp5.Model.AllSubCategoryListModel;
import com.mpfaith.adminmp5.Model.MainCategoryListModel;
import com.mpfaith.adminmp5.Model.MerchantLoginModel;
import com.mpfaith.adminmp5.Model.ProductStockModel;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ErrorConstants;
import com.mpfaith.adminmp5.utils.NetConnection;
import com.mpfaith.adminmp5.utils.Permissi;
import com.mpfaith.adminmp5.utils.ProgressDialogScreen;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.amirs.JSON;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class AddProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    RestAPI api;
    ArrayList<String>meterSrArrayList_main;
    ArrayList<String>meterSrArrayList_cate_main_2;
    ArrayList<String> meterSrArrayList;
    ArrayList<String> meterSrArrayList_2;
    UserSharedPreferences userSharedPreferences;
    ArrayAdapter<String> meterSrNumAdapter_main;
    ArrayAdapter<String> meterSrNumAdapter;
    ArrayAdapter<String> meterSrNumAdapter_2;
    ArrayList<String>meterSrArrayList_cat;
    String str_brand;
    String str_category,str_category_id;
    EditText cgst_et,gst_edit,product_name,available_Quantity,product_brand,product_description,product_price,product_dist,color_edit,size_edit;
    ImageView imageView,image_set;
    ListView listView;
    private static final int CAMERA_PIC_REQUEST = 1337;
    private static final int SELECT_IMAGE = 1338;
    private Bitmap bitmap;
    ArrayList<Uri> imagesUriArrayList ;
    ArrayList<String>api_array_list;
    ArrayList<String>imagesCammerArrayList;
    ArrayList<String>  cammer_api_array_list;
    private Uri fileURI;
    Button submit_button;
    ImageView color_add;
    ColorAdapter adapter;
    String str_image_aa;
    String str_category_main_id,str_gst,str_cgst,str_product_name,str_size_edit,str_color_edit,str_available_Quantity,str_product_brand,str_product_dist,str_product_price,str_product_description;
    ProductStockModel model;
    ArrayList<ProductStockModel> color_list;
    ArrayList<String> size_list;
    RecyclerView recyclerView;
    String str_category_cate,str_sub_category_id,str_category_sub_cate;
    ArrayList<String>meterSrArrayList_sub;
    String str_sub_ccate_id;
    ArrayList<String>meterSrArrayList_cate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        init();
    }
    private void init(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Add Product");
        submit_button=findViewById(R.id.submit_button);
        color_add=findViewById(R.id.color_add);
        gst_edit=findViewById(R.id.gst_ed);
        cgst_et=findViewById(R.id.cgstf);
        color_edit=findViewById(R.id.color_edit);
        size_edit=findViewById(R.id.size_edit);
        product_name=findViewById(R.id.product_name);
        available_Quantity=findViewById(R.id.available_Quantity);
        product_brand=findViewById(R.id.product_brand);
        product_price=findViewById(R.id.product_price);
        product_dist=findViewById(R.id.product_dist);
        product_description=findViewById(R.id.product_description);
        imageView = (ImageView) findViewById(R.id.image);
        image_set=findViewById(R.id.image_set);
        listView = (ListView) findViewById(R.id.listView);
        listView.setVisibility(View.INVISIBLE);
        api = APIClient.getClient().create(RestAPI.class);
        userSharedPreferences = new UserSharedPreferences(this);
        color_list = new ArrayList<ProductStockModel>();
        recyclerView = findViewById(R.id.recy_color);// get the reference of ViewFlipper
        GridLayoutManager layoutManager = new GridLayoutManager(AddProductActivity.this, 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        if (NetConnection.isNetworkAvailable(AddProductActivity.this)) {
            getMainCategoryList();

        }else {
            Toast.makeText(AddProductActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();

        }
        SearchableSpinner searchableSpinner_main = (SearchableSpinner)findViewById(R.id.searchable_main_category);
        meterSrArrayList_main = new ArrayList<>();
        meterSrArrayList_cate_main_2 = new ArrayList<>();
        meterSrNumAdapter_main = new ArrayAdapter<String>(AddProductActivity.this, R.layout.support_simple_spinner_dropdown_item, meterSrArrayList_main);
        searchableSpinner_main.setAdapter(meterSrNumAdapter_main);
        searchableSpinner_main.setTitle("Select Item");
        searchableSpinner_main.setPositiveButton("Ok");

        searchableSpinner_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_category_main_id = meterSrArrayList_cate_main_2.get(position);


                if(str_category_main_id !=null){
                    if (NetConnection.isNetworkAvailable(AddProductActivity.this)) {
                        getCategoryList(str_category_main_id);

                    }else {
                        Toast.makeText(AddProductActivity.this, "No  internet Connection found", Toast.LENGTH_SHORT).show();

                    }

                }else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        SearchableSpinner searchableSpinner = (SearchableSpinner)findViewById(R.id.searchable_category);
        meterSrArrayList = new ArrayList<>();
        meterSrArrayList_cate = new ArrayList<>();
        meterSrNumAdapter = new ArrayAdapter<String>(AddProductActivity.this, R.layout.support_simple_spinner_dropdown_item, meterSrArrayList);
        searchableSpinner.setAdapter(meterSrNumAdapter);
        searchableSpinner.setTitle("Select Item");
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
        SearchableSpinner searchableSpinner_2 = (SearchableSpinner)findViewById(R.id.sub_searchable_category);
        meterSrArrayList_2 = new ArrayList<>();
        meterSrArrayList_sub = new ArrayList<>();
        meterSrNumAdapter_2 = new ArrayAdapter<String>(AddProductActivity.this, R.layout.support_simple_spinner_dropdown_item, meterSrArrayList_2);
        searchableSpinner_2.setAdapter(meterSrNumAdapter_2);
        searchableSpinner_2.setTitle("Select Item");
        searchableSpinner_2.setPositiveButton("Ok");

        searchableSpinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_sub_ccate_id = meterSrArrayList_sub.get(position);

//                Log.d("TAG", "instantiateItem: " + str_service);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto(1);
            }
        });

        color_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 adapter = new ColorAdapter(AddProductActivity.this, color_list);
                 recyclerView.setAdapter(adapter);
                 str_available_Quantity = available_Quantity.getText().toString().trim();
                 str_size_edit = size_edit.getText().toString().trim();
                 str_color_edit = color_edit.getText().toString().trim();

                if (!str_color_edit.isEmpty()&&!str_available_Quantity.isEmpty()&&!str_size_edit.isEmpty()){
                    ProductStockModel model=new ProductStockModel();
                   //  color_list = new ArrayList<String>(Arrays.asList(str_color_edit.split(",")));
                    model.setProduct_color(str_color_edit);
                    model.setProduct_size(str_size_edit);
                    model.setAvailableQuantity(str_available_Quantity);
                    color_list.add(model);
                    color_edit.getText().clear();
                    size_edit.getText().clear();
                    available_Quantity.getText().clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AddProductActivity.this, "Successfully ", Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(AddProductActivity.this, "please Add color ", Toast.LENGTH_SHORT).show();

                }
            }
        });
//        size_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String  str_size_edit = size_edit.getText().toString().trim();
//                if (!str_size_edit.isEmpty()) {
//                    size_list = new ArrayList<String>();
//                    //size_list = new ArrayList<String>(Arrays.asList(str_size_edit.split(",")));
//                    size_list.add(str_size_edit);
//                   size_edit.getText().clear();
//                    Toast.makeText(AddProductActivity.this, "Successfully ", Toast.LENGTH_SHORT).show();
//
//                }else {
//                    Toast.makeText(AddProductActivity.this, "please enter the cloth size", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_product_name = product_name.getText().toString().trim();
                str_product_brand = product_brand.getText().toString().trim();
                str_product_dist = product_dist.getText().toString().trim();
                str_product_price = product_price.getText().toString().trim();
                str_product_description = product_description.getText().toString().trim();
                str_gst = gst_edit.getText().toString().trim();
                str_cgst = cgst_et.getText().toString().trim();
                if (NetConnection.isNetworkAvailable(AddProductActivity.this)) {

                    if (color_list != null) {
                        if(api_array_list!=null){
                        if(str_category_id !=null&&str_sub_ccate_id !=null){
                            if(!str_gst.isEmpty()&&!str_cgst.isEmpty()&&!sellItemApi().isEmpty()&&!str_product_name.isEmpty()&&!str_product_brand.isEmpty()&&!str_product_dist.isEmpty()&&!str_product_price.isEmpty()&&!str_product_description.isEmpty()){
                                getAddCreateProduct(str_sub_ccate_id,str_gst,str_cgst,str_category_id,str_product_name,str_product_brand,str_product_price,str_product_dist,str_product_description);
                            }else {
                                Toast.makeText(AddProductActivity.this, "Fill the field ", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(AddProductActivity.this, "Please Selected Category", Toast.LENGTH_SHORT).show();
                        }


                    }else {
                        Toast.makeText(AddProductActivity.this, "Plz upload Images ", Toast.LENGTH_SHORT).show();
                    }
                        }else {
                            Toast.makeText(AddProductActivity.this, "size list null", Toast.LENGTH_SHORT).show();

                        }

                    }else {
                        Toast.makeText(AddProductActivity.this, "color list null", Toast.LENGTH_SHORT).show();

                    }

            }
        });
    }

    private int RC_PERMISSIONS = 10;
    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private void takePhoto( int RC_CAPTURE) {

        if (Permissi.hasPermissions(AddProductActivity.this, PERMISSIONS)) {
            if(RC_CAPTURE==1){
                //  final CharSequence[] options = {"Take Photo", "Choose Single photos from Gallery", "Select multiple photos from Gallery","Cancel"};

                final CharSequence[] options = {"Take Photo", "Select multiple photos from Gallery","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AddProductActivity.this);
                builder.setTitle("Add Photo!");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            //  image_set.setVisibility(View.VISIBLE);
                            // imageView.setVisibility(View.GONE);
                            // add new requset of picture like this
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                            //         Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            for(int i=0;i<3;i++){
//                                // start the image capture Intent
//                                startActivityForResult(intent, CAMERA_PIC_REQUEST);
//                            }

//                            container = (LinearLayout) findViewById(R.id.container);
//                            for (int j = 0; j < 4; j++) {
//                                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(takePicture, CAMERA_PIC_REQUEST);
//                                ImageView newimageview = new ImageView(AddNewVehicleActivity.this);
//                                newimageview.setBackgroundResource(R.drawable.no_image);
//                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
//                                newimageview.setLayoutParams(params);
//                                container.addView(newimageview);
//
//
//                            }
                        }
//                        else if (options[item].equals("Choose Single photos from Gallery")) {
//                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                            startActivityForResult(intent, 2);
//                        }
                        else if (options[item].equals("Select multiple photos from Gallery")) {
                            image_set.setVisibility(View.GONE);
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, SELECT_IMAGE);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }


        } else {
            ActivityCompat.requestPermissions(AddProductActivity.this, PERMISSIONS, RC_PERMISSIONS);
        }
//        if (Permissi.hasPermissions(AddNewVehicleActivity.this, PERMISSIONS)) {
//            if(RC_CAPTURE==1){
//                final CharSequence[] options = {"Select multiple photos from Gallery","Cancel"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(AddNewVehicleActivity.this);
//                builder.setTitle("Add Photo!");
//                builder.setItems(options, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                         if (options[item].equals("Select multiple photos from Gallery")) {
////                            Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                            intent.setType("image/*");
////                            intent.setAction(Intent.ACTION_GET_CONTENT);
////                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
////                            startActivityForResult(intent, SELECT_IMAGE);
//                             Intent intent = new Intent();
//                             intent.setType("image/*");
//                             intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                             intent.setAction(Intent.ACTION_GET_CONTENT);
//                             intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                             startActivityForResult(intent, SELECT_IMAGE);
//                         } else if (options[item].equals("Cancel")) {
//                            dialog.dismiss();
//                        }
//                    }
//                });
//                builder.show();
//            }
//
//
//        } else {
//            ActivityCompat.requestPermissions(AddNewVehicleActivity.this, PERMISSIONS, RC_PERMISSIONS);
//        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bmp;
        api_array_list = new ArrayList();

        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK && null != data) {
            imagesCammerArrayList = new ArrayList();
            cammer_api_array_list = new ArrayList();
            Bundle extras1 = data.getExtras();
            //  listView.setVisibility(View.GONE);
            Bitmap thumbnail_1 = (Bitmap) extras1.get("data");
            LinearLayout layout = (LinearLayout)findViewById(R.id.imageLayout);
//            for(int i=0;i<10;i++)
//            {

            // }

            if (thumbnail_1 != null) {
                thumbnail_1 = getResizedBitmap(thumbnail_1, 400);
                ImageView image = new ImageView(this);
                image.setLayoutParams(new android.view.ViewGroup.LayoutParams(180,160));
                image.setMaxHeight(100);
                image.setMaxWidth(90);
                // Adds the view to the layout
                layout.addView(image);
                image.setImageBitmap(thumbnail_1);
                //str_image_1 = encodeToBase64(thumbnail_1, Bitmap.CompressFormat.JPEG, 50);
                str_image_aa = getEncoded64ImageStringFromBitmap(thumbnail_1);
            } else {
                Toast.makeText(AddProductActivity.this, "null", Toast.LENGTH_SHORT).show();
            }
            api_array_list.add("data:image/jpeg;base64,"+str_image_aa);
//            if(PIC_CODE<2){
//                // add new requset of picture like this
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
//                PIC_CODE++;
//            }




        }
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && null != data) {
            ClipData cd = data.getClipData();

            if(data.getClipData()== null){
                Toast.makeText(this, "Please select minimum 2 images", Toast.LENGTH_SHORT).show();

            }else {
                if (data.getClipData().getItemCount() <= 10) {
                    imagesUriArrayList = new ArrayList();
                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {

                        imagesUriArrayList.add(data.getClipData().getItemAt(i).getUri());
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getClipData().getItemAt(i).getUri());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String str_image_aa = getEncoded64ImageStringFromBitmap(bitmap);

                        api_array_list.add("data:image/jpeg;base64,"+str_image_aa);

                    }
                    Log.e("SIZE", imagesUriArrayList.size() + "");
                    image_set.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);

                    DataAdapter adapter = new DataAdapter(AddProductActivity.this, AddProductActivity.this, imagesUriArrayList);
                    listView.setAdapter(adapter);



                } else {
                    Toast.makeText(AddProductActivity.this, "Please select maximum10 images", Toast.LENGTH_SHORT).show();

                }
            }
        }
//        else if(requestCode == 2 && resultCode == RESULT_OK && null != data){
//            Uri imaguri = data.getData();
//            if (imaguri != null) {
//                try {
//                    bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imaguri);
//                    imageView.setImageBitmap(bmp);
//                    str_image_1 = encodeToBase64(bmp, Bitmap.CompressFormat.JPEG, 50);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Toast.makeText(AddNewVehicleActivity.this, "andar null", Toast.LENGTH_SHORT).show();
//            }
//
//        }

        else if (resultCode == this.RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteFormat = stream.toByteArray();

        // Get the Base64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }


    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.NO_WRAP);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    private String sellItemApi() {

        JSONObject[] ob = new JSONObject[api_array_list.size()];
        int a = 0;
        for (String cartDetailPayload : api_array_list) {


            ob[a] = JSON.dic(
                    "productImage", cartDetailPayload

            );
            a++;

        }
        JSON generatedJsonObject = JSON.create(JSON.array((Object[]) ob)
        );


        String jsonString = generatedJsonObject.toString();

        Log.d(TAG, "sellItemApi: " + jsonString);

        return jsonString;
    }

    private String sellItemColor() {
ArrayList<ProductStockModel>dd=new ArrayList<>();
        JSONObject[] ob = new JSONObject[color_list.size()];
        int a = 0;
        for (ProductStockModel colorPayload : color_list) {


            ob[a] = JSON.dic(
                    "productColors", colorPayload.getProduct_color(),
                    "productSizes", colorPayload.getProduct_size(),
                    "availableQuantity", colorPayload.getAvailableQuantity()

            );
            a++;

        }
        JSON generatedJsonObject = JSON.create(JSON.array((Object[]) ob)
        );


        String jsonString = generatedJsonObject.toString();

        Log.d(TAG, "sellItemApi: " + jsonString);

        return jsonString;

    }
    private String sellItemSize() {

        JSONObject[] ob = new JSONObject[size_list.size()];
        int a = 0;
        for (String sizePayload : size_list) {


            ob[a] = JSON.dic(
                    "productSizes", sizePayload

            );
            a++;

        }
        JSON generatedJsonObject = JSON.create(JSON.array((Object[]) ob)
        );


        String jsonString = generatedJsonObject.toString();

        Log.d(TAG, "sellItemApi: " + jsonString);

        return jsonString;
    }

    private void getCategoryList(String str_category_main_id){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("mainCategoryId", str_category_main_id);
        requestBody.put("requestFor", "MERCHANT");

        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(AddProductActivity.this);
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
                            NetConnection.showMessage(AddProductActivity.this, ""+category_model.getStatus());

                        }

                    }else {
                        //body
                        NetConnection.showMessage(AddProductActivity.this, ErrorConstants.JSONDATA_NULL);

                    }

                } else {

                    //response
                    NetConnection.showMessage(AddProductActivity.this, ErrorConstants.RESPONSE_NULL);
                }

            }

            @Override
            public void onFailure(@NonNull Call<AllCategoryListModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(AddProductActivity.this, ErrorConstants.Fail_message);

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
        ProgressDialogScreen.showProgressDialog(AddProductActivity.this);
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
                            NetConnection.showMessage(AddProductActivity.this, ""+category_model.getStatus());

                        }

                    }else {
                        //body
                        NetConnection.showMessage(AddProductActivity.this, ErrorConstants.JSONDATA_NULL);

                    }

                } else {

                    //response
                    NetConnection.showMessage(AddProductActivity.this, ErrorConstants.RESPONSE_NULL);
                }

            }

            @Override
            public void onFailure(@NonNull Call<AllSubCategoryListModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(AddProductActivity.this, ErrorConstants.Fail_message);

                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getAddCreateProduct(String str_sub_ccate_id,String gst,String cgst,String str_category_id,String str_product_name,String str_product_brand,String str_product_price,String str_product_dist,String str_product_description){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("subCategoryId", str_sub_ccate_id);
        requestBody.put("sgst", gst);
        requestBody.put("cgst", cgst);
        requestBody.put("categoryId", str_category_id);
        requestBody.put("productName", str_product_name);
       // requestBody.put("availableQuantity", str_available_Quantity);
        requestBody.put("productBrand", str_product_brand);
        requestBody.put("productPrice", str_product_price);
        requestBody.put("discount", str_product_dist);
        requestBody.put("productDescription", str_product_description);
        requestBody.put("storeId", userSharedPreferences.getLoginID());
        requestBody.put("allProductImage", sellItemApi());
        requestBody.put("productStock", sellItemColor());
       // requestBody.put("allProductSize", sellItemSize());
        requestBody.put("createdBy", userSharedPreferences.getLoginID());
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(AddProductActivity.this);
        Call<AddProductModel> call = api.getAddProduct(requestBody1);
        call.enqueue(new Callback<AddProductModel>() {
            @Override
            public void onResponse(@NonNull Call<AddProductModel> call, @NonNull Response<AddProductModel> response) {
                ProgressDialogScreen.hideProgressDialog();
                if (response.isSuccessful() ) {
                    if (response.body().getPayload() != null) {
                        AddProductModel addProductModel= response.body();
                        if(addProductModel.getResponseCode().equals(200)){
                            if(addProductModel.getPayload().getRespCode().equals(400)){

//                                startActivity(new Intent(AddProductActivity.this, DashboardActivity.class));
//                                finish();
                                Toast.makeText(AddProductActivity.this, addProductModel.getPayload().getRespMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                NetConnection.showMessage(AddProductActivity.this, ""+addProductModel.getPayload().getRespMessage());
                            }


                        }else {
                            NetConnection.showMessage(AddProductActivity.this, ""+addProductModel.getStatus());

                        }

                    }else {
                        //body
                        NetConnection.showMessage(AddProductActivity.this, ErrorConstants.JSONDATA_NULL);

                    }

                } else {

                    //response
                    NetConnection.showMessage(AddProductActivity.this, ErrorConstants.RESPONSE_NULL);
                }

            }

            @Override
            public void onFailure(@NonNull Call<AddProductModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(AddProductActivity.this, ErrorConstants.Fail_message);

                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getMainCategoryList() {

        Map<String, String> requestBody = new HashMap<>();
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody.put("requestFor", "MERCHANT");
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


                        }

                    }
                    else {
                        NetConnection.showMessage(AddProductActivity.this, ""+listModel.getStatus());
                    }




                } else {
                    Toast.makeText(AddProductActivity.this, response.message(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MainCategoryListModel> call, @NonNull Throwable t) {


                NetConnection.showMessage(AddProductActivity.this, ErrorConstants.Fail_message);
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