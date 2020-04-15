package model;

import java.util.Observable;

public class ReversiModel extends Observable  {

	private int xPos;
	private int yPos;
	private int totBlack = 2;
	private int totWhite = 2;
	private int[][] arr = new int[8][8];
	
	public ReversiModel() { 
		// 0 is transparent
		// 1 is white
		// 2 is black
		arr[3][3] = 1;
		arr[4][4] = 1;
		arr[4][3] = 2;
		arr[3][4] = 2;
	}
	
	public void updateSpace(int row, int col, int color) {
		arr[row][col] = color;
	}

	public int getTotWhite() {
		return totWhite;
	}

	public void setTotWhite(int totWhite) {
		this.totWhite = totWhite;
	}
	
	public int incrementWhite() {
		return totWhite + 1;
	}

	public int getTotBlack() {
		return totBlack;
	}

	public void setTotBlack(int totBlack) {
		this.totBlack = totBlack;
	}
	
	public int incrementBlack() {
		return totBlack + 1;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int[][] getBoard() {
		return arr;
	}
}
