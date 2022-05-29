package com.mpfaith.adminmp5.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.mpfaith.adminmp5.Model.NumberForDashboardModel;
import com.mpfaith.adminmp5.R;
import com.mpfaith.adminmp5.ViewModel.DashboardActivityViewModel;
import com.mpfaith.adminmp5.api.APIClient;
import com.mpfaith.adminmp5.api.RestAPI;
import com.mpfaith.adminmp5.utils.ErrorConstants;
import com.mpfaith.adminmp5.utils.NetConnection;
import com.mpfaith.adminmp5.utils.ProgressDialogScreen;
import com.mpfaith.adminmp5.utils.UserSharedPreferences;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    UserSharedPreferences userSharedPreferences;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView text_View,pending_order,earning_m;
    RestAPI api;
    boolean isClicked=false;
    DashboardActivityViewModel mainActivityViewModel;
    String name,last;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashborad);
        init();
        dashboardNewOrder();

    }


    public void init() {
        mainActivityViewModel = new ViewModelProvider(DashboardActivity.this).get(DashboardActivityViewModel.class);
        LiveData liveData = DashboardActivityViewModel.getFragmentIntentCheck();
        LiveData liveData1 = DashboardActivityViewModel.getHomeText();
        //livedataObserver(liveData, liveData1);
        userSharedPreferences = new UserSharedPreferences(this);
        toolbar = findViewById(R.id.toolbar);
        pending_order=findViewById(R.id.pending_order);
        earning_m=findViewById(R.id.earning_m);
       // graphView=findViewById(R.id.idGraphView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        api = APIClient.getClient().create(RestAPI .class);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        name=userSharedPreferences.getFirstName();
        last=userSharedPreferences.getLastName();
        View headerView = navigationView.inflateHeaderView(R.layout.header_view);
        text_View=headerView.findViewById(R.id.textView);
        text_View.setText(name+" "+last);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                finish();
            }
        });
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.getMenu().getItem(1).setActionView(R.layout.layout_menu);
        navigationView.getMenu().getItem(4).setActionView(R.layout.layout_menu);



