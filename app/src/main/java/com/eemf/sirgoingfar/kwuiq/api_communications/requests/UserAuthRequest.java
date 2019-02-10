package com.eemf.sirgoingfar.kwuiq.api_communications.requests;

import com.eemf.sirgoingfar.kwuiq.api_communications.responses.CreateCustomerResponse;
import com.eemf.sirgoingfar.kwuiq.models.user.User;
import com.eemf.sirgoingfar.kwuiq.models.user.UserData;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAuthRequest {

    @POST("api/v1/customers/create")
    Call<CreateCustomerResponse.Success> createCustomer(@Body User user,
                                                @Part MultipartBody.Part profilePicture);
}
