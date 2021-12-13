package fr.insa.mas.GlobalMS.model;

public class Door {
	private int id;
	private boolean currentLockState;
	private boolean currentOpeningState;
	private boolean orderLock;
	private boolean orderOpening;
	
	public Door(int id, boolean currentLockState, boolean currentOpeningState, boolean orderLock, boolean orderOpening) {
		this.id = id;
		this.currentLockState = currentLockState;
		this.currentOpeningState = currentOpeningState;
		this.orderLock = orderLock;
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

	public boolean getCurrentLockState() {
		return currentLockState;
	}
	
	public boolean getCurrentOpeningState() {
		return currentOpeningState;
	}

	public boolean getOrderLock() {
		return orderLock;
	}

	public void setOrderLock(boolean order) {
		this.orderLock = order;
		if(order != this.currentLockState) {
			this.currentLockState = !this.currentLockState;
		}
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
