package ru.namibios.arduino.model.state;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.gui.Gui;
import ru.namibios.arduino.model.Rod;

public class FishBot {

	private Gui gui;
	
	private State state;
	
	private Rod rod;
	
	private boolean isRunned;
	
	public FishBot(Gui gui) {
		this.gui = gui;
		this.rod = new Rod(Application.getInstance().COUNT_ROD());
		
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

	public Rod getRod() {
		return rod;
	}

	public void setRod(Rod rod) {
		this.rod = rod;
	}
	
}