package spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class LogAJ implements MethodBeforeAdvice, AfterReturningAdvice {
	@Override
	public void before(Method mothod, Object[] params, Object o) throws Throwable {
		System.out.println("enter method: " + mothod.getName());
	}

	@Override
	public void afterReturning(Object arg0, Method method, Object[] arg2, Object arg3) throws Throwable {
		System.out.println("leave method: " + method.getName());
	}
}
