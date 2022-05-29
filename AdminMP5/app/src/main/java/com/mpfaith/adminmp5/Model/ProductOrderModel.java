package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductOrderModel {

        @SerializedName("listPayload")
        @Expose
        private List<ProductorderModelPayload> listPayload = null;
        @SerializedName("responseCode")
        @Expose
        private Integer responseCode;
        @SerializedName("status")
        @Expose
        private String status;

        public List<ProductorderModelPayload> getListPayload() {
            return listPayload;
        }

        public void setListPayload(List<ProductorderModelPayload> listPayload) {
            this.listPayload = listPayload;
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
