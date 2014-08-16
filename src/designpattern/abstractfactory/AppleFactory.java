package designpattern.abstractfactory;

public class AppleFactory implements Provider{
	public Fruit produce() {
		return new Apple();
	}
}
