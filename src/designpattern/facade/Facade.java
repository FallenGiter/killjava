package designpattern.facade;

/**
 * 外观/门面
 * @author fuckme
 */
public class Facade {
	private SubSys1 ss1 = new SubSys1();
	private SubSys2 ss2 = new SubSys2();
	
	public void start() {
		ss1.action1();
		ss2.action3();
	}
	
	public void end() {
		ss1.action2();
		ss2.action4();
	}
}
