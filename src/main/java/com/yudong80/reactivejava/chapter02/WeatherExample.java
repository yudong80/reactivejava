package com.yudong80.reactivejava.chapter02;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherExample {
	public static void main(String[] args) { 
		new WeatherExample().run();
	}
	
	private static final String URL = "http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=";
	private static final String API_KEY = "5712cae3137a8f6bcbebe4fb35dfb434";
			
	public void run() { 
		Observable.just(getWeather(API_KEY))
		.map(str -> regexTemperature(str))
		.map(str -> str.replaceAll("\"temp\":", ""))
		.subscribe(System.out::println);
	}
	
	public static String getWeather(String apiKey) { 
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(URL + apiKey)
				.build();
		try { 
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) { 
			System.err.println("ERROR: " + e.getMessage());
		}
		return "";		
	}
	
	public static String regexTemperature(String str) { 
		String regex = "\"temp\":[0-9]*.[0-9]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(str);
		if (match.find()) {
			return match.group();
		}
		return "";		
	}
}
