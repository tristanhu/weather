package com.stu.heweather.http;

import android.text.TextUtils;

import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.stu.heweather.model.Const;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hujinguang on 2016/8/5.
 */
public class OkHttpManager {
    private static OkHttpManager ourInstance = new OkHttpManager();

    public static OkHttpManager getInstance() {
        return ourInstance;
    }

    private OkHttpManager() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(OkLogInterceptor.builder().build())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        HttpUrl url = request.url();
                        if(TextUtils.isEmpty(url.queryParameter("APPID"))){
                            request = request.newBuilder()
                                    .url(url.toString()+"&APPID=" + Const.KEY)
                                    .build();
                        }

                        Response response = chain.proceed(request);
                        return response;
                    }
                })
                .build();
    }

    private OkHttpClient okHttpClient;

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
