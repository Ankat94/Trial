package com.manish.trial.Utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.manish.trial.Utils.Data_Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ManagerData {

    private static String Base_Url = "https://mp-android-challenge.herokuapp.com/data";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void Get_Call(final Data_Callback callback) {

        client.setConnectTimeout(5);
        client.get(Base_Url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Check Data", response.toString());
                try {
                    callback.handler(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("Check Data", throwable.getMessage().toString());
            }
        });
    }
}
