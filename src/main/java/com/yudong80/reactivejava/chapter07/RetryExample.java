package com.yudong80.reactivejava.chapter07;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class RetryExample {
	public void retry5() { 
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
			.map(OkHttpHelper::get)
			.retry(5);
		
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}	
	
	public void retry5NoError() { 
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
			.map(OkHttpHelper::get)
			.retry(5)
			.onErrorReturnItem("NO ERROR");
		
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}		
	
	public void retryOnCount() {
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
			.map(OkHttpHelper::get)
			.retry((Integer cnt, Throwable t) -> { 
				Log.d("Retry count: " + cnt + " / Error :" + t.getMessage());
				if (cnt < 5) return true;				
				return false; //stop
			})
			.onErrorReturnItem("NO ERROR");
		
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();		
	}
	
	public void retryUntil() { 
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
			.map(OkHttpHelper::get)
			.retryUntil(CommonUtils::isNetworkAvailable);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();		
	}
	
	public void retryWhen() { 
		Observable.create((ObservableEmitter<String> emitter) -> { 
			emitter.onError(new RuntimeException("always fails"));
		}).retryWhen(attempts -> {
			return attempts.zipWith(Observable.range(1, 3), (n,i) -> i)
					.flatMap( i-> { 
						Log.d("delay retry by " + i + " seconds");
						return Observable.timer(i, TimeUnit.SECONDS);
					});
		}).blockingForEach(Log::d);				
	}
	
	public static void main(String[] args) { 
		RetryExample demo = new RetryExample();
//		demo.retry5();
//		demo.retry5NoError();
//		demo.retryOnCount();
//		demo.retryUntil();
		demo.retryWhen();
	}
}
