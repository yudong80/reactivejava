package com.yudong80.reactivejava.chapter03;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.yudong80.reactivejava.common.CommonUtils;
import com.yudong80.reactivejava.common.MarbleDiagram;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class ReduceExample implements MarbleDiagram {
	public void marbleDiagram() { 
		String[] balls = {"RED", "GREEN", "BLUE"};
		Maybe<String> source = Observable.fromArray(balls)
				.reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void usingBiFunction() {
		BiFunction<String, String, String> mergeBalls = 
				(ball1, ball2) -> ball2 + "(" + ball1 + ")";
		
		String[] balls = {"RED", "GREEN", "BLUE"};
		Maybe<String> source = Observable.fromArray(balls)
				.reduce(mergeBalls);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void queryTvSales() { 
		//1. 데이터 입력 
		List<Pair<String,Integer>> sales = new ArrayList<>();
		sales.add(Pair.of("TV", 2500));
		sales.add(Pair.of("Camera", 300));
		sales.add(Pair.of("TV", 1600));
		sales.add(Pair.of("Phone", 800));
		
		Maybe<Integer> tvSales = Observable.fromIterable(sales)
				//2. 매출 데이터중 TV 매출을 필터링 함 
				.filter(sale -> "TV".equals(sale.getLeft()))
				.map(sale -> sale.getRight())
				//3. TV 매출의 합을 구함 
				.reduce((sale1, sale2) -> sale1 + sale2);		
		tvSales.subscribe(tot -> System.out.println("TV Sales: " + tot + "$"));		
		CommonUtils.exampleComplete();
	}
	
	class ProductSales { 
		private String mProduct;
		private int mSale;
		
		public String getProduct() {
			return mProduct;
		}
		public void setProduct(String product) {
			this.mProduct = product;
		}
		public int getSale() {
			return mSale;
		}
		public void setSale(int sale) {
			this.mSale = sale;
		} 	
	}
	
	public void queryTvSalesUsingClass() { 
		
	}
	
	public static void main(String[] args){ 
		ReduceExample demo = new ReduceExample();
		demo.marbleDiagram();
		demo.usingBiFunction();
		demo.queryTvSales();
	}
}
