package designpattern.adapter;


/**
 * 类适配
 */
public class ClassAdapter extends ExsitedService implements ClassExpectedService {
	@Override
	public void callMeMaybe() {
		this.justCallMe();
	}
}
