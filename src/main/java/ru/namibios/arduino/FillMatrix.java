package ru.namibios.arduino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.namibios.arduino.model.MatrixElement;

public class FillMatrix {

	private static final int MIN_ROW_INDEX = 20;
	
	public static final int SYMBOL_ROW = 14;
	public static final int SYMBOL_COLUMN = 12;
	
	private static final int EMPTY = 0;
	private static final int VALUE = 1;
	
	private int[][] matrix;
	private Map<Integer, MatrixElement> elements;
	
	private int maxRow;
	private int maxColumn;
	
	private int counter;
	
	public FillMatrix(int[][] matrix, int row, int column) {
		this.matrix = matrix;
		this.elements = new HashMap<>();
		
		this.maxRow = row;
		this.maxColumn = column;
		
		this.counter = 2;
	}
	
	private void fill(int row, int column){
		
		//up
		if(row > 0 && matrix[row - 1][column] == VALUE){
			matrix[row - 1][column] = counter;
			fill(row - 1 , column);
		}
		
		//down
		if(row < maxRow - 1 && matrix[row + 1][column] == VALUE){
			matrix[row + 1][column] = counter;
			fill(row + 1 , column);
		}
		
		//left
		if(column > 0 && matrix[row][column - 1] == VALUE){
			matrix[row][column - 1]= counter;
			fill(row , column - 1);
		}
		
		//right
		if(column < maxColumn - 1  && matrix[row][column + 1] == VALUE){
			matrix[row][column + 1] = counter;
			fill(row , column + 1);
		}
	}
	
	public int getCounter(){
		return this.counter;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}

	private void clear(int obj){
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				if(matrix[i][j] == obj) matrix[i][j] = EMPTY;
			}
		}
	}
	
	public void cleanOfBounds(int minCnt, int maxCnt){
		int objCnt= 2;
		
		while(objCnt < counter){
			int sum=0;
			int minIndexRow=maxRow;
			int minIndexColumn= maxColumn;
			int maxIndexRow = 0;
			int maxIndexColumn= 0;
			
			for (int i = 0; i < maxRow; i++) {
				for (int j = 0; j < maxColumn; j++) {
					if(matrix[i][j] == objCnt){
						
						if(minIndexRow > i)    minIndexRow= i;
						if(minIndexColumn > j) minIndexColumn= j; 
						if(maxIndexRow < i)    maxIndexRow= i; 
						if(maxIndexColumn < j) maxIndexColumn= j; 
						
						sum++;
					} 
				}
			}
			
			boolean isIntervalOk = sum < minCnt || sum > maxCnt;
			boolean isDimensionOk = maxIndexRow - minIndexRow < 5 || maxIndexColumn - minIndexColumn < 5;
			boolean isMinIndexOk = minIndexRow < MIN_ROW_INDEX;
			if(isIntervalOk || isDimensionOk || isMinIndexOk){
				clear(objCnt);
			} else{
				MatrixElement element = new MatrixElement(minIndexRow, maxIndexRow, minIndexColumn, maxIndexColumn);
				elements.put(objCnt, element);
				System.out.print("Object " + objCnt + " sum= " + sum + " | ");
				System.out.println(element);
			}
			objCnt++;
		}
		
	}
	
	public List<int[][]> toListMatrix(){
		List<int[][]> rezult = new ArrayList<>();
		
		for (int key : elements.keySet()) {
			MatrixElement element = elements.get(key);
			int[][] symbol = new int[SYMBOL_ROW][SYMBOL_COLUMN];
			int row=0;
			int column=0;
			for (int i = element.getMinRow(); i <= element.getMaxRow(); i++) {
				column=0;
				for (int j = element.getMinColumn(); j <= element.getMaxColumn(); j++) {
					symbol[row][column] = matrix[i][j];
					column++;
				}
				System.out.println();
				row++;
			}
			rezult.add(symbol);
		}

		return rezult;
	}
	
	public void markupMatrix(){
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				if(matrix[i][j] == VALUE){
					fill(i, j);
					counter++;
				}
			}
		}
	}
	
}