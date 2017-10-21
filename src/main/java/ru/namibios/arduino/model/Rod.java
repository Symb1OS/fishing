package ru.namibios.arduino.model;

import java.util.ArrayList;
import java.util.List;

public class Rod {
	
	private static final int START_X = 1532;
	private static final int START_Y = 355;
	
	private static final int DX = 48;
	private static final int DY = 0;

	private int currentId;
	private List<Touch> rods;

	public Rod(int cntRod) {
		currentId = 0;
		rods = new ArrayList<>();
		
		int x = START_X;
		int y = START_Y;
		
		for (int i = 0; i < cntRod; i++) {
			rods.add(new Touch(x, y));
			x += DX;
			y += DY;
		}
	}
	
	public boolean hasNext(){
		return (currentId < rods.size()) ? true : false;
	}
	
	public Touch getNext(){
		Touch touch = rods.get(currentId);
		currentId++;
		return touch;
	}
	
}