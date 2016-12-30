package com.yudong80.reactivejava.chapter02;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class AsyncSubjectExample {
	public void create() { 
		AsyncSubject<Integer> subject = AsyncSubject.create();
		subject.onNext(10);
		subject.onNext(11);
		subject.onNext(12);
		subject.onComplete();
		subject.subscribe(System.out::println);
	}
	
	public void fromArray() { 
		AsyncSubject<Float> subject = AsyncSubject.create();
		Float[] temperature = { 10.1f, 13.4f, 12.5f  };
		Observable<Float> source = Observable.fromArray(temperature);
		source.subscribe(subject);
		subject.subscribe(System.out::println);	
	}
	
	public void multiSubscribed() { 
		AsyncSubject<Integer> subject = AsyncSubject.create();
		subject.onNext(10);
		subject.onNext(11);
		subject.subscribe(str -> System.out.println("#1 => " + str));
		subject.onNext(12);
		subject.onComplete();
		subject.subscribe(str -> System.out.println("#2 => " + str));		
	}
	
	public static void main(String[] args) { 
		AsyncSubjectExample demo = new AsyncSubjectExample();
		demo.create(); 
		CommonUtils.dash();
		demo.fromArray();
		CommonUtils.dash();
		demo.multiSubscribed();
	}
}
