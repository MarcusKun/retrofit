package com.marcusli.retrofitclient.net;

import android.util.Log;

import com.marcusli.retrofitclient.entity.User;
import com.marcusli.retrofitclient.http.DataService;
import com.marcusli.retrofitclient.presenter.DataPresenter;
import com.marcusli.retrofitclient.presenter.listener.OnCallbackListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 59821 on 2017/8/16.
 */

public class DataNet extends BaseNet {
    private DataService service;

    public DataNet() {
        service = retrofit.create(DataService.class);
    }

    public void getUserbyQuery(String username, final OnCallbackListener onCallbackListener) {
        Call<User> call = service.getDataByQuery(username);
        handResponse(call, onCallbackListener);
    }

    public void getUserByQueryMap(User user, final OnCallbackListener onCallbackListener) {
        Map<String, Object> datas = new HashMap<>();
        datas.put("username", user.getUsername());
        datas.put("password", user.getPassword());
        datas.put("address", user.getAddress());
        datas.put("message", user.getMessage());
        Call<User> call = service.getDataByQueryMap(datas);
        handResponse(call, onCallbackListener);
    }

    public void getUserByField(String username, final OnCallbackListener onCallbackListener) {
        Call<User> call = service.getDataByField(username);
        handResponse(call, onCallbackListener);
    }

    public void getUserByFieldMap(User user, final OnCallbackListener onCallbackListener) {
        Map<String, Object> datas = new HashMap<>();
        datas.put("username", user.getUsername());
        datas.put("password", user.getPassword());
        datas.put("address", user.getAddress());
        datas.put("message", user.getMessage());
        Call<User> call = service.getDataByFieldMap(datas);
        handResponse(call, onCallbackListener);
    }

    /**
     * 上传文件演示
     *
     * @param file
     */
    public void getDataByPart1(File file, final OnCallbackListener onCallbackListener) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        Call<User> call = service.getDataByPart1(part);
        handResponse(call, onCallbackListener);
    }

    public void getDataByPart2(String data, final OnCallbackListener onCallbackListener) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), data);
        Call<User> call = service.getDataByPart2(requestBody);
        handResponse(call, onCallbackListener);
    }

    /**
     * 用PartMap传多个参数
     * @param usernmae
     * @param file
     * @param onCallbackListener
     */
    public void getDataByPartMap(String usernmae, File file, final OnCallbackListener onCallbackListener) {
        RequestBody username = RequestBody.create(MediaType.parse("text/plain"), usernmae);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        Map<String,RequestBody> datas = new HashMap<>();
        datas.put("username",username);
        datas.put("file\"; filename=\"" + file.getName(), requestFile);

        Call<User> call = service.getDataByPartMap(datas);
        handResponse(call, onCallbackListener);

    }

    private void handResponse(Call<User> call, final OnCallbackListener onCallbackListener) {
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                onCallbackListener.onResponse(user.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                onCallbackListener.onFailure(t);
            }
        });
    }

}
