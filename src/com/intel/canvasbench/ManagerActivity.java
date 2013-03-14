package com.intel.canvasbench;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ManagerActivity extends Activity {

	
	public static String TAG = "CanvasBench"; 
	private TextView mTextView = null;
	
	
	public static final int TAG_TEST_DRAW_IMAGE = 0;
	public static final int TAG_TEST_DRAW_TEXT = 1;
	public static final int TAG_END=10;
	
	private static String[] names = new String[TAG_END];
	
	static {
		
		names[TAG_TEST_DRAW_IMAGE] = "drawImage";
		names[TAG_TEST_DRAW_TEXT]  = "drawText";
		//names[TAG_TEST_DRAW_IMAGE] = "drawImage";
		//names[TAG_TEST_DRAW_IMAGE] = "drawImage";
		
		
	}
	
	public  static String getTestName(int tag){
		if(tag > TAG_END){
			return "unknow";
		}
		
		return names[tag];
		
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mTextView = (TextView)findViewById(R.id.textInfo);
		
	}
	
	void startTestcaseImageView(){
		
		Intent intent = new Intent(this,TestDrawImage.class);
		startActivityForResult(intent,TAG_TEST_DRAW_IMAGE);
		
	}
	
	void startTestcaseDrawText(){
		
		Intent intent = new Intent(this,TestDrawText.class);
		startActivityForResult(intent,TAG_TEST_DRAW_TEXT);
		
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
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.d(TAG,"test case finished" + resultCode);
//		switch(resultCode){
//		case TAG_TEST_DRAW_IMAGE:
//			mTextView.setText("testImage: 30fps");			
//			break;
//		case TAG_TEST_DRAW_TEXT:
//			mTextView.setText("testText: 30fps");
//			break;
//		}
		
		long times[] = (long[])data.getLongArrayExtra("times");
		
		//Calculate fps and print out each value
		long t = 0;
		for (int i = 0 ;i < times.length ;i++){
			t+=times[i];
		}
		long ave = t/times.length;
		
		mTextView.setText(getTestName(requestCode) + " average fps :" + 1000/ave + " fps ");
		
		
		
	}
	
	
	

}
