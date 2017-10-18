package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.command.CheckCut;

public class CheckCutState extends State{

	private static final Logger logger = Logger.getLogger(CheckCutState.class);
	
	private int step ;
	
	public CheckCutState(FishBot fishBot) {
		super(fishBot);
		this.beforeStart = 0;
		this.afterStart = 0;
		step = 0;
	}

	@Override
	public void onStep() {
		
		try{
			
			if(step > 150){
				logger.info("Status not identified... Go to FilterLoot..");
				fishBot.setState(new FilterLootState(fishBot));
			} 
			
			CheckCut perfect = new CheckCut();
			String key = perfect.getKey();
			switch ( key ) {
				case "perfect":{
					logger.info("PERFECT. Go filter loot..");
					fishBot.setState(new FilterLootState(fishBot));
					break;
				} 
				case "good": {
					logger.info("Good. Go parse kapcha");
					fishBot.setState(new KapchaState(fishBot));
					break;
				}
				case "bad": {
					logger.info("Bad. Go to start...");
					fishBot.setState(new StartFishState(fishBot));
					break;
				}
			}
			
			step++;
		}catch (Exception e) {
			logger.error("Exception " + e);
			fishBot.setState(new KapchaState(fishBot));
		}
		
	}
}
