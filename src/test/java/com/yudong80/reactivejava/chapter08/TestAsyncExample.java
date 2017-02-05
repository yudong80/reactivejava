package com.yudong80.reactivejava.chapter08;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.GsonHelper;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@RunWith(JUnitPlatform.class)
public class TestAsyncExample {

	@DisplayName("test Observable.interval() wrong")
	@Test
	@Disabled
	void testIntervalWrongWay() { 
		Observable<Integer> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.take(5)
				.map(Long::intValue);
		
		source.doOnNext(Log::d)
		.test().assertResult(0,1,2,3,4);
	}

	@DisplayName("test Observable.interval()")
	@Test
	void testInterval() { 
		Observable<Integer> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.take(5)
				.map(Long::intValue);
		
		source.doOnNext(Log::d)
			.test()
			.awaitDone(1L, TimeUnit.SECONDS)
			.assertResult(0,1,2,3,4);
		CommonUtils.exampleComplete();
	}	
	
	@DisplayName("test HTTP")
	@Test
	void testHttp() { 
		final String url = "https://api.github.com/users/yudong80";
		Observable<String> source = Observable.just(url)
				.observeOn(Schedulers.io())
				.map(OkHttpHelper::get)
				.map(json -> GsonHelper.parseValue(json,"name"));
		
		String expected = "Dong Hwan Yu";
		source.doOnNext(Log::d)
			.test()
			.awaitDone(3, TimeUnit.SECONDS)
			.assertResult(expected);
		CommonUtils.exampleComplete();
	}
	
}
