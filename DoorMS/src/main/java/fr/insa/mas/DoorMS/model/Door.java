package fr.insa.mas.DoorMS.model;

public class Door {
	private int id;
	private boolean currentOpeningState;
	private boolean orderOpening;
	
	public Door(int id, boolean currentOpeningState, boolean orderOpening) {
		this.id = id;
		this.currentOpeningState = currentOpeningState;
		this.orderOpening = orderOpening;
	}

	public Door() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getCurrentOpeningState() {
		return currentOpeningState;
	}
	
	public boolean getOrderOpening() {
		return orderOpening;
	}

	public void setOrderOpening(boolean order) {
		this.orderOpening = order;
		if(order != this.currentOpeningState) {
			this.currentOpeningState = !this.currentOpeningState;
		}
	}
}
