package fr.insa.mas.StationMS.model;

public class Station {
	private int id;
	private boolean currentState;
	private boolean order;
	
	public Station(int id, boolean currentState, boolean order) {
		this.id = id;
		this.currentState = currentState;
		this.order = order;
	}

	public Station() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getCurrentState() {
		return currentState;
	}

	public void setCurrentState(boolean currentState) {
		this.currentState = currentState;
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
}
