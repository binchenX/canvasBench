package com.intel.canvasbench;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;


/**
 * HumbleView keeping reporting how long it take to draw one frame.
 * 
 * @author binchen1
 *
 */

public abstract class HumbleView extends View {
	
	private DrawListener mDrawListener;

	public HumbleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public HumbleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public HumbleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	
	public void setDrawListener(DrawListener l){
		
		mDrawListener = l;
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		long b = System.currentTimeMillis();
		doDraw(canvas);
		long a = System.currentTimeMillis();
		
		if(mDrawListener!=null){
			mDrawListener.notify(b-a);
		}
	}

	
   abstract void doDraw(Canvas canvas);

	public interface DrawListener{
		
		void notify(long time);
	}
	

}
