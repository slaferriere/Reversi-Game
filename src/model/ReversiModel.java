package model;

import java.util.Observable;

/**
 * This class holds the underlying structure of the program and specifically
 * the Reversi game board.
 * 
 * @author Trevor Freudig, Scott LaFerriere
 *
 */
public class ReversiModel extends Observable  {


	private int totBlack = 2;
	private int totWhite = 2;
	private int[][] arr = new int[8][8];
	
	/**
	 * Constructor for ReversiModel
	 */
	public ReversiModel() { 
		// 0 is transparent
		// 1 is white
		// 2 is black
		arr[3][3] = 1;
		arr[4][4] = 1;
		arr[4][3] = 2;
		arr[3][4] = 2;
	}
	
	/**
	 * This method updates a given location in the main grid.
	 * 
	 * @param row current row of clicked location
	 * @param col current col of clicked location
	 * @param color color of piece at given location
	 */
	public void updateSpace(int row, int col, int color) {
		arr[row][col] = color;
		setChanged();
		notifyObservers();
	}

	/**
	 * Getter method for total white score.
	 * 
	 * @return int white piece count
	 */
	public int getTotWhite() {
		return totWhite;
	}

	/**
	 * Setter method for white score.
	 * 
	 * @param totWhite amount of white pieces
	 */
	public void setTotWhite(int totWhite) {
		this.totWhite = totWhite;
	}
	
	/**
	 * This method increments the white piece score and returns 
	 * the new white score.
	 * 
	 * @return int new white score
	 */
	public int incrementWhite() {
		return totWhite + 1;
	}

	/**
	 * Getter method for total black score.
	 * 
	 * @return int black piece count
	 */
	public int getTotBlack() {
		return totBlack;
	}

	/**
	 * Setter method for black score.
	 * 
	 * @param totWhite amount of black pieces
	 */
	public void setTotBlack(int totBlack) {
		this.totBlack = totBlack;
	}
	
	/**
	 * This method increments the black piece score and returns 
	 * the new black score.
	 * 
	 * @return int new black score
	 */
	public int incrementBlack() {
		return totBlack + 1;
	}
	
	/**
	 * This method returns a 2D integer array that holds the 
	 * game board.
	 * 
	 * @return int[][] 2D array of game board
	 */
	public int[][] getBoard() {
		return arr;
	}
}
