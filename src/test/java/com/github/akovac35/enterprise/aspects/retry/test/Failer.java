package com.github.akovac35.enterprise.aspects.retry.test;

import com.github.akovac35.enterprise.aspects.retry.Retry;

public class Failer {
	protected int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@Retry(maxRetries = 3)
	public boolean fail() throws Exception {
		count++;
		if(count < 3) {
			throw new Exception("Failing as designed.");
		}
		
		return false;
	}
}
