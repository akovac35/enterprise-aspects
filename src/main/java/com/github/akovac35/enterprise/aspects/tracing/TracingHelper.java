package com.github.akovac35.enterprise.aspects.tracing;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Use this class for tracing concern implementation in custom code. All
 * invocations of this class are handled by the TracingAspect.
 * 
 * @author Aleksander Kovač
 *
 */
public class TracingHelper {
	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.SEVERE.
	 * 
	 * @param msg Message to be logged
	 */
	public static void severe(String msg) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.WARNING.
	 * 
	 * @param msg Message to be logged
	 */
	public static void warning(String msg) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.INFO.
	 * 
	 * @param msg Message to be logged
	 */
	public static void info(String msg) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINE. FINE logging level is generally
	 * used for debugging when enabling higher logging levels would be prohibitive.
	 * 
	 * @param msg Message to be logged
	 */
	public static void fine(String msg) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINE. FINE logging level is generally
	 * used for debugging when enabling higher logging levels would be prohibitive.
	 * 
	 * @param msg Message to be logged
	 * @param o   Object to be logged
	 */
	public static void fine(String msg, Object o) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINE. FINE logging level is generally
	 * used for debugging when enabling higher logging levels would be prohibitive.
	 * 
	 * @param msg         Message to be logged
	 * @param o           Object to be logged
	 * @param correlation Used to correlate distinct log entries when more than
	 *                    thread id is required for that
	 */
	public static void fine(String msg, Object o, String correlation) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINER. FINER logging level is generally
	 * used for printing method entry/return, input parameters and return value.
	 * 
	 * @param msg Message to be logged
	 */
	public static void finer(String msg) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINER. FINER logging level is generally
	 * used for printing method entry/return, input parameters and return value.
	 * 
	 * @param msg Message to be logged
	 * @param o   Object to be logged
	 */
	public static void finer(String msg, Object o) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINER. FINER logging level is generally
	 * used for printing method entry/return, input parameters and return value.
	 * 
	 * @param msg         Message to be logged
	 * @param o           Object to be logged
	 * @param correlation Used to correlate distinct log entries when more than
	 *                    thread id is required for that
	 */
	public static void finer(String msg, Object o, String correlation) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINEST. FINEST logging level is
	 * generally used for debugging.
	 * 
	 * @param msg Message to be logged
	 */
	public static void finest(String msg) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINEST. FINEST logging level is
	 * generally used for debugging.
	 * 
	 * @param msg Message to be logged
	 * @param o   Object to be logged
	 */
	public static void finest(String msg, Object o) {
	}

	/**
	 * Code marker to be handled by the TracingAspect at logging level
	 * equivalent to java.util.logging.Level.FINEST. FINEST logging level is
	 * generally used for debugging.
	 * 
	 * @param msg         Message to be logged
	 * @param o           Object to be logged
	 * @param correlation Used to correlate distinct log entries when more than
	 *                    thread id is required for that
	 */
	public static void finest(String msg, Object o, String correlation) {
	}

	/**
	 * Code marker to be handled by the TracingAspect.
	 * 
	 * @return true if logging level equivalent to java.util.logging.Level.FINE is
	 *         enabled by the logger
	 */
	public static boolean isFineEnabled() {
		throw new IllegalAccessError();
	}

	/**
	 * Code marker to be handled by the TracingAspect.
	 * 
	 * @return true if logging level equivalent to java.util.logging.Level.FINER is
	 *         enabled by the logger
	 */
	public static boolean isFinerEnabled() {
		throw new IllegalAccessError();
	}

	/**
	 * Code marker to be handled by the TracingAspect.
	 * 
	 * @return true if logging level equivalent to java.util.logging.Level.FINEST is
	 *         enabled by the logger
	 */
	public static boolean isFinestEnabled() {
		throw new IllegalAccessError();
	}

	/**
	 * Will stringify the exception as follows: message + lineSeparator + stack
	 * 
	 * @param ex Exception
	 * @return Error message and stack trace
	 */
	public static String convertExceptionToString(Exception ex) {
		if(ex == null) return null;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String stack = sw.toString();

		return ex.getMessage() + System.lineSeparator() + stack;
	}
}
