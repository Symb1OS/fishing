package ru.namibios.arduino;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageParser {

	private static final boolean DEBUG = true;
	
	private static final double MIN_KOEF = 0.88;
	
	private static final int WHITE_R = 120;
	private static final int WHITE_G = 120;
	private static final int WHITE_B = 120;
	
	private static final int BLACK_R = 100;
	private static final int BLACK_G = 100;
	private static final int BLACK_B = 100;
	
	private BufferedImage screen;
	private ImageType imageType;
	private int row;
	private int column;
	
	private ArrayList<int[][]> keyWordListList;
	
	public ImageParser(BufferedImage screen){
		this.screen = screen;
		this.row = screen.getHeight();
		this.column = screen.getWidth();
		this.keyWordListList = new ArrayList<int[][]>();
	}
	
	public ImageParser(ImageType type, BufferedImage screen){
		this.screen = screen;
		this.imageType = type;
		this.row = screen.getHeight();
		this.column = screen.getWidth();
		this.keyWordListList = new ArrayList<int[][]>();
	}
	
	public void getMatrix(){

		if(DEBUG){
			System.out.println("row: " + row);
			System.out.println("column: " + column);
		}
		
		boolean isKey = false;
		int tmp[][] = new int[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Color color = new Color(screen.getRGB(j, i));
				switch (imageType) {
					case SPACE:	  isKey = color.getRed() > WHITE_R && color.getGreen() > WHITE_G && color.getBlue() > WHITE_B; break; 
					case SUBLINE: isKey = !(color.getRed() < BLACK_R && color.getGreen() < BLACK_G && color.getBlue() < BLACK_B); break;
					case KAPCHA:  break;
					default: break;
				}	
				
				tmp[i][j] = isKey ? 1 : 0; 
			}
		}
		
		//printMatrix(tmp, row, column);
		keyWordListList.add(tmp);
}

	@SuppressWarnings("unused")
	private void printMatrix(int[][] tmp, int row, int column){
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(tmp[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	@SuppressWarnings("unused")
	private void printTemplate(int[][] tmp, int row, int column){
		for (int i = 0; i < row; i++) {
			System.out.print("{");
			for (int j = 0; j < column; j++) {
				System.out.print(tmp[i][j] + ", ");
			}
			System.out.print("},");
			System.out.println();
		}
	}
	
	private int equalsMatrix(int[][] numberMatrix) {
		int rezultIndex = -1;
		
		double koef = 0;
		double templateKoef = 0;
		double calcKoef = 0;
		double maxCalcKoef = 0;

		int index = 0;
		int size = Chars.values().length;
		while(index < size){
			
			List<int[][]> templateNumber = Chars.values()[index].getTemplates();
			for (int[][] template : templateNumber) {
			
				//TODO разница в размерности массива, возможны ошибки
				if(calcKoef > MIN_KOEF) break;
				
				templateKoef=0; koef = 0;
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						boolean isValue = (template[i][j] == 1); 
						if(isValue) templateKoef++;
						
						boolean valuesEqual = numberMatrix[i][j] == template[i][j] && template[i][j] != 0; 
						if(valuesEqual) koef +=1;
					}
				}
				
				calcKoef = koef / templateKoef;
				boolean isNewKoef = calcKoef > maxCalcKoef; 
				if( isNewKoef ){
					rezultIndex = index;
					maxCalcKoef = calcKoef;
				}
				
				boolean isUndefined = maxCalcKoef < MIN_KOEF;
				if( isUndefined ) rezultIndex = -1;
				
				System.out.println("index= " + index + " |templateKoef= " + templateKoef + " | koef= " + koef + " | " + calcKoef );
				System.out.println("========================================");
			}
			index++;
		}
		
		return rezultIndex == 10 ? -1 : rezultIndex;
	}
	
	public String getkeyFromTemlate() {
		StringBuilder rezult = new StringBuilder();
		for (int[][] numberMatrix : keyWordListList) {
			rezult.append(equalsMatrix(numberMatrix));
		}
		System.out.println("REZULT = " + rezult.toString().replace("-1", "?"));
		return rezult.toString().replace("-1", "?");
		
	}
	
	public static void main(String[] args) throws Exception {
		
		Screen screenLine = new Screen("20170705_215907.jpg");
		ImageType type = ImageType.SUBLINE;
		screenLine.getSubImage(type);
		
		BufferedImage img = screenLine.getImage();
		
		ImageParser parser = new ImageParser(type, img);
		parser.getMatrix();
		parser.getkeyFromTemlate();
		
	}
}
