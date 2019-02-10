package com.eemf.sirgoingfar.kwuiq.api_communications.requests;

import android.support.annotation.NonNull;

import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PostRequest<S extends SuccessResponse, F extends FailureResponse> implements Request<S,F> {

    private JSONObject postParams;

    public PostRequest(){
        postParams = new JSONObject();
    }
    public PostRequest(@NonNull JSONObject postParams) {
        this.postParams = postParams;
    }

    public void addStringParam(String key, String value) {
        try {
            postParams.put(key, value);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    /**
     * add integer post value
     *
     * @param key   key
     * @param value value
     */
    public void addIntParam(String key, int value) {
        try {
            postParams.put(key, value);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    /**
     * add long post value
     *
     * @param key   key
     * @param value value
     */
    public void addLongParam(String key, long value) {
        try {
            postParams.put(key, value);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    /**
     * adds map post value
     *
     * @param key   key
     * @param value value
     */
    public void addMapParam(String key, Map value) {
        try {
            postParams.put(key, new JSONObject(value));
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    /**
     * Adds array items post value
     *
     * @param key
     * @param items
     */
    public void addArrayItems(String key, ArrayList<Map> items) {
        try {
            JSONArray jArray;
            try {
                if (postParams.has(key)) {
                    jArray = postParams.getJSONArray(key);
                } else {
                    jArray = new JSONArray();
                }
            } catch (Exception e) {
                jArray = new JSONArray();
            }

            for (Map item : items) {
                jArray.put(new JSONObject(item));
            }

            postParams.put(key, jArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addArrayItems(String key, List<String> stringItems) {

        try {

            JSONArray jsonArray;

            try {
                if (postParams.has(key))
                    jsonArray = postParams.getJSONArray(key);
                else
                    jsonArray = new JSONArray();
            } catch (Exception e) {
                e.printStackTrace();
                jsonArray = new JSONArray();
            }

            for (String stringItem : stringItems)
                jsonArray.put(stringItem);

            postParams.put(key, jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * Adds a json object
     *
     * @param object jsonObject
     */
    public void addJSONObject(Object object) {
        try {
            postParams = new JSONObject(new Gson().toJson(object));
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    @Override
    public abstract Class<S> getSuccessResponse();

    @Override
    public abstract Class<F> getFailureResponse();

    @Override
    public int getRequestTimeoutInSeconds() {
        return TimeOut.DEFAULT_60Ss.getTimeOut();
    }

    @Override
    public String getRequestTag() {
        return getClass().getName();
    }

    @Override
    public String getRequestType() {
        return Type.GET.getType();
    }
}
