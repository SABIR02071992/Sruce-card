package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentWalletModel {
    @SerializedName("payload")
    @Expose
    private PaymentWalletModelPayload payload;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("status")
    @Expose
    private String status;

    public PaymentWalletModelPayload getPayload() {
        return payload;
    }

    public void setPayload(PaymentWalletModelPayload payload) {
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
