package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterMerchantModel {
    @SerializedName("payload")
    @Expose
    private RegisterMerchantModelPayload payload;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("status")
    @Expose
    private String status;

    public RegisterMerchantModelPayload getPayload() {
        return payload;
    }

    public void setPayload(RegisterMerchantModelPayload payload) {
        this.payload = payload;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
