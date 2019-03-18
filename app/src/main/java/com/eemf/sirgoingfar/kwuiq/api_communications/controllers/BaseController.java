package com.eemf.sirgoingfar.kwuiq.api_communications.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.eemf.sirgoingfar.kwuiq.R;
import com.eemf.sirgoingfar.kwuiq.api_communications.NetworkCallback;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.BaseNetworkResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponseCode;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponseParser;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;
import com.eemf.sirgoingfar.kwuiq.dialog_fragments.CustomDialog;
import com.eemf.sirgoingfar.kwuiq.utils.NetworkUtil;
import com.eemf.sirgoingfar.kwuiq.utils.Prefs;
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
    private CustomDialog customDialog;
    private Call request;
    protected Prefs prefs;

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
        prefs = Prefs.getInstance();
    }

    @Override
    public void onNetworkSuccess(SuccessResponse response) {
        hideProgressDialog();
        responseListener.onNetworkSuccess(response);
    }

    @Override
    public void onNetworkFailure(FailureResponse response) {
        hideProgressDialog();
        //Todo: Check an error code corresponding to 'No Authentication' - logout if that error
        responseListener.onNetworkFailure(response);
    }

    protected <C extends SuccessResponse> void call(Call<C> request) {

        //check network connectivity
        if (!NetworkUtil.isConnected(context)) {

            final FailureResponse response = new FailureResponse();
            response.setErrorAction(FailureResponseCode.ACTION_RETAIN_SESSION);
            response.setErrorCode(FailureResponseCode.E101);
            response.setErrorMessage(FailureResponseCode.E101_MSG);

            new AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.no_internet_connection))
                    .setMessage(context.getString(R.string.poor_connectivity_toast_msg))
                    .setIcon(context.getDrawable(R.drawable.ic_signal_wifi_0_bar_black_24dp))
                    .setPositiveButton(context.getString(R.string.make_order_manually), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Send Failure
                            responseListener.onNetworkFailure(response);
                            //context.startActivity(); - Todo: Start manual ordering activity
                            Toast.makeText(context, "Manual Ordering Activity opened", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNeutralButton(context.getString(R.string.no_internet_connection_dialog_neutral_button_label), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Send Failure
                            responseListener.onNetworkFailure(response);
                            dialog.dismiss();
                        }
                    }).show();
            return;
        }

        showProgressDialog();
        this.request = request;
        request.enqueue(new Callback<C>() {
            @Override
            public void onResponse(Call<C> call, Response<C> response) {
                if (response.isSuccessful())
                    onNetworkSuccess(response.body());
                else {
                    FailureResponse failureResponse = FailureResponseParser.getFailure(response);
                    onNetworkFailure(failureResponse);
                }
            }

            @Override
            public void onFailure(Call<C> call, Throwable t) {
                handleGeneralError(call, t);
            }
        });
    }

    private <C extends BaseNetworkResponse> void handleGeneralError(Call<C> call, Throwable response) {
        hideProgressDialog();
        //Todo: Toast why the call was not successful -possibly request timeout, etc
    }

    private void retry() {
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

    private void showProgressDialog() {

        if (!showProgressDialog)
            return;

        if (customDialog == null)
            customDialog = new CustomDialog(context);

        if (customDialog.isShowing())
            return;

        customDialog.show();
    }

    private void hideProgressDialog() {

        if (customDialog == null)
            return;

        if (customDialog.isShowing())
            customDialog.hide();
    }
}