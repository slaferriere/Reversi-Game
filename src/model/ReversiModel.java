package model;

import java.util.Observable;

public class ReversiModel extends Observable  {

	private int xPos;
	private int yPos;
	private int totBlack = 2;
	private int totWhite = 2;
	
	public ReversiModel(int x, int y) { 
		this.setxPos(x);
		this.setyPos(y);
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
}
