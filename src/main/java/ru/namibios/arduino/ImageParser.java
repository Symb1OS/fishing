package ru.namibios.arduino;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageParser {

	private static final boolean INFO = true;
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
				boolean isWhite = color.getRed() > WHITE_R && color.getGreen() > WHITE_G && color.getBlue() > WHITE_B;
				boolean isBlack = (color.getRed() < BLACK_R && color.getGreen() < BLACK_G && color.getBlue() < BLACK_B);
				boolean isGray = (color.getRed() > 120 && color.getGreen() > 120 && color.getBlue() > 120) && (color.getRed() < 135 && color.getGreen() < 135 && color.getBlue() < 135);
				
				switch (imageType) {
					case SPACE:	  isKey = isWhite;  break; 
					case LINE:    isKey = isBlack;  break;
					case SUBLINE: isKey = !isBlack; break;
					case KAPCHA:  isKey = isGray;   break;
					default: break;
				}	
				
				tmp[i][j] = isKey ? 1 : 0;
			}
		}
		
		printMatrix(tmp, row, column);
		keyWordListList.add(tmp);
}

	private void printMatrix(int[][] tmp, int row, int column){
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(tmp[i][j] == 1 ? "1 " :"  ");
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
	
	private int getLength(int[][] tmp){
		return Arrays.toString(tmp).length();
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
				
				if(calcKoef > MIN_KOEF) break;
				
				if (DEBUG) {
					int tempateLength = getLength(template);
					int numberLength= getLength(numberMatrix);
				
					System.out.println("template" + tempateLength);
					System.out.println("numberMatrix" + numberLength);
				}
				
				templateKoef=0; koef = 0;
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						boolean isValue;
						try{ isValue = (template[i][j] == 1); }catch(ArrayIndexOutOfBoundsException e){if(DEBUG)System.out.println("ArrayIndexOutOfBoundsException"); break;}
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
				
				if(INFO){
					System.out.println("index= " + index + " |templateKoef= " + templateKoef + " | koef= " + koef + " | " + calcKoef );
					System.out.println("========================================");
				}
				
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
		if(INFO) System.out.println("REZULT = " + rezult.toString().replace("-1", "?"));
		return rezult.toString();
		
	}
	
	/*public static void main(String[] args) throws Exception {

		File dir = new File("resources/kapcha");
		File[] files = dir.listFiles();
		//20170705_215909.jpg
		for (File file : files) { 
			Screen screen = new Screen(file.getName(), ImageType.KAPCHA);
			
			BufferedImage img = screen.getImage();
			
			ImageParser parser = new ImageParser(ImageType.SPACE, img);
			parser.getMatrix();
			parser.getkeyFromTemlate();
		}
		
		//20170705_215909.jpg
		//20170705_215910.jpg
		
		Screen screen = new Screen("20170705_215910.jpg", ImageType.KAPCHA);
		BufferedImage img = screen.getImage();
		
		ImageParser parser = new ImageParser(ImageType.KAPCHA, img);
		parser.getMatrix();
		//parser.getkeyFromTemlate();
	}*/
}
