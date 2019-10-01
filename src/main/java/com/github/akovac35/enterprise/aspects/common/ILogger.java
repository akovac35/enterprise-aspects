package com.github.akovac35.enterprise.aspects.common;

/**
 * Logger interface used by aspects.
 * 
 * @author Aleksander Kovač
 *
 */
public interface ILogger {
	/**
	 * Equivalent to java.util.logging.Level.SEVERE.
	 * 
	 * @param msg Message to be logged
	 */
	public void severe(String msg);

	/**
	 * Equivalent to java.util.logging.Level.WARNING.
	 * 
	 * @param msg Message to be logged
	 */
	public void warning(String msg);

	/**
	 * Equivalent to java.util.logging.Level.INFO.
	 * 
	 * @param msg Message to be logged
	 */
	public void info(String msg);

	/**
	 * Equivalent to java.util.logging.Level.FINE. FINE logging level is generally
	 * used for debugging when enabling higher logging levels would be prohibitive.
	 * 
	 * @param msg Message to be logged
	 */
	public void fine(String msg);

	/**
	 * Equivalent to java.util.logging.Level.FINE. FINE logging level is generally
	 * used for debugging when enabling higher logging levels would be prohibitive.
	 * 
	 * @param msg Message to be logged
	 * @param o   Object to be logged
	 */
	public void fine(String msg, Object o);

	/**
	 * Equivalent to java.util.logging.Level.FINE. FINE logging level is generally
	 * used for debugging when enabling higher logging levels would be prohibitive.
	 * 
	 * @param msg         Message to be logged
	 * @param o           Object to be logged
	 * @param correlation Used to correlate distinct log entries when more than
	 *                    thread id is required for that
	 */
	public void fine(String msg, Object o, String correlation);

	/**
	 * Equivalent to java.util.logging.Level.FINER. FINER logging level is generally
	 * used for printing method entry/return, input parameters and return value.
	 * 
	 * @param msg Message to be logged
	 */
	public void finer(String msg);

	/**
	 * Equivalent to java.util.logging.Level.FINER. FINER logging level is generally
	 * used for printing method entry/return, input parameters and return value.
	 * 
	 * @param msg Message to be logged
	 * @param o   Object to be logged
	 */
	public void finer(String msg, Object o);

	/**
	 * Equivalent to java.util.logging.Level.FINER. FINER logging level is generally
	 * used for printing method entry/return, input parameters and return value.
	 * 
	 * @param msg         Message to be logged
	 * @param o           Object to be logged
	 * @param correlation Used to correlate distinct log entries when more than
	 *                    thread id is required for that
	 */
	public void finer(String msg, Object o, String correlation);

	/**
	 * Equivalent to java.util.logging.Level.FINEST.
	 * 
	 * @param msg Message to be logged
	 */
	public void finest(String msg);

	/**
	 * Equivalent to java.util.logging.Level.FINEST.
	 * 
	 * @param msg Message to be logged
	 * @param o   Object to be logged
	 */
	public void finest(String msg, Object o);

	/**
	 * Equivalent to java.util.logging.Level.FINEST.
	 * 
	 * @param msg         Message to be logged
	 * @param o           Object to be logged
	 * @param correlation Used to correlate distinct log entries when more than
	 *                    thread id is required for that
	 */
	public void finest(String msg, Object o, String correlation);

	/**
	 * Activates when {@link ILogger#isFinerEnabled()} returns true.
	 * 
	 * @param className   Name of the class entering a method
	 * @param methodName  Method being entered
	 * @param inputParams Method input parameters
	 */
	public void entering(String className, String methodName, Object inputParams);

	/**
	 * Activates when {@link ILogger#isFinerEnabled()} returns true.
	 * 
	 * @param className  Name of the class entering a method
	 * @param methodName Method being entered
	 */
	public void entering(String className, String methodName);

	/**
	 * Activates when {@link ILogger#isFinerEnabled()} returns true.
	 * 
	 * @param className   Name of the class entering a method
	 * @param methodName  Method being entered
	 * @param returnValue Method's return value
	 */
	public void returning(String className, String methodName, Object returnValue);

	/**
	 * Activates when {@link ILogger#isFinerEnabled()} returns true.
	 * 
	 * @param className  Name of the class entering a method
	 * @param methodName Method being entered
	 */
	public void returning(String className, String methodName);

	/**
	 * @return true if logging level equivalent to java.util.logging.Level.FINE is
	 *         enabled by the logger
	 */
	public boolean isFineEnabled();

	/**
	 * @return true if logging level equivalent to java.util.logging.Level.FINER is
	 *         enabled by the logger
	 */
	public boolean isFinerEnabled();

	/**
	 * @return true if logging level equivalent to java.util.logging.Level.FINEST is
	 *         enabled by the logger
	 */
	public boolean isFinestEnabled();
}
