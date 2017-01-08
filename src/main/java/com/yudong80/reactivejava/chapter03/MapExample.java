package com.yudong80.reactivejava.chapter03;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.ThreeSubscribers;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MapExample extends ThreeSubscribers {
	public void basic() { 
		String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"}; 
		Observable<String> source = Observable.fromArray(balls)
				.map(ball -> ball + "<>");
		source.subscribe(firstSubscriber);
		CommonUtils.exampleComplete();
	}
	
	public void mapWithFunction() { 
		Function<String, String> ballToDiamond = ball -> ball + "<>";
		
		String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(balls)
				.map(ballToDiamond);
		source.subscribe(firstSubscriber);
		CommonUtils.exampleComplete();
	}
	
	public void mapTypeConversion() { 
		Function<String, Integer> ballToIndex = ball -> { 
			switch(ball){
			case "RED": 		return 0;
			case "YELLOW":		return 1;
			case "GREEN":		return 2;
			case "BLUE":		return 3;
			default:			return -1;
			}
		};
		
		String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"}; 
		Observable<Integer> source = Observable.fromArray(balls)
				.map(ballToIndex);   //명시적인 형 변환없이 바로 사용가능 
		source.subscribe(System.out::println);  
		CommonUtils.exampleComplete();		
	}	
	
	public String getDiamond(String ball) { 
		return ball + "<>";
	}
		
	public static void main(String[] args) { 
		MapExample demo = new MapExample();
		demo.basic();
		demo.mapWithFunction();
		demo.mapTypeConversion();
	}
}
