package com.stu.heweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.stu.heweather.http.RetrofitManager;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    @InjectView(R.id.edt)
    EditText edt;
    @InjectView(R.id.btnCityName)
    Button btnCityName;
    @InjectView(R.id.btnID)
    Button btnID;
    @InjectView(R.id.btnGeo)
    Button btnGeo;
    @InjectView(R.id.btnZip)
    Button btnZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.inject(this);

        RxView.clicks(btnCityName)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Func1<Void, String>() {
                    @Override
                    public String call(Void aVoid) {
                        String cityName = edt.getText().toString();
                        Call<String> call = RetrofitManager.getInstance().getApiService().weather(cityName);

                        try {
                            return call.execute().body().toString();
                        } catch (IOException e) {
                            e.printStackTrace();

                            return null;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, s);
                    }
                });
    }
}
