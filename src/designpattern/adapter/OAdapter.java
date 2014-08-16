package designpattern.adapter;

/**
 * 对象适配
 */
public class OAdapter implements OExpectedService {
	private ExsitedService sv;
	
	public OAdapter(){
		sv = new ExsitedService();
	}
	
	@Override
	public void callMeMaybe() {
		sv.justCallMe();
	}
}
