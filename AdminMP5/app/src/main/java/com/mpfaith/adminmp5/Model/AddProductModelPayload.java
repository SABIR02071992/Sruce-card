package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddProductModelPayload {
    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("cgst")
    @Expose
    private int cgst;
    @SerializedName("sgst")
    @Expose
    private int sgst;
    @SerializedName("commission")
    @Expose
    private int commission;
    @SerializedName("allProductImage")
    @Expose
    private String allProductImage;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("availableQuantity")
    @Expose
    private int availableQuantity;
    @SerializedName("productBrand")
    @Expose
    private String productBrand;
    @SerializedName("productDescription")
    @Expose
    private String productDescription;
    @SerializedName("storeId")
    @Expose
    private String storeId;
    @SerializedName("productPrice")
    @Expose
    private int productPrice;
    @SerializedName("discount")
    @Expose
    private int discount;
    @SerializedName("productStock")
    @Expose
    private String productStock;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("respCode")
    @Expose
    private Integer respCode;
    @SerializedName("respMessage")
    @Expose
    private String respMessage;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getAllProductImage() {
        return allProductImage;
    }

    public void setAllProductImage(String allProductImage) {
        this.allProductImage = allProductImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }
}
