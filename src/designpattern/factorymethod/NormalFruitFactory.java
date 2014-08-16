package designpattern.factorymethod;
/**
 * 一般工厂方法模式
 */
public class NormalFruitFactory {
	public Fruit produceFruit(String type) {
		if ("Apple".equals(type)) {
			return new Apple();
		} else if ("Banana".equals(type)) {
			return new Banana();
		}
		
		return null;
	}
}
