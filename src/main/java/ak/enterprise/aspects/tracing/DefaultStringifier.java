package ak.enterprise.aspects.tracing;

import com.google.gson.Gson;

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
