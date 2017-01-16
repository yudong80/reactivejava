package com.yudong80.reactivejava.chapter04;

import java.util.concurrent.TimeUnit;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Function;

public class ConcatMapExample {
	public void usingConcatMap() { 
		Function<String, Observable<String>> ballToDoubleDiamonds = 
				ball -> Observable.zip(
					Observable.just(ball+ "<>", ball+ "<>"),
					Observable.interval(100L, TimeUnit.MILLISECONDS),
					(value,i) -> value					
				);
	
		Observable<String> source = ballsWithDelay()
				.concatMap(ballToDoubleDiamonds);
//				.flatMap(ballToDoubleDiamonds);  //for interleaving
//				.switchMap(ballToDoubleDiamonds);
		
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
	    	Thread.sleep(50);
	    	emitter.onNext("BLUE");
	    	emitter.onComplete();
	    }); 
	}
	
	public void bb() { 
		//Observable.range(1, 2).map(idx -> "XX" + idx).subscribe(System.out::println);
		Observable<String> source = Observable.range(1, 2).map(idx -> "XX" + idx);
		
		//.subscribe(System.out::println);
	}
	
	public static void main(String[] args) { 
		ConcatMapExample demo = new ConcatMapExample();
		demo.usingConcatMap();
//		demo.bb();
	}
}
