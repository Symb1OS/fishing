package ru.namibios.arduino.gui.adapter;

import javax.swing.ImageIcon;

import ru.namibios.arduino.model.command.Kapcha;

public class KapchaReloader extends ReloaderAdapter{

	private Kapcha kapcha;
	
	public KapchaReloader(Kapcha kapcha) {
		this.kapcha = kapcha;
	}
	
	@Override
	public ImageIcon getKapcha() {
		return new ImageIcon(kapcha.getScreen().getScreenShot());
	}
	
	@Override
	public String getKeyKapcha() {
		return kapcha.getRezultKey();
	}
}
