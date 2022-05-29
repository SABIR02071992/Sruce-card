package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateListProductModel {
    @SerializedName("payload")
    @Expose
    private UpdateListProductPayload payload;
    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("status")
    @Expose
    private String status;

    public UpdateListProductPayload getPayload() {
        return payload;
    }

    public void setPayload(UpdateListProductPayload payload) {
        this.payload = payload;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
