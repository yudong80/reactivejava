package com.yudong80.reactivejava.chapter02.subjects;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.BLUE;

public class AsyncSubjectExample {
	public void marbleDiagram() { 
		AsyncSubject<String> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => "+ data));
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => "+ data));
		subject.onNext(BLUE);
		subject.onComplete();
		CommonUtils.exampleComplete();
	}
	
	public void asSubscriber() { 
		Float[] temperature = { 10.1f, 13.4f, 12.5f  };
		Observable<Float> source = Observable.fromArray(temperature);
		
		AsyncSubject<Float> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));	
		
		source.subscribe(subject);
		CommonUtils.exampleComplete();		
	}
	
	public void afterComplete() { 
		AsyncSubject<Integer> subject = AsyncSubject.create();
		subject.onNext(10);
		subject.onNext(11);
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext(12);
		subject.onComplete();
		subject.onNext(13);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		AsyncSubjectExample demo = new AsyncSubjectExample();
		demo.marbleDiagram(); 
		demo.asSubscriber();
		demo.afterComplete();
	}
}
