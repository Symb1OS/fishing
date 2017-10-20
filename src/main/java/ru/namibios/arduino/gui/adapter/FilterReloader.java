package ru.namibios.arduino.gui.adapter;

import javax.swing.ImageIcon;

import ru.namibios.arduino.model.command.FishLoot;

public class FilterReloader extends ReloaderAdapter {
	
	private FishLoot fishLoot;

	public FilterReloader(FishLoot fishLoot) {
		this.fishLoot = fishLoot;	
	}
	
	@Override
	public ImageIcon getLootOne() {
		return new ImageIcon(fishLoot.getOne().getScreenShot());
	}

	@Override
	public ImageIcon getLootTwo() {
		return new ImageIcon(fishLoot.getTwo().getScreenShot());
	}
	
}