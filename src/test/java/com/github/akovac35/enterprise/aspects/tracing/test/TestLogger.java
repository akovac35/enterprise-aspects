package com.github.akovac35.enterprise.aspects.tracing.test;

import java.util.HashMap;

import com.github.akovac35.enterprise.aspects.common.ILogger;

public class TestLogger implements ILogger {
	protected static HashMap<String, String> messages = new HashMap<String, String>();
	protected String name;
	
	
	public TestLogger(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static HashMap<String, String> getMessages() {
		return messages;
	}

	@Override
	public void severe(String msg) {
		messages.put(msg, "severe");
	}

	@Override
	public void warning(String msg) {
		messages.put(msg, "warning");
	}

	@Override
	public void info(String msg) {
		messages.put(msg, "info");
	}

	@Override
	public void fine(String msg) {
		messages.put(msg, "fine");
	}
	
	@Override
	public void fine(String msg, Object o) {
		messages.put(msg+" fine", String.valueOf(o));
	}
	
	@Override
	public void fine(String msg, Object o, String correlation) {
		messages.put(msg+" fine", String.valueOf(o));
	}

	@Override
	public void finer(String msg) {
		messages.put(msg, "finer");
	}
	
	@Override
	public void finer(String msg, Object o) {
		messages.put(msg+" finer", String.valueOf(o));
	}
	
	@Override
	public void finer(String msg, Object o, String correlation) {
		messages.put(msg+" finer", String.valueOf(o));
	}

	@Override
	public void finest(String msg) {
		messages.put(msg, "finest");
	}
	
	@Override
	public void finest(String msg, Object o) {
		messages.put(msg+" finest", String.valueOf(o));
	}
	
	@Override
	public void finest(String msg, Object o, String correlation) {
		messages.put(msg+" finest", String.valueOf(o));
	}

	@Override
	public void entering(String className, String methodName, Object inputParams) {
		messages.put("entering " + className + " " + methodName, String.valueOf(inputParams));
	}

	@Override
	public void entering(String className, String methodName) {
		messages.put("entering " + className + " " + methodName, "none");
	}

	@Override
	public void returning(String className, String methodName, Object returnValue) {
		messages.put("returning " + className + " " + methodName, String.valueOf(returnValue));
	}

	@Override
	public void returning(String className, String methodName) {
		messages.put("returning " + className + " " + methodName, "none");
	}

	@Override
	public boolean isFineEnabled() {
		return true;
	}
	
	@Override
	public boolean isFinerEnabled() {
		return true;
	}

	@Override
	public boolean isFinestEnabled() {
		return true;
	}
}
