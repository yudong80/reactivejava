package com.yudong80.reactivejava.chapter02;

import java.util.stream.IntStream;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;

public class ObservableFromArray {
	public void integerArray() { 
		Observable<Integer> source = Observable.fromArray(
				new Integer[] {100,200,300});
		source.subscribe(System.out::println);		
		CommonUtils.exampleComplete();
	}
	
	public void intArray() {	
		int[] intArray = { 400, 500, 600};
		Observable<Integer> source = Observable.fromArray(toIntegerArray(intArray));
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void intArrayWrong() { 
		int[] intArray = { 400, 500, 600};
		Observable.fromArray(intArray).subscribe(System.out::println);		
		CommonUtils.exampleComplete();
	}
	
	private static Integer[] toIntegerArray(int[] intArray) { 
		return IntStream.of(intArray).boxed().toArray(Integer[]::new);
	}
	
	public static void main(String[] args){ 
		ObservableFromArray demo = new ObservableFromArray();
		demo.integerArray();
		demo.intArray();
		demo.intArrayWrong();
	}	
}
