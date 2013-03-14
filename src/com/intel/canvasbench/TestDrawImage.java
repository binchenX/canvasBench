package com.intel.canvasbench;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.util.Log;

public class TestDrawImage extends AbstractTestCase {

	MyImageView mImageView;
	
	//long mTime = 0;

	private Bitmap mBitmap;
	
	@Override
	void onSetup() {
		
		
		setContentView(R.layout.image);
		
		mImageView = (MyImageView)findViewById(R.id.image);
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.harborb);
		mImageView.setBitmap(getResources(),mBitmap);
		
	}

	@Override
	long onDrawOneFrame(int index) {
	
		
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
	void onFinishTest() {
		
		mBitmap.recycle();
		
	}

	@Override
	int getTestTag() {
		
		return ManagerActivity.TAG_TEST_DRAW_IMAGE;
	}

	@Override
	AstractView getTestTargetView() {
		
		return mImageView;
	}

	@Override
	void onStartDraw() {
		
		mImageView.invalidate();
	}
	

		

}
