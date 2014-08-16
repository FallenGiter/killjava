package designpattern.prototype;

public class SimplePrototype implements Cloneable {
	public Object clone() throws CloneNotSupportedException {  
		SimplePrototype me = (SimplePrototype) super.clone();  
        return me;  
    }  
}
