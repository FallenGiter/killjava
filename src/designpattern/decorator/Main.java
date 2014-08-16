package designpattern.decorator;
/**
 * 装饰模式使用实例
 * @author fuckme
 */
public class Main {
	public static void main(String[] args) {
		Component cpn = new ConcreteComponent();
		Component dcr = new ConcreteDecorator(cpn);
		
		dcr.action();
	}
}
