package jvm;

import java.util.ArrayList;
import java.util.List;

public class JVMRuntimeErrors {
	
	public static void main(String[] args) throws Exception {
		outOfMemoryErrorPermGenSpace();
	}
	
	/**
	 * java.lang.StackOverflowError
	 * java 栈溢出，每一个线程都拥有自己独立的栈，该栈以帧为单位存储线程的运行状态
	 * 当线程调用一个方法时，JVM都会往该栈压入一帧，直到方法返回才会将该帧出栈
	 * 当线程调用该方法嵌套层次太多（如递归调用），将会导致栈溢出
	 * 
	 * -Xss 设置栈大小
	 * 
	 * 原理：死递归调用
	 */
	static void stackOverflowError() {
		stackOverflowError();
	}
	
	/**
	 * java.lang.OutOfMemoryError: Java heap space
	 * java 堆溢出，堆是用于存储类实例的，当类实例大小超过最大的限制，将会导致堆溢出
	 * 
	 * -Xms 设置堆最小值
	 * -Xmx 设置堆最大值
	 * 
	 * 原理：无限创建对象
	 */
	static void outOfMemoryError_HeapSpace() {
		List<Object> os = new ArrayList<Object>();
		
		while (true) {
			os.add(new Object());
		}
	}

	/**
	 * java.lang.OutOfMemoryError: PermGen space
	 * java 方法区溢出，方法区是用于存储类信息（如类名、访问修饰符、常量、字段描述、方法描述等）
	 * 当类信息大小超过最大限制，将导致方法区溢出
	 * 
	 * -XX:PermSize 设置初始方法区大小
	 * -XX:MaxPermSize 设置方法区最大值
	 * 
	 * 原理：不断往常量池放塞字符串
	 */
	static void outOfMemoryErrorPermGenSpace() {
		int runtimeConstantCount = 1;  
		List<String> list = new ArrayList<String>();  
        while (true) {  
            list.add(String.valueOf(runtimeConstantCount++).intern());  
        }  
	}
}
