package fr.insa.mas.GlobalMS.model;

public class EmergencySW {
	private boolean currentState;
	private boolean order;
	
	public EmergencySW(boolean currentState, boolean order) {
		this.currentState = currentState;
		this.order = order;
	}

	public EmergencySW() {
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
