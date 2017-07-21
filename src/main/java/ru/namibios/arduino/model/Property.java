package ru.namibios.arduino.model;

public class Property {

	private boolean bear;

	private boolean minigame;

	private boolean dinner1;
	private boolean dinner2;
	private boolean dinner3;

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

	public boolean isDinner1() {
		return dinner1;
	}

	public void setDinner1(boolean dinner1) {
		this.dinner1 = dinner1;
	}

	public boolean isDinner2() {
		return dinner2;
	}

	public void setDinner2(boolean dinner2) {
		this.dinner2 = dinner2;
	}

	public boolean isDinner3() {
		return dinner3;
	}

	public void setDinner3(boolean dinner3) {
		this.dinner3 = dinner3;
	}

}