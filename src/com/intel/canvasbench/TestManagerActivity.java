package com.intel.canvasbench;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TestManagerActivity extends Activity {

	
	public static String TAG = "CanvasBench"; 
	private TextView mTextView = null;
	
	
	public static final int TAG_TEST_IMAGE = 0;
	public static final int TAG_TEST_TEXT = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mTextView = (TextView)findViewById(R.id.textInfo);
		
	}
	void startTestcaseImageView(){
		
		Intent intent = new Intent(this,TestDrawImage.class);
		startActivityForResult(intent,TAG_TEST_IMAGE);
		
	}
	
	void startTestcaseDrawText(){
		
		Intent intent = new Intent(this,TestDrawText.class);
		startActivityForResult(intent,TAG_TEST_TEXT);
		
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
			
			case R.id.m_test_draw_image:
				Log.d(TAG,"start image view test case");
				startTestcaseImageView();
				break;
			case R.id.m_test_draw_text:
				Log.d(TAG,"start image view test case");
				startTestcaseDrawText();
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
		
		Log.d(TAG,"testcase finished" + resultCode);
		switch(resultCode){
		case TAG_TEST_IMAGE:
			mTextView.setText("testImage: 30fps");
			break;
		}
		
		
		
	}
	
	
	

}
