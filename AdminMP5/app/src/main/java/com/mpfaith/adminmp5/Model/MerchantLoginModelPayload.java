package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantLoginModelPayload {
    @SerializedName("loginId")
    @Expose
    private String loginId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("permission")
    @Expose
    private String permission;
    @SerializedName("respCode")
    @Expose
    private Integer respCode;
    @SerializedName("respMessage")
    @Expose
    private String respMessage;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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
