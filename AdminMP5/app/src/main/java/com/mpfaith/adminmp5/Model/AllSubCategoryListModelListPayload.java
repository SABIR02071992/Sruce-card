package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllSubCategoryListModelListPayload {
    @SerializedName("subCategoryId")
    @Expose
    private int subCategoryId;
    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("subCategoryName")
    @Expose
    private String subCategoryName;
    @SerializedName("cgst")
    @Expose
    private int cgst;
    @SerializedName("sgst")
    @Expose
    private int sgst;
    @SerializedName("commission")
    @Expose
    private int commission;
    @SerializedName("subCategoryImage")
    @Expose
    private String subCategoryImage;
    @SerializedName("discount")
    @Expose
    private int discount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdAt")
    @Expose
    private long createdAt;
    @SerializedName("updatedAt")
    @Expose
    private long updatedAt;

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getCgst() {
        return cgst;
    }

    public void setCgst(int cgst) {
        this.cgst = cgst;
    }

    public int getSgst() {
        return sgst;
    }

    public void setSgst(int sgst) {
        this.sgst = sgst;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
