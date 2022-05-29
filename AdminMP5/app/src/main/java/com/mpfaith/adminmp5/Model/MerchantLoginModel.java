package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantLoginModel {
    @SerializedName("payload")
    @Expose
    private MerchantLoginModelPayload payload;
    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("status")
    @Expose
    private String status;

    public MerchantLoginModelPayload getPayload() {
        return payload;
    }

    public void setPayload(MerchantLoginModelPayload payload) {
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
