package com.yudong80.reactivejava.chapter08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.yudong80.reactivejava.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

@RunWith(JUnitPlatform.class)
public class TestObserverExample {
	
	@DisplayName("assertResult() : getShape()")
	@Test
	void assertResultGetShape() { 
		String[] data = {"RED", "BLUE-R", "YELLOW-T"};
		Observable<String> source = Observable.fromArray(data)
				.map(CommonUtils::getShape);

		String[] expected = {"BALL", "RECTANGLE", "TRIANGLE"};		
		source.test()
		.assertResult(expected);		
	}
	
	@DisplayName("assertFailure() example")
	@Test
	void assertFailure() { 
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
				"XXXFor input string: \"%300\"", 
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
