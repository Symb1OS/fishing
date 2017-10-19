package ru.namibios.arduino.model.template;

import java.util.ArrayList;
import java.util.List;

public enum StatusKapchaTemplate implements MatrixTemplate{

	SUCCESS(),
	
	FAILURE();
	
	private final List<int[][]> templates;
	
	public List<int[][]> getTemplates() {
		return templates;
	}
	
	private StatusKapchaTemplate(int[][]... mas) {
		this.templates = new ArrayList<int[][]>();
		for (int[][] template : mas) {
			templates.add(template);
		}
	}
}