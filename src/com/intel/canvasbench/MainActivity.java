package com.intel.canvasbench;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	
	public static String TAG = "CanvasBench"; 


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startTestcaseImageView();
		
	}
	void startTestcaseImageView(){
		
		Intent intent = new Intent(this,TestImageView.class);
		startActivityForResult(intent,0);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
			
			case R.id.test_image:
				Log.d(TAG,"start image view test case");
				startTestcaseImageView();
				break;
			default:
				break;
		}
		
		return true;
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	

}
