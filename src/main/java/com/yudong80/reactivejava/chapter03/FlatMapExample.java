package com.yudong80.reactivejava.chapter03;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.ThreeSubscribers;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FlatMapExample extends ThreeSubscribers implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		//함수를 별도로 정의하는 것이 가장 먼저 겪어야 할 관문임 
		Function<String, Observable<String>> ballToDoubleDiamonds = 
				ball -> Observable.just(ball + "<>", ball + "<>");
		
		String[] balls = {"RED", "GREEN", "BLUE"}; 
		Observable<String> source = Observable.fromArray(balls)
				.flatMap(ballToDoubleDiamonds);
		source.subscribe(firstSubscriber);
		CommonUtils.exampleComplete();
	}

	public void flatMapLambda() { 
		//Observable안에 Observable을 넣는다는 것이 직관적이지는 않음 		
		String[] balls = {"RED", "GREEN", "BLUE"}; 
		Observable<String> source = Observable.fromArray(balls)
				.flatMap(ball -> Observable.just(ball + "<>", ball + "<>"));
		source.subscribe(firstSubscriber);
		CommonUtils.exampleComplete();
	}
	
	
	public static void main(String[] args) { 
		FlatMapExample demo = new FlatMapExample();
		demo.marbleDiagram();
	}
}
