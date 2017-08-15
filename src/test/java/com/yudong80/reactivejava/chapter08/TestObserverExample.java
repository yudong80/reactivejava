package com.yudong80.reactivejava.chapter08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.YELLOW;
import static com.yudong80.reactivejava.common.Shape.BLUE;
import static com.yudong80.reactivejava.common.Shape.rectangle;
import static com.yudong80.reactivejava.common.Shape.triangle;

@RunWith(JUnitPlatform.class)
public class TestObserverExample {
	
	@DisplayName("#1: using TestObserver for Shape.getShape()")
	@Test
	void testGetShapeUsingTestObserver() { 
		String[] data = {RED, rectangle(BLUE), triangle(YELLOW)};
		Observable<String> source = Observable.fromArray(data)
				.map(Shape::getShape);

		String[] expected = {Shape.BALL, Shape.RECTANGLE, Shape.TRIANGLE};		
		source.test()
		.assertResult(expected);		
	}
	
	@DisplayName("assertFailure() example")
	@Test
	void assertFailureExample() { 
		String[] data = {"100", "200", "%300"};
		Observable<Integer> source = Observable.fromArray(data)
				.map(Integer::parseInt);
		
		source.test()
		.assertFailure(NumberFormatException.class, 100, 200);
	}
	
	@DisplayName("assertFailureAndMessage() example")
	@Test
	void assertFailureAndMessage() { 
		String[] data = {"100", "200", "%300"};
		Observable<Integer> source = Observable.fromArray(data)
				.map(Integer::parseInt);
		
		source.test()
		.assertFailureAndMessage(NumberFormatException.class, 
				"For input string: \"%300\"", 
				100, 200);		
	}

	@DisplayName("assertComplete() example")
	@Test
	void assertComplete() { 
		Observable<String> source = Observable.create(
			(ObservableEmitter<String> emitter) -> { 
				emitter.onNext("Hello RxJava");
				emitter.onComplete();
			});
		source.test().assertComplete();
	}
}
