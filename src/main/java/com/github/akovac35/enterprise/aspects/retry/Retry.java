package com.github.akovac35.enterprise.aspects.retry;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * Retries the annotated method by applying {@link RetryAspect}. Example use:
 * {@code @Retry(maxRetries = 3)} or
 * {@code @Retry(maxRetries = 3, delayMilliseconds = 500)}.
 * 
 * @author Aleksander Kovač
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Retry {
	int maxRetries();
	int delayMilliseconds() default 0;
}
