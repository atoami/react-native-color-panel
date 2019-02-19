package com.ato.reactlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RNSelectedColorView extends View {
	public static final int MARGIN = 5;
	
	private float[] hsv = {0.f, 1.f, 1.f};
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);	
	private RectF contentRect = new RectF();
	
	private RNColorPanel mainColorPanel;
	
	public RNSelectedColorView(Context context) {
		this(context, null);
	}

	public RNSelectedColorView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RNSelectedColorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.HSVToColor(hsv));
		paint.setShadowLayer(2, 1, 1, 0xC0000000);
	}

	public void setMainPanel(RNColorPanel panel) {
		mainColorPanel = panel;
	}

	public void setHue(float hue) {
		hsv[0] = hue;
		colorChanged();
	}
	
	public void setSat(float sat) {
		hsv[1] = sat;
		colorChanged();
	}
	
	public void setVal(float val) {
		hsv[2] = val;
		colorChanged();
	}
	
	public void setHSV(float hue, float sat, float val) {
		hsv[0] = hue;
		hsv[1] = sat;
		hsv[2] = val;
		colorChanged();
	}
	
	private void colorChanged() {
		
		paint.setColor(Color.HSVToColor(hsv));
		int rgb = getColor();
		int r = (rgb >> 16) & 0xFF;
		int g = (rgb >> 8) & 0xFF;
		int b = rgb & 0xFF;

		mainColorPanel.rvalueTxt.setText(String.format("R: %3d", r * 100 / 255) + "%");
		mainColorPanel.gvalueTxt.setText(String.format("G: %3d", g * 100 / 255) + "%");
		mainColorPanel.bvalueTxt.setText(String.format("B: %3d", b * 100 / 255) + "%");

		mainColorPanel.onColorChanged();

		invalidate();
	}
	
	public int getColor() {		
		return Color.HSVToColor(hsv);
	}
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		contentRect.set(MARGIN, MARGIN, w - MARGIN, h - MARGIN);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRoundRect(contentRect, 8.f, 8.f, paint);
	}
}
