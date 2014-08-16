package hibernate.entitys;

import java.util.Set;

public class Channel extends Auditable {
	private String name;
	private int order;
	
	private Channel parent;
	private Set<Channel> childs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Channel getParent() {
		return parent;
	}
	public void setParent(Channel parent) {
		this.parent = parent;
	}
	public Set<Channel> getChilds() {
		return childs;
	}
	public void setChilds(Set<Channel> childs) {
		this.childs = childs;
	}
}
