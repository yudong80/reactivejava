package com.yudong80.reactivejava.chapter04.etc;

import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AllFunctionExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] data = {"RED", "YELLOW", "GREEN", "SKY"};
		
		Single<Boolean> source = Observable.fromArray(data)
			.map(Shape::getShape)
			.all(Shape.BALL::equals);
			//.all(val -> Shape.BALL.equals(Shape.getShape(val)));
		source.subscribe(Log::i);
	}
	
	public static void main(String[] args) { 
		AllFunctionExample demo = new AllFunctionExample();
		demo.marbleDiagram();
	}
}
