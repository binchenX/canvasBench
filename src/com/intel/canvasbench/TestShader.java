package com.intel.canvasbench;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
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
	AbstractView getTestTargetView() {
		// TODO Auto-generated method stub
		return mTestView;
	}

	private static class SampleView extends AbstractView {
		private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		private float mRotate;
		private Matrix mMatrix = new Matrix();
		private Shader mShader;
		private boolean mDoTiming;
		
		private Paint redPaint, greenPaint, bluePaint, alphaPaint;
		private Bitmap redChanImg, greenChanImg,blueChanImg,alphaImg;

		public SampleView(Context context) {
			super(context);
			setFocusable(true);
			setFocusableInTouchMode(true);

			float x = 160;
			float y = 100;
			mShader = new SweepGradient(x, y, new int[] { Color.GREEN,
					Color.RED, Color.BLUE, Color.GREEN }, null);
			mPaint.setShader(mShader);
			
			
			redChanImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.r);
			greenChanImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.g);
			blueChanImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.b);
			alphaImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.alpha);
			
			redPaint = new Paint();
			redPaint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
			redPaint.setShader(new BitmapShader(redChanImg, TileMode.CLAMP,
					TileMode.CLAMP));
			redPaint.setColorFilter(new PorterDuffColorFilter(Color.RED,
					Mode.DARKEN));

			greenPaint = new Paint();
			greenPaint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
			greenPaint.setShader(new BitmapShader(greenChanImg, TileMode.CLAMP,
					TileMode.CLAMP));
			greenPaint.setColorFilter(new PorterDuffColorFilter(Color.GREEN,
					Mode.DARKEN));

			bluePaint = new Paint();
			bluePaint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
			bluePaint.setShader(new BitmapShader(blueChanImg, TileMode.CLAMP,
					TileMode.CLAMP));
			bluePaint.setColorFilter(new PorterDuffColorFilter(Color.BLUE,
					Mode.DARKEN));

			alphaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			alphaPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			
			
			
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

			
			combineChanels(canvas);
		}
		
		
		void combineChanels(Canvas c){

		
			int width = 320;
			int height = 240;
			int top = 10;
			int left = 10;

			//fix me?
			//c.setBitmap(resultImage);
			c.drawRect(top,left , width, height, redPaint);
			c.drawRect(top,left , width, height, greenPaint);
			c.drawRect(top,left , width, height, bluePaint);
		//	c.drawBitmap(alphaImg, 0, 0, alphaPaint);

		}
	}

}
