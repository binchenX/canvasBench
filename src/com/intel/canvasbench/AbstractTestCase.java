package com.intel.canvasbench;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


/**
 * 
 * All test should inherit from TestCase
 * 
 * @author binchen1
 *
 */

public  abstract class AbstractTestCase extends Activity implements AstractView.DrawListener{
	
	private TestThread mTestThread;
	
	
	private List<Long>testResult = new ArrayList<Long>();
	
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
	
	abstract AstractView getTestTargetView();
	
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
		 finishTest();
		 
		 //return the result to Manager
		 Intent returnIntent = new Intent();
		 //returnIntent.putExtra("result",0);
		 
		 long[] times = new long[testResult.size()]; 
		 int i = 0;
		 for (Long t : testResult){
			 times[i++] = t.longValue();
		 }
		 returnIntent.putExtra("times", times);
		 setResult(0/*result_OK*/,returnIntent);     
		
		 finish();
		
	}
	
	class TestThread extends Thread{

		@Override
		public void run() {
			
			Log.d(ManagerActivity.TAG,"TestThread start");
			
			for (int i = 0 ; i < 60 ; i++){
				
				try{
					Thread.sleep(1000);
				}catch(InterruptedException ex){}
				
				drawOneFrame(i);
				//Log.d(ManagerActivity.TAG, "take " + t + " ms to draw one frame" );
			}
			
			finishTestcase();
		}
		
		
	}

}
