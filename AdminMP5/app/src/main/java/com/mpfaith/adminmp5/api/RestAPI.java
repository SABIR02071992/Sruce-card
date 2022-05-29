package com.mpfaith.adminmp5.api;

import com.mpfaith.adminmp5.Model.ActiveProductModel;
import com.mpfaith.adminmp5.Model.AddProductModel;
import com.mpfaith.adminmp5.Model.AllCategoryListModel;
import com.mpfaith.adminmp5.Model.AllProductListByUserIdModel;
import com.mpfaith.adminmp5.Model.AllSubCategoryListModel;
import com.mpfaith.adminmp5.Model.DeliveryAddressModel;
import com.mpfaith.adminmp5.Model.MainCategoryListModel;
import com.mpfaith.adminmp5.Model.MerchantLoginModel;
import com.mpfaith.adminmp5.Model.MerchantrListModel;
import com.mpfaith.adminmp5.Model.NumberForDashboardModel;
import com.mpfaith.adminmp5.Model.PaymentWalletModel;
import com.mpfaith.adminmp5.Model.ProductOrderModel;
import com.mpfaith.adminmp5.Model.ProfileModel;
import com.mpfaith.adminmp5.Model.RegisterMerchantModel;
import com.mpfaith.adminmp5.Model.UpdateListProductModel;
import com.mpfaith.adminmp5.Model.UpdateMerchantModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RestAPI {


    @POST("registerMerchant")
    Call<RegisterMerchantModel> getRegisterMerchant(@Body Map<String, Map> body);
    @POST("merchantLogin")
    Call<MerchantLoginModel> getMerchantLogin(@Body Map<String, Map> body);
    @POST("getMerchantrList")
    Call<MerchantrListModel> getMerchantrList(@Body Map<String, Map> body);
   // @POST("updateMerchant")
   // Call<UpdateMerchantModel> getUpdateMerchant(@Body Map<String, Map> body);
    @POST("getAllCategoryList")
    Call<AllCategoryListModel> getAllCategoryList(@Body Map<String, Map> body);
    @POST("addProduct")
    Call<AddProductModel> getAddProduct(@Body Map<String, Map> body);
    @POST("getAllProductListByUserId")
    Call<AllProductListByUserIdModel> getAllProductListByUserId(@Body Map<String, Map> body);

    @POST("updateProduct")
    Call<UpdateListProductModel> getUpdateProduct(@Body Map<String, Map> body);

    @POST("changeProductStatus")
    Call<ActiveProductModel> getActiceDeactive(@Body Map<String, Map> body);

    @POST("getMerchantWalletByLoginId")
    Call<PaymentWalletModel> getMerchantWallet(@Body Map<String, Map> body);
    @POST("getAllOrderList")
    Call<ProductOrderModel> getAllOrderList(@Body Map<String, Map> body);

    @POST("numberForDashboard")
    Call<NumberForDashboardModel> getNumberForDashboard(@Body Map<String, Map> body);

    @POST("getCustomerAddressList")
    Call<DeliveryAddressModel> getAddress(@Body Map<String, Map> body);
    @POST("getAllSubCategoryList")
    Call<AllSubCategoryListModel> getAllSubCategoryList(@Body Map<String, Map> body);
    @POST("getMainCategoryList")
    Call<MainCategoryListModel> getMainCatList(@Body Map<String, Map> body);
    @POST("getMerchantList")
    Call<ProfileModel> getProfile(@Body Map<String, Map> body);
    @POST("updateMerchant")
    Call<UpdateMerchantModel> getUpdateMerchant(@Body Map<String, Map> body);
}