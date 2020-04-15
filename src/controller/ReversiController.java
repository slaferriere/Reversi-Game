package controller;

import model.ReversiModel;

public class ReversiController {
	
	private ReversiModel model;
	private int turn;

	public ReversiController(ReversiModel model) {
		this.model = model;
	}

	public int getWhiteScore() {
		return 0;
	}
	
	public int getBlackScore() {
		return 0;
	}
	
	// Method that checks to see if move is valid
	public boolean isMoveValid() {
		return true;
	}
	
	public void humanTurn(int row, int col) {
		
	}
	
	public void computerTurn() {
		
	}
	
	public boolean endGame() {
		return true;
	}
	
	public void incrementTurn() {
		turn++;
	}
	
	public int getTurn() {
		return turn;
	}
	
}
