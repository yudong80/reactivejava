package com.yudong80.reactivejava.chapter03;

import java.util.Scanner;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Gugudan {
	public void plainJava() { 
		Scanner in = new Scanner(System.in);
		System.out.println("Gugudan Input:");
		int dan = Integer.parseInt(in.nextLine());
		for (int row=1; row<= 9; ++row) { 
			System.out.println(dan + " * " + row + " = " + dan * row);
		}
		in.close();
	}

	public void reactiveV1() { 
		Scanner in = new Scanner(System.in);
		System.out.println("Gugudan Input:");
		int dan = Integer.parseInt(in.nextLine());				
		Observable<Integer> source = Observable.range(1, 9);
		source.subscribe(row -> System.out.println(dan + " * " + row + " = " + dan * row)); 
		in.close();
	}					
	
	public void reactiveV2() { 
		Scanner in = new Scanner(System.in);
		System.out.println("Gugudan Input:");
		int dan = Integer.parseInt(in.nextLine());				
		
		Function<Integer,Observable<String>> gugudan = num -> 
			Observable.range(1,9)
			   		  .map(row -> num + " * " + row + " = " + num*row);			
		Observable<String> source = Observable.just(dan).flatMap(gugudan);
		source.subscribe(System.out::println);
		in.close();
	}					

	public void reactiveV3() { 
		Scanner in = new Scanner(System.in);
		System.out.println("Gugudan Input:");
		int dan = Integer.parseInt(in.nextLine());				
			
		Observable<String> source = Observable.just(dan)
				.flatMap(num -> Observable.range(1,9)
		   		  			  .map(row -> num + " * " + row + " = " + dan*row));
		source.subscribe(System.out::println);
		in.close();
	}					
		
	public void usingResultSelector() { 
		Scanner in = new Scanner(System.in);
		System.out.println("Gugudan Input:");
		int dan = Integer.parseInt(in.nextLine());		
		Observable<String> source = Observable.just(dan)
				.flatMap(gugu -> Observable.range(1,9),
						(gugu, i) -> gugu + " * " + i + " = " + gugu*i);
		source.subscribe(System.out::println);
		in.close();
	}					
					
	public static void main(String[] args) { 
		Gugudan demo = new Gugudan();
//		demo.plainJava();
//		demo.reactiveV1();
//		demo.reactiveV2();
		demo.reactiveV3();
//		demo.usingResultSelector();
	}
}
