package com.eemf.sirgoingfar.kwuiq.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.eemf.sirgoingfar.kwuiq.models.user.UserData;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class Prefs {

    private static String PREFS = "prefs";
    private static String LOGIN_PREFS = "login_prefs";
    private static String APP_PREFS = "prefs.app";


    private SharedPreferences mPrefs;
    private SharedPreferences mLoginPrefs;
    private SharedPreferences appPrefs;

    private static Prefs sInstance;

    private final String PREF_USERDATA = "pref_userdata";
    private final String PREF_CUSTOMER_EMAIL = "pref_customer_email";
    private final String PREF_CUSTOMER_PASSWORD = "pref_customer_password";
    private final String PREFS_IS_AUTH_HEADER_SET = "prefs_is_auth_header_set";


    public static Prefs getInstance() {
        if (sInstance == null) {
            sInstance = new Prefs(App.getInstance().getAppContext());
        }
        return sInstance;
    }

    private Prefs(Context context) {
        sInstance = this;
        mPrefs = context.getSharedPreferences(PREFS, MODE_PRIVATE);
        mLoginPrefs = context.getSharedPreferences(LOGIN_PREFS, MODE_PRIVATE);
        appPrefs = context.getSharedPreferences(APP_PREFS, MODE_PRIVATE);
    }

    public void saveCustomerDataAndSetSessionData(@NonNull UserData userDataResponse) {
        mPrefs.edit().putString(PREF_USERDATA, new Gson().toJson(userDataResponse)).apply();
        Session.setUserData(userDataResponse);
    }

    public void saveEmail(@NonNull String verifiedEmail) {
        mLoginPrefs.edit().putString(PREF_CUSTOMER_EMAIL, verifiedEmail).apply();
    }

    public String getCustomerEmail(){
        return mLoginPrefs.getString(PREF_CUSTOMER_EMAIL, null);
    }

    public void savePassword(@NonNull String verifiedPassword) {
        mLoginPrefs.edit().putString(PREF_CUSTOMER_PASSWORD, verifiedPassword).apply();
    }

    public String getCustomerPassword(){
        return mLoginPrefs.getString(PREF_CUSTOMER_PASSWORD, null);
    }

    public void authHeaderSet(){
        mPrefs.edit().putBoolean(PREFS_IS_AUTH_HEADER_SET, true).apply();
    }

    public boolean isAuthHeaderSet() {
        return mPrefs.getBoolean(PREFS_IS_AUTH_HEADER_SET, false);
    }
}
