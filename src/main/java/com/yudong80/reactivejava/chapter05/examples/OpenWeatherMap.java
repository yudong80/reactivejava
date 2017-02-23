package com.yudong80.reactivejava.chapter05.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;

public class OpenWeatherMap {
	private static final String URL = "http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=";
	private static final String API_KEY = "e7681f2ac93cbdf1bc3952e30ab80533";
			
	public void run() { 
		Observable<String> source = Observable.just(URL + API_KEY)
				.map(OkHttpHelper::get)
				.map(str -> regexTemperature(str))
				.map(str -> str.replaceAll("\"temp\":", ""));		
		source.subscribe(Log::i);
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
	
	public static void main(String[] args) { 
		OpenWeatherMap demo = new OpenWeatherMap();
		demo.run();
	}	
}
