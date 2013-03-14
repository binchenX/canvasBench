package com.intel.canvasbench;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class BenchDrawText extends HumbleView{
	
	int index = 0;
	public final String TEXT1 = "Intel";
	public final String TEXT2 = "Graphic";
	public final int TIMES = 10;
	
	private Paint bgPaint;

	public BenchDrawText(Context context) {
		super(context);
		init();
	}
	
	

	public BenchDrawText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}



	public BenchDrawText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	
	private void init(){
		  bgPaint = new Paint();
	      bgPaint.setColor(Color.BLACK);
	      bgPaint.setStyle(Paint.Style.FILL);
	}


	@Override
	void doDraw(Canvas canvas) {
		
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
