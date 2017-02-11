package com.yudong80.reactivejava.chapter02.subjects;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample {
	public void basicUsage() { 
		ReplaySubject<String> subject = ReplaySubject.create();
		subject.subscribe(str -> System.out.println("#1 => " + str));
		subject.onNext("Red");
		subject.onNext("Green");
		subject.subscribe(str -> System.out.println("#2 => " + str));
		subject.onNext("Blue");
		subject.onComplete();
	}
	
	public static void main(String[] args) { 
		ReplaySubjectExample demo = new ReplaySubjectExample();
		demo.basicUsage();
	}
}
