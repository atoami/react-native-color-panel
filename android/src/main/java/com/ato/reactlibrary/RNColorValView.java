package com.ato.reactlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RNColorValView extends View {
	public static final int MARGIN_TOP = 5;
	public static final int MARGIN_BOTTOM = 5;
	public static final int MARGIN_LEFT = 15;
	public static final int MARGIN_RIGHT = 15;
	public static final int THUMB_SIZE = 20;
	
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Paint thumbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private RectF contentRect = new RectF();
	private RectF thumbRect = new RectF();
	
	private float hsv[] = new float[3];
	
	private RNColorPanel mainColorPanel;

	public RNColorValView(Context context) {
		this(context, null);
	}

	public RNColorValView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RNColorValView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		hsv[0] = 0.f;
		hsv[1] = 1.f;
		hsv[2] = 1.f;
		int rgb = Color.HSVToColor(hsv);
		Shader shader = new LinearGradient(0.f, 0.f, this.getMeasuredWidth(), 0.f, rgb, 0xff000000, TileMode.CLAMP);
		paint.setShader(shader);
		thumbPaint.setStyle(Paint.Style.FILL);
		thumbPaint.setColor(0xFFF2F2F2);
		thumbPaint.setShadowLayer(2, 1, 1, 0xC0000000);
	}

	public void setMainPanel(RNColorPanel panel) {
		mainColorPanel = panel;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		contentRect.set(MARGIN_LEFT, MARGIN_TOP, w - MARGIN_RIGHT, h - MARGIN_BOTTOM);			
	}
	
	public void colorHueSatChanged(float hue, float sat) {
		hsv[0] = hue;
		hsv[1] = sat;
		hsv[2] = 1.0f;

		mainColorPanel.selectedColorView.setHSV(hsv[0], hsv[1], hsv[2]);
		
		int rgb = Color.HSVToColor(hsv);
		Shader shader = new LinearGradient(0.f, 0.f, this.getMeasuredWidth(), 0.f, rgb, 0xff000000, TileMode.CLAMP);
		paint.setShader(shader);
		invalidate();
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawRoundRect(contentRect, 8.f, 8.f, paint);
		drawThumb(canvas);
	}
	
	private void drawThumb(Canvas canvas) {
		int thumbX = (int)((1 - hsv[2]) * (getMeasuredWidth() - MARGIN_LEFT - MARGIN_RIGHT) + MARGIN_LEFT);
		int thumbY = getMeasuredHeight() / 2;		
		thumbRect.set(thumbX - THUMB_SIZE / 2, thumbY - THUMB_SIZE / 2, thumbX + THUMB_SIZE / 2, thumbY + THUMB_SIZE / 2);
		canvas.drawRoundRect(thumbRect, 4.f, 4.f, thumbPaint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();		
		
		float val = 1.f;		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(x < MARGIN_LEFT || x >= getMeasuredWidth() - MARGIN_RIGHT
				|| y < MARGIN_TOP || y >= getMeasuredHeight() - MARGIN_BOTTOM)
				return true;
			val = 1.f - (float)(x - MARGIN_LEFT) / (getMeasuredWidth() - MARGIN_LEFT - MARGIN_RIGHT);
			setVal(val);
			break;
			
		case MotionEvent.ACTION_MOVE:
			if(x < MARGIN_LEFT)
				x = MARGIN_LEFT;
			if(x >= getMeasuredWidth() - MARGIN_RIGHT)
				x = getMeasuredWidth() - MARGIN_RIGHT;
			val = 1.f - (float)(x - MARGIN_LEFT) / (getMeasuredWidth() - MARGIN_LEFT - MARGIN_RIGHT);
			setVal(val);
			
			break;

		case MotionEvent.ACTION_UP:
			break;

		default:
			break;		
		}
		return true;

	}
	
	public void setVal(float val) {
		hsv[2] = val;
		mainColorPanel.selectedColorView.setVal(hsv[2]);
		invalidate();
	}
}
