package com.stu.heweather.react;

import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.csslayout.CSSConstants;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.image.ImageResizeMode;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTextView;
import com.facebook.react.views.text.TextInlineImageSpan;

import javax.annotation.Nullable;

/**
 * Created by hujinguang on 2016/8/13.
 */
public class MyReactTextView extends SimpleViewManager<TextView> {

    private static final String TAG = "MyReactTextView";

    public static final String REACT_CLASS = "MyReactTextView";


    private StringBuilder sb = new StringBuilder();

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected TextView createViewInstance(final ThemedReactContext reactContext) {
        TextView view = new TextView(reactContext);
        view.setTextColor(reactContext.getResources().getColor(android.R.color.background_dark));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WritableMap event = Arguments.createMap();
                event.putString("message", "MyMessage");

                reactContext.getJSModule(RCTEventEmitter.class)
                        .receiveEvent(view.getId(), "topChange", event);
            }
        });
        return view;
    }

    @ReactProp(name = "src")
    public void setSrc(TextView view,@javax.annotation.Nullable ReadableArray sources){
        sb.append(String.format("setSrc %s\n", sources.toString()));
        view.setText(sb.toString());
    }

    @ReactPropGroup(names = {
            ViewProps.BORDER_RADIUS,
            ViewProps.BORDER_TOP_LEFT_RADIUS,
            ViewProps.BORDER_TOP_RIGHT_RADIUS,
            ViewProps.BORDER_BOTTOM_LEFT_RADIUS,
            ViewProps.BORDER_BOTTOM_RIGHT_RADIUS,
    }, defaultFloat = CSSConstants.UNDEFINED)
    public void setBorderRadius(TextView view,  int index, float borderRadius){
        if (!CSSConstants.isUndefined(borderRadius)) {
            borderRadius = PixelUtil.toPixelFromDIP(borderRadius);
        }

        sb.append(String.format("setBorderRadius %d %f\n", index, borderRadius));
        view.setText(sb.toString());
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(TextView view, @Nullable String resizeMode) {
       ScalingUtils.ScaleType type = ImageResizeMode.toScaleType(resizeMode);

        Log.d(TAG, "setResizeMode: " + resizeMode);

        sb.append(String.format("setResizeMode %s \n", resizeMode));
        view.setText(sb.toString());
    }



//    @Override
//    public void updateExtraData(ReactTextView view, Object extraData) {
//        ReactTextUpdate update = (ReactTextUpdate) extraData;
//        if (update.containsImages()) {
//            Spannable spannable = update.getText();
//            TextInlineImageSpan.possiblyUpdateInlineImageSpans(spannable, view);
//        }
//        view.setText(update);
//    }
}
