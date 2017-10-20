package ru.namibios.arduino.model.state;

import ru.namibios.arduino.gui.Gui;

public class FishBot {

	private Gui gui;
	
	private State state;
	
	private boolean isRunned;
	
	public FishBot(Gui gui) {
		this.gui = gui;
		isRunned = true;
		state = new StartFishState(this);
	}

	public State getState() {
		return state;
	}

	public void setState(State command) {
		this.state = command;
	}
	
	public Gui getGui() {
		return gui;
	}

	public boolean isRunned() {
		return isRunned;
	}

	public void setRunned(boolean isRunned) {
		this.isRunned = isRunned;
	}
	
}