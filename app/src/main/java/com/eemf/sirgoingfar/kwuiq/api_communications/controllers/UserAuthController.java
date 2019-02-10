package com.eemf.sirgoingfar.kwuiq.api_communications.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.eemf.sirgoingfar.kwuiq.api_communications.NetworkCallback;
import com.eemf.sirgoingfar.kwuiq.api_communications.requests.UserAuthRequest;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.CreateCustomerResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;
import com.eemf.sirgoingfar.kwuiq.models.user.User;
import com.eemf.sirgoingfar.kwuiq.utils.RetrofitUtil;

import retrofit2.Call;

public class UserAuthController extends BaseController {

    public UserAuthController(Context context, NetworkCallback responseListener,
                              boolean showProgressDialog, boolean isRequestCancellable) {
        super(context, responseListener, showProgressDialog, isRequestCancellable);
    }

    private void createCustomer(@NonNull User user, @NonNull String profilePicture) {
        Call<CreateCustomerResponse.Success> request = retrofit.create(UserAuthRequest.class)
                .createCustomer(user, RetrofitUtil.createPartFromFile(context, "profile_pic", profilePicture));
        call(request);
    }

    private void customerSignIn(@NonNull String email, @NonNull String password) {
    }

    private void forgotPassword() {
    }

    private void resetPassword() {
    }

    @Override
    public <C extends SuccessResponse> void call(Call<C> request) {
        super.call(request);
    }
}
