package designpattern.builder;

import java.util.ArrayList;
import java.util.List;


/**
 *  建造者
 */
public class Builder {
	public List<Fruit> buildApple(int count) {
		List<Fruit> apples = new ArrayList<Fruit>(count);
		
		for (int i = 0; i < count; i++) {
			apples.add(new Apple());
		}
		
		return apples;
	}
	
	public List<Fruit> buildBanana(int count) {
		List<Fruit> bananas = new ArrayList<Fruit>(count);
		
		for (int i = 0; i < count; i++) {
			bananas.add(new Banana());
		}
		
		return bananas;
	}
}
