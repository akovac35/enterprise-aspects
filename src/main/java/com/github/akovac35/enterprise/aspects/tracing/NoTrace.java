package com.github.akovac35.enterprise.aspects.tracing;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * Indicates that annotated methods or types should be excluded from the
 * entry/return tracing. Methods invoked within the annotated methods will still
 * be traced, including {@link TracingHelper}, with the reason being runtime
 * performance impact of testing the control flow for annotation.
 * 
 * @author Aleksander Kovač
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface NoTrace {

}
