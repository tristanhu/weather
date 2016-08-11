package com.stu.heweather.react;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by hujinguang on 2016/8/10.
 */
public class ToastModule extends ReactContextBaseJavaModule {
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    private Handler handler;
    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);

        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public String getName() {
        return "MyToastModule";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> map = new HashMap<>();
        map.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        map.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);

        return map;
    }

    @ReactMethod
    public void show(String message, int duration, Callback errorCallBack, final Callback successCallback){
    Toast.makeText(getReactApplicationContext(), message, duration).show();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            successCallback.invoke("HAHAHA");
        }
    }, 1000);
    }

    @ReactMethod
    public void showABC(String message, int duration){
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

}
