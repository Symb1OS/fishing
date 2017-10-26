package ru.namibios.arduino.notification;

public abstract class Notification {
	
	public static final String OUT_RODS = "Удочки закончились. Завершаем работу.";
	
	public static final String OUT_SLOT_INVENTORY = "Кончилось место в инвентаре. Завершаем работу.";
	
	protected String message; 
	
	private Notification nextNotification; 
	
	public Notification(String message) {
		this.message = message;
	}
	
	public void setNextNotification(Notification notification) {
		this.nextNotification = notification;
	}
	
	public void notifyUser() {
		
		send();
		
		if(nextNotification != null) {
			nextNotification.notifyUser();
		}
	}
	
	public abstract void send();

}