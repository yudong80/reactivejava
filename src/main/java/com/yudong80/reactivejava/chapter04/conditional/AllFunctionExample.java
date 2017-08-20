package com.yudong80.reactivejava.chapter04.conditional;

import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.Single;

import static com.yudong80.reactivejava.common.Shape.GREEN;
import static com.yudong80.reactivejava.common.Shape.RED;
import static com.yudong80.reactivejava.common.Shape.SKY;
import static com.yudong80.reactivejava.common.Shape.YELLOW;
import static com.yudong80.reactivejava.common.Shape.rectangle;

public class AllFunctionExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data = {RED, YELLOW, GREEN, SKY};
		
		Single<Boolean> source = Observable.fromArray(data)
			.map(Shape::getShape)
			.all(Shape.BALL::equals);
			//.all(val -> Shape.BALL.equals(Shape.getShape(val)));
		source.subscribe(Log::i);
	}
	
	public void wrongCase() { 
		String[] data = {RED, rectangle(YELLOW), GREEN, SKY};
		
		Single<Boolean> source = Observable.fromArray(data)
			.map(Shape::getShape)
			.doOnNext(Log::d)
			.doOnComplete(() -> Log.d("onComplete"))
			.all(Shape.BALL::equals)
			.doOnSuccess(v -> Log.d("onSuccess : val = " + v));
		
			//.all(val -> Shape.BALL.equals(Shape.getShape(val)));
		source.subscribe(Log::i);		
	}
	
	public static void main(String[] args) { 
		AllFunctionExample demo = new AllFunctionExample();
		demo.marbleDiagram();
		demo.wrongCase();
	}
}
