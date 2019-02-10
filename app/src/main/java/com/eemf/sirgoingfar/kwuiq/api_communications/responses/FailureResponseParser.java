package com.eemf.sirgoingfar.kwuiq.api_communications.responses;

import com.eemf.sirgoingfar.kwuiq.utils.RetrofitUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class FailureResponseParser {

    public static FailureResponse getFailure(Response<?> response) {

        Converter<ResponseBody, FailureResponse> converter = RetrofitUtil.getRetrofitInstance()
                .responseBodyConverter(FailureResponse.class, new Annotation[0]);

        FailureResponse failureResponse = null;

        try {
            failureResponse = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return failureResponse;
    }
}
