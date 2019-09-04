package ak.enterprise.aspects.tracing;

public interface ILogger {
	public void severe(String msg);

	public void warning(String msg);

	public void info(String msg);

	public void fine(String msg);
	
	public void fine(String msg, Object o);
	
	public void fine(String msg, Object o, String correlation);

	public void finer(String msg);
	
	public void finer(String msg, Object o);
	
	public void finer(String msg, Object o, String correlation);

	public void finest(String msg);
	
	public void finest(String msg, Object o);
	
	public void finest(String msg, Object o, String correlation);

	public void entering(String className, String methodName, Object inputParams);

	public void entering(String className, String methodName);

	public void exiting(String className, String methodName, Object returnValue);

	public void exiting(String className, String methodName);

	/**
	 * FINE logging level is generally used for debugging when enabling higher
	 * logging levels would be prohibitive.
	 * 
	 * @return true if FINE logging level is enabled for the logger
	 */
	public boolean isFineLoggingLevelEnabled();

	/**
	 * FINER logging level is generally used for printing method entry/exit, input
	 * parameters and return value.
	 * 
	 * @return true if FINER logging level is enabled for the logger
	 */
	public boolean isFinerLoggingLevelEnabled();

	/**
	 * FINEST logging level is generally used for debugging.
	 * 
	 * @return true if FINEST logging level is enabled for the logger
	 */
	public boolean isFinestLoggingLevelEnabled();
}
