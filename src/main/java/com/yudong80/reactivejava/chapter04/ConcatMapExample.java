package com.yudong80.reactivejava.chapter04;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class ConcatMapExample {
	public void usingConcatMap() { 
		List<Pair<String,Integer>> balls = new ArrayList<>();
		balls.add(Pair.of("RED", 300));
		balls.add(Pair.of("GREEN", 100));
		balls.add(Pair.of("BLUE", 100));
		
		//Observable<String> ballsWithDelay = 
		
		try { 
			Thread.sleep(1000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		
		CommonUtils.exampleComplete();
	}
	
	public static Observable<String> ballsWithDelay(Pair<String, Integer> balls) { 
		return Observable.create((ObservableEmitter<String> emitter) -> { 
	    	emitter.onNext("1000");
	    	emitter.onComplete();
	    }); 
	}
	
	public static void main(String[] args) { 
		ConcatMapExample demo = new ConcatMapExample();
		demo.usingConcatMap();
	}
}
