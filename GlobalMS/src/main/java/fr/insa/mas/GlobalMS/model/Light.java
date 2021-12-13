package fr.insa.mas.GlobalMS.model;

public class Light {
	private boolean currentState;
	private boolean order;
	private int id;
	
	public Light(int id, boolean currentState, boolean order) {
		this.currentState = currentState;
		this.order = order;
		this.id = id;
	}
	
	public Light() {
	}
	
	public boolean getCurrentState() {
		return currentState;
	}
	
	public boolean getOrder() {
		return order;
	}
	
	public void setOrder(boolean order) {
		this.order = order;
		if(order != this.currentState) {
			this.currentState = !this.currentState;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
