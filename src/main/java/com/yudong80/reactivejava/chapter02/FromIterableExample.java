package com.yudong80.reactivejava.chapter02;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FromIterableExample {
	public void run() { 
		//1. List 
		List<String> names = new ArrayList<>();
		names.add("Jerry");
		names.add("William");
		names.add("Bob");
		
		Observable<String> source = Observable.fromIterable(names);
		source.subscribe(System.out::println);
		
		//2. Set 
		
		//3. BlockingQueue
		
		
	}
	
	public static void main(String[] args){ 
		new FromIterableExample().run();
	}	
}
