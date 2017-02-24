package com.yudong80.reactivejava.chapter05.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import static com.yudong80.reactivejava.common.CommonUtils.API_KEY;


public class OpenWeatherMapV2 {
	private static final String URL = "http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=";
	
	//-> ConnectableObservable로 변경 
	public void run() { 
		Observable<String> source = Observable.just(URL + API_KEY)
				.map(url -> OkHttpHelper.get(url, true));
		
		//어떻게 호출을 한번만 하게 할 수 있을까? 
		ConnectableObservable<String> connectable = source.publish();
		CommonUtils.exampleStart();
		StringBuilder sb = new StringBuilder();

		connectable.map(this::parseTemperature)
			       .subscribe(v -> sb.append(v + "\n"));
		connectable.map(this::parseCityName)
		           .subscribe(v -> sb.append(v + "\n"));
		connectable.map(this::parseCountry)
		           .subscribe(v -> sb.append(v + "\n"));
		connectable.connect();		
		
		Log.i(sb.toString());
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
		OpenWeatherMapV2 demo = new OpenWeatherMapV2();
		demo.run();
	}	
}
