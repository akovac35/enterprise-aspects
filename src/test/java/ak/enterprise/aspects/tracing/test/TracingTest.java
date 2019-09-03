package ak.enterprise.aspects.tracing.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TracingTest {

	@Test
	public void staticInitializorTest() {
		String key = null;
		String value = null;
		
		key = "entering ak.enterprise.aspects.tracing.test.CustomCode <clinit>";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("none".equals(value));
		
		key = "Hello from static initializer.";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("finest".equals(value));
		
		key = "exiting ak.enterprise.aspects.tracing.test.CustomCode <clinit>";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("none".equals(value));
	}

	@Test
	public void constructorTest() {
		CustomCode test = new CustomCode();
		
		String key = null;
		String value = null;
		
		key = "entering ak.enterprise.aspects.tracing.test.CustomCode <init>";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("none".equals(value));
		
		key = "Hello from constructor.";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("finest".equals(value));
		
		key = "exiting ak.enterprise.aspects.tracing.test.CustomCode <init>";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("none".equals(value));
	}

	@Test
	public void staticMethodCallTest() {
		CustomCode.doSomethingStatic("lorem", 0, null);
		
		String key = null;
		String value = null;
		
		key = "entering ak.enterprise.aspects.tracing.test.CustomCode doSomethingStatic";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("[\"lorem\",0,null]".equals(value));
		
		key = "Just having a good time with par1 static: \"lorem\"";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("finest".equals(value));
		
		key = "exiting ak.enterprise.aspects.tracing.test.CustomCode doSomethingStatic";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("null".equals(value));
	}
	
	@Test
	public void methodCallTest() {
		CustomCode test = new CustomCode();
		test.doSomething("lorem", 0, new CustomObject());
		
		String key = null;
		String value = null;
		
		key = "entering ak.enterprise.aspects.tracing.test.CustomCode doSomething";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("[\"lorem\",0,{\"firstName\":\"custom\",\"lastName\":\"object\"}]".equals(value));
		
		key = "Just having a good time with par1: \"lorem\"";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("finest".equals(value));
		
		key = "exiting ak.enterprise.aspects.tracing.test.CustomCode doSomething";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("{\"firstName\":\"custom\",\"lastName\":\"object\"}".equals(value));
	}
	
	@Test
	public void fineLevelWithObjectTest() {
		CustomCode test = new CustomCode();
		test.fineLevelWithObject();
		
		String key = null;
		String value = null;
		
		key = "Testing fine level. fine";
		value = TestLogger.getMessages().get(key);
		assertTrue(TestLogger.getMessages()
				.containsKey(key));
		assertTrue("{\"firstName\":\"custom\",\"lastName\":\"object\"}".equals(value));
	}
}
