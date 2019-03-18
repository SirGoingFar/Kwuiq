package com.eemf.sirgoingfar.kwuiq.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
            return false;

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null
                && (networkInfo.isConnected()
                || networkInfo.isConnectedOrConnecting()); //Todo: Find out the new way if implementing this deprecated function
    }

    /**
     * We use this method to check if there is poor network connectivity, or something else is going on with
     * the network, that is preventing the network from being optimal.
     *
     * @param context
     * @return
     */
    public static boolean isPoorNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return true;
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        NetworkInfo.DetailedState detailedState = info.getDetailedState();
        return detailedState == NetworkInfo.DetailedState.VERIFYING_POOR_LINK
                || detailedState == NetworkInfo.DetailedState.SUSPENDED
                || detailedState == NetworkInfo.DetailedState.DISCONNECTING
                || detailedState == NetworkInfo.DetailedState.DISCONNECTED
                || detailedState == NetworkInfo.DetailedState.FAILED
                || detailedState == NetworkInfo.DetailedState.BLOCKED;
    }
}
