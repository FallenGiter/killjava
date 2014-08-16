package spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/aop/aop-proxy.xml");
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/aop/aop-auto-proxy.xml");
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/aop/aop-annotation-proxy.xml");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/aop/aop-config.xml");

//		Service sv = (Service) ctx.getBean("ServiceProxy");
		Service sv = (Service) ctx.getBean("Service");
		sv.doWork();
	}
}
