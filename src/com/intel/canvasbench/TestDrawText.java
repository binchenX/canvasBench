package com.intel.canvasbench;

public class TestDrawText  extends AbstractTestCase implements AstractView.DrawListener{

	
	private MyTextView mTextView = null;
	
	
	@Override
	void onSetup() {
		
		setContentView(R.layout.layout_text);
		mTextView = (MyTextView)findViewById(R.id.text);
		
	}

	@Override
	long onDrawOneFrame(int index) {
		
		mTextView.postInvalidate();
		return 0;
	}

	@Override
	void onFinishTest() {
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
