package com.yudong80.reactivejava.chapter03;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class ElectricBills {
	public void basic() { 
		Function<Integer, Double> elecOrdniary = use -> { 
			return  
				(410 + Math.max(use - 0,   100) * 60.7) + 
				(910 + Math.max(Math.max(use - 100, 100),0) * 125.9) +
				(1600 + Math.max(Math.max(use - 200, 100),0) * 187.9) + 
				(3850 + Math.max(Math.max(use - 300, 100),0) * 280.6) + 
				(7300 + Math.max(Math.max(use - 400, 100),0) * 417.7) + 
				(12940 + Math.max((use - 500),0) * 709.5);			
		};

		Function<Integer, Double> elec7to9 = use -> { 
			return  
				(410 + Math.max(use - 0,   150) * 60.7) + 
				(910 + Math.max(Math.max(use - 150, 100),0) * 125.9) +
				(1600 + Math.max(Math.max(use - 250, 100),0) * 187.9) + 
				(3850 + Math.max(Math.max(use - 350, 100),0) * 280.6) + 
				(7300 + Math.max(Math.max(use - 450, 100),0) * 417.7) + 
				(12940 + Math.max((use - 550),0) * 709.5);			
		};
	
		Function<ElectricUsage, Double> collectMoney = usage -> {
			if(7 <= usage.month || usage.month <= 9)
				return elec7to9.apply(usage.usage);
			else 
				return elecOrdniary.apply(usage.usage);
		};
		
		List<ElectricUsage> thisYear = new ArrayList<>();
		thisYear.add(new ElectricUsage(2016, 1, 120));
		thisYear.add(new ElectricUsage(2016, 2, 120));
		thisYear.add(new ElectricUsage(2016, 3, 330));
		thisYear.add(new ElectricUsage(2016, 4, 100));
		thisYear.add(new ElectricUsage(2016, 5, 150));
		thisYear.add(new ElectricUsage(2016, 6, 270));
		thisYear.add(new ElectricUsage(2016, 7, 360));
		thisYear.add(new ElectricUsage(2016, 8, 50));
		thisYear.add(new ElectricUsage(2016, 9, 600));
		thisYear.add(new ElectricUsage(2016, 10, 320));
		thisYear.add(new ElectricUsage(2016, 11, 220));
		thisYear.add(new ElectricUsage(2016, 12, 80));
		
		Observable<Double> source = Observable.fromIterable(thisYear)
				.map(collectMoney);
		source.subscribe(System.out::println);
	}
	
	public static void main(String[] args) { 
		ElectricBills demo = new ElectricBills();
		demo.basic();
	}
}

class ElectricUsage {
	public int year;
	public int month;
	public int usage;
	public ElectricUsage(int y, int m, int u) { 
		year = y;
		month = m;
		usage = u;
	}
}