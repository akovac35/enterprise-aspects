package com.github.akovac35.enterprise.aspects.common;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default logger implementation.
 * 
 * @author Aleksander Kovač
 *
 */
public class DefaultLogger implements ILogger {
	protected final Logger l;
	public final static String CORRELATION = "CORRELATION";

	public DefaultLogger(String name) {
		l = Logger.getLogger(name);
	}

	public Logger getLogger() {
		return l;
	}

	@Override
	public void severe(String msg) {
		l.severe(msg);
	}

	@Override
	public void warning(String msg) {
		l.warning(msg);
	}

	@Override
	public void info(String msg) {
		l.info(msg);
	}

	@Override
	public void fine(String msg) {
		l.fine(msg);
	}

	@Override
	public void fine(String msg, Object o) {
		l.log(Level.FINE, msg, o);
	}

	@Override
	public void fine(String msg, Object o, String correlation) {
		l.log(Level.FINE, msg, new Object[] { prepareCorrelation(correlation), o });
	}

	@Override
	public void finer(String msg) {
		l.finer(msg);
	}

	@Override
	public void finer(String msg, Object o) {
		l.log(Level.FINER, msg, o);
	}

	@Override
	public void finer(String msg, Object o, String correlation) {
		l.log(Level.FINER, msg, new Object[] { prepareCorrelation(correlation), o });
	}

	@Override
	public void finest(String msg) {
		l.finest(msg);
	}

	@Override
	public void finest(String msg, Object o) {
		l.log(Level.FINEST, msg, o);
	}

	@Override
	public void finest(String msg, Object o, String correlation) {
		l.log(Level.FINEST, msg, new Object[] { prepareCorrelation(correlation), o });
	}

	@Override
	public void entering(String className, String methodName, Object inputParams) {
		l.entering(className, methodName, inputParams);
	}

	@Override
	public void entering(String className, String methodName) {
		l.entering(className, methodName);
	}

	@Override
	public void returning(String className, String methodName, Object returnValue) {
		l.exiting(className, methodName, returnValue);
	}

	@Override
	public void returning(String className, String methodName) {
		l.exiting(className, methodName);
	}

	@Override
	public boolean isFineEnabled() {
		return l.isLoggable(Level.FINE);
	}

	@Override
	public boolean isFinerEnabled() {
		return l.isLoggable(Level.FINER);
	}

	@Override
	public boolean isFinestEnabled() {
		return l.isLoggable(Level.FINEST);
	}

	protected String prepareCorrelation(String correlation) {
		return CORRELATION + ": " + correlation;
	}
}
