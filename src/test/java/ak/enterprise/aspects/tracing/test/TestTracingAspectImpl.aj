package ak.enterprise.aspects.tracing.test;

import ak.enterprise.aspects.tracing.TracingAspect;

public aspect TestTracingAspectImpl extends TracingAspect  pertypewithin(ak.enterprise.aspects.tracing.test..*) {

	public pointcut loggableCode(): 
		within(ak.enterprise.aspects.tracing.test..*) &&
		!within(ak.enterprise.aspects.tracing.test.TracingTest+);
	
	@Override
	protected void setTypeLogger(String name) {
		l = new TestLogger(name);
	}
}
