package com.eemf.sirgoingfar.kwuiq.api_communications.responses;

import com.eemf.sirgoingfar.kwuiq.models.user.UserData;
import com.google.gson.annotations.SerializedName;

public class CreateCustomerResponse {

    public class Success extends SuccessResponse{

        @SerializedName("status")
        String status;

        @SerializedName("user_data")
        UserData userData;

        public String getStatus() {
            return status;
        }

        public UserData getUserData() {
            return userData;
        }
    }

    public class Failure extends FailureResponse {}
}
