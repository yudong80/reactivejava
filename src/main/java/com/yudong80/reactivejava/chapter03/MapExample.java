package com.yudong80.reactivejava.chapter03;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MapExample implements MarbleDiagram {
	@Override
	public void marbleDiagram() { 
		String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"}; 
		Observable<String> source = Observable.fromArray(balls)
				.map(ball -> ball + "<>");
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void mapFunction() { 
		Function<String, String> getDiamond = ball -> ball + "<>";
		
		String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(balls)
				.map(getDiamond);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void mappingType() { 
		Function<String, Integer> getIndex = ball -> { 
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
				.map(getIndex);    
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
