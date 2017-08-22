package com.marcusli.retrofitclient.presenter.listener;

/**
 * Created by 59821 on 2017/8/16.
 */

public interface OnCallbackListener<T> {
    void onResponse(T t);
    void onFailure(Throwable t);
}
