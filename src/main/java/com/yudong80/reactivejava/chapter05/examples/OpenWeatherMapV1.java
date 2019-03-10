package com.yudong80.reactivejava.chapter05.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.yudong80.reactivejava.common.CommonUtils.API_KEY;


public class OpenWeatherMapV1 {
	private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=";
	
	public void run() { 
		Observable<String> source = Observable.just(URL + API_KEY)
				.map(OkHttpHelper::getWithLog)
				.subscribeOn(Schedulers.io());
		
		//어떻게 호출을 한번만 하게 할 수 있을까? 
		Observable<String> temperature = source.map(this::parseTemperature);
		Observable<String> city = source.map(this::parseCityName);
		Observable<String> country = source.map(this::parseCountry);
		
		CommonUtils.exampleStart();
		Observable.concat(temperature, 
				city, 
				country)
				.observeOn(Schedulers.newThread())
				.subscribe(Log::it);
		
		CommonUtils.sleep(3000);
	}
	
	private String parseTemperature(String json) { 
		return parse(json, "\"temp\":[0-9]*.[0-9]*");
	}

	private String parseCityName(String json) { 
		return parse(json, "\"name\":\"[a-zA-Z]*\"");
	}

	private String parseCountry(String json) { 
		return parse(json, "\"country\":\"[a-zA-Z]*\"");
	}
	
	private String parse(String json, String regex) { 
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(json);
		if (match.find()) {
			return match.group();
		}
		return "N/A";				
	}
	
	public static void main(String[] args) { 
		OpenWeatherMapV1 demo = new OpenWeatherMapV1();
		demo.run();
	}	
}
