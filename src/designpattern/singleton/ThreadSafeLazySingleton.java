package designpattern.singleton;

public class ThreadSafeLazySingleton {
	private static ThreadSafeLazySingleton singleton;
	
	private ThreadSafeLazySingleton(){}
	
	public static ThreadSafeLazySingleton getInstance() {
		if (singleton == null) {
			synchronized (ThreadSafeLazySingleton.class) {
				if (singleton == null) {
					singleton = new ThreadSafeLazySingleton();
				}
			}
		}
		
		return singleton;
	}
}
