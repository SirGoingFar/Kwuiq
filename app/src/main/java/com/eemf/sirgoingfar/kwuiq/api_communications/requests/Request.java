package com.eemf.sirgoingfar.kwuiq.api_communications.requests;

import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;

public interface Request<S extends SuccessResponse, F extends FailureResponse> {

    Class<S> getSuccessResponse();

    Class<F> getFailureResponse();

    int getRequestTimeoutInSeconds();

    String getRequestTag();

    String getRequestType();


    enum Type {

        GET("GET"),
        POST("POST"),
        DELETE("DELETE"),
        PUT("PUT");

        private String type;

        Type(String type) {
            this.type = type;
        }

        String getType(){
            return type;
        }
    }

    enum TimeOut{

        DEFAULT_60Ss(60);

        int timeOut;

        TimeOut(int timeInSeconds){
            timeOut = timeInSeconds;
        }

        int getTimeOut(){
            return timeOut;
        }
    }
}
