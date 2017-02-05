package com.yudong80.reactivejava.common;

import com.google.gson.JsonParser;

public class GsonHelper {
	public static String parseValue(String json, String key) { 
		return new JsonParser().parse(json)
				.getAsJsonObject()
				.get(key)
				.getAsString();
	}
}
