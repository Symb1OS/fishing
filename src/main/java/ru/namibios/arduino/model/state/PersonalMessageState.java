package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.command.PersonalMessage;
import ru.namibios.arduino.notification.Notification;
import ru.namibios.arduino.utils.Keyboard;

public class PersonalMessageState extends State {

	private static final Logger logger = Logger.getLogger(PersonalMessage.class);
	
	public PersonalMessageState(FishBot fishBot) {
		super(fishBot);
		this.beforeStart = 0;
		this.afterStart = 0;
	}

	@Override
	public void onStep() {
		
		try {
			
			PersonalMessage pm = new PersonalMessage(0.05);
			if(pm.isDetected() && !fishBot.isPmDetected()) {
				
				logger.info("Reseived a private message. Send telegram notification.");
				fishBot.notifyUser(Notification.RECEIVED_PRIVATE_MESSAGE);
				
				if(Application.getInstance().PM_AUTOFISH()) {
					logger.info("Received a private message. Switch to autofish...");
					fishBot.notifyUser(Notification.TURN_AUTOFISH);
					fishBot.setRunned(false);
				}
				
				if(Application.getInstance().PM_EXIT_GAME()) {
					logger.info("Received a private message. Exit game...");
					fishBot.notifyUser(Notification.TURN_AUTOFISH);
					Keyboard.send(() -> "Exit");
				}
				
				fishBot.setPmDetected(true);
			}
			
			fishBot.setState(new WaitFishState(fishBot));
		
		} catch (Exception e) {
			logger.error("Exception " + e);
		}

	}
}