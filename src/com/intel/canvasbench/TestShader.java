package com.intel.canvasbench;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.view.KeyEvent;
import android.view.View;

public class TestShader extends AbstractTestCase {

	private SampleView mTestView = null;

	@Override
	void onSetup() {
		// TODO Auto-generated method stub
		mTestView = new SampleView(this);
		setContentView(mTestView);

	}

	@Override
	void onStartDraw() {
		// TODO Auto-generated method stub
		mTestView.invalidate();

	}

	@Override
	long onDrawOneFrame(int index) {
		// TODO Auto-generated method stub
		mTestView.postInvalidate();
		return 0;
	}

	@Override
	void onFinishTest() {
		// nothing

	}

	@Override
	int getTestTag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	AstractView getTestTargetView() {
		// TODO Auto-generated method stub
		return mTestView;
	}

	private static class SampleView extends AstractView {
		private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		private float mRotate;
		private Matrix mMatrix = new Matrix();
		private Shader mShader;
		private boolean mDoTiming;

		public SampleView(Context context) {
			super(context);
			setFocusable(true);
			setFocusableInTouchMode(true);

			float x = 160;
			float y = 100;
			mShader = new SweepGradient(x, y, new int[] { Color.GREEN,
					Color.RED, Color.BLUE, Color.GREEN }, null);
			mPaint.setShader(mShader);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_D:
				mPaint.setDither(!mPaint.isDither());
				invalidate();
				return true;
			case KeyEvent.KEYCODE_T:
				mDoTiming = !mDoTiming;
				invalidate();
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}

		@Override
		void doDraw(Canvas canvas) {

			Paint paint = mPaint;
			float x = 160;
			float y = 100;
			
			canvas.drawColor(Color.WHITE);

			for (int i = 0; i < 3; i++) {
							mMatrix.setRotate(mRotate, x, y);
				mShader.setLocalMatrix(mMatrix);
				mRotate += 3;
				if (mRotate >= 360) {
					mRotate = 0;
				}
				canvas.drawCircle(x, y, 80, paint);
				canvas.translate(0, 160);
			}

		}
	}

}
