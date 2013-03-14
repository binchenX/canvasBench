package com.intel.canvasbench;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;

public class MyImageView extends AstractView{

	//private Bitmap mBitmap = null;
	private BitmapDrawable d = null;
	private int index = 0;
	float mScale = 0.1f;
	int mTop = 10;
	int mLeft = 10;
	int mOrigWidth = 0;
	int mOrigHeight = 0;
	
	public void setBitmap(Bitmap bitmap){
		//mBitmap = bitmap;
		
		
	}
	
	public void setBitmap(Resources res, Bitmap bitmap){
		//mBitmap = bitmap;
		mOrigWidth = bitmap.getWidth();
		mOrigHeight = bitmap.getHeight();
		d = new BitmapDrawable(res,bitmap);
		//d.setBounds(10, 100, 500, 600);
	}
	


	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}



	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}



	public MyImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	@Override
	void doDraw(Canvas canvas) {
		
		Log.d(TestManagerActivity.TAG , "draw one frame..." + index + " is hwa? " + canvas.isHardwareAccelerated());
		
		//for (int i = 0 ; i < 100;i++){
		//canvas.drawBitmap(mBitmap, 10.0f + index*5, 10.0f, null);
		//}
		
		d.draw(canvas);
		d.setBounds(mTop, mLeft, (int)(mTop + mOrigHeight*mScale),(int)( mLeft + mOrigWidth*mScale));
		
		if(index > 30){
			mScale-=0.01f;
		}else{
			mScale+=0.01f;	
		}
		index++;
	}
	
	

}