//        LineGraphSeries<DataPoint> series=new LineGraphSeries<DataPoint>(new DataPoint[]{
//                // on below line we are adding
//                // each point on our x and y axis.
//                new DataPoint(0, 1),
//                new DataPoint(1, 3),
//                new DataPoint(2, 4),
//                new DataPoint(3, 9),
//                new DataPoint(4, 6),
//                new DataPoint(5, 3),
//                new DataPoint(6, 6),
//                new DataPoint(7, 1),
//                new DataPoint(8, 2)
//
//        });
//        // after adding data to our line graph series.
//        // on below line we are setting
//        // title for our graph view.
//        graphView.setTitle("My Graph View");
//
//        // on below line we are setting
//        // text color to our graph view.
//        graphView.setTitleColor(R.color.colorPrimary);
//
//        // on below line we are setting
//        // our title text size.
//        graphView.setTitleTextSize(18);
//
//        // on below line we are adding
//        // data series to our graph view.
//        graphView.addSeries(series);
//


    }
    public void dashboardNewOrder(){
        if (NetConnection.isNetworkAvailable(DashboardActivity.this)){
            pendingOrderDashboard();
        }else {
            Toast.makeText(this, "No internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    private void pendingOrderDashboard( ) {
        Map<String,String>requestBody=new HashMap<>();
        requestBody.put("storeId",userSharedPreferences.getLoginID());
        Map<String,Map>requestBody1=new HashMap<>();
        requestBody1.put("payload",requestBody);
        ProgressDialogScreen.showProgressDialog(DashboardActivity.this);
        Call<NumberForDashboardModel>call=api.getNumberForDashboard(requestBody1);
        call.enqueue(new Callback<NumberForDashboardModel>() {
            @Override
            public void onResponse(Call<NumberForDashboardModel> call, Response<NumberForDashboardModel> response) {
                ProgressDialogScreen.hideProgressDialog();
                if (response.isSuccessful()){
                    NumberForDashboardModel number_model=response.body();
                    if (number_model.getPayload().getRespCode().equals(200)){
                        int total =number_model.getPayload().getTotalPendingOrder();
                        int earning = (int) number_model.getPayload().getTodaysEarning();
                        pending_order.setText(String.valueOf(total));
                        earning_m.setText(String.valueOf(earning));
                    }
                }else {
                    NetConnection.showMessage(DashboardActivity.this, ErrorConstants.RESPONSE_NULL);

                }
            }

            @Override
            public void onFailure(Call<NumberForDashboardModel> call, Throwable t) {

            }
        });


    }


    NavOptions navOptions;
    Bundle bundle = new Bundle();
    @SuppressLint("ResourceAsColor")
    private void displaySelectedScreen(int itemId) {
        switch (itemId) {
            case R.id.home:
                backButtonIntiate();
                navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.mobile_navigation, true)
                        .build();
                bundle.putString("dashboard_type", "1");

                //                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_home
//                        , bundle, navOptions);
                break;
                case R.id.product_manger:

               // navigationView.getMenu().getItem(1).setActionView(R.layout.layout_menu).setVisible(true);

                if (!isClicked){
                    navigationView.getMenu().findItem(R.id.add_product).setVisible(true);
                    navigationView.getMenu().findItem(R.id.product_list).setVisible(true);
                    isClicked = true;
                }else {
                    navigationView.getMenu().findItem(R.id.add_product).setVisible(false);
                    navigationView.getMenu().findItem(R.id.product_list).setVisible(false);
                    isClicked = false;
                }

                break;
                case R.id.add_product:
                startActivity(new Intent(DashboardActivity.this, AddProductActivity.class));
                break;
                case R.id.product_list:
                    startActivity(new Intent(DashboardActivity.this, ProductListActivity.class));
                    break;


            case R.id.payment_management:
              //navigationView.getMenu().getItem(4).setActionView(R.layout.layout_menu).setVisible(true);
                if (!isClicked){
                    navigationView.getMenu().findItem(R.id.a_a).setVisible(true);
                    navigationView.getMenu().findItem(R.id.b_b).setVisible(true);
                    isClicked = true;
               }else {
                    navigationView.getMenu().findItem(R.id.a_a).setVisible(false);
                    navigationView.getMenu().findItem(R.id.b_b).setVisible(false);
                   isClicked = false;
                }
                break;

            case R.id.a_a:
                startActivity(new Intent(DashboardActivity.this, PaymentManagementActivity.class));
                break;

            case R.id.b_b:
                startActivity(new Intent(DashboardActivity.this, PaymentListActivity.class));
                break;

            case R.id.order_management:
                startActivity(new Intent(DashboardActivity.this, ProductOrderManagementActivity.class));
                break;

            case R.id.nav_contact_us:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:7065760549"));
                startActivity(callIntent);
                break;


            case R.id.nav_log_out:
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DashboardActivity.this);
                alertDialogBuilder.setMessage("Are you really want to LOG OUT your current session");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                if(userSharedPreferences.isLogin()) {
                                    userSharedPreferences.resetLogin();
                                    Intent intent=new Intent(DashboardActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();


                                }

                            }
                        });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                break;
                default:
                break;

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
       navigationView.getMenu().findItem(R.id.add_product).setVisible(true);
       navigationView.getMenu().findItem(R.id.product_list).setVisible(true);


    }
    @Override
    public void onBackPressed() {
        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {

            super.onBackPressed();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void backButtonIntiate() {

        String s = DashboardActivityViewModel.FragmentIntentCheck.getValue();
        if (s == null) {
            s = "0";

        }
        int a = Integer.parseInt(s);
        a = a + 1;
        DashboardActivityViewModel.FragmentIntentCheck.setValue(String.valueOf(a));
    }
    private void onBackPressSupport() {


        String s = DashboardActivityViewModel.FragmentIntentCheck.getValue();
        if (s == null) {
            s = "0";

        }

        int a = Integer.parseInt(s);
        if (a > 0) {
            a = a - 1;
            DashboardActivityViewModel.FragmentIntentCheck.setValue(String.valueOf(a));
        }

    }



}