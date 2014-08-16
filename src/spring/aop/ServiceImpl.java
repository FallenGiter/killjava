package spring.aop;

public class ServiceImpl implements Service {
	@Override
	public void doWork() {
		System.out.println("doing real working");
	}
}
