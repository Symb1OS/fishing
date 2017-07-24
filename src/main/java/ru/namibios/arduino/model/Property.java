package ru.namibios.arduino.model;

public class Property {

	private String hash;
	
	private boolean bear;
	private boolean minigame;

	private boolean rock;
	private boolean fish;
	private boolean keys;
	private boolean event;

	public boolean isBear() {
		return bear;
	}

	public void setBear(boolean bear) {
		this.bear = bear;
	}

	public boolean isMinigame() {
		return minigame;
	}

	public void setMinigame(boolean minigame) {
		this.minigame = minigame;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public boolean isRock() {
		return rock;
	}

	public void setRock(boolean rock) {
		this.rock = rock;
	}

	public boolean isFish() {
		return fish;
	}

	public void setFish(boolean fish) {
		this.fish = fish;
	}

	public boolean isKeys() {
		return keys;
	}

	public void setKeys(boolean keys) {
		this.keys = keys;
	}

	public boolean isEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}
	
}