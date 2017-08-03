package com.yudong80.reactivejava.chapter03;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.BLUE;

public class ReduceExample implements MarbleDiagram {
	public void marbleDiagram() { 
		String[] balls = {RED, GREEN, BLUE}; //1,3,5
		Maybe<String> source = Observable.fromArray(balls)
				.reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void biFunction() {
		BiFunction<String, String, String> mergeBalls = 
				(ball1, ball2) -> ball2 + "(" + ball1 + ")";
		
		String[] balls = {RED, GREEN, BLUE};
		Maybe<String> source = Observable.fromArray(balls)
				.reduce(mergeBalls);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args){ 
		ReduceExample demo = new ReduceExample();
		demo.marbleDiagram();
		demo.biFunction();
	}
}
