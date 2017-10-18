package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.command.Kapcha;
import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.Keyboard;

public class KapchaState extends State {

	private static final Logger logger = Logger.getLogger(KapchaState.class);

	public KapchaState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_KAPCHA();
	}
	
	@Override
	public void onStep() {
	
		logger.info("Parsing kapcha...");
		
		try{
			
			Kapcha kapcha = new Kapcha(50);
			boolean isIdentified = Keyboard.send(kapcha);
			kapcha.reloadGui();
			
			if(isIdentified){
				logger.info("Kapcha identified. Go to the next state...");
				fishBot.setState(new FilterLootState(fishBot));
			}
			else {
				logger.info("Captcha is not recognized. Return to start...");
				//TODO Need add PerfectState() to not loosing loot
				fishBot.setState(new FilterLootState(fishBot));
			}
			
		}catch (Exception e) {
			logger.error("Exception " + e);
			DelayUtils.delay(7000);
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}
}