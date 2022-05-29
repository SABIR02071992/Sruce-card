package com.mpfaith.adminmp5.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.mpfaith.adminmp5.Model.ProfileModel;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ProgressDialogScreen;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    UserSharedPreferences userSharedPreferences;
    RestAPI api;
    TextView name_user,status,mobile_number,email,member_type;
    ImageView edit_images,back;
    CircularImageView pro_imag,pro_ima_2;
    String str_images;
    ProfileModel profileModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }
    private void init(){
        userSharedPreferences = new UserSharedPreferences(this);
        api = APIClient.getClient().create(RestAPI.class);
        name_user=findViewById(R.id.name_user_p);
        email=findViewById(R.id.email_id_p);
        mobile_number=findViewById(R.id.mobile_number_p);
        edit_images=findViewById(R.id.edit_images_p);
        back=findViewById(R.id.back_p);
        pro_imag=findViewById(R.id.pro_im_p);





        getData();
        edit_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, UpdateSellerDetailsActivity.class));
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, DashboardActivity.class));
                finish();
            }
        });

    }

    private void getData() {
        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("loginId", userSharedPreferences.getLoginID());
        Map<String, Map> requestBody1 = new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(ProfileActivity.this);
        Call<ProfileModel> call = api.getProfile(requestBody1);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<ProfileModel> userIdcall, @NonNull Response<ProfileModel> response) {
                ProgressDialogScreen.hideProgressDialog();


                if (response.isSuccessful() ) {
                    profileModel= response.body();
                    if (profileModel != null && profileModel.getResponseCode().equals(200)) {
                        for (int j = 0; j < profileModel.getListPayload().size(); j++) {
                                                       if(profileModel.getListPayload().get(j).getFirstName() !=null) {
                                                           name_user.setText(profileModel.getListPayload().get(j).getFirstName() + profileModel.getListPayload().get(j).getLastName());
                                                       }
                                                       if(profileModel.getListPayload().get(j).getEmailId() !=null) {
                                                           email.setText(profileModel.getListPayload().get(j).getEmailId());
                                                       }
                            if(profileModel.getListPayload().get(j).getMobileNumber() !=null) {

                                mobile_number.setText(String.valueOf(profileModel.getListPayload().get(j).getMobileNumber()));

                            }
                          //  String images=profileModel.getListPayload().get(j).getUserPhoto();


//                            if(images !=null){
//
//
//                                Picasso.get().load(userSharedPreferences.geturl()+"/images/"+images).into(pro_imag);
//
//                            }

                        }
                    }
                    Toast.makeText(ProfileActivity.this, profileModel.getStatus(), Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(ProfileActivity.this, "ERROR : 101 " + response.message(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<ProfileModel> call, @NonNull Throwable t) {


                Toast.makeText(ProfileActivity.this, "server error" ,Toast.LENGTH_LONG).show();

                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

}