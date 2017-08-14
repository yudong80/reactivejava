package com.yudong80.reactivejava.chapter07;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.OkHttpHelper;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;

public class RetryExample {
	public void retry5() {
		CommonUtils.exampleStart();
		
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
			.map(OkHttpHelper::getT)
			.retry(5)
			.onErrorReturn(e -> CommonUtils.ERROR_CODE);
		
		source.subscribe(data -> Log.it("result : " + data));
		CommonUtils.exampleComplete();
	}	

	public void retryWithDelay() {
		final int RETRY_MAX = 5;
		final int RETRY_DELAY = 1000;
		
		CommonUtils.exampleStart();
		
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
			.map(OkHttpHelper::getT)
			.retry((retryCnt, e) -> {
				Log.e("retryCnt = " + retryCnt);
				CommonUtils.sleep(RETRY_DELAY);
				
				return retryCnt < RETRY_MAX ? true: false;
			})
			.onErrorReturn(e -> CommonUtils.ERROR_CODE);
		
		source.subscribe(data -> Log.it("result : " + data));
		CommonUtils.exampleComplete();
	}	
		
	public void retryUntil() { 
		CommonUtils.exampleStart();
		
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
			.map(OkHttpHelper::getT)
			.subscribeOn(Schedulers.io())
			.retryUntil(() -> { 
				if(CommonUtils.isNetworkAvailable()) 
					return true; //stop 
				
				CommonUtils.sleep(1000);
				return false; //continue 
			});
		source.subscribe(Log::i);
		
		CommonUtils.sleep(5000);
		CommonUtils.exampleComplete();		
	}
	
	public void retryWhen() { 
        Observable.create((ObservableEmitter<String> emitter) -> {
		      System.out.println("subscribing");
		      emitter.onError(new RuntimeException("always fails"));
		  }).retryWhen(attempts -> {
		      return attempts.zipWith(Observable.range(1, 3), (n, i) -> i).flatMap(i -> {
		          System.out.println("delay retry by " + i + " second(s)");
		          return Observable.timer(i, TimeUnit.SECONDS);
		      });
		  }).blockingForEach(System.out::println);
        CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		RetryExample demo = new RetryExample();
//		demo.retry5();
//		demo.retryWithDelay();
//		demo.retryUntil();
//		demo.retryWhen();
	}
}
