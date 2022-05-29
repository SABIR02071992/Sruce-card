package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumberForDashboardModelPayload {
    @SerializedName("cgst")
    @Expose
    private int cgst;
    @SerializedName("sgst")
    @Expose
    private int sgst;
    @SerializedName("commission")
    @Expose
    private int commission;
    @SerializedName("availableQuantity")
    @Expose
    private int availableQuantity;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("storeId")
    @Expose
    private String storeId;
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
    @SerializedName("totalPendingOrder")
    @Expose
    private int totalPendingOrder;
    @SerializedName("todaysEarning")
    @Expose
    private double todaysEarning;
    @SerializedName("respCode")
    @Expose
    private int respCode;

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

    public int getTotalPendingOrder() {
        return totalPendingOrder;
    }

    public void setTotalPendingOrder(int totalPendingOrder) {
        this.totalPendingOrder = totalPendingOrder;
    }

    public double getTodaysEarning() {
        return todaysEarning;
    }

    public void setTodaysEarning(double todaysEarning) {
        this.todaysEarning = todaysEarning;
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }
}
