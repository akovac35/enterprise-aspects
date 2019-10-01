package com.github.akovac35.enterprise.aspects.retry.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FailerTest {
	@Test
	public void testFail(){
		Failer f = new Failer();
		try {
			f.fail();
			assertTrue(f.getCount() == 3);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
}
