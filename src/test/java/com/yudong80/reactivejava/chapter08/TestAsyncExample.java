package com.yudong80.reactivejava.chapter08;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

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
	@Disabled  //테스트 코드를 비활화시키는 경우에는  @Disable을 추가합니다. 
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
	
	@DisplayName("test Github v3 API on HTTP")
	@Test
	void testHttp() { 
		final String url = "https://api.github.com/users/yudong80";
		Observable<String> source = Observable.just(url)
				.subscribeOn(Schedulers.io())
				.map(OkHttpHelper::get)
				.doOnNext(Log::d)				
				.map(json -> GsonHelper.parseValue(json,"name"))
				.observeOn(Schedulers.newThread());
		
		String expected = "Dong Hwan Yu";
		source.doOnNext(Log::i)
			.test()
			.awaitDone(3, TimeUnit.SECONDS)
			.assertResult(expected);
		CommonUtils.exampleComplete();
	}
	
}
