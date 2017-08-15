package com.yudong80.reactivejava.chapter08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;

import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.YELLOW;
import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.rectangle;
import static com.yudong80.reactivejava.common.Shape.triangle;

@RunWith(JUnitPlatform.class)
public class JUnit5Basic {
	@DisplayName("JUnit5 First Example")
	@Test 
	void testFirst() { 
		int expected = 3; 
		int actual = 1 + 2;
		assertEquals(expected, actual);
	}
	
	@DisplayName("test getShape() Observable")
	@Test
	void testGetShapeObservable() { 
		String[] data = {RED, rectangle(YELLOW), triangle(YELLOW)};
		Observable<String> source = Observable.fromArray(data)
				.map(Shape::getShape);
		
		String[] expected = {Shape.BALL, Shape.RECTANGLE, Shape.TRIANGLE};
		List<String> actual = new ArrayList<>();
		source.doOnNext(Log::d)
		.subscribe(actual::add);
		
		assertEquals(Arrays.asList(expected), actual);
	}
}
