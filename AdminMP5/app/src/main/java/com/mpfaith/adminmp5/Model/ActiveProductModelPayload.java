package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveProductModelPayload {
    @SerializedName("cgst")
    @Expose
    private int cgst;
    @SerializedName("sgst")
    @Expose
    private int sgst;
    @SerializedName("commission")
    @Expose
    private int commission;
    @SerializedName("productId")
    @Expose
    private int productId;
    @SerializedName("availableQuantity")
    @Expose
    private int availableQuantity;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("productPrice")
    @Expose
    private int productPrice;
    @SerializedName("discount")
    @Expose
    private int discount;
    @SerializedName("sellQuantity")
    @Expose
    private int sellQuantity;
    @SerializedName("totalPrice")
    @Expose
    private double totalPrice;
    @SerializedName("respCode")
    @Expose
    private int respCode;
    @SerializedName("respMessage")
    @Expose
    private String respMessage;

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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(int sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }
}
