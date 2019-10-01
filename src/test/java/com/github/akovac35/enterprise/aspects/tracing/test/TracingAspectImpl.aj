package com.github.akovac35.enterprise.aspects.tracing.test;

import com.github.akovac35.enterprise.aspects.common.ILogger;
import com.github.akovac35.enterprise.aspects.tracing.TracingAspect;

public aspect TracingAspectImpl extends TracingAspect pertypewithin(com.github.akovac35.enterprise.aspects.tracing.test..*) {

	public pointcut traceableCode(): 
		within(com.github.akovac35.enterprise.aspects.tracing.test..*) &&
		!within(com.github.akovac35.enterprise.aspects.tracing.test.TracingAspectImplTest+);
	
	@Override
	protected ILogger getLogger(String name) {
		return new TestLogger(name);
	}
}
