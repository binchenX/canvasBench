package com.intel.canvasbench;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;


/**
 * HumbleView keeping reporting how long it take to draw one frame.
 * 
 * @author binchen1
 *
 */

public abstract class AstractView extends View {
	
	private DrawListener mDrawListener;
	
	private int mMode = -1; 
	public static final int CONTINOUSE_MODE = 0;
	public static final int ON_REQUEST_MODE = 1;
	
	private boolean isTestFinish = false;

	public AstractView(Context context) {
		super(context);
		onInit();
	}

	public AstractView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		onInit();
	}

	public AstractView(Context context, AttributeSet attrs) {
		super(context, attrs);
		onInit();
	}
	
	
	public void setDrawListener(DrawListener l){
		
		mDrawListener = l;
	}
	
	public void setDrawMode(int mode){
		
		mMode = mode;
	}
	
	//this will called in main thread,so no sync needed with
	
	private boolean testFinish(){
		return isTestFinish;
	}
	
	//this will called in main thread, so no sync needed with 
	public void setTestFinish(){
		isTestFinish = true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//long before = System.currentTimeMillis();
		long before =  SystemClock.uptimeMillis();
		doDraw(canvas);
		long after =  SystemClock.uptimeMillis();
		
		if(mDrawListener!=null){
			mDrawListener.notify(after-before);
		}
		
		if((mMode == CONTINOUSE_MODE) && !testFinish()){
			invalidate();
		}
	}

	
   abstract void doDraw(Canvas canvas);
   abstract void onInit();

	public interface DrawListener{
		
		void notify(long time);
	}
	

}
