package com.intel.canvasbench;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class MyTextView extends AstractView {

	int index = 0;

	private final String[] mTestString = { "samyak-smrti", "mindfulless",
			"intel", "graphic", "身念住" };
	private int mSize = 0;

	public final int TIMES = 40;
	

    public final String TEXT1 = "0xbench";
    public final String TEXT2 = "0xlab";
    
	private Paint bgPaint;

	public MyTextView(Context context) {
		super(context);
		init();

	}

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();

	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	
	void init() {
		bgPaint = new Paint();
		bgPaint.setColor(Color.BLACK);
		bgPaint.setStyle(Paint.Style.FILL);

		mSize = mTestString.length;
	}

	@Override
	void doDraw(Canvas canvas) {

		//generateText2(canvas);
		
		generateText1(canvas);

		// keep running
		// invalidate();
	}

	//our Text
	private void generateText2(Canvas canvas) {
		Random mRandom = new Random();
		int height = getHeight();
		int width = getWidth();
		canvas.drawRect(0, 0, width, height, bgPaint);

		int cx;
		int cy;
		int color;
		for (int i = 0; i < TIMES; i++) {

			cx = (mRandom.nextInt((int) (width * 0.8)));
			cy = (mRandom.nextInt((int) (height * 0.8)));
			;

			color = (0x00555555 | mRandom.nextInt()) | Color.BLACK;
			Paint p = new Paint();
			p.setAntiAlias(true);
			p.setStyle(Paint.Style.FILL);
			p.setTextAlign(Paint.Align.LEFT);

			if (mRandom.nextInt() % 2 == 0)
				p.setFakeBoldText(true);

			if (mRandom.nextInt() % 2 == 0)
				p.setTextSkewX((float) -0.45);

			p.setColor(color);
			p.setTextSize(42 + mRandom.nextInt() % 28);

			canvas.drawText(mTestString[mRandom.nextInt(mSize)], cx, cy, p);

		}
	}
	
	//Text generate used in 0xbench so that we can compare the performance
	private void generateText1(Canvas canvas) {
        Random mRandom = new Random();
        int height = getHeight();
        int width  = getWidth();
        canvas.drawRect(0,0,width,height,bgPaint);
       
        int cx;
        int cy;
        int color; 
        for(int i=0; i<TIMES; i++) {
            cx = (int)((mRandom.nextInt() % (width*0.8) ) + (width*0.1));
            cy = (int)((mRandom.nextInt() % (height*0.8) ) + (height*0.1));

            color = (0x00555555 | mRandom.nextInt() ) | Color.BLACK; 
            Paint p = new Paint();
            p.setAntiAlias(true);
            p.setStyle(Paint.Style.FILL);
            p.setTextAlign(Paint.Align.CENTER);

            if(mRandom.nextInt()%2 == 0)
                p.setFakeBoldText(true);

            if(mRandom.nextInt()%2 == 0)
                p.setTextSkewX((float)-0.45);

            p.setColor(color);
            p.setTextSize(42 + (mRandom.nextInt()%28));

            if(mRandom.nextInt()%2 == 0)
                canvas.drawText(TEXT1, cx, cy, p);
            else
                canvas.drawText(TEXT2, cx, cy, p);
        }
    }

}
