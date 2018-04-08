package ie.gmit.sw.ai;

import java.util.*;

public class Playfair {
	
	private List<Position> position;
	private StringBuilder text;
	private char[][] cipTable;
	private String cipText;
	
	public Playfair(String cipText) {
		super();
		this.position = new LinkedList<Position>();
		this.text = new StringBuilder();
		this.cipTable = new char[5][5];
		this.cipText = cipText;
	}

	public String decrpytText(String key) throws Exception{
		
		System.out.println("DECRYPT TEXT");
		
		String deKey = key;
		char[][] cipTable = new char[5][5];
		int index = 0;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; i < 5; i++) {
				cipTable[i][j] = deKey.charAt(index);
				index++;
			}
		}
		
		// Setting the array to this and creating a String Builder
		//this.cipTable = cipTable;
		StringBuilder builder = new StringBuilder();
		
		// When the index is equal to one, less than the length of the cipher text divided by two
		for(index = 0; index < cipText.length()/2; index ++){
			
			// Setting a and b to characters in the position of index multiplied by two
			char a = this.cipText.charAt(2 * index);
			char b = this.cipText.charAt(2 * index + 1);
			
			System.out.println("IN DECRYPT A: " + a + " B: " + b);
			
			// Get the positions of the rows and columns for both
			int row1 = (int) Position.getPosition(a, cipTable).getOne();
			int column1 = (int) Position.getPosition(a, cipTable).getTwo();
			int row2 = (int) Position.getPosition(b, cipTable).getOne();
			int column2 = (int) Position.getPosition(b, cipTable).getTwo();
		
			// If the rows are equal then set the columns to add four with modulo five
			if(row1 == row2) {
				column1 = (column1 + 4) % 5;
				column2 = (column2 + 4) % 5;
			}
			// If the columns are equal then set the rows to add four with modulo five
			else if(column1 == column2) {
				row1 = (row1 + 4) % 5;
				row2 = (row2 + 4) % 5;
			}
			// Otherwise create temp & set to column one, set column one to column two and set column two to temp
			else {
				int temp = column1;
				column1 = column2;
				column2 = temp;
			}
			// Append the cipher table of row & column one, row & column two to the String Builder variable
			builder.append(cipTable[row1][column1] + "" + cipTable[row2][column2]);
		}
		// Return the builder and convert to string
		return builder.toString();
	}
	
	/*
	//
	// GETTERS AND SETTERS
	//
	*/
	
	public List<Position> getPosition() {
		return position;
	}

	public void setPosition(List<Position> position) {
		this.position = position;
	}

	public StringBuilder getText() {
		return text;
	}

	public void setText(StringBuilder text) {
		this.text = text;
	}

	public char[][] getCipTable() {
		return cipTable;
	}

	public void setCipTable(char[][] cipTable) {
		this.cipTable = cipTable;
	}

	public String getCipText() {
		return cipText;
	}

	public void setCipText(String cipText) {
		this.cipText = cipText;
	}

}
