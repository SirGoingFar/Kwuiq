package com.eemf.sirgoingfar.kwuiq.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DisplayUtil {

    private Context mContext;

    public DisplayUtil(Context context) {
        this.mContext = context;
    }

    public DisplayMetrics getDisplayMetrics() {
        return mContext.getResources().getDisplayMetrics();
    }

    private Configuration getConfiguration() {
        return mContext.getResources().getConfiguration();
    }

    public int getWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public int getHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public float getXdpi() {
        return getDisplayMetrics().xdpi;
    }

    public float getYdpi() {
        return getDisplayMetrics().ydpi;
    }

    public float getDensity() {
        return getDisplayMetrics().density;
    }

    public int getDensityDpi() {
        return getDisplayMetrics().densityDpi;
    }

    public String getDensityDpiString() {
        int dpi = getDensityDpi();
        if (dpi <= DisplayMetrics.DENSITY_LOW) {
            return "low";
        }

        if (dpi <= DisplayMetrics.DENSITY_MEDIUM) {
            return "medium";
        }

        if (dpi <= DisplayMetrics.DENSITY_HIGH) {
            return "high";
        }

        if (dpi <= DisplayMetrics.DENSITY_XHIGH) {
            return "xhigh";
        }

        if (dpi <= DisplayMetrics.DENSITY_XXHIGH) {
            return "xxhigh";
        }

        return "xxxhigh";
    }

    public float getScaledDensity() {
        return getDisplayMetrics().scaledDensity;
    }

    public int getScreenWidth() {
        return getConfiguration().screenWidthDp;
    }

    public int getScreenHeight() {
        return getConfiguration().screenHeightDp;
    }

    public String getScreenSizeType() {
        String type;
        switch (getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) {
            case (Configuration.SCREENLAYOUT_SIZE_SMALL):
                type = "small";
                break;
            case (Configuration.SCREENLAYOUT_SIZE_NORMAL):
                type = "normal";
                break;
            case (Configuration.SCREENLAYOUT_SIZE_LARGE):
                type = "large";
                break;
            case (Configuration.SCREENLAYOUT_SIZE_XLARGE):
                type = "xlarge";
                break;
            default:
                type = "unknown";
                break;
        }

        return type;
    }

    public float dp(float f) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, f, getDisplayMetrics());
    }

    public float dp(int i) {
        return dp((float) i);
    }

    public float sp(float f) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, f, getDisplayMetrics());
    }

    public float sp(int i) {
        return sp((float) i);
    }

    public float px(float f) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, f, getDisplayMetrics());
    }

    public float px(int i) {
        return px((float) i);
    }
}
