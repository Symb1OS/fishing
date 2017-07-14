package ru.namibios.arduino;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import ru.namibios.arduino.model.Chars;

public class ImageParser {

	private static final int GRAY = 45;
	private static final boolean INFO = true;
	private static final boolean DEBUG = false;
	
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
	
	public void getCodes(){

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
			//	boolean isBlack = (color.getRed() < BLACK_R && color.getGreen() < BLACK_G && color.getBlue() < BLACK_B);
				boolean isGray = (color.getRed() > GRAY && color.getGreen() > GRAY && color.getBlue() > GRAY);
				
				switch (imageType) {
					case SPACE:	  isKey = isWhite;  break; 
					case KAPCHA:  isKey = isGray;   break;
					default: break;
				}	
				
				tmp[i][j] = isKey ? 1 : 0;
			}
		}
		
		switch (imageType) {
			case KAPCHA:
				FillMatrix fillMatrix = new FillMatrix(tmp, row, column);
				fillMatrix.markupMatrix();
				fillMatrix.cleanOfBounds(45, 100);
				tmp = fillMatrix.getMatrix();
				
				printMatrix(tmp, row, column);
				
				keyWordListList = new ArrayList<>(fillMatrix.toListMatrix());
				
				List<int[][]> list = fillMatrix.toListMatrix();
				for (int[][] is : list) {
					printTemplate(is, FillMatrix.SYMBOL_ROW, FillMatrix.SYMBOL_COLUMN);
				}
				
				break;	
				
			default: keyWordListList.add(tmp); break;
				
			}
		}
			
	private void printMatrix(int[][] tmp, int row, int column){
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(tmp[i][j]!= 0 ? 1 : " ");
			}
			System.out.println();
		}
	}
	
	private void printTemplate(int[][] tmp, int row, int column){
		System.out.println("new int[][]{");
		for (int i = 0; i < row; i++) {
			System.out.print("{");
			for (int j = 0; j < column; j++) {
				System.out.print((tmp[i][j] == 0 ? "0" : "1") + ", ");
			}
			System.out.print("},");
			System.out.println();
		}
		System.out.println("}");
		System.out.println();
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
				if(template.length != numberMatrix.length ) continue;
					
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
		return rezult.toString().replace("-1", "");
		
	}
	
	public static void main(String[] args) throws Exception {

		// 1/20170711_222631_327.jpg
		// 2/20170711_222802_871.jpg
		// 3/20170711_223643_686.jpg
		// 4/20170711_224313_115.jpg   
		// 5/20170711_231756_162.jpg
		// 6/20170711_235951_232.jpg   
		// 7/20170712_000451_976.jpg
		File file  = new File("resources/debug/6/20170711_235951_232.jpg");
		BufferedImage image = ImageIO.read(file);
		
		ImageParser parser = new ImageParser(ImageType.KAPCHA, image);
		parser.getCodes();
		String key = parser.getkeyFromTemlate();
		System.out.println("key " + key);
		
	}
}