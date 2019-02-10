package com.eemf.sirgoingfar.kwuiq.api_communications.controllers;

import android.content.Context;

import com.eemf.sirgoingfar.kwuiq.api_communications.NetworkCallback;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.BaseNetworkResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponseParser;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;
import com.eemf.sirgoingfar.kwuiq.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BaseController implements NetworkCallback {

    protected Context context;
    protected NetworkCallback responseListener;
    protected boolean showProgressDialog;
    protected boolean isRequestCancellable;
    protected Retrofit retrofit;
    private Call request;

    public BaseController(Context context, NetworkCallback responseListener) {
        this(context, responseListener, true, false);
    }

    public BaseController(Context context, NetworkCallback responseListener, boolean showProgressDialog,
                          boolean isRequestCancellable) {
        this.context = context;
        this.responseListener = responseListener;
        this.showProgressDialog = showProgressDialog;
        this.isRequestCancellable = isRequestCancellable;

        retrofit = RetrofitUtil.getRetrofitInstance();
    }

    @Override
    public void onNetworkSuccess(SuccessResponse response) {
        //hide progress dialog
        responseListener.onNetworkSuccess(response);
    }

    @Override
    public void onNetworkFailure(FailureResponse response) {
        //hide progress dialog
        //Todo: Check an error code corresponding to 'No Authentication' - logout if that error
        responseListener.onNetworkFailure(response);
    }

    protected <C extends SuccessResponse> void call(Call<C> request) {
        //Todo: show progress dialog
        this.request = request;
        request.enqueue(new Callback<C>() {
            @Override
            public void onResponse(Call<C> call, Response<C> response) {
                //Todo: hide progress dialog
                if (response.isSuccessful())
                    onNetworkSuccess(response.body());
                else{
                    FailureResponse failureResponse = FailureResponseParser.getFailure(response);
                    onNetworkFailure(failureResponse);
                }
            }

            @Override
            public void onFailure(Call<C> call, Throwable t) {
                //Todo: hide progress dialog
                handleGeneralError(call, t);
            }
        });
    }

    private <C extends BaseNetworkResponse> void handleGeneralError(Call<C> call, Throwable response) {
        //Todo: Toast why the call was not successful -possibly request timeout, etc
    }

    private void retry(){
        call(request);
    }

    protected void cancelRequest() {

        if (isRequestCancellable && request != null)
            request.cancel();
    }

    protected boolean isRequestCancelled() {
        return request.isCanceled();
    }

    protected boolean isRequestExecuted() {
        return request.isExecuted();
    }
}