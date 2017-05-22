package com.yudong80.reactivejava.chapter04.create;

import java.util.concurrent.Callable;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;

public class DeferExample implements MarbleDiagram{
	class ShapeSupplier { 
		private String[] colors = {"RED", "GREEN", "BLUE", "YELLOW"};
		private int index = 0;
		
		//색상이 있는 도형을 발행하는 옵저버블을 생성합니다.
		public Observable<String> getObservable() {
			String color = getNextColor();
			return Observable.just(
				Shape.getString(color, Shape.BALL),
				Shape.getString(color, Shape.RECTANGLE),
				Shape.getString(color, Shape.PENTAGON));
		}
		
		private String getNextColor() { 
			String color =colors[index];
			index++;
			if (index > colors.length) { 
				index = 0;
			}
			return color;
		}
	}
	
	@Override
	public void marbleDiagram() { 
		ShapeSupplier ss = new ShapeSupplier();
		Callable<Observable<String>> supplier = () -> ss.getObservable();
		
		Observable<String> source = Observable.defer(supplier);
		
		source.subscribe(val -> Log.i("Subscriber #1:" + val));
		source.subscribe(val -> Log.i("Subscriber #2:" + val));
		source.subscribe(val -> Log.i("Subscriber #3:" + val));
		CommonUtils.exampleComplete();
	}
	
	public void notDeferred() { 
		ShapeSupplier ss = new ShapeSupplier();
		Observable<String> source = ss.getObservable();

		source.subscribe(val -> Log.i("Subscriber #1:" + val));
		source.subscribe(val -> Log.i("Subscriber #2:" + val));
		source.subscribe(val -> Log.i("Subscriber #3:" + val));
		CommonUtils.exampleComplete();
		
	}
	
	public static void main(String[] args) { 
		DeferExample demo = new DeferExample();
//		demo.marbleDiagram();
		demo.notDeferred();
	}
}
