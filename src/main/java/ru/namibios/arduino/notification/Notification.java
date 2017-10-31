package ru.namibios.arduino.notification;

public abstract class Notification {
	
	public static final String OUT_RODS = "Удочки закончились. Завершаем работу.";
	
	public static final String OUT_SLOT_INVENTORY = "Кончилось место в инвентаре. Завершаем работу.";
	
	public static final String RECEIVED_PRIVATE_MESSAGE = "Получено персональное сообщение";
	public static final String TURN_AUTOFISH = "Переходим в режим авто рыбалки..";
	public static final String EXIT_GAME = "Выходим из игры..";
	
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