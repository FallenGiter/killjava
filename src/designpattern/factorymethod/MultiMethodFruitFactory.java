package designpattern.factorymethod;

/**
 * 多个工厂方法模式
 */
public class MultiMethodFruitFactory {
	public Fruit produceApple() {
		return new Apple();
	}
	
	public Fruit produceBanana() {
		return new Banana();
	}
}
