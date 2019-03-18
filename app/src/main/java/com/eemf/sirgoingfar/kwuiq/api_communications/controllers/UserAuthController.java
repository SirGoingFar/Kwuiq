package com.eemf.sirgoingfar.kwuiq.api_communications.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.eemf.sirgoingfar.kwuiq.api_communications.NetworkCallback;
import com.eemf.sirgoingfar.kwuiq.api_communications.requests.UserAuthRequest;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.CreateCustomerResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.CustomerLoginResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;
import com.eemf.sirgoingfar.kwuiq.models.user.UserData;
import com.eemf.sirgoingfar.kwuiq.utils.RetrofitUtil;

import retrofit2.Call;

public class UserAuthController extends BaseController {

    public UserAuthController(Context context, NetworkCallback responseListener,
                              boolean showProgressDialog, boolean isRequestCancellable) {
        super(context, responseListener, showProgressDialog, isRequestCancellable);
    }

    public void createCustomer(@NonNull UserData userData) {
        Call<CreateCustomerResponse.Success> request = retrofit.create(UserAuthRequest.class)
                .createCustomer(userData);
        call(request);
    }

    public void customerSignIn(@NonNull String email, @NonNull String password) {
        UserData userData = new UserData.Builder()
                .setEmail(email)
                .setPassword(password)
                .build();

        Call<CustomerLoginResponse.Success> request = retrofit.create(UserAuthRequest.class)
                .customerLogin(userData);

        call(request);
    }

    public void forgotPassword() {
    }

    public void resetPassword() {
    }

    @Override
    public <C extends SuccessResponse> void call(Call<C> request) {
        super.call(request);
    }
}
