package com.yudong80.reactivejava.chapter04.transform;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.MarbleDiagram;
import com.yudong80.reactivejava.common.Shape;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

import static com.yudong80.reactivejava.common.Shape.PUPPLE;
import static com.yudong80.reactivejava.common.Shape.SKY;
import static com.yudong80.reactivejava.common.Shape.YELLOW;
import static com.yudong80.reactivejava.common.Shape.triangle;

public class GroupByExample implements MarbleDiagram{
	@Override
	public void marbleDiagram() { 
		String[] objs = {PUPPLE, SKY, triangle(YELLOW), YELLOW, triangle(PUPPLE), triangle(SKY)};
		Observable<GroupedObservable<String, String>> source = 
				Observable.fromArray(objs)
				.groupBy(Shape::getShape);
		
		source.subscribe(obj -> {
			obj.subscribe(val -> 
			System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});
		CommonUtils.exampleComplete();
	}
	
	public void filterBallGroup() { 
		String[] objs = {PUPPLE, SKY, triangle(YELLOW), YELLOW, triangle(PUPPLE), triangle(SKY)};
		Observable<GroupedObservable<String, String>> source = 
				Observable.fromArray(objs)
				.groupBy(Shape::getShape);
		
		source.subscribe(obj -> {
			obj.filter(val -> obj.getKey().equals(Shape.BALL))
			.subscribe(val -> 
			System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});	
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		GroupByExample demo = new GroupByExample();
		demo.marbleDiagram();
		demo.filterBallGroup();
	}
}
