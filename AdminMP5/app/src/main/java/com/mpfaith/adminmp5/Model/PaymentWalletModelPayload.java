package com.mpfaith.adminmp5.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentWalletModelPayload {

        @SerializedName("loginId")
        @Expose
        private String loginId;
        @SerializedName("merchantWalletAmount")
        @Expose
        private double merchantWalletAmount;
        @SerializedName("respCode")
        @Expose
        private int respCode;
        @SerializedName("respMessage")
        @Expose
        private String respMessage;

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public double getMerchantWalletAmount() {
            return merchantWalletAmount;
        }

        public void setMerchantWalletAmount(double merchantWalletAmount) {
            this.merchantWalletAmount = merchantWalletAmount;
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
