package com.eemf.sirgoingfar.kwuiq.api_communications.responses;

import com.google.gson.annotations.SerializedName;

public class FailureResponse extends BaseNetworkResponse {

    @SerializedName("error_code")
    String errorCode;

    @SerializedName("error_action")
    String errorAction;

    @SerializedName("message")
    String errorMessage;

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorAction(String errorAction) {
        this.errorAction = errorAction;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorAction() {
        return errorAction;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
