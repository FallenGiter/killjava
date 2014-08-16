package designpattern.proxy;

/**
 * 具体构建角色 - 相当于FileInputStream
 * @author fuckme
 */
public class ConcreteComponent implements Component {
	@Override
	public void action() {
		System.out.println("Concrete Action");
	}
}
