package designpattern.factorymethod;

/**
 * 静态工厂方法模式
 */
public class StaticFruitFactory {
	public static Fruit produceApple() {
		return new Apple();
	}
	
	public static Fruit produceBanana() {
		return new Banana();
	}
}
