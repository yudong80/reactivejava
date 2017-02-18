package com.yudong80.reactivejava.chapter04.create;

import java.util.concurrent.Callable;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Observable;

public class DeferExample implements MarbleDiagram{
	private static int colorIdx = 0;
	
	@Override
	public void marbleDiagram() { 
		Callable<Observable<String>> supplier = () -> { 
			String[] colors = {"RED", "GREEN", "BLUE", "YELLOW"};
			colorIdx++; 
			if (colorIdx > colors.length) { 
				colorIdx = 0;
			}
			
			String[] shapes = {"", "-R", "-P"};
			
			return Observable.fromArray(shapes)
					.map(s -> colors[colorIdx] + s);
		};
		
		Observable<String> source = Observable.defer(supplier);
		
		source.subscribe(val -> Log.i("Oberserver #1:" + val));
		source.subscribe(val -> Log.i("Oberserver #2:" + val));	
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		DeferExample demo = new DeferExample();
		demo.marbleDiagram();
	}
}
