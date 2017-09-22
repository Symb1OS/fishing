package ru.namibios.arduino.model.state;

public abstract class State {
	
	public FishBot fishBot;
	
	public State(FishBot fishBot) {
		this.fishBot = fishBot;
	}
	
	public abstract void onNext();

}
