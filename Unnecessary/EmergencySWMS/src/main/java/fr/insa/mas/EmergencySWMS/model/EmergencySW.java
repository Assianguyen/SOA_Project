package fr.insa.mas.EmergencySWMS.model;

public class EmergencySW {
	private boolean order;
	
	public EmergencySW(boolean order) {
		this.order = order;
	}

	public EmergencySW() {
	}
	
	public boolean getOrder() {
		return order;
	}
	
	public void setOrder(boolean order) {
		this.order = order;
	}
}
