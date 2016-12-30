package com.yudong80.reactivejava.chapter02;

import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectExample {
	public void basic() { 
		BehaviorSubject<Integer> subject = BehaviorSubject.create();
		subject.subscribe(str -> System.out.println("#1 => " + str));
		subject.onNext(10);
		subject.onNext(20);
		subject.subscribe(str -> System.out.println("#2 => " + str));
		subject.onNext(30);
		subject.onComplete();
		subject.subscribe(str -> System.out.println("#3 => " + str));		
	}
	
	public static void main(String[] args) { 
		BehaviorSubjectExample demo = new BehaviorSubjectExample();
		demo.basic();
	}
}
