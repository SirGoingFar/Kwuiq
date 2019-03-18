package com.eemf.sirgoingfar.kwuiq.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class App extends MultiDexApplication {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        //initialize class reference
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }

    public Context getAppContext() {
        return this;
    }
}
