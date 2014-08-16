package jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import cache.Bean;

public class Main {
	public static void main(String[] args) throws Exception{
		MyClassLoader mcl = new MyClassLoader("D:\\interview\\KillJava\\bin\\");
		Class<Bean> bean = (Class<Bean>) mcl.findClass("cache.Bean");
		Bean b1 = bean.newInstance();
//		ClassLoader cl = bean.getClassLoader();
//		while (cl != null) {
//			System.out.println(cl.getClass().getName());
//			cl = cl.getParent();
//		}
		
		Class<Bean> bean2 = (Class<Bean>) Thread.currentThread().getContextClassLoader().loadClass("cache.Bean");
		Bean b2 = bean2.newInstance();
	}
}

class MyClassLoader extends ClassLoader {
	private String classDir;

	public MyClassLoader(String classDir) {
		this.classDir = classDir;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = getClassData(name);
		return super.defineClass(name, classData, 0, classData.length);
	}

	private byte[] getClassData(String className) {
		String path = classNameToPath(className);
		try {
			InputStream ins = new FileInputStream(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String classNameToPath(String className) {
		return classDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
	}
}