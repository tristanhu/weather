package com.stu.heweather.http;

import com.stu.heweather.model.Const;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by hujinguang on 2016/8/5.
 */
public class RetrofitManager {
    private static RetrofitManager ourInstance = new RetrofitManager();

    public static RetrofitManager getInstance() {
        return ourInstance;
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.HOST)
                .client(OkHttpManager.getInstance().getOkHttpClient())
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new ResponseBodyStringConverter();
                    }

                    @Override
                    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
                        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
                    }

                    @Override
                    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return super.stringConverter(type, annotations, retrofit);
                    }
                })
                .build();


        apiService = retrofit.create(APIService.class);
    }

    private class ResponseBodyStringConverter implements Converter<ResponseBody, String>{
        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    private Retrofit retrofit;

    private APIService apiService;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public APIService getApiService() {
        return apiService;
    }
}
