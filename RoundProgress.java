package com.rsmc.roundprogress;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Example of a custom view for android. 
 * @author ruicouto
 *
 */
public class RoundProgress extends View {
	private Paint paint;
	
	/** The back color */
	private int backColor;
	/** The front color */
	private int fillColor;
	
	/** The progress value (0 - 100) */
	private int progress;
	/** The drawing area */
	private RectF rectF;
	
	/** Bar thickness */
	private int thickness; 
	
	public RoundProgress(Context context) {
		super(context);
		initialize();
	}
	
	public RoundProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}
	
	public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize();
	}
	
	/**
	 * Private method to initialize default values.
	 */
	private void initialize() {
		paint = new Paint();
		backColor = Color.rgb(0, 153, 204);
		fillColor = Color.rgb(0,221,255);
		progress = 50;
		int ht = thickness/2;
		rectF = new RectF(ht, ht, getWidth()-ht, getHeight()-ht);
		thickness = 15;
	}
	
	/**
	 * Define the color for the placeholder progress (background)
	 * @param backColor
	 */
	public void setBackColor(int backColor) {
		this.backColor = backColor;
		invalidate();
	}
	
	/**
	 * Define the color for the filled progress (foreground)
	 * @param fillColor
	 */
	public void setFillColor(int fillColor) {
		this.fillColor = fillColor;
		invalidate();
	}
	
	/**
	 * Define the progress thickness.
	 * @param thickness
	 */
	public void setThickness(int thickness) {
		this.thickness = thickness;
		if(this.thickness>getWidth()/2) this.thickness=getWidth()/2;
		invalidate();
	}
	
	/**
	 * Get the progress value.
	 * @return 0 to 100 (%)
	 */
	public int getProgress() {
		return progress;
	}
	
	/**
	 * Get the bar thickness.
	 * @return
	 */
	public int getThickness() {
		return thickness;
	}
	
	/**
	 * Get current back color.
	 * @return
	 */
	public int getBackColor() {
		return backColor;
	}
	
	/**
	 * Get current fill color.
	 * @return
	 */
	public int getFillColor() {
		return fillColor;
	}
	
	/**
	 * Define the value for the progress bar
	 * @param progress
	 */
	public void setProgress(int progress) {
		this.progress = progress;
		if(this.progress>100) {
			this.progress = 100;
		} else if(this.progress<0) {
			this.progress = 0;
		}
		invalidate();
	}
	
	/**
	 * Update drawing area on resize.
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		int ht = thickness;
		ht = ht-ht/2+2;
		if(thickness%2!=0) ht-=1;
		rectF = new RectF(ht, ht, getWidth()-ht, getHeight()-ht);
	}
	
	/**
	 * When The view is draw.
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		paint.setColor(Color.TRANSPARENT);
		canvas.drawPaint(paint);
		paint.setAntiAlias(true);
			
		paint.setStyle(Style.STROKE);
		
		paint.setColor(backColor);
		paint.setStrokeWidth(thickness);
		canvas.drawOval(rectF, paint);
		
		paint.setStrokeWidth(thickness+1);
		paint.setColor(fillColor);
		
		canvas.drawArc (rectF, 270, (progress * 365)/100, false, paint);
	}

}
