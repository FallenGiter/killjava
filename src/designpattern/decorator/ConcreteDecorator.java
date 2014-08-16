package designpattern.decorator;

/**
 * 具体装饰角色 - 相当于一个BufferedInputStream
 * @author fuckme
 */
public class ConcreteDecorator extends Decorator {
	public ConcreteDecorator(Component cpn) {
		super(cpn);
	}

	public void action() {
		this.additionalActionStart();
		super.action();
		this.additionalActionEnd();
	}
	
	
	private void additionalActionStart() {
		System.out.println("Additional Action Start");
	}
	
	private void additionalActionEnd() {
		System.out.println("Additional Action End");
	}
}
