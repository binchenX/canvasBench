package com.intel.canvasbench;

public class TestDrawText  extends TestCase implements HumbleView.DrawListener{

	
	private BenchDrawText mTextView = null;
	
	@Override
	void setup() {
		
		setContentView(R.layout.layout_text);
		mTextView = (BenchDrawText)findViewById(R.id.text);
		
	}

	@Override
	long drawOneFrame(int index) {
		
		mTextView.postInvalidate();
		return 0;
	}

	@Override
	void finishTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	int getTestTag() {
		return TestManagerActivity.TAG_TEST_TEXT;
		
	}

	@Override
	HumbleView getTestTargetView() {
		
		return mTextView;
		
	}

}
