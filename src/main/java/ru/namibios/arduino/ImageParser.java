package ru.namibios.arduino;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.Chars;
import ru.namibios.arduino.model.ImageType;
import ru.namibios.arduino.model.Loot;

public class ImageParser {
	
	final static Logger logger = Logger.getLogger(ImageParser.class);

	private static final int GRAY = 40;
	private static final boolean DEBUG = true;
	
	private static final double CHARS_MIN_KOEF = 0.88;
	
	private static final int WHITE_R = 120;
	private static final int WHITE_G = 120;
	private static final int WHITE_B = 120;
	
	private BufferedImage screen;
	private ImageType imageType;
	private int row;
	private int column;
	
	private int[][] imageMatrix;
	private ArrayList<int[][]> keyWordListList;
	
	public ImageParser(BufferedImage screen){
		this.screen = screen;
		this.row = screen.getHeight();
		this.column = screen.getWidth();
		this.keyWordListList = new ArrayList<int[][]>();
		this.imageMatrix = new int[row][column];
	}
	
	public ImageParser(ImageType type, BufferedImage screen){
		this.screen = screen;
		this.imageType = type;
		this.row = screen.getHeight();
		this.column = screen.getWidth();
		this.keyWordListList = new ArrayList<int[][]>();
		this.imageMatrix = new int[row][column];
	}
	
	public void getCodes(){

		if(DEBUG){
			logger.debug("row: " + row);
			logger.debug("column: " + column);
		}
		
		boolean isKey = false;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Color color = new Color(screen.getRGB(j, i));
				boolean isWhite = color.getRed() > WHITE_R && color.getGreen() > WHITE_G && color.getBlue() > WHITE_B;
				boolean isGray = (color.getRed() > GRAY && color.getGreen() > GRAY && color.getBlue() > GRAY);
				
				switch (imageType) {
					case SPACE:	    isKey = isWhite;  break; 
					case KAPCHA:    isKey = isGray;   break;
					case SUBLINE:   isKey = isGray;   break;
					case FISH_LOOT: isKey = isGray;   break;
					default: break;
				}	
				imageMatrix[i][j] = isKey ? 1 : 0;
			}
		}
		
			//printMatrix(imageMatrix, row, column);
			keyWordListList.add(imageMatrix);
		
		}
	
	public int[][] getImageMatrix(){
		return imageMatrix;
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
	
	private List<int[][]> getTemplates(int index){
		List<int[][]> templateNumber = null; 
		
		switch (imageType) {
		case FISH_LOOT:
			templateNumber = Loot.values()[index].getTemplates();
			break;

		default:
			templateNumber = Chars.values()[index].getTemplates();
			break;
		}
		
		return templateNumber;
	}
	
	private int equalsMatrix(int[][] numberMatrix) {
		int rezultIndex = -1;
		
		double koef = 0;
		double templateKoef = 0;
		double calcKoef = 0;
		double maxCalcKoef = 0;

		int index = 0;
		int size = (imageType == ImageType.FISH_LOOT ? Loot.values().length : Chars.values().length);
		while(index < size){
			
			List<int[][]> templateNumber = getTemplates(index); 
			for (int[][] template : templateNumber) {
				
				if(calcKoef > CHARS_MIN_KOEF) break;
				if(template.length != numberMatrix.length ) continue;
					
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
				
				boolean isUndefined = maxCalcKoef < CHARS_MIN_KOEF;
				if( isUndefined ) rezultIndex = -1;
				
				if(DEBUG){
					logger.debug("index= " + index + " |templateKoef= " + templateKoef + " | koef= " + koef + " | " + calcKoef );
					logger.debug("========================================");
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
		return rezult.toString().replace("-1", "");
	}
	
	public String getNumberkeyFromTemlate() {
		StringBuilder rezult = new StringBuilder();
		
		for (int[][] numberMatrix : keyWordListList) {
			int rezultIndex = equalsMatrix(numberMatrix);
			if(rezultIndex != -1){
				rezult.append(equalsMatrix(numberMatrix));
			} else{
				logger.info("Undefined symbol: ");
				printMatrix(numberMatrix, row, column);
			}
		}	
		return rezult.toString();
	}
	
	public static void main(String[] args) throws Exception {

		// 1/20170711_222631_327.jpg
		// 2/20170711_222802_871.jpg
		// 3/20170711_223643_686.jpg
		// 4/20170711_224313_115.jpg   
		// 5/20170711_231756_162.jpg
		// 6/20170711_235951_232.jpg   
		// 7/20170712_000451_976.jpg
		
	/*	String filename= "resources/loot/ok/new";
		File folder = new File(filename);
		for (File file: folder.listFiles()) {
			//File file  = new File("resources/loot/trash/rope.jpg");
			BufferedImage image = ImageIO.read(file);
			
			ImageParser parser = new ImageParser(ImageType.FISH_LOOT, image);
			parser.getCodes();
			parser.getkeyFromTemlate();
		}*/
		
		Screen screen = new Screen("resources/debug/-1/20170722_012605_63.jpg");
	
		ImageParser imageParser = new ImageParser(ImageType.KAPCHA, screen.getImage());
		imageParser.getCodes();
		imageParser.getkeyFromTemlate();
		
		//String key = parser.getkeyFromTemlate();
		//System.out.println("key " + key);
		
	}
}