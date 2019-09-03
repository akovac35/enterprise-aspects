package ak.enterprise.aspects.tracing;

/**
 * This tracing aspect is designed to meet small to medium enterprise
 * application requirements:
 * 
 * <pre>
 * - one instantiated logger instance per type,
 * - entry/exit trace with included input parameters and return value,
 * - minimal performance impact when tracing is not enabled,
 * - minimal development effort to support tracing in custom code.
 * </pre>
 * 
 * To enable entry/exit tracing in custom code, extend this aspect as follows:
 * 
 * <pre>
public aspect TracingAspectImpl extends TracingAspect  pertypewithin(com.myco..*) {
	
	public pointcut loggableCode(): 
		within(com.myco..*) &&
		!within(com.myco.xy..*);
}
 * </pre>
 * 
 * <strong>Note:</strong> loggableCode pointcut is internally enhanced to filter
 * out internal and common code such as toString, getClass, etc.
 * 
 * @author Aleksander Kovač
 *
 */
public privileged abstract aspect TracingAspect {

	public abstract pointcut loggableCode();

	public pointcut commonCodeBase():		
		!execution(String *.toString()) &&
	    !execution(int *.hashCode()) &&
	    !execution(boolean *.canEqual(Object)) &&
	    !execution(boolean *.equals(Object)) &&
	    !execution(* *.getClasses()) &&
	    !execution(* *.getClass()) && 
	    !within(ak.enterprise.aspects.tracing.TracingHelper+) && 
	    !within(ak.enterprise.aspects.tracing.ILogger+) &&
	    !within(ak.enterprise.aspects.tracing.DefaultStringifier+) &&
	    !within(ak.enterprise.aspects.tracing.TracingAspect+);

	public pointcut commonCode(): execution(* * (..)) &&
	commonCodeBase();

	public pointcut commonVoidCode(): execution(void * (..)) &&
	commonCodeBase();

	public pointcut commonNonVoidCode(): execution(!void * (..)) &&
	commonCodeBase();

	public pointcut constructors(): execution(*.new(..)) && commonCodeBase();

	public pointcut staticinit(): staticinitialization(*) && commonCodeBase();

	/**
	 * Logger instance will be specific to each advised type once this aspect is
	 * extended with "pertypewithin" aspect instantiation model.
	 */
	protected ILogger l;

	protected void setTypeLogger(String name) {
		l = new DefaultLogger(name);
	}

	/**
	 * Convert an object to String.
	 * 
	 * @param o Object to be converted
	 * @return "null" if o is null, o as String otherwise
	 */
	protected String stringify(Object o) {
		return DefaultStringifier.stringify(o);
	}

	before(): staticinit() && loggableCode() {
		setTypeLogger(thisJoinPointStaticPart.getSignature().getDeclaringTypeName());

		if (l.isFinerLoggingLevelEnabled()) {
			l.entering(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
					thisJoinPointStaticPart.getSignature().getName());
		}
	}

	after() returning: staticinit() && loggableCode() {
		if (l.isFinerLoggingLevelEnabled()) {
			l.exiting(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
					thisJoinPointStaticPart.getSignature().getName());
		}
	}

	before(): (commonCode() || constructors()) && loggableCode() {
		if (l.isFinerLoggingLevelEnabled()) {
			Object[] args = thisJoinPoint.getArgs();
			if (args != null && args.length > 0) {
				l.entering(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
						thisJoinPointStaticPart.getSignature().getName(),
						stringify(args));
			} else {
				l.entering(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
						thisJoinPointStaticPart.getSignature().getName());
			}
		}
	}

	after() returning(Object r): commonNonVoidCode() && loggableCode() {
		if (l.isFinerLoggingLevelEnabled()) {
			l.exiting(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
					thisJoinPointStaticPart.getSignature().getName(), stringify(r));
		}
	}

	after() returning: (commonVoidCode() || constructors()) && loggableCode() {
		if (l.isFinerLoggingLevelEnabled()) {
			l.exiting(thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
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
		if (l.isFineLoggingLevelEnabled()) {
			l.fine(msg, stringify(o));
		}
	}

	void around(String msg): call(public void TracingHelper.finer(String)) && args(msg) {
		l.finer(msg);
	}

	void around(String msg, Object o): call(public void TracingHelper.finer(String, Object)) && args(msg, o) {
		if (l.isFinerLoggingLevelEnabled()) {
			l.finer(msg, stringify(o));
		}
	}

	void around(String msg): call(public void TracingHelper.finest(String)) && args(msg) {
		l.finest(msg);
	}

	void around(String msg, Object o): call(public void TracingHelper.finest(String, Object)) && args(msg, o) {
		if (l.isFinestLoggingLevelEnabled()) {
			l.finest(msg, stringify(o));
		}
	}
}