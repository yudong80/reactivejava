package com.yudong80.reactivejava.chapter03;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.YELLOW;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.BLUE;
import static com.yudong80.reactivejava.common.CommonUtils.toInt;

public class MapExample implements MarbleDiagram {
	@Override
	public void marbleDiagram() { 
		String[] balls = {RED, YELLOW, GREEN, BLUE}; //1, 2, 3, 5 
		Observable<String> source = Observable.fromArray(balls)
				.map(ball -> ball + "<>");
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void mapFunction() { 
		Function<String, String> getDiamond = ball -> ball + "<>";
		
		String[] balls = {RED, YELLOW, GREEN, BLUE}; //1, 2, 3, 5
		Observable<String> source = Observable.fromArray(balls)
				.map(getDiamond);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void mappingType() { 
		Function<String, Integer> getIndex = ball -> { 
			switch(ball){
			case "RED": 		return toInt(RED); //1
			case "YELLOW":		return toInt(YELLOW); //2
			case "GREEN":		return toInt(GREEN); //3
			case "BLUE":		return toInt(BLUE); //5 
			default:			return -1;
			}
		};
		
		String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"}; 
		Observable<Integer> source = Observable.fromArray(balls)
				.map(getIndex); //명시적인 타입 변환없이 바로 사용할 수 있음    
		source.subscribe(Log::i);  
		CommonUtils.exampleComplete();		
	}	
	
	public String getDiamond(String ball) { 
		return ball + "<>";
	}
		
	public static void main(String[] args) { 
		MapExample demo = new MapExample();
		demo.marbleDiagram();
		demo.mapFunction();
		demo.mappingType();
	}
}
