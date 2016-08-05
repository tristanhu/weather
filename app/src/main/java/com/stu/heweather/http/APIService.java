package com.stu.heweather.http;

import android.provider.CallLog;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hujinguang on 2016/8/4.
 */
public interface APIService {
    @GET("data/2.5/weather")
    Call<String> weather(@Query("q")String city);

    @GET("data/2.5/weather")
    Call<String> weather(@Query("id")long id);


    @GET("data/2.5/weather")
    Call<String> weather(@Query("lon")double lon, @Query("lat")double lat);


    @GET("data/2.5/weather?zip={zip},{country}")
    Call<String> weather(@Path("zip") String zip, @Path("country") String country);
}
