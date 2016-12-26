package com.yudong80.reactivejava.chapter02;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FromArrayExample {
	public void run() { 
		//1. Integer array 
		Observable<Integer> source = Observable.fromArray(
				new Integer[] {100,200,300});
		source.subscribe(System.out::println);
		
		//2. int array ????
		int[] intSource = { 100, 200, 300};	
		Observable.fromArray(intSource).subscribe(System.out::println);
	}
	
	public static void main(String[] args){ 
		new FromArrayExample().run();
	}	
}
