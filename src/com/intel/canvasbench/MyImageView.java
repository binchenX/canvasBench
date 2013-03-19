package com.intel.canvasbench;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;


public class MyImageView extends AbstractView {

	// private Bitmap mBitmap = null;
	private BitmapDrawable d = null;

	float mScale = 0.2f;
	int mTop = 0;
	int mLeft = 0;
	int mOrigWidth = 0;
	int mOrigHeight = 0;

	boolean mUp = true;

	private Paint bgPaint;

	public void setBitmap(Bitmap bitmap) {
		// mBitmap = bitmap;

	}

	public void setBitmap(Resources res, Bitmap bitmap) {
		// mBitmap = bitmap;
		mOrigWidth = bitmap.getWidth();
		mOrigHeight = bitmap.getHeight();
		d = new BitmapDrawable(res, bitmap);
		// d.setBounds(10, 100, 500, 600);
	}

	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public MyImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	
	void init() {
		bgPaint = new Paint();
		bgPaint.setColor(Color.BLACK);
		bgPaint.setStyle(Paint.Style.FILL);

	}

	@Override
	void doDraw(Canvas canvas) {

		// background

		int height = getHeight();
		int width = getWidth();
		canvas.drawRect(0, 0, width, height, bgPaint);

		for (int row = 0; row < 7; row++) {
			int hSpace = 150;
			int vSpace = 200;

			for (int col = 0; col < 4; col++) {

				int top = mTop + row * vSpace;
				int left = mLeft + col * hSpace;
				int right = left + (int) (mOrigWidth * mScale);
				int bottom = top + (int) (mOrigHeight * mScale);

				d.setBounds(left, top, right, bottom);
				d.draw(canvas);
				d.setAlpha(200);
			}

		}

		if (mScale <= 0.2f) {
			mUp = true;
		}

		if (mScale >= 0.8f) {
			mUp = false;
		}

		if (mUp) {
			mScale += 0.1;
		} else {
			mScale -= 0.1;
		}

	}

}
