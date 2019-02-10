package com.eemf.sirgoingfar.kwuiq.api_communications;

import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;

public interface NetworkCallback {

    void onNetworkSuccess(SuccessResponse response);

    void onNetworkFailure(FailureResponse response);
}
