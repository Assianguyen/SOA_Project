package fr.insa.mas.BlindMS.model;

public class Blind {
	private int id;
	private int order;
	
	public Blind(int id, int order) {
		this.id = id;
		this.order = order;
	}

	public Blind() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
