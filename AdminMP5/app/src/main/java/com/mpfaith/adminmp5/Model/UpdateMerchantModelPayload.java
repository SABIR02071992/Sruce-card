package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMerchantModelPayload {
    @SerializedName("loginId")
    @Expose
    private String loginId;
    @SerializedName("merchantWalletAmount")
    @Expose
    private int merchantWalletAmount;
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

    public int getMerchantWalletAmount() {
        return merchantWalletAmount;
    }

    public void setMerchantWalletAmount(int merchantWalletAmount) {
        this.merchantWalletAmount = merchantWalletAmount;
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
