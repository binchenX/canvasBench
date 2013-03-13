package com.intel.canvasbench;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;

public class BenchImageView extends HumbleView{

	private Bitmap mBitmap = null;
	private BitmapDrawable d = null;
	private int index = 0;
	float mScale = 0.1f;
	int mTop = 10;
	int mLeft = 10;
	int mOrigWidth = 0;
	int mOrigHeight = 0;
	
	public void setBitmap(Bitmap bitmap){
		mBitmap = bitmap;
		
	}
	
	public void setBitmap(Resources res, Bitmap bitmap){
		mBitmap = bitmap;
		mOrigWidth = bitmap.getWidth();
		mOrigHeight = bitmap.getHeight();
		d = new BitmapDrawable(res,bitmap);
		//d.setBounds(10, 100, 500, 600);
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
		
		Log.d(MainActivity.TAG , "draw one frame..." + index);
		
		//for (int i = 0 ; i < 100;i++){
		//canvas.drawBitmap(mBitmap, 10.0f + index*5, 10.0f, null);
		//}
		
		d.draw(canvas);
		d.setBounds(mTop, mLeft, (int)(mTop + mOrigHeight*mScale),(int)( mLeft + mOrigWidth*mScale));
		
		if(index > 5){
			mScale-=0.05f;
		}else{
			mScale+=0.05f;	
		}
		index++;
	}
	
	

}
