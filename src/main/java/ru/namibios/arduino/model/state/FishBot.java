package ru.namibios.arduino.model.state;

public class FishBot {

	private State state;
	
	private boolean isRunned;
	
	public FishBot() {
		isRunned = true;
		state = new StartFishState(this);
	}

	public State getState() {
		return state;
	}

	public void setState(State command) {
		this.state = command;
	}
	
	public boolean isRunned() {
		return isRunned;
	}

	public void setRunned(boolean isRunned) {
		this.isRunned = isRunned;
	}
	
}