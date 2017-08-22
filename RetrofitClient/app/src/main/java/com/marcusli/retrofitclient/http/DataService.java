package com.marcusli.retrofitclient.http;

import com.marcusli.retrofitclient.entity.User;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 59821 on 2017/8/16.
 */

public interface DataService {

    @GET("user!getUser")
    Call<User> getDataByQuery(@Query("username") String username);

    @FormUrlEncoded
    @POST("user!getUser")
    Call<User> getDataByField(@Field("username") String username);

    @Multipart
    @POST("user!saveFile")
    Call<User> getDataByPart1(@Part MultipartBody.Part part);

    @Multipart
    @POST("user!getUser")
    Call<User> getDataByPart2(@Part("username") RequestBody body);

    @GET("user!getUser")
    Call<User> getDataByQueryMap(@QueryMap Map<String, Object> datas);

    @FormUrlEncoded
    @POST("user!getUser")
    Call<User> getDataByFieldMap(@FieldMap Map<String, Object> fields);

    @Multipart
    @POST("user!getUserAndFile")
    Call<User> getDataByPartMap(@PartMap Map<String, RequestBody> params);
}
