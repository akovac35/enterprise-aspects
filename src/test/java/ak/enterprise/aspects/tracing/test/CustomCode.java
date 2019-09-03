package ak.enterprise.aspects.tracing.test;

import ak.enterprise.aspects.tracing.DefaultStringifier;
import ak.enterprise.aspects.tracing.TracingHelper;

public class CustomCode {
	static {
		TracingHelper.finest("Hello from static initializer.");
	}
	
	public CustomCode() {
		TracingHelper.finest("Hello from constructor.");
	}
	
	public static CustomObject doSomethingStatic(String par1, int par2, CustomObject cobj) {
		TracingHelper.finest("Just having a good time with par1 static: " + DefaultStringifier.stringify(par1));
		
		return cobj;
	}
	
	public CustomObject doSomething(String par1, int par2, CustomObject cobj) {
		TracingHelper.finest("Just having a good time with par1: " + DefaultStringifier.stringify(par1));
		
		return cobj;
	}
	
	public void fineLevelWithObject() {
		TracingHelper.fine("Testing fine level.", new CustomObject());
	}
}
