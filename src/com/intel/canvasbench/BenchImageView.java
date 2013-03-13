package com.intel.canvasbench;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class BenchImageView extends View{

	private Bitmap mBitmap = null;
	
	
	public void setBitmap(Bitmap bitmap){
		mBitmap = bitmap;
		
	}
	
	public BenchImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	public BenchImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public BenchImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawBitmap(mBitmap, 10.0f, 10.0f, null);
	}
	
	

}
