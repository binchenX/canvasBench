package com.intel.canvasbench;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

public class BenchImageView extends HumbleView{

	private Bitmap mBitmap = null;
	private int index = 0;
	
	
	public void setBitmap(Bitmap bitmap){
		mBitmap = bitmap;
		
	}

	public BenchImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}



	public BenchImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}



	public BenchImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	@Override
	void doDraw(Canvas canvas) {
		
		Log.d(MainActivity.TAG , "draw one frame...");
		canvas.drawBitmap(mBitmap, 10.0f + index*5, 10.0f, null);
		index++;
	}
	
	

}
