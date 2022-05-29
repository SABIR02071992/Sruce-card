package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MerchantrListModel {
    @Expose
    private List<MerchantrListModelListPayload> listPayload = null;
    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("status")
    @Expose
    private String status;

    public List<MerchantrListModelListPayload> getListPayload() {
        return listPayload;
    }

    public void setListPayload(List<MerchantrListModelListPayload> listPayload) {
        this.listPayload = listPayload;
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
