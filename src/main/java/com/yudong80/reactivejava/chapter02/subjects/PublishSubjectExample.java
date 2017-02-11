package com.yudong80.reactivejava.chapter02.subjects;

import io.reactivex.subjects.PublishSubject;

public class PublishSubjectExample {
	public void basicUsage() { 
		PublishSubject<String> subject = PublishSubject.create();
		subject.subscribe(str -> System.out.println("#1 => " + str));
		subject.onNext("Red");
		subject.onNext("Green");
		subject.subscribe(str -> System.out.println("#2 => " + str));
		subject.onNext("Blue");
		subject.onComplete();
	}
	
	public static void main(String[] args) { 
		PublishSubjectExample demo = new PublishSubjectExample();
		demo.basicUsage();
	}
}
