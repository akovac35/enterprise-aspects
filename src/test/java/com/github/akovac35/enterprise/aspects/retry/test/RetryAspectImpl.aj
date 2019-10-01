package com.github.akovac35.enterprise.aspects.retry.test;

import com.github.akovac35.enterprise.aspects.common.DefaultLogger;
import com.github.akovac35.enterprise.aspects.common.ILogger;
import com.github.akovac35.enterprise.aspects.retry.RetryAspect;

public aspect RetryAspectImpl extends RetryAspect {
	@Override
	protected ILogger getLogger() {
		return new DefaultLogger(this.getClass().getName());
	}
}
