package com.stu.heweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.stu.heweather.react.MyReactActivity;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.btnReact)
    Button btnReact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        RxView.clicks(btn)
                .throttleWithTimeout(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                        startActivity(intent);
                    }
                });

        RxView.clicks(btnReact)
                .throttleWithTimeout(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent intent = new Intent(MainActivity.this, MyReactActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
