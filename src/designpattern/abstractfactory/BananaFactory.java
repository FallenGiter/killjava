package designpattern.abstractfactory;

public class BananaFactory implements Provider {
	public Fruit produce() {
		return new Banana();
	}
}
