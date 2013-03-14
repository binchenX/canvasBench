package com.intel.canvasbench;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

public class MyTextView extends AstractView{
	
	int index = 0;
	
	private final String[] mTestString = {"samyak-smrti","mindfulless","intel","graphic","身念住"};
	private int mSize =0 ;
	
	public final int TIMES = 40;
	
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

	
	private void init(){
		  bgPaint = new Paint();
	      bgPaint.setColor(Color.BLACK);
	      bgPaint.setStyle(Paint.Style.FILL);
	      
	      mSize = mTestString.length;
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
	        	
	        	
	            cx = (mRandom.nextInt((int) (width*0.8)));
	            cy = (mRandom.nextInt((int) (height*0.8)));;
	            
		        color = (0x00555555 | mRandom.nextInt() ) | Color.BLACK; 
	            Paint p = new Paint();
	            p.setAntiAlias(true);
	            p.setStyle(Paint.Style.FILL);
	            p.setTextAlign(Paint.Align.LEFT);

	            if(mRandom.nextInt()%2 == 0)
	                p.setFakeBoldText(true);

	            if(mRandom.nextInt()%2 == 0)
	                p.setTextSkewX((float)-0.45);

	            p.setColor(color);
	            p.setTextSize(42 + mRandom.nextInt() %28);

	            canvas.drawText(mTestString[mRandom.nextInt(mSize)], cx, cy, p);
	           
	        }
	}

}
