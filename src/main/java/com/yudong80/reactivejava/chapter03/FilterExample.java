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
	
	public void filterEvenNumber() { 
		Integer[] data = {100, 34, 27, 99, 50};
		Observable<Integer> source = Observable.fromArray(data)
				.filter(number -> number % 2 == 0);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void showFilters() { 
		Integer[] data = {100, 200, 300, 400, 500};
		
		//>> 결과가 예쁘게 나오게 소스를 다등자. 
		
		//1. first 
		Single<Integer> source1 = Observable.fromArray(data).first(-1);
		source1.subscribe(System.out::println);
		
		//2. last 
		source1 = Observable.fromArray(data).last(999);
		source1.subscribe(System.out::println);
		
		//3. take(N) 
		Observable<Integer> source2 = Observable.fromArray(data).take(3);
		source2.subscribe(System.out::println);

		//4. takeLast(N) 
		source2 = Observable.fromArray(data).takeLast(3);
		source2.subscribe(System.out::println);
		
		//5. skip(N) 
		source2 = Observable.fromArray(data).skip(2);
		source2.subscribe(System.out::println);
		
		//6. skipLast(N) 
		source2 = Observable.fromArray(data).skipLast(2);
		source2.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}	
	
	public static void main(String[] args) { 
		FilterExample demo = new FilterExample();
		demo.marbleDiagram();
		demo.usingPredicate();
		demo.filterEvenNumber();
		demo.showFilters();
	}
}
