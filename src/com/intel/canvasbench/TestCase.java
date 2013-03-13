package com.intel.canvasbench;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public  abstract class TestCase extends Activity {
	
	
	
	private TestThread mTestThread;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setup();
		
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
	
	
	void finishTestcase()
	{
		 Intent returnIntent = new Intent();
		 returnIntent.putExtra("result",0);
		 setResult(RESULT_OK,returnIntent);     
		 finish();
		
	}
	
	class TestThread extends Thread{

		@Override
		public void run() {
			
			Log.d(MainActivity.TAG,"TestThread start");
			
			for (int i = 0 ; i < 20 ; i++){
				
				try{
					Thread.sleep(1000);
				}catch(InterruptedException ex){}
				
				drawOneFrame(i);
			}
			
			//finishTestcase();
			
		}
		
		
	}

}
