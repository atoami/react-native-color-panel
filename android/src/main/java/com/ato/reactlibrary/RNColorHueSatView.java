package com.ato.reactlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RNColorHueSatView extends View {
	public static final int TILE_DEFAULT_COUNT = 20;
	public static final int TILE_GAP = 2;
	public static final int MARGIN = 10;
	public static final int STROKE_WIDTH = 4;
	
	private float tileWidth = 0;
	private float tileHeight = 0;
	private int tileHCount = TILE_DEFAULT_COUNT;
	private int tileVCount = TILE_DEFAULT_COUNT;
	
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);	
	private Paint selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Rect tileRect = new Rect();
	private RectF borderRect = new RectF();
	
	private int selX = 0;
	private int selY = 0;
	
	private RNColorPanel mainColorPanel;

	public RNColorHueSatView(Context context) {
		this(context, null);
	}

	public RNColorHueSatView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RNColorHueSatView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		selectedPaint.setStyle(Paint.Style.STROKE);
		selectedPaint.setColor(0xFFFCFCFC);
		selectedPaint.setStrokeWidth(STROKE_WIDTH);
		selectedPaint.setStrokeJoin(Join.ROUND);
		selectedPaint.setShadowLayer(2, 1, 1, 0xC0000000);
	}

	public void setMainPanel(RNColorPanel panel) {
		mainColorPanel = panel;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		tileWidth = (float)(w - MARGIN * 2 - TILE_GAP * (TILE_DEFAULT_COUNT - 1)) / TILE_DEFAULT_COUNT;
		tileHeight = (float)(h - MARGIN * 2 - TILE_GAP * (TILE_DEFAULT_COUNT - 1)) / TILE_DEFAULT_COUNT;
		if(tileHeight > tileWidth) {
			tileHCount = TILE_DEFAULT_COUNT;
			tileHeight = tileWidth;
			tileVCount = (int)((float)(h - MARGIN * 2 + TILE_GAP) / (tileHeight + TILE_GAP));			
		} else {
			tileVCount = TILE_DEFAULT_COUNT;
			tileWidth = tileHeight;
			tileHCount = (int)((float)(w - MARGIN * 2 + TILE_GAP) / (tileWidth + TILE_GAP));
		}
		setSelectedTile(selX, selY);
	}

	@Override 
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		float[] colorHSV = new float[3];
		colorHSV[2] = 1.0f;
		for(int y = 0; y < tileVCount; y ++) {
			for(int x = 0; x < tileHCount; x ++) {
				int rgb = getTileColor(x, y);
				paint.setColor(rgb);
				setTileRect(x, y);
				canvas.drawRect(tileRect, paint);
			}
		}
		
		drawSelectedTile(canvas);
	}
	
	private void drawSelectedTile(Canvas canvas) {
		int rgb = getTileColor(selX, selY);
		paint.setColor(rgb);
		setSelectedTileRect();
		canvas.drawRect(tileRect, paint);
		
		setSelectedTileBorderRect();
		canvas.drawRoundRect(borderRect, 1, 1, selectedPaint);
	}
	
	private void setTileRect(int x, int y) {
		tileRect.set((int)(MARGIN + x * (tileWidth + TILE_GAP)), (int)(MARGIN + y * (tileHeight + TILE_GAP)), (int)(MARGIN + x * (tileWidth + TILE_GAP) + tileWidth - 1), (int)(MARGIN + y * (tileHeight + TILE_GAP) + tileHeight - 1));
	}
	
	private void setSelectedTileRect() {
		tileRect.set((int)(MARGIN + selX * (tileWidth + TILE_GAP) - TILE_GAP), (int)(MARGIN + selY * (tileHeight + TILE_GAP) - TILE_GAP), (int)(MARGIN + selX * (tileWidth + TILE_GAP) + tileWidth + TILE_GAP - 1), (int)(MARGIN + selY * (tileHeight + TILE_GAP) + tileHeight + TILE_GAP - 1));
	}
	
	private void setSelectedTileBorderRect() {
		borderRect.set(MARGIN + selX * (tileWidth + TILE_GAP) - TILE_GAP - STROKE_WIDTH / 2, MARGIN + selY * (tileHeight + TILE_GAP) - TILE_GAP - STROKE_WIDTH / 2, MARGIN + selX * (tileWidth + TILE_GAP) + tileWidth + TILE_GAP + STROKE_WIDTH / 2 - 1, MARGIN + selY * (tileHeight + TILE_GAP) + tileHeight + TILE_GAP + STROKE_WIDTH / 2 - 1);
	}
	
	private int getTileColor(int x, int y) {
		float[] hsv = new float[3];
		hsv[0] = 360.0f / tileHCount * x;
		hsv[1] = 1.0f - 0.9f / (tileVCount - 1) * y;
		hsv[2] = 1.0f;
		return Color.HSVToColor(hsv);
	}
	
	public void setSelectedTile(int selX, int selY) {
		this.selX = selX;
		this.selY = selY;
		invalidate();
		mainColorPanel.valView.colorHueSatChanged(getHue(), getSat());
	}
	
	public float getHue() {
		return 360.0f / tileHCount * selX;
	}
	
	public float getSat() {
		return 1.0f - 0.9f / (tileVCount - 1) * selY;
	}
	
	public void setHue(float h) {
		int tileX = (int)(tileHCount / 360f * h);
		tileX = tileX >= tileHCount? tileHCount - 1: tileX;
		setSelectedTile(tileX, selY);
	}
	
	public void setSat(float s) {
		if(s < 0.1f)
			s = 0.1f;
		int tileY = (int)((1 - s) / 0.9f * (tileVCount - 1)); 
		tileY = tileY >= tileVCount? tileVCount - 1: tileY;
		setSelectedTile(selX, tileY);
	}
	
	public void setHueSat(float h, float s) {
		int tileX = (int)(tileHCount / 360f * h);
		int tileY = (int)((1 - s) / 0.9f * (tileVCount - 1)); 
		tileX = tileX >= tileHCount? tileHCount - 1: tileX;		
		tileY = tileY >= tileVCount? tileVCount - 1: tileY;
		setSelectedTile(tileX, tileY);		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			int x = (int) event.getX();
			int y = (int) event.getY();
			int tileX = (int)((x - MARGIN) / (tileWidth + TILE_GAP));
			int tileY = (int)((y - MARGIN) / (tileHeight + TILE_GAP));			
			if(tileX < 0 || tileX >= tileHCount
					|| tileY < 0 || tileY >= tileVCount)
				break;
			
			setSelectedTile(tileX, tileY);
			break;

		case MotionEvent.ACTION_UP:
			break;

		default:
			break;		
		}
		return true;

	}
}
