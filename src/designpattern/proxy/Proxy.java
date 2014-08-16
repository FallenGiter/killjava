package designpattern.proxy;

/**
 * 装饰角色   - 相当于FilterInputStream
 * @author fuckme
 */
public abstract class Proxy implements Component {
	private Component cpn;
	
	public Proxy() {
		this.cpn = new ConcreteComponent();
	}
	
	@Override
	public void action() {
		System.out.println("before proxy .");
		this.cpn.action();
		System.out.println("after proxy .");
	}
}
