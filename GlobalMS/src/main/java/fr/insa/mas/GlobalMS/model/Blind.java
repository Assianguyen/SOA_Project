package fr.insa.mas.GlobalMS.model;

public class Blind {
	private int id;
	private int currentOpening;
	private int order;
	private boolean isMoving;
	
	public Blind(int id, int currentOpening, int order, boolean isMoving) {
		this.id = id;
		this.currentOpening = currentOpening;
		this.order = order;
		this.isMoving = isMoving;
	}

	public Blind() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentOpening() {
		return currentOpening;
	}

	public void setCurrentOpening(int currentOpening) {
		this.currentOpening = currentOpening;
		updateIsMoving();
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
		updateIsMoving();
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void updateIsMoving() {
		this.isMoving = !(this.order == this.currentOpening);
	}
}
