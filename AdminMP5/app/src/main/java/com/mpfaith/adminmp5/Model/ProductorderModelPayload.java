package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductorderModelPayload {
    @SerializedName("sellId")
    @Expose
    private int sellId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productBrand")
    @Expose
    private String productBrand;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("sellQuantity")
    @Expose
    private int sellQuantity;
    @SerializedName("productPrice")
    @Expose
    private int productPrice;
    @SerializedName("discount")
    @Expose
    private int discount;
    @SerializedName("totalPrice")
    @Expose
    private double totalPrice;

    @SerializedName("storeId")
    @Expose
    private String storeId;
    @SerializedName("paymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("addressId")
    @Expose
    private int addressId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdAt")
    @Expose
    private long createdAt;
    @SerializedName("updatedAt")
    @Expose
    private long updatedAt;

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(int sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
