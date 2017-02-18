package com.yudong80.reactivejava.chapter04.combine;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.text.DecimalFormat;

import org.apache.commons.lang3.tuple.Pair;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.Log;

import io.reactivex.Observable;

public class ElectricBills {
	private int index = 0; //FIXME don't use it 
	
	public void electricBillV1() { 
		String[] data = {
			"100",  //910 + 93.3 * 100 = 10,240원  
			"300"   //1600 + 93.3 * 200 + 187.9 * 100 = 39,050원 
		};
		
		Observable<Integer> basePrice = Observable.fromArray(data)
				.map(Integer::parseInt)
				.map(val -> { 
					if (val <= 200) return 910;
					if (val <= 400) return 1600;
					return 7300;
				});
		
		Observable<Integer> usagePrice = Observable.fromArray(data)
				.map(Integer::parseInt)
				.map(val -> { 
					double series1 = min(200, val) * 93.3;
					double series2 = min(200, max(val-200, 0)) * 187.9;
					double series3 = min(0, max(val-400, 0)) * 280.65;
					return (int)(series1 + series2 + series3);
				});
		
		Observable<Integer> source = Observable.zip(
				basePrice, 
				usagePrice,
				(v1, v2) -> v1 + v2);
		
		//print the result 
		source.map(val -> new DecimalFormat("#,###").format(val))
		.subscribe(val -> { 
			StringBuilder sb = new StringBuilder();
			sb.append("Usage: " + data[index] + " kWh => ");
			sb.append("Price: " + val + "원");
			Log.i(sb.toString());
			
			index++; //FIXME side effect!!!! 
		});
		CommonUtils.exampleComplete();
	}
	
	public void electricBillV2() { 
		String[] data = {
			"100",  //910 + 93.3 * 100 = 10,240원  
			"300"   //1600 + 93.3 * 200 + 187.9 * 100 = 39,050원 
		};
		
		Observable<Integer> basePrice = Observable.fromArray(data)
				.map(Integer::parseInt)
				.map(val -> { 
					if (val <= 200) return 910;
					if (val <= 400) return 1600;
					return 7300;
				});
		
		Observable<Integer> usagePrice = Observable.fromArray(data)
				.map(Integer::parseInt)
				.map(val -> { 
					double series1 = min(200, val) * 93.3;
					double series2 = min(200, max(val-200, 0)) * 187.9;
					double series3 = min(0, max(val-400, 0)) * 280.65;
					return (int)(series1 + series2 + series3);
				});
		
		Observable<Pair<String, Integer>> source = Observable.zip(
				basePrice, 
				usagePrice,
				Observable.fromArray(data),
				(v1, v2, i) -> Pair.of(i, v1+v2));
		
		//print the result 
		source.map(val -> Pair.of(val.getLeft(), 
					new DecimalFormat("#,###").format(val.getValue())))
		.subscribe(val -> { 
			StringBuilder sb = new StringBuilder();
			sb.append("Usage: " + val.getLeft() + " kWh => ");
			sb.append("Price: " + val.getRight() + "원");
			Log.i(sb.toString());
		});
		CommonUtils.exampleComplete();
	}
	
	
	public static void main(String[] args) { 
		ElectricBills demo = new ElectricBills();
		demo.electricBillV1();
		demo.electricBillV2();
	}
}
