package com.intel.canvasbench;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class TestImageView extends TestCase implements HumbleView.DrawListener{

	BenchImageView mImageView;
	
	long mTime = 0;
	
	@Override
	void setup() {
		
		Log.d(MainActivity.TAG ,"setup TestImageView");
		setContentView(R.layout.image);
		
		mImageView = (BenchImageView)findViewById(R.id.image);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.harborb);
		mImageView.setBitmap(getResources(),bitmap);
	
		mImageView.setDrawListener(this);
		
	}

	@Override
	long drawOneFrame(int index) {
	
		
		mImageView.postInvalidate();
//
//		long time = 0;
//		//wait and notify
//		synchronized(this){
//			while(mTime != 0){
//				try {
//					wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			time = mTime;
//			mTime = 0;
//
//		}
//		
		return 0;
		
	}
	

	@Override
	public void notify(long time) {
		synchronized(this){
			Log.d(MainActivity.TAG,"take " + time + " ms to one draw");
			mTime = time;
			notifyAll();
		}
		
	}
	
		

}
