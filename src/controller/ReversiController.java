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
	private int changedRow;
	private int changedCol;

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
			model.updateSpace(row, col, colorCode);
			return true;
		}
			
		return false;
	}
	
	/**
	 * Javadoc comment
	 * 
	 * @param row 
	 * @param col
	 * @return
	 */
	private boolean validOnRow(int row, int col) {
		int rowCount1 = row + 1;
		int rowCount2 = row + 2;
		
		if (row + 2 > 7) {
			rowCount2 = 7;
		} 
		
		if (row + 1 > 7) {
			rowCount1 = 7;
		}
		
		if(row < 6) { // Checks all pieces to the right
			if(board[rowCount1][col] == oppColorCode) {
				for(int i = rowCount2; i < 8; i++) {
					if(board[i][col] == 0) {
						break;
					} else if(board[i][col] == colorCode) {
						for(int j = row; j < i; j++) {
							model.updateSpace(j, col, colorCode);
						}
						return true;
					}
				}
			}
		}
		
		rowCount1 = row - 1;
		rowCount2 = row - 2;
		
		if(row > 1) { // Checks all pieces to the left
			if(board[rowCount1][col] == oppColorCode) {
				for(int i = rowCount2; i > -1; i--) {
					if(board[i][col] == 0) {
						break;
					} else if(board[i][col] == colorCode) {
						for(int j = row; j > i; j--) {
							model.updateSpace(j, col, colorCode);
						}
						return true;
					}
				}
			}
		}
		
//		if(board[rowCount1][col] == oppColorCode && board[rowCount2][col] == colorCode) { // piece to the left is opp color
//			changedRow = rowCount1;
//			changedCol = col;
//			model.updateSpace(rowCount1, col, colorCode);
//			return true;
//		} 
		
//		rowCount1 = row - 1;
//		rowCount2 = row - 2;
//		if(board[rowCount1][col] == oppColorCode && board[rowCount2][col] == colorCode) { // checks right
//			changedRow = rowCount1;
//			changedCol = col;
//			model.updateSpace(rowCount1, col, colorCode);
//			return true;
//		} 
		
		return false;
	}
	
	/**
	 * Javadoc Comment
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean validOnCol(int row, int col) {
		int colCount1 = col + 1;
		int colCount2 = col + 2;
		
		if (col + 2 > 7) {
			colCount2 = 7;
		} 
		
		if (col + 1 > 7) {
			colCount1 = 7;
		}
		
		if(col < 6) { // Checks all pieces above
			if(board[row][colCount1] == oppColorCode) {
				for(int i = colCount2; i < 8; i++) {
					if(board[row][i] == 0) {
						break;
					} else if(board[row][i] == colorCode) {
						for(int j = col; j < i; j++) {
							model.updateSpace(row, j, colorCode);
						}
						return true;
					}
				}
			}
		}
		
		
		colCount1 = col - 1;
		colCount2 = col - 2;
		
		if(col > 1) { // Checks all pieces below
			if(board[row][colCount1] == oppColorCode) {
				for(int i = colCount2; i > -1; i--) {
					if(board[row][i] == 0) {
						break;
					} else if(board[row][i] == colorCode) {
						for(int j = col; j > i; j--) {
							model.updateSpace(row, j, colorCode);
						}
						return true;
					}
				}
			}
		}
		

		return false;
	}
	
	/**
	 * Javadoc comment
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean validOnDiagonal(int row, int col) {
		
		int colCount1 = col + 1;
		int colCount2 = col + 2;
		int rowCount1 = row + 1;
		int rowCount2 = row + 2;
		
		if (row + 2 > 7) {
			rowCount2 = 7;
		} 
		
		if (row + 1 > 7) {
			rowCount1 = 7;
		}
		
		if (col + 2 > 7) {
			colCount2 = 7;
		} 
		
		if (col + 1 > 7) {
			colCount1 = 7;
		}
		
		int maxCount = Math.max(rowCount2, colCount2);
		
		if(col < 6) { // Checks all pieces below and right
			if(board[rowCount1][colCount1] == oppColorCode) {
				for(int i = maxCount; i < 8; i++) {
					if(board[i][i] == 0) {
						break;
					} else if(board[i][i] == colorCode) {
						for(int j = col; j < i; j++) {
							model.updateSpace(j, j, colorCode);
						}
						return true;
					}
				}
			}
		}
		
		
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
	
	

	/**
	 * Javadoc comment
	 * 
	 * @param row
	 * @param col
	 */

	/**
	 * Javadoc comment
	 * 
	 * @param row
	 * @param col
	 */
	public void humanTurn(int row, int col) {
		
	}
	
	/**
	 * Javadoc comment
	 */
	public void computerTurn() {

	}
	
	// Not completely done b/c only checks if one color has a valid move
	// Don't wanna increment the turn in case it fucks something else up
	/**
	 * Javadoc comment
	 * 
	 * @return
	 */
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
	
	/**
	 * Javadoc comment
	 */
	public void incrementTurn() {
		turn++;
	}
	
	/**
	 * Javadoc comment
	 * 
	 * @return
	 */
	public int getTurn() {
		return turn;
	}
	
	/**
	 * JAvadoc comment
	 * 
	 * @return
	 */
	public int getChangedRow() {
		return changedRow;
	}
	
	/**
	 * Javadoc comment
	 * 
	 * @return
	 */
	public int getChangedCol() {
		return changedCol;
	}

	
}
