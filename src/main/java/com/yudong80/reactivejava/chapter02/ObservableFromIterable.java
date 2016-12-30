package com.yudong80.reactivejava.chapter02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import io.reactivex.Observable;

public class ObservableFromIterable {
	public void forList() { 
		List<String> names = new ArrayList<>();
		names.add("Jerry");
		names.add("William");
		names.add("Bob");
		Observable<String> source = Observable.fromIterable(names);
		source.subscribe(System.out::println);
	}
	
	public void forSet() { 
		Set<String> cities = new HashSet<>();
		cities.add("Seoul");
		cities.add("London");
		cities.add("Paris");
		Observable<String> source = Observable.fromIterable(cities);
		source.subscribe(System.out::println);		
	}
	
	public void forBlockingQueue() { 
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
		queue.add("ORD-1");
		queue.add("ORD-2");
		queue.add("ORD-3");
		Observable<String> source = Observable.fromIterable(queue);
		source.subscribe(System.out::println);				
	}
	
	public static void main(String[] args){ 
		ObservableFromIterable demo = new ObservableFromIterable();
		demo.forList();
		demo.forSet();
		demo.forBlockingQueue();
	}	
}
