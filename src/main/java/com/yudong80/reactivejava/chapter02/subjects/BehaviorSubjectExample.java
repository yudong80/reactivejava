package com.yudong80.reactivejava.chapter02.subjects;

import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectExample {
	public void basic() { 
		BehaviorSubject<String> subject = BehaviorSubject.createDefault("Pupple");
		subject.subscribe(str -> System.out.println("#1 => " + str));
		subject.onNext("Red");
		subject.onNext("Green");
		subject.subscribe(str -> System.out.println("#2 => " + str));
		subject.onNext("Blue");
		subject.onComplete();
	}
	
	public static void main(String[] args) { 
		BehaviorSubjectExample demo = new BehaviorSubjectExample();
		demo.basic();
	}
}
