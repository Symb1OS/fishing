package ru.namibios.arduino;

import javax.swing.ImageIcon;

public class GuiHolder {

	static {
		imgKapcha = new ImageIcon("resources/demo/kapcha.jpg");
		kapcha = "";
		
		lootOne = new ImageIcon("resources/demo/key.jpg");
		lootTwo = new ImageIcon("resources/demo/scala.jpg");
	}
	
	private static ImageIcon imgKapcha;
	private static String kapcha;

	private static ImageIcon lootOne;
	private static ImageIcon lootTwo;

	public static ImageIcon getImgKapcha() {
		return imgKapcha;
	}

	public static void setImgKapcha(ImageIcon imgKapcha) {
		GuiHolder.imgKapcha = imgKapcha;
	}

	public static String getKapcha() {
		return kapcha;
	}

	public static void setKapcha(String kapcha) {
		GuiHolder.kapcha = kapcha;
	}

	public static ImageIcon getLootOne() {
		return lootOne;
	}

	public static void setLootOne(ImageIcon lootOne) {
		GuiHolder.lootOne = lootOne;
	}

	public static ImageIcon getLootTwo() {
		return lootTwo;
	}

	public static void setLootTwo(ImageIcon lootTwo) {
		GuiHolder.lootTwo = lootTwo;
	}

}