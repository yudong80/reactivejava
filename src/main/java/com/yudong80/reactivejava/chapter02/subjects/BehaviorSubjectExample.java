package com.yudong80.reactivejava.chapter02.subjects;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.subjects.BehaviorSubject;

import static com.yudong80.reactivejava.common.Shape.PUPPLE;
import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.BLUE;

public class BehaviorSubjectExample {
	public void marbleDiagram() { 
		BehaviorSubject<String> subject = BehaviorSubject.createDefault(PUPPLE);
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext(BLUE);
		subject.onComplete();
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		BehaviorSubjectExample demo = new BehaviorSubjectExample();
		demo.marbleDiagram();
	}
}
