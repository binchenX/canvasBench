package com.intel.canvasbench;

public class Utility {

	
	public static long fps(long[] times) {
		// Calculate fps and print out each value

		if (times.length == 0) {
			return 1;
		}

		long t = 0;
		for (int i = 0; i < times.length; i++) {
			t += times[i];
		}
		long ave = t / times.length;
		
		

		long fps = 1000;
		if (ave != 0) {
			fps = 1000 / ave;
		}
		
		return fps;
	}
	
	

	
}
