package com.yudong80.reactivejava.chapter03;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;

public class FilterExample implements MarbleDiagram {
	@Override
	public void marbleDiagram() { 
		String[] objs = {"RED CIRCLE", "YELLOW DIAMOND", "GREEN TRIANGLE", 
				"SKY DIAMOND", "BLUE CIRCLE", "PUPPLE HEXAGON"};
		Observable<String> source = Observable.fromArray(objs)
				.filter(obj -> obj.endsWith("CIRCLE"));
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}

	public void usingPredicate() {
		Predicate<String> filterCircle = obj -> obj.endsWith("CIRCLE");
		
		String[] objs = {"RED CIRCLE", "YELLOW DIAMOND", "GREEN TRIANGLE", 
				"SKY DIAMOND", "BLUE CIRCLE", "PUPPLE HEXAGON"};
		Observable<String> source = Observable.fromArray(objs)
				.filter(filterCircle);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void evenNumbers() { 
		Integer[] data = {100, 34, 27, 99, 50};
		Observable<Integer> source = Observable.fromArray(data)
				.filter(number -> number % 2 == 0);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void otherFilters() { 
		Integer[] numbers = {100, 200, 300, 400, 500};
		Single<Integer> single;
		Observable<Integer> source;
				
		//1. first 
		single = Observable.fromArray(numbers).first(-1);
		single.subscribe(data -> System.out.println("first() value = " + data));
		
		//2. last 
		single = Observable.fromArray(numbers).last(999);
		single.subscribe(data -> System.out.println("last() value = " + data));
		
		//3. take(N) 
		source = Observable.fromArray(numbers).take(3);
		source.subscribe(data -> System.out.println("take(3) values =" + data));

		//4. takeLast(N) 
		source = Observable.fromArray(numbers).takeLast(3);
		source.subscribe(data -> System.out.println("takeLast(3) values =" + data));
		
		//5. skip(N) 
		source = Observable.fromArray(numbers).skip(2);
		source.subscribe(data -> System.out.println("skip(2) values = " + data));
		
		//6. skipLast(N) 
		source = Observable.fromArray(numbers).skipLast(2);
		source.subscribe(data -> System.out.println("skipLast(2) values = " + data));
		CommonUtils.exampleComplete();
	}	
	
	public static void main(String[] args) { 
		FilterExample demo = new FilterExample();
//		demo.marbleDiagram();
//		demo.usingPredicate();
//		demo.evenNumbers();
		demo.otherFilters();
	}
}
