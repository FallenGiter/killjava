package spring.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // 使用aop:config时可去掉
public class AnnotationAJ {
	
	@Pointcut("execution(* spring.aop.*.doWork())") // 使用aop:config时可去掉
	public void pc(){
	}
	
	@Before("pc()") // 使用aop:config时可去掉
	public void enter(){
		System.out.println("enter method");
	}
	
	@AfterReturning("pc()") // 使用aop:config时可去掉
	public void leave(){
		System.out.println("leave method");
	}
}
