package com.yudong80.reactivejava.chapter02;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Order;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleExample {
	public void just() { 
		Single<String> source = Single.just("Hello Single");
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void fromObservable() {
		//1. Single.fromObservable() 
		Observable<String> source = Observable.just("Hello Single");
		Single.fromObservable(source)
			.subscribe(System.out::println);
		
		//2. Observable.just() -> Single 
		Observable.just("Hello Single")
			.single("default item")
			.subscribe(System.out::println);
		
		//3. Observable.fromArray() -> Single 
		String[] colors = {"Red", "Blue", "Gold"};
		Observable.fromArray(colors)
			.first("default value")
			.subscribe(System.out::println);
		
		//4. Observabke.empty() -> Single 
		Observable.empty()
			.single("default value")
			.subscribe(System.out::println);
		
		//5. Observable.take() -> Single 
		Observable.just(new Order("ORD-1"), new Order("ORD-2"))
				.take(1)
				.single(new Order("default order"))
				.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}

	public void errorCase() {
		Single<String> source = Observable.just("Hello Single", "Error").single("default item");
		source.subscribe(System.out::println);
	}	
		
	public static void main(String[] args) { 
		SingleExample demo = new SingleExample();
//		demo.just();
		demo.fromObservable();
	}
}
