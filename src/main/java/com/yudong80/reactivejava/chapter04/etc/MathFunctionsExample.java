package com.yudong80.reactivejava.chapter04.etc;

import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class MathFunctionsExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		Integer[] data = {1, 2, 3, 4};
		
		//1. count 
		Single<Long> source = Observable.fromArray(data)
				.count();
		source.subscribe(count -> Log.i("count is " + count));
		
		//2. max() & min() 
		Flowable.fromArray(data)
			.to(MathFlowable::max)
			.subscribe(max -> Log.i("max is " + max));
		
		Flowable.fromArray(data)
			.to(MathFlowable::min)
			.subscribe(min -> Log.i("min is " + min));
		
		//3. sum() & average 
		Flowable<Integer> flowable = Flowable.fromArray(data)
				.to(MathFlowable::sumInt);
		flowable.subscribe(sum -> Log.i("sum is " + sum));
	
		Flowable<Double> flowable2 = Observable.fromArray(data) 
				.toFlowable(BackpressureStrategy.BUFFER)
				.to(MathFlowable::averageDouble);
		flowable2.subscribe(avg -> Log.i("average is " + avg));		
	}
	
	public static void main(String[] args) { 
		MathFunctionsExample demo = new MathFunctionsExample();
		demo.marbleDiagram();
	}
}
