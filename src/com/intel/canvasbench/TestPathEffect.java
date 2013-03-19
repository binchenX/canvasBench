package com.intel.canvasbench;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class TestPathEffect extends AbstractTestCase {

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
		private Paint mPaint;
		private Path mPath;
		private PathEffect[] mEffects;
		private int[] mColors;
		private float mPhase;

		private static final int LINE_HEIGHT = 50;
		private static final int PATH_INTEVAL = 20;
//		private static PathEffect makeDash(float phase) {
//			return new DashPathEffect(new float[] { 15, 5, 8, 5 }, phase);
//		}

		private static void makeEffects(PathEffect[] e, float phase) {
			e[0] = null; // no effect
			e[1] = new CornerPathEffect(10);
			e[2] = new DashPathEffect(new float[] { 10, 5, 5, 5 }, phase);
			e[3] = new PathDashPathEffect(makePathDash(), 12, phase,
					PathDashPathEffect.Style.ROTATE);
			e[4] = new ComposePathEffect(e[2], e[1]);
			e[5] = new ComposePathEffect(e[3], e[1]);
		}

		public SampleView(Context context) {
			super(context);
			setFocusable(true);
			setFocusableInTouchMode(true);

			mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setStrokeWidth(6);

			mPath = makeFollowPath();

			mEffects = new PathEffect[6];

			mColors = new int[] { Color.BLACK, Color.RED, Color.BLUE,
					Color.GREEN, Color.MAGENTA, Color.BLACK };
			
			makeEffects(mEffects, mPhase);
		}



//		@Override
//		public boolean onKeyDown(int keyCode, KeyEvent event) {
//			switch (keyCode) {
//			case KeyEvent.KEYCODE_DPAD_CENTER:
//				mPath = makeFollowPath();
//				return true;
//			}
//			return super.onKeyDown(keyCode, event);
//		}

		private static Path makeFollowPath() {
			Path p = new Path();
			p.moveTo(0, 0);
			for (int i = 1; i <= 35; i++) {
				p.lineTo(i * PATH_INTEVAL, (float) Math.random() * LINE_HEIGHT);
			}
			return p;
		}

		private static Path makePathDash() {
			Path p = new Path();
			p.moveTo(4, 0);
			p.lineTo(0, -4);
			p.lineTo(8, -4);
			p.lineTo(12, 0);
			p.lineTo(8, 4);
			p.lineTo(0, 4);
			return p;
		}

		@Override
		void doDraw(Canvas canvas) {
			canvas.drawColor(Color.WHITE);

			RectF bounds = new RectF();
			mPath.computeBounds(bounds, false);
			canvas.translate(10 - bounds.left, 10 - bounds.top);
			mPhase += 1;
			canvas.translate(mPhase % 100, 0);
			for (int i = 0; i < mEffects.length; i++) {
				mPaint.setPathEffect(mEffects[i]);
				mPaint.setColor(mColors[i]);
				canvas.drawPath(mPath, mPaint);
				canvas.translate(0, LINE_HEIGHT);
			}
			

		}
	}

}