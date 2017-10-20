package ru.namibios.arduino.model.state;

import ru.namibios.arduino.gui.adapter.Reloader;
import ru.namibios.arduino.utils.DelayUtils;

public abstract class State {
	
	public FishBot fishBot;
	
	protected long beforeStart;
	protected long afterStart;
	
	public State(FishBot fishBot) {
		this.fishBot = fishBot;
	}
	
	public State(FishBot fishBot, long beforeStart, long afterStart) {
		this.fishBot = fishBot;
		this.beforeStart = beforeStart;
		this.afterStart = afterStart;
		
	}
	
	public void start(){
		DelayUtils.delay(beforeStart);
		onStep();
		DelayUtils.delay(afterStart);
	}
	
	public abstract void onStep();
	
	public void repaint(Reloader reloader) {
		fishBot.getGui().repaint(reloader);
	}

}