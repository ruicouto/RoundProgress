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
	private int backColor;
	private int fillColor;
	
	private int progress;
	private RectF rectF;
	
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
		rectF = new RectF(5, 5, getWidth()-5, getHeight()-5);
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
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		rectF = new RectF(5, 5, getWidth()-5, getHeight()-5);
	}
		
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		paint.setColor(color.white);
		canvas.drawPaint(paint);
		paint.setAntiAlias(true);
		
		
		paint.setStyle(Style.STROKE);
		
		paint.setColor(backColor);
		paint.setStrokeWidth(10);
		canvas.drawOval(rectF, paint);
		
		paint.setStrokeWidth(11);
		paint.setColor(fillColor);
		
		canvas.drawArc (rectF, 270, (progress * 365)/100, false, paint);
		
	}

	

	
	

}
