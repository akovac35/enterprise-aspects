package com.github.akovac35.enterprise.aspects.tracing;

import com.github.akovac35.enterprise.aspects.common.DefaultLogger;
import com.github.akovac35.enterprise.aspects.common.DefaultStringifier;
import com.github.akovac35.enterprise.aspects.common.ILogger;

/**
 * The TracingAspect is designed for aspect extension based tracing concern
 * implementation. See project Wiki on how to use it: <a href=
 * "https://github.com/akovac35/enterprise-aspects/wiki/TracingAspect">TracingAspect</a>
 * 
 * @author Aleksander Kovač
 *
 */
public privileged abstract aspect TracingAspect {
	/**
	 * Logger instance will be specific to each advised class once this aspect is
	 * extended with "pertypewithin" aspect instantiation model.
	 */
	protected ILogger l;

	/**
	 * Override this method to use a custom logger.
	 * 
	 * @param name Class name, including package, for the logger instance. For
	 *             example: com.my.Class
	 */
	protected ILogger getLogger(String name) {
		return new DefaultLogger(name);
	}

	/**
	 * Convert an Object to its String representation.
	 * 
	 * @param o Object to be converted
	 * @return "null" if o is null, o as String otherwise
	 */
	protected String stringify(Object o) {
		return DefaultStringifier.stringify(o);
	}

	/**
	 * Extension point holding custom code pattern to which this aspect should be
	 * applied.
	 */
	protected abstract pointcut traceableCode();

	protected pointcut noTrace():
		!within(@NoTrace *) &&
		!execution(@NoTrace * *(..));

	protected pointcut commonCodeBase():		
		!execution(String *.toString()) &&
	    !execution(int *.hashCode()) &&
	    !execution(boolean *.canEqual(Object)) &&
	    !execution(boolean *.equals(Object)) &&
	    !execution(* *.getClasses()) &&
	    !execution(* *.getClass()) && 
	    !within(com.github.akovac35.enterprise.aspects.common.*) &&
	    !within(com.github.akovac35.enterprise.aspects.common.ILogger+) &&
	    !within(com.github.akovac35.enterprise.aspects.common.DefaultStringifier+) &&
	    !within(com.github.akovac35.enterprise.aspects.tracing.*) && 
	    !within(com.github.akovac35.enterprise.aspects.tracing.TracingHelper+) && 
	    !within(com.github.akovac35.enterprise.aspects.tracing.TracingAspect+) &&
	    noTrace();

	protected pointcut commonCode(): execution(* * (..)) &&
	commonCodeBase();

	protected pointcut commonVoidCode(): execution(void * (..)) &&
	commonCodeBase();

	protected pointcut commonNonVoidCode(): execution(!void * (..)) &&
	commonCodeBase();

	protected pointcut constructors(): execution(*.new(..)) && commonCodeBase();

	protected pointcut staticinit(): staticinitialization(*) && commonCodeBase();
	
	before(): staticinit() && traceableCode() && noTrace() {
		l = getLogger(thisJoinPointStaticPart.getSignature().getDeclaringTypeName());

		if (l.isFinerEnabled()) {
			l.entering(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
					thisJoinPointStaticPart.getSignature().getName());
		}
	}
	
	after() returning: staticinit() && traceableCode() && noTrace() {
		if (l.isFinerEnabled()) {
			l.returning(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
					thisJoinPointStaticPart.getSignature().getName());
		}
	}
	
	before(): (commonCode() || constructors()) && traceableCode() {
		if (l.isFinerEnabled()) {
			Object[] args = thisJoinPoint.getArgs();
			if (args != null && args.length > 0) {
				l.entering(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
						thisJoinPointStaticPart.getSignature().getName(), stringify(args));
			} else {
				l.entering(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
						thisJoinPointStaticPart.getSignature().getName());
			}
		}
	}
	
	after() returning(Object r): commonNonVoidCode() && traceableCode() {
		if (l.isFinerEnabled()) {
			l.returning(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
					thisJoinPointStaticPart.getSignature().getName(), stringify(r));
		}
	}
	
	after() returning: (commonVoidCode() || constructors()) && traceableCode() {
		if (l.isFinerEnabled()) {
			l.returning(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
					thisJoinPointStaticPart.getSignature().getName());
		}
	}
	
	void around(String msg): call(public void TracingHelper.severe(String)) && args(msg) {
		l.severe(msg);
	}

	void around(String msg): call(public void TracingHelper.warning(String)) && args(msg) {
		l.warning(msg);
	}

	void around(String msg): call(public void TracingHelper.info(String)) && args(msg) {
		l.info(msg);
	}

	void around(String msg): call(public void TracingHelper.fine(String)) && args(msg) {
		l.fine(msg);
	}

	void around(String msg, Object o): call(public void TracingHelper.fine(String, Object)) && args(msg, o) {
		if (l.isFineEnabled()) {
			l.fine(msg, stringify(o));
		}
	}

	void around(String msg, Object o, String correlation): call(public void TracingHelper.fine(String, Object, String)) && args(msg, o, correlation) {
		if (l.isFineEnabled()) {
			l.fine(msg, stringify(o), correlation);
		}
	}

	void around(String msg): call(public void TracingHelper.finer(String)) && args(msg) {
		l.finer(msg);
	}

	void around(String msg, Object o): call(public void TracingHelper.finer(String, Object)) && args(msg, o) {
		if (l.isFinerEnabled()) {
			l.finer(msg, stringify(o));
		}
	}

	void around(String msg, Object o, String correlation): call(public void TracingHelper.finer(String, Object, String)) && args(msg, o, correlation) {
		if (l.isFinerEnabled()) {
			l.finer(msg, stringify(o), correlation);
		}
	}

	void around(String msg): call(public void TracingHelper.finest(String)) && args(msg) {
		l.finest(msg);
	}

	void around(String msg, Object o): call(public void TracingHelper.finest(String, Object)) && args(msg, o) {
		if (l.isFinestEnabled()) {
			l.finest(msg, stringify(o));
		}
	}

	void around(String msg, Object o, String correlation): call(public void TracingHelper.finest(String, Object, String)) && args(msg, o, correlation) {
		if (l.isFinestEnabled()) {
			l.finest(msg, stringify(o), correlation);
		}
	}

	boolean around(): call(public boolean TracingHelper.isFineEnabled()) {
		return l.isFineEnabled();
	}

	boolean around(): call(public boolean TracingHelper.isFinerEnabled()) {
		return l.isFinerEnabled();
	}

	boolean around(): call(public boolean TracingHelper.isFinestEnabled()) {
		return l.isFinestEnabled();
	}
}