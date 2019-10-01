package com.github.akovac35.enterprise.aspects.retry;

import org.aspectj.lang.reflect.MethodSignature;

import com.github.akovac35.enterprise.aspects.common.DefaultLogger;
import com.github.akovac35.enterprise.aspects.common.ILogger;
import com.github.akovac35.enterprise.aspects.tracing.TracingHelper;

/**
 * The RetryAspect is designed for extension based retry concern implementation.
 * Relevant methods should be annotated with the {@link Retry} annotation. See
 * project Wiki on how to use it: <a href=
 * "https://github.com/akovac35/enterprise-aspects/wiki/RetryAspect">RetryAspect</a>
 * 
 * @author Aleksander Kovač
 *
 */
public abstract aspect RetryAspect {
	protected ILogger l = getLogger();

	/**
	 * This method is called once when the aspect is initialized to provide a logger
	 * instance.
	 * 
	 * @return A new logger instance
	 */
	protected ILogger getLogger() {
		return new DefaultLogger(this.getClass().getName());
	}

	Object around() throws Exception: execution(@com.github.akovac35.enterprise.aspects.retry.Retry * *(..)) {
		Retry retry = ((MethodSignature) thisJoinPointStaticPart.getSignature()).getMethod().getAnnotation(Retry.class);
		int retries = retry.maxRetries();
		int milliseconds = retry.delayMilliseconds();

		Exception exception = null;
		for (int i = 1; i <= retries; i++) {
			try {
				return proceed();
			} catch (Exception ex) {
				exception = ex;
				String exString = TracingHelper.convertExceptionToString(ex);
				l.warning(exString);
				if (milliseconds > 0 && i < retries)
					Thread.sleep(milliseconds);
			}
		}
		throw exception;
	}
}
