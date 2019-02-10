package com.eemf.sirgoingfar.kwuiq.api_communications.requests;

import android.support.annotation.NonNull;

import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;

import java.util.HashMap;
import java.util.Map;

public abstract class GetRequest<S extends SuccessResponse, F extends FailureResponse> implements Request<S,F> {

    private Map<String, Object> requestParams;

    public GetRequest(){
        requestParams = new HashMap<>();
    }
    public GetRequest(@NonNull HashMap<String, Object> requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public abstract Class<S> getSuccessResponse();

    @Override
    public abstract Class<F> getFailureResponse();

    @Override
    public int getRequestTimeoutInSeconds() {
        return TimeOut.DEFAULT_60Ss.getTimeOut();
    }

    @Override
    public String getRequestTag() {
        return getClass().getName();
    }

    @Override
    public String getRequestType() {
        return Type.GET.getType();
    }

    public void addRequestParam(@NonNull String key, @NonNull Object value){
        if(!requestParams.containsKey(key))
            requestParams.put(key, value);
    }

    public Map<String, Object> getRequestParams() {
        return requestParams;
    }
}
