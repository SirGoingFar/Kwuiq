package com.eemf.sirgoingfar.kwuiq.api_communications.responses;

import com.google.gson.annotations.SerializedName;

public class FailureResponse extends BaseNetworkResponse {

    @SerializedName("error_code")
    String errorCode;

    @SerializedName("error_status")
    String errorStatus;

    @SerializedName("error_message")
    String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
