package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryAddressModelPayload {
    @SerializedName("addressId")
    @Expose
    private int addressId;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("pinCode")
    @Expose
    private String pinCode;
    @SerializedName("houseNo")
    @Expose
    private String houseNo;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("landmarks")
    @Expose
    private String landmarks;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(String landmarks) {
        this.landmarks = landmarks;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
