package com.eemf.sirgoingfar.kwuiq.api_communications.responses;

import com.eemf.sirgoingfar.kwuiq.models.user.UserData;
import com.google.gson.annotations.SerializedName;

public class CustomerLoginResponse {

    public class Success extends SuccessResponse{

        @SerializedName("data")
        UserData user;

        public UserData getUser() {
            return user;
        }
    }

    public class Failure extends FailureResponse{}
}
