package com.stu.heweather;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.jakewharton.rxbinding.view.RxView;
import com.stu.heweather.model.Const;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    public OkHttpClient okHttpClient;
    @InjectView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

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
        RxView.clicks(btn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                RequestBody requestBody = new FormBody.Builder()
                        .add("key", Const.KEY)
                        .add("search", "allchina")
                        .build();

                String url = Const.HOST + "?q=Lodon";
                Log.d(TAG, url);

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "error");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().source().readUtf8();
                        Log.d(TAG, json);
                    }
                });

            }
        });


    }
}
