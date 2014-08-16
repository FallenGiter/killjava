package designpattern.adapter;

public class Impl1 extends Wrapper {
	@Override
	public void m1() {
		super.m1();
		System.out.println("m1");
	}
}
