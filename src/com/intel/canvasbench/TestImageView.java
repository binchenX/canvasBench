package com.intel.canvasbench;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class TestImageView extends TestCase {

	BenchImageView mImageView;
	
	
	@Override
	void setup() {
		
		Log.d(MainActivity.TAG ,"setup TestImageView");
		setContentView(R.layout.image);
		
		mImageView = (BenchImageView)findViewById(R.id.image);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.harborb);
		mImageView.setBitmap(bitmap);
		
	}

	@Override
	long drawOneFrame(int index) {
	
		
		mImageView.postInvalidate();
		
		return 0;
		
	}
	
	
	

}
