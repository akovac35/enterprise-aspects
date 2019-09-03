package ak.enterprise.aspects.tracing;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultLogger implements ILogger {
	final Logger l;

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
	public void finer(String msg) {
		l.finer(msg);
	}
	
	@Override
	public void finer(String msg, Object o) {
		l.log(Level.FINER, msg, o);
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
	public void entering(String className, String methodName, Object inputParams) {
		l.entering(className, methodName, inputParams);
	}

	@Override
	public void entering(String className, String methodName) {
		l.entering(className, methodName);
	}

	@Override
	public void exiting(String className, String methodName, Object returnValue) {
		l.exiting(className, methodName, returnValue);
	}

	@Override
	public void exiting(String className, String methodName) {
		l.exiting(className, methodName);
	}

	@Override
	public boolean isFineLoggingLevelEnabled() {
		return l.isLoggable(Level.FINE);
	}
	
	@Override
	public boolean isFinerLoggingLevelEnabled() {
		return l.isLoggable(Level.FINER);
	}

	@Override
	public boolean isFinestLoggingLevelEnabled() {
		return l.isLoggable(Level.FINEST);
	}
}
