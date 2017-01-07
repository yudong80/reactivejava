package com.yudong80.reactivejava.chapter03;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.ThreeSubscribers;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FlatMapExample extends ThreeSubscribers{
	public void basic() { 
		Function<String, Observable<String>> ballToDoubleDiamonds = 
				ball -> Observable.just(ball + "<>", ball + "<>");
		
		String[] balls = {"RED", "GREEN", "BLUE"}; 
		Observable<String> source = Observable.fromArray(balls)
				.flatMap(ballToDoubleDiamonds);
		source.subscribe(firstSubscriber);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		FlatMapExample demo = new FlatMapExample();
		demo.basic();
	}
}
