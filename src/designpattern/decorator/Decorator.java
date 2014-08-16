package designpattern.decorator;

/**
 * 装饰角色   - 相当于FilterInputStream
 * @author fuckme
 */
public abstract class Decorator implements Component {
	private Component cpn;
	
	public Decorator(Component cpn) {
		this.cpn = cpn;
	}
	
	@Override
	public void action() {
		this.cpn.action();
	}
}
