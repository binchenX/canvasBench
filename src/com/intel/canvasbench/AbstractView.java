package com.intel.canvasbench;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * HumbleView keeping reporting how long it take to draw one frame.
 * 
 * @author binchen1
 * 
 */

public abstract class AbstractView extends View {

	private DrawListener mDrawListener;

	private int mMode = -1;
	public static final int CONTINOUSE_MODE = 0;
	public static final int ON_REQUEST_MODE = 1;

	private boolean isTestFinish = false;
	
	private int mFrameNum = 0;
	private Paint mTextPaint = new Paint();

	public AbstractView(Context context) {
		super(context);
		//onInit();
		init();
		
	}

	public AbstractView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public AbstractView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//onInit();
		init();
	}
	
	
	private void init(){
		
		mTextPaint.setColor(Color.RED);
		mTextPaint.setStyle(Style.FILL);
		mTextPaint.setTextSize(100);
	}

	public void setDrawListener(DrawListener l) {

		mDrawListener = l;
	}

	public void setDrawMode(int mode) {

		mMode = mode;
	}

	// this will called in main thread,so no sync needed with

	private boolean testFinish() {
		return isTestFinish;
	}

	// this will called in main thread, so no sync needed with
	public void setTestFinish() {
		isTestFinish = true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// long before = System.currentTimeMillis();
		long before = SystemClock.uptimeMillis();
		//Trace.traceBegin(Trace.TRACE_TAG_VIEW, "doDraw");
		// Debug.startMethodTracing("calc")
		
		//android.os.Debug.startMethodTracing();
		
		doDraw(canvas);
		//Trace.traceEnd(Trace.TRACE_TAG_VIEW, "doDraw");
		
		
		boolean isHw = canvas.isHardwareAccelerated();
		
		String canvasType = isHw ? "HW":"SW";
		//draw frame number
		
		canvas.drawText(mFrameNum+"", 500, 1000, mTextPaint);
		canvas.drawText(canvasType, 500, 900, mTextPaint);
		mFrameNum++;
		long after = SystemClock.uptimeMillis();
		
		if(mDrawListener == null){
			Toast.makeText(this.getContext(),"ERROR:missing listener", Toast.LENGTH_LONG).show();
			return;
		}

		if (mDrawListener != null) {
			mDrawListener.notify(after - before);
		}

		if ((mMode == CONTINOUSE_MODE) && !testFinish()) {
			invalidate();
		}
	}

	abstract void doDraw(Canvas canvas);

	//abstract void onInit();

	public interface DrawListener {

		void notify(long time);
	}

}
