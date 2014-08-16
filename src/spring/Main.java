package spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/beans.xml");
		FileSystemXmlApplicationContext fCtx = new FileSystemXmlApplicationContext("src/spring/beans.xml");
		
		Bean bean = (Bean) ctx.getBean(Bean.class);
		Bean bean2 = (Bean) ctx.getBean(Bean.class);
		
		bean.getName();
		System.out.println(bean.equals(bean2));
		System.out.println(bean == bean2);
	}
}
