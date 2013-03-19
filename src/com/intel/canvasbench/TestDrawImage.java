package com.intel.canvasbench;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TestDrawImage extends AbstractTestCase {

	MyImageView mImageView;
	private Bitmap mBitmap;

	@Override
	void onSetup() {

		setContentView(R.layout.image);

		mImageView = (MyImageView) findViewById(R.id.image);
		mBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.harborb);
		mImageView.setBitmap(getResources(), mBitmap);

	}

	@Override
	long onDrawOneFrame(int index) {

		mImageView.postInvalidate();
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
	AbstractView getTestTargetView() {

		return mImageView;
	}

	@Override
	void onStartDraw() {

		mImageView.invalidate();
	}

}
