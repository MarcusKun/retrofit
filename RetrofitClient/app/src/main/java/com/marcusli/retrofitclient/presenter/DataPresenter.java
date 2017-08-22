package com.marcusli.retrofitclient.presenter;

import android.util.Log;

import com.marcusli.retrofitclient.MainActivity;
import com.marcusli.retrofitclient.entity.User;
import com.marcusli.retrofitclient.net.DataNet;
import com.marcusli.retrofitclient.presenter.listener.OnCallbackListener;

import java.io.File;

/**
 * Created by 59821 on 2017/8/16.
 */

public class DataPresenter extends BasePresenter implements OnCallbackListener<String> {
    private MainActivity activity;

    public DataPresenter(MainActivity activity) {
        net = new DataNet();
        this.activity = activity;
    }

    public void getUserbyQuery(String username) {
        ((DataNet) net).getUserbyQuery(username, this);
    }

    public void getUserByQueryMap(User user) {
        ((DataNet) net).getUserByQueryMap(user, this);
    }

    public void getUserByField(String username) {
        ((DataNet) net).getUserByField(username, this);
    }

    public void getUserByFieldMap(User user) {
        ((DataNet) net).getUserByFieldMap(user, this);
    }

    public void getDataByPart1(File file) {
        ((DataNet) net).getDataByPart1(file, this);
    }

    public void getDataByPart2(String data) {
        ((DataNet) net).getDataByPart2(data, this);
    }

    public void getDataByPartMap(String username, File file) {
        ((DataNet) net).getDataByPartMap(username, file, this);
    }

    @Override
    public void onResponse(String msg) {
        activity.tvShow.setText(msg);
    }

    @Override
    public void onFailure(Throwable t) {
        activity.tvShow.setText("load fail:" + t.getMessage());
    }
}
