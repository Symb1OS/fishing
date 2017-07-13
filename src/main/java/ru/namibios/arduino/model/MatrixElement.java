package ru.namibios.arduino.model;

public class MatrixElement {
	
	private int minRow;
	private int maxRow;
	
	private int minColumn;
	private int maxColumn;
	
	public MatrixElement(int minRow, int maxRow, int minColumn, int maxColumn) {
		this.minRow = minRow;
		this.maxRow = maxRow;
		this.minColumn = minColumn;
		this.maxColumn = maxColumn;
	}

	public int getMinRow() {
		return minRow;
	}

	public void setMinRow(int minRow) {
		this.minRow = minRow;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMinColumn() {
		return minColumn;
	}

	public void setMinColumn(int minColumn) {
		this.minColumn = minColumn;
	}

	public int getMaxColumn() {
		return maxColumn;
	}

	public void setMaxColumn(int maxColumn) {
		this.maxColumn = maxColumn;
	}

	@Override
	public String toString() {
		return "MatrixElement [minRow=" + minRow + ", maxRow=" + maxRow + ", minColumn=" + minColumn + ", maxColumn="
				+ maxColumn + "]";
	}
}