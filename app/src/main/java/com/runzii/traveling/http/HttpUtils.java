package com.runzii.traveling.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Wouldyou on 2015/6/4.
 */
public class HttpUtils {

    private static AsyncHttpClient httpClient;

    public static void init() {
        httpClient = new AsyncHttpClient();
        httpClient.setEnableRedirects(true);
    }

    public static void getStreetViewImage(String url, final HttpCallBack callBack) {

        try {
            url = new String(url.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpClient.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    JSONArray images = jsonObject.getJSONArray("data");
                    for (int i = 0; i < images.length(); i++) {
                        JSONObject jsonObject1 = images.getJSONObject(i);
                        if (jsonObject1.getInt("width") > 512 && jsonObject1.getInt("height") > 256)
                        {
                            callBack.onSuccess(jsonObject1.getString("objURL"));
                            break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callBack.onFailed("getStreetViewImage " + responseString);
            }
        });
    }

}
