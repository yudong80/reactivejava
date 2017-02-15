package com.yudong80.reactivejava.chapter04.transform;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Function;

public class SwitchMapExample {
	public void usingSwitchMap() { 
		Function<String, Observable<String>> ballToDoubleDiamonds = 
				ball -> Observable.zip(
					Observable.just(ball+ "<>", ball+ "<>"),
					Observable.interval(100L, TimeUnit.MILLISECONDS),
					(value,i) -> value					
				);
	
		Observable<String> source = ballsWithDelay()
				.switchMap(ballToDoubleDiamonds);
		
		long start = System.currentTimeMillis();
		source.subscribe(str -> { 
			long now = System.currentTimeMillis();
			System.out.println((now - start) + " | " + str);
		});
		CommonUtils.sleep(1000);		
		CommonUtils.exampleComplete();
	}
	
	public static Observable<String> ballsWithDelay() { 
		return Observable.create((ObservableEmitter<String> emitter) -> { 
	    	emitter.onNext("RED");
	    	Thread.sleep(300);
	    	emitter.onNext("GREEN");
	    	Thread.sleep(150);
	    	emitter.onNext("BLUE");
	    	emitter.onComplete();
	    }); 
	}
	
	public static void main(String[] args) { 
		SwitchMapExample demo = new SwitchMapExample();
		demo.usingSwitchMap();
	}
}
