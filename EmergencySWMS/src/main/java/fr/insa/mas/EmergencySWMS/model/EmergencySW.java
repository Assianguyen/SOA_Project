package fr.insa.mas.EmergencySWMS.model;

public class EmergencySW {
	private boolean currentState;

	public EmergencySW(boolean currentState) {
		this.currentState = currentState;
	}

	public EmergencySW() {
	}

	public boolean getCurrentState() {
		return currentState;
	}

	public void setCurrentState(boolean currentState) {
		this.currentState = currentState;
	}
}
