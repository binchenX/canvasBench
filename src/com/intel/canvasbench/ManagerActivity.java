package com.intel.canvasbench;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


/**
 * 
 * 
 * 2 MODE support:
 * TIMER_MODE    : draw as fast as possbile for 10 seconds
 * FIXFRMAE_MODE : draw 60 frame with 500ms interval
 * 
 * TEST case support:
 * drawImage     : draw image with scale using HWUI
 * drawImageSW   : draw image with scale using SW
 * drawText      : draw text using HWUI
 * drawTextSW    : draw text using SW
 * drawPathEffect: draw Path with effect
 * drawShader    : draw using various shaders, combining with colorFilter and xferMode  - (SW) 
 * drawXferMode  : draw all xfer mode
 * 
 * 
 * 
 *
 * @author binchen1
 *
 */

public class ManagerActivity extends Activity {

	public static String TAG = "CanvasBench";
	private TextView mTextView = null;

	// private boolean[] testIndicator =
	
	private boolean mSingleMode = false;

	class TestCase {

		TestCase(int tag, Class<?> clz, String name) {
			mTag = tag;
			mClz = clz;
			mName = name;

		}

		public int mTag;
		public Class<?> mClz;
		public String mName;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.textInfo);

		initTestcase();
	}

	void startTestcaseImageView() {

		Intent intent = new Intent(this, TestDrawImage.class);
		startActivityForResult(intent, TAG_TEST_DRAW_IMAGE);

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.m_test_draw_image:
			mSingleMode = false;
			Log.d(TAG, "start image view test case");
			// startTestcaseImageView();
			kickOffTest();
			break;
		case R.id.m_test_draw_text:
			mSingleMode = true;
			Log.d(TAG, "start image view test case");
			startTestByTag(TAG_TEST_DRAW_PATH_EFFECT);
			break;
		default:
			break;
		}

		return true;
	}

	private StringBuilder testResult = new StringBuilder();

	void saveResult(String msg) {

		testResult.append(msg).append("\n");

	}

	void displayResult() {

		mTextView.setText(testResult.toString());

	}

	/* test managerment */

	public static final int TAG_TEST_DRAW_IMAGE = 0;
	public static final int TAG_TEST_DRAW_IMAGE_SW = 1;
	public static final int TAG_TEST_DRAW_TEXT = 2;
	public static final int TAG_TEST_DRAW_TEXT_SW = 3;
	public static final int TAG_TEST_DRAW_PATH_EFFECT = 4;
	public static final int TAG_TEST_SHADER = 5;
	public static final int TAG_TEST_XFERMODE = 6;
	
	
	
	public static final int TAG_END = 10;

	public String getTestName(int tag) {
		for (TestCase tc : testTarget) {
			if (tag == tc.mTag) {
				return tc.mName;
			}
		}

		return "unknown tc";

	}
	
	TestCase findTestByTag(int tag){
		
		for (TestCase tc : testTarget) {
			if (tag == tc.mTag) {
				return tc;
			}
		}

		return null;
		
		
	}

	private List<TestCase> testTarget = new ArrayList<TestCase>();
	private Iterator<TestCase> mTestIterator = null;

	void initTestcase() {

		testTarget.add(new TestCase(TAG_TEST_DRAW_IMAGE, TestDrawImage.class,"drawImage"));
		testTarget.add(new TestCase(TAG_TEST_DRAW_IMAGE_SW,TestDrawImageSW.class, "drawImageSW"));
		testTarget.add(new TestCase(TAG_TEST_DRAW_TEXT, TestDrawText.class,"drawText"));
		testTarget.add(new TestCase(TAG_TEST_DRAW_TEXT_SW,TestDrawTextSW.class, "drawTextSW"));
		testTarget.add(new TestCase(TAG_TEST_DRAW_PATH_EFFECT,TestPathEffect.class,"drawPathEffect"));
		testTarget.add(new TestCase(TAG_TEST_SHADER,TestShader.class,"drawShader"));
		testTarget.add(new TestCase(TAG_TEST_XFERMODE,TestXferMode.class,"drawXferMode"));
		
		
		mTestIterator = testTarget.iterator();

	}

	void kickOffTest() {

		if (mTestIterator.hasNext()) {
			// start next
			TestCase clz = mTestIterator.next();
			startTest(clz);
		}
	}
	
	

	void startTest(TestCase tc) {

		Intent intent = new Intent(this, tc.mClz);
		startActivityForResult(intent, tc.mTag);

	}
	
	boolean startTestByTag(int tag ) {
		TestCase tc = findTestByTag(tag);
		if(tc != null){
			startTest(tc);
			return true;
		}
		
		return false;

	}

	boolean noMoreTestcase() {

		return true;
	}

	/**
	 * 
	 * @param testCase
	 * @param times
	 */

	private void onReceiveTestResult(int testCase, long[] times) {

		String msg = getTestName(testCase) + " average  :" + Utility.fps(times)
				+ " fps ";

		saveResult(msg);
		
		if(mSingleMode){
			
			displayResult();
			return;
		}

		if (mTestIterator.hasNext()) {
			// start next
			TestCase tc = mTestIterator.next();
			startTest(tc);

		} else {
			displayResult();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Log.d(TAG, "test case finished" + resultCode);

		long times[] = (long[]) data.getLongArrayExtra("times");

		onReceiveTestResult(requestCode, times);

	}

}
