package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

  public class MainCategoryListModelListPayload {
    @SerializedName("mainCategoryId")
    @Expose
    private int mainCategoryId;
    @SerializedName("mainCategoryName")
    @Expose
    private String mainCategoryName;
    @SerializedName("mainCategoryImage")
    @Expose
    private String mainCategoryImage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdAt")
    @Expose
    private long createdAt;
    @SerializedName("updatedAt")
    @Expose
    private long updatedAt;

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getMainCategoryImage() {
        return mainCategoryImage;
    }

    public void setMainCategoryImage(String mainCategoryImage) {
        this.mainCategoryImage = mainCategoryImage;
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
