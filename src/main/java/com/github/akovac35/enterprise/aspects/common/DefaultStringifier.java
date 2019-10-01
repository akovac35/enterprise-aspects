package com.github.akovac35.enterprise.aspects.common;

import com.google.gson.Gson;

/**
 * Default stringifier implementation.
 * 
 * @author Aleksander Kovač
 *
 */
public class DefaultStringifier {
	protected static Gson jsonConverter = new Gson();

	public static String stringify(Object o) {
		if (o == null) {
			return "null";
		} else {
			try {
				return jsonConverter.toJson(o);
			} catch (Exception ex) {
				return o.toString();
			}
		}
	}

	public static Gson getJsonConverter() {
		return jsonConverter;
	}
}
