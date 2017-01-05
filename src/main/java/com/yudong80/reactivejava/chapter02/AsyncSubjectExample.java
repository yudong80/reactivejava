package com.yudong80.reactivejava.chapter02;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class AsyncSubjectExample {
	public void basicUsage() { 
		AsyncSubject<String> subject = AsyncSubject.create();
		subject.subscribe(str -> System.out.println("#1 => "+ str));
		subject.onNext("Red");
		subject.onNext("Green");
		subject.subscribe(str -> System.out.println("#2 => "+ str));
		subject.onNext("Blue");
		subject.onComplete();
		CommonUtils.printDash();
	}
	
	public void subscribeObservable() { 
		Float[] temperature = { 10.1f, 13.4f, 12.5f  };
		Observable<Float> source = Observable.fromArray(temperature);
		
		AsyncSubject<Float> subject = AsyncSubject.create();
		source.subscribe(subject);
		subject.subscribe(System.out::println);	
		CommonUtils.printDash();		
	}
	
	public void multiSubscribed() { 
		AsyncSubject<Integer> subject = AsyncSubject.create();
		subject.onNext(10);
		subject.onNext(11);
		subject.subscribe(str -> System.out.println("#1 => " + str));
		subject.onNext(12);
		subject.onComplete();
		subject.subscribe(str -> System.out.println("#2 => " + str));
		CommonUtils.printDash();		
	}
	
	public static void main(String[] args) { 
		AsyncSubjectExample demo = new AsyncSubjectExample();
		demo.basicUsage(); 
		demo.subscribeObservable();
		demo.multiSubscribed();
	}
}
