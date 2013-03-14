package com.intel.canvasbench;

public class TestDrawText  extends AbstractTestCase implements AstractView.DrawListener{

	
	private MyTextView mTextView = null;
	
	@Override
	void setup() {
		
		setContentView(R.layout.layout_text);
		mTextView = (MyTextView)findViewById(R.id.text);
		
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
		return ManagerActivity.TAG_TEST_DRAW_TEXT;
		
	}

	@Override
	AstractView getTestTargetView() {
		
		return mTextView;
		
	}

}
