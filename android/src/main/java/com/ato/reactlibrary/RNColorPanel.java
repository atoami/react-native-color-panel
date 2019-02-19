package com.ato.reactlibrary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class RNColorPanel extends LinearLayout {

    public RNColorHueSatView hueSatView;
    public RNColorValView valView;
    public RNSelectedColorView selectedColorView;
    public TextView rvalueTxt;
    public TextView gvalueTxt;
    public TextView bvalueTxt;

    public RNColorPanel(Context context)
    {
        super(context);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainContentView = inflater.inflate(R.layout.color_select_layout, null);
        addView(mainContentView);

        hueSatView = (RNColorHueSatView)mainContentView.findViewById(R.id.color_huesat_view);
        hueSatView.setMainPanel(this);

        valView = (RNColorValView)mainContentView.findViewById(R.id.color_val_view);
        valView.setMainPanel(this);

        selectedColorView = (RNSelectedColorView)mainContentView.findViewById(R.id.selected_color_view);
        selectedColorView.setMainPanel(this);

        rvalueTxt = (TextView)mainContentView.findViewById(R.id.rvalue_txt);
        gvalueTxt = (TextView)mainContentView.findViewById(R.id.gvalue_txt);
        bvalueTxt = (TextView)mainContentView.findViewById(R.id.bvalue_txt);
        rvalueTxt.setText("R:");
        gvalueTxt.setText("G:");
        bvalueTxt.setText("B:");

        setColor(Color.BLACK);
    }

    public void setColor(int rgb) {
        if (rgb == selectedColorView.getColor()) {
            return;
        }

        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        float hsv[] = new float[3];
        Color.RGBToHSV(r, g, b, hsv);

        hueSatView.setHueSat(hsv[0], hsv[1]);
        valView.setVal(hsv[2]);
    }

    public void onColorChanged() {
        WritableMap event = Arguments.createMap();
        event.putString("color", String.format("#%06X", (0xFFFFFF & selectedColorView.getColor())));
        ReactContext reactContext = (ReactContext)getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "colorDidChange",
                event);
    }
}
