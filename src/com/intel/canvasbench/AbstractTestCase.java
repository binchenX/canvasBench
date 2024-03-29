package com.intel.canvasbench;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;



/**
 * 
 * All test should inherit from TestCase
 * 
 * @author binchen1
 *
 */

public  abstract class AbstractTestCase extends Activity implements AbstractView.DrawListener{
	
	private TestThread mTestThread;
	
	//1. Timer mode : start, run 20 seconds, invalidate after each draw, stop
	//2. Fix Frame Mode : start an seperted thread, draw fixed frame every 1 seconds
	
	protected static final int TIMER_MODE = 0; 
	protected static final int FIXFRAME_MODE = 1; 
	
	private int getTestMode(){
		return FIXFRAME_MODE;
	};
	
	
	private static final long TEST_LASTING_TIME = 5000; //20s
	
	
	private final static long DRAW_FRAME_NUMER = 60; 
	private final static long SLEEP_FRAME_INTERVAL = 500; 
	
	
	private List<Long>testResult = new ArrayList<Long>();
	
	private Handler mHandler = new Handler();
	
	private long mFps = 0;
	Context mContext = null;
	
	
	
	
	private boolean isTimerMode(){
		return getTestMode()==TIMER_MODE;
	}
	
	
	
	
	
	@Override
	public void onBackPressed() {
		//disable back key - we always have to finish the test case
		
		//TODO: make the case cancel-able
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//Log.d(ManagerActivity.TAG ,"setup " + ManagerActivity.getTestName(getTestTag()));
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
		//override on 
		onSetup();
		
		getTestTargetView().setDrawListener(this);
		
		if(!isTimerMode()){
			mTestThread = new TestThread();
		}
		
		Context mContext = this;
	}
	
	
	
	private void stopTest(){
		
		//ask test case to stop
		Log.d(ManagerActivity.TAG,"please stop test");
		getTestTargetView().setTestFinish();
		
		//clean up
		finishTestcase();
	}
	
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		getTestTargetView().setDrawMode(isTimerMode()?AbstractView.CONTINOUSE_MODE:AbstractView.ON_REQUEST_MODE);
		
		if(isTimerMode()){
			onStartDraw();
			
			//setup an timer
			mHandler.postDelayed(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					stopTest();
					
				}
				
				
			}, TEST_LASTING_TIME);
		}else{
			mTestThread.start();
		}
	}
	
	

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}




	@Override
	protected void onPause() {
		
		super.onPause();
		//stop the test case
		if(!isTimerMode()){
			mTestThread.exit();
		}
		
	}




	@Override
	protected void onStop() {
		
		super.onStop();
	}


	abstract void onSetup();
	
	/**
	 * 
	 * trigger one single draw, called in main thread
	 */
	abstract void onStartDraw();
	
	/**
	 * 
	 * @param index
	 * @return the time (ms) it take to draw this frame
	 */
	abstract long onDrawOneFrame(int index);
	
	abstract void onFinishTest();
	
	abstract int getTestTag();
	
	abstract AbstractView getTestTargetView();
	
	@Override
	public void notify(long time) {
		synchronized(this){
			testResult.add(Long.valueOf(time));
			Log.d(ManagerActivity.TAG,"case " + getTestTag() + "take " + time + " ms to draw oneFrame");
			//mTime = time;
			//notifyAll();
		}
		
	}
	
	
	void finishTestcase(){
		
		 //override in subclass
		 onFinishTest();
		 
		 //return the result to Manager
		 Intent returnIntent = new Intent();
		 //returnIntent.putExtra("result",0);
		 
		 long[] times = new long[testResult.size()]; 
		 int i = 0;
		 for (Long t : testResult){
			 times[i++] = t.longValue();
		 }
		 
		 mFps =  Utility.fps(times);
		 
		 
		 returnIntent.putExtra("times", times);
		 setResult(0/*result_OK*/,returnIntent);     
		
		 
		 finish();
		
	}
	
	class TestThread extends Thread{

		private boolean mExit = false;
		public  synchronized void exit(){
			mExit = true;
			
		}
		
		@Override
		public void run() {
			
			Log.d(ManagerActivity.TAG,"TestThread start");
			
			for (int i = 0 ; i < DRAW_FRAME_NUMER ; i++){
				synchronized(this){
				if(mExit){
					finishTestcase();
					return;
				}
				}
				
				try{
					Thread.sleep(SLEEP_FRAME_INTERVAL);
				}catch(InterruptedException ex){}
				
				onDrawOneFrame(i);
			
			}

			finishTestcase();
		}
		
		
	}

}
