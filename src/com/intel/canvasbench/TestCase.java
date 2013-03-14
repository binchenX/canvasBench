package com.intel.canvasbench;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public  abstract class TestCase extends Activity implements HumbleView.DrawListener{
	
	
	
	private TestThread mTestThread;
	
	//private HumbleView testTargetView;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//Log.d(MainActivity.TAG ,"setup " + getTestName());
		
		setup();
		
		getTestTargetView().setDrawListener(this);
		
		mTestThread = new TestThread();
		
	}
	
	
	
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		mTestThread.start();
	}
	
	

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}




	@Override
	protected void onPause() {
		
		super.onPause();
		
	}




	@Override
	protected void onStop() {
		
		super.onStop();
	}


	abstract void setup();
	
	/**
	 * 
	 * @param index
	 * @return the time (ms) it take to draw this frame
	 */
	abstract long drawOneFrame(int index);
	
	abstract void finishTest();
	
	abstract int getTestTag();
	
	abstract HumbleView getTestTargetView();
	
	@Override
	public void notify(long time) {
		synchronized(this){
			Log.d(TestManagerActivity.TAG,"take " + time + " ms to one draw");
			//mTime = time;
			//notifyAll();
		}
		
	}
	
	
	void finishTestcase()
	{
		
		finishTest();
		 
		 Intent returnIntent = new Intent();
		 returnIntent.putExtra("result",0);
		 setResult(getTestTag(),returnIntent);     
		 finish();
		
	}
	
	class TestThread extends Thread{

		@Override
		public void run() {
			
			Log.d(TestManagerActivity.TAG,"TestThread start");
			
			for (int i = 0 ; i < 60 ; i++){
				
				try{
					Thread.sleep(1000);
				}catch(InterruptedException ex){}
				
				long t = drawOneFrame(i);
				Log.d(TestManagerActivity.TAG, "take " + t + " ms to draw one frame" );
			}
			
			finishTestcase();
			
			
			
		}
		
		
	}

}
