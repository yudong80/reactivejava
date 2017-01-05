package com.yudong80.reactivejava.chapter02;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleExample {
	public void justToSingle() {
		Single<String> source = Observable.just("Hello Single").single("default item");
		source.subscribe(System.out::println);
	}

	public void justToSingleError() {
		Single<String> source = Observable.just("Hello Single", "Error").single("default item");
		source.subscribe(System.out::println);
	}	
	
	public void fromArrayToSingle() { 
		String[] colors = {"Red", "Blue", "Gold"};
		Single<String> source = Observable.fromArray(colors).first("default arr");
		source.subscribe(System.out::println);		
	}
	
	public void emptyToSingle() { 
		Observable<String> emptySource = Observable.empty();
		Single<String> source = emptySource.single("default from empty");
		source.subscribe(System.out::println);				
	}
	
	public void neverToSingle() { 
		Observable<String> neverSource = Observable.never();
		Single<String> source = neverSource.single("default from never");
		source.subscribe(System.out::println);						
	}
	
	public static void main(String[] args) { 
		SingleExample demo = new SingleExample();
		demo.justToSingle();
		//demo.justToSingleError();  //error case 
		demo.fromArrayToSingle();
		demo.emptyToSingle();
		demo.neverToSingle();
	}
}
