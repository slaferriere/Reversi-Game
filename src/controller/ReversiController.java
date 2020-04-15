package controller;

import model.ReversiModel;

public class ReversiController {
	
	private ReversiModel model;
	private int turn;
	private int colorCode;
	private int oppColorCode;
	private int[][] board;
	private int whiteScore;
	private int blackScore;

	public ReversiController(ReversiModel model) {
		this.model = model;
	}

	public int getWhiteScore() {
		calculateScore();
		return whiteScore;
	}
	
	public int getBlackScore() {
		calculateScore();
		return blackScore;
	}
	
	private void calculateScore() {
		whiteScore = 0;
		blackScore = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j] == 1) {
					whiteScore++;
				} else if(board[i][j] == 2) {
					blackScore++;
				}
			}
		}
	}
	
	// Method that checks to see if move is valid
	public boolean isMoveValid(int row, int col) {
		
		if(turn % 2 == 0) {
			colorCode = 1; // White
			oppColorCode = 2; // Black
		} else {
			colorCode = 2;
			oppColorCode = 1;
		}
		
		board = model.getBoard();
		
		
		//Checks if space is already occupied
		if(board[row][col] != 0) { 
			return false;
		}
		
		
		if (validOnRow(row, col) || validOnCol(row, col) || validOnDiagonal(row, col)) {
			updateBoard(row, col);
			return true;
		}
			
		
		
		return false;
	}
	
	// The validOnRow, validOnCol, and validOnDiagonal is pretty sloppy b/c it's pretty much just copy pasted code
	// but I'm pretty sure it works so fuck it
	
	// Doesn't handle situation when click is on edge of board
	
	// Checks to see if move works based on checking the row
	private boolean validOnRow(int row, int col) {
		int possibleSpots = 8 - row;
		if(board[row + 1][col] == oppColorCode) { // piece to the right is opp color
			for(int i = 2; i < possibleSpots; i++) {
				if(board[row + i][col] == 0) {
					return false;
				} else if(board[row + i][col] == colorCode) {
					return true;
				}
			}
		} else if(board[row - 1][col] == oppColorCode) { // checks left
			for(int i = 2; i < possibleSpots; i++) {
				if(board[row - i][col] == 0) {
					return false;
				} else if(board[row - i][col] == colorCode) {
					return true;
				}
			}
			
		} 
		return false;
	}
	
	// Checks to see if move works based on checking the column
	private boolean validOnCol(int row, int col) {
		int possibleSpots = 8 - col;
		if(board[row][col + 1] == oppColorCode) { // piece above is opp color
			for(int i = 2; i < possibleSpots; i++) {
				if(board[row][col + i] == 0) {
					return false;
				} else if(board[row][col + i] == colorCode) {
					return true;
				}
			}
		} else if(board[row][col - 1] == oppColorCode) { // piece below is opp color
			for(int i = 2; i < possibleSpots; i++) {
				if(board[row][col - i] == 0) {
					return false;
				} else if(board[row][col - i] == colorCode) {
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	// Checks to see if move works based on checking the diagonals
	private boolean validOnDiagonal(int row, int col) {
		int possibleSpots = 8 - col;
		if(board[row + 1][col + 1] == oppColorCode) { // piece above right is opp color
			for(int i = 2; i < possibleSpots; i++) {
				if(board[row + i][col + i] == 0) {
					return false;
				} else if(board[row + i][col + i] == colorCode) {
					return true;
				}
			}
		} else if(board[row + 1][col - 1] == oppColorCode) { // piece below right is opp color
			for(int i = 2; i < possibleSpots; i++) {
				if(board[row + i][col - i] == 0) {
					return false;
				} else if(board[row + i][col - i] == colorCode) {
					return true;
				}
			}
			
		} else if(board[row - 1][col - 1] == oppColorCode) { // piece below left is opp color
			for(int i = 2; i < possibleSpots; i++) {
				if(board[row - i][col - i] == 0) {
					return false;
				} else if(board[row - i][col - i] == colorCode) {
					return true;
				}
			}
			
		} else if(board[row - 1][col + 1] == oppColorCode) { // piece above left is opp color
			for(int i = 2; i < possibleSpots; i++) {
				if(board[row - i][col + i] == 0) {
					return false;
				} else if(board[row - i][col + i] == colorCode) {
					return true;
				}
			}
			
		}
		return false;
	}
	
	

	// Updates the 2D array in the model
	public void updateBoard(int row, int col) {
		model.updateSpace(row, col, colorCode);
	}

	
	public void humanTurn(int row, int col) {
		
	}
	
	public void computerTurn() {
		
	}
	
	// Not completely done b/c only checks if one color has a valid move
	// Don't wanna increment the turn in case it fucks something else up
	public boolean isGameOver() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j] == 0) {
					if(isMoveValid(i, j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void incrementTurn() {
		turn++;
	}
	
	public int getTurn() {
		return turn;
	}

	
}
