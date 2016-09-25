package com.runzii.traveling.http;

/**
 * Created by Wouldyou on 2015/6/4.
 */
public interface HttpCallBack {

    void onSuccess(String response);

    void onFailed(String failedInfo);

}
