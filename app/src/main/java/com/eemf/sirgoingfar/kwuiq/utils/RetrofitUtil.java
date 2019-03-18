package com.eemf.sirgoingfar.kwuiq.utils;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;

import com.eemf.sirgoingfar.kwuiq.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtil {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {

        final Prefs prefs = Prefs.getInstance();

        if (retrofit != null && prefs.isAuthHeaderSet()) {
            return retrofit;
        }

        //Create OkHttpClient Builder and HttpLoggingInterceptor
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(Constants.DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Add logger to client if it's DEBUG
        if (BuildConfig.DEBUG)
            clientBuilder.addInterceptor(logger);

        //Get Customer Login Credentials
        String email = prefs.getCustomerEmail();
        String password = prefs.getCustomerPassword();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            String authParams = email.concat(":").concat(password);
            final String authHeader = "Basic " + Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP);

            //Add an Authorization Header Interceptor
            clientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    //get the built request
                    Request newRequest = chain.request();

                    //get a builder on the request
                    Request.Builder newRequestBuilder = newRequest.newBuilder();
                    newRequestBuilder.addHeader("authorization", authHeader);

                    //flag that 'auth header' is added
                    prefs.authHeaderSet();

                    //continue the flow
                    return chain.proceed(newRequestBuilder.build());
                }
            });
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();

        return retrofit;
    }

    public static RequestBody createPartFromString(String stringValue) {

        RequestBody valuePart = null;

        if (!TextUtils.isEmpty(stringValue))
            valuePart = RequestBody.create(MultipartBody.FORM, stringValue);

        return valuePart;
    }

    public static MultipartBody.Part createPartFromFile(
            @NonNull Context context, @NonNull String fileDesc, @Nullable String fileUri
    ) {

        if (TextUtils.isEmpty(fileUri))
            return null;

        Uri uri = Uri.parse(fileUri);

        if (uri == null)
            return null;

        File file = new File(UriUtil.getPath(context, uri));

        MultipartBody.Part filePart;

        RequestBody fileRequestBody = RequestBody.create(MediaType.parse(context.getContentResolver().getType(uri)), file);

        filePart = MultipartBody.Part.createFormData(fileDesc, file.getName(), fileRequestBody);

        return filePart;

    }
}
