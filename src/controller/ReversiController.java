package controller;

import java.util.ArrayList;

import java.util.List;


import model.ReversiModel;

/**
 * This class handles the main calculations and logic of the program. In this 
 * class, the score is calculated/updated and each move is checked to be 
 * valid or not. There are methods that check validity for a row move, col
 * move, and diagonal move. The controller modifies the main board object of
 * the program which is found in the model.
 * 
 * @author Trevor Freudig, Scott LaFerriere
 *
 */
public class ReversiController {
	
	private ReversiModel model;
	private int turn;
	private int colorCode;
	private int oppColorCode;
	private int[][] board;
	private int whiteScore;
	private int blackScore;
	private boolean validRow;
	private boolean validCol;
	private boolean validDiagonal;
	private List<List<Integer>> updatedSpaces = new ArrayList<List<Integer>>();
	private int placeHolder = 2;

	/**
	 * Constructor for ReversiController.
	 * 
	 * @param model instance of ReversiModel
	 */
	public ReversiController(ReversiModel model) {
		this.model = model;
	}

	/**
	 * Getter method for the white score.
	 * 
	 * @return int white score
	 */
	public int getWhiteScore() {
		calculateScore();
		return whiteScore;
	}
	
	/**
	 * Getter method for the black score.
	 * 
	 * @return int black score
	 */
	public int getBlackScore() {
		calculateScore();
		return blackScore;
	}
	
	/**
	 * This method iterates through every stackpane in the board 
	 * and determines how many white pieces there are and how many
	 * black pieces there are.
	 */
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
	
	/**
	 * This method calls helper methods to determine whether or not a move 
	 * is valid. 
	 * 
	 * @param row current row of clicked location
	 * @param col current col of clicked location
	 * @return boolean if move is valid or not
	 */
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
		
		// Call helper methods to check validity of move
		validOnRow(row, col);
		validOnCol(row, col);
		validOnDiagonal(row, col);
		
		if (validRow || validCol || validDiagonal) {
			model.updateSpace(row, col, colorCode);
			return true;
		}
			
		return false;
	}
	
	/**
	 * This method uses iteration logic to determine if a move is valid
	 * either to the left or to the right of another piece (in the row in
	 * which the user clicks). This method also accounts for multiple
	 * pieces of the opposite color in between the clicked location and the 
	 * location that is the same color as the current turn.
	 * 
	 * @param row current row of clicked location
	 * @param col current col of clicked location
	 */
	private void validOnRow(int row, int col) {
		
		validRow = false;
		
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
							List<Integer> coordinates = new ArrayList<>();
							coordinates.add(j);
							coordinates.add(col);
							updatedSpaces.add(coordinates);
						}
						validRow = true;
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
							List<Integer> coordinates = new ArrayList<>();
							coordinates.add(j);
							coordinates.add(col);
							updatedSpaces.add(coordinates);
						}
						validRow = true;
					}
				}
			}
		}
			
		
	}
	
	/**
	 * This method uses iteration logic to determine if a move is valid
	 * either above or below of another piece (in the col in
	 * which the user clicks). This method also accounts for multiple
	 * pieces of the opposite color in between the clicked location and the 
	 * location that is the same color as the current turn.
	 * 
	 * @param row current row of clicked location
	 * @param col current col of clicked location
	 */
	private void validOnCol(int row, int col) {
		int colCount1 = col + 1;
		int colCount2 = col + 2;
		
		validCol = false;
		
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
							List<Integer> coordinates = new ArrayList<>();
							coordinates.add(row);
							coordinates.add(j);
							updatedSpaces.add(coordinates);
						}
						validCol = true;
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
							List<Integer> coordinates = new ArrayList<>();
							coordinates.add(row);
							coordinates.add(j);
							updatedSpaces.add(coordinates);
						}
						validCol = true;
					}
				}
			}		
		} 
	}
	
	/**
	 * This method uses iteration logic to determine if a move is valid
	 * diagonally from another piece. This method also accounts for multiple
	 * pieces of the opposite color in between the clicked location and the 
	 * location that is the same color as the current turn.
	 * 
	 * @param row current row of clicked location
	 * @param col current col of clicked location
	 */
	private void validOnDiagonal(int row, int col) {
		
		validDiagonal = false;
		
		int newRow = row;
		int newCol = col;
		
		if (row + 1 > 7) {
			newRow = 5;
		}
		
		if (col - 1 < 0) {
			newCol = 2;
		}
			
		//Bottom left
		if(board[newRow + 1][newCol - 1] == oppColorCode) {
			int count = newCol;
			for(int i = newRow + 1; i < 8; i++) {
				if (count - 1 < 0) {
					continue;
				} else {
					count--;
				}
				if(board[i][count] == 0) {
					break;
				} else if(board[i][count] == colorCode) {
					int rowCount = newRow;
					int colCount = newCol;
					for(int j = newRow; j < i; j++) {
						rowCount++;
						colCount--;
						List<Integer> coordinates = new ArrayList<>();
						coordinates.add(rowCount);
						coordinates.add(colCount);
						updatedSpaces.add(coordinates);
					}
					validDiagonal = true;
				}
			}
		}
		
		if (row - 1 < 0) {
			newRow = 2;
		} else {
			newRow = row;
		}
		
		if (col - 1 < 0) {
			newCol = 2;
		} else {
			newCol = col;
		}
		
		// Bottom right
		if(board[newRow - 1][newCol - 1] == oppColorCode) {
			int count = newCol;
			for(int i = newRow - 1; i >= 0; i--) {
				if (count - 1 < 0) {
					continue;
				} else {
					count--;
				}
				if(board[i][count] == 0) {
					break;
				} else if(board[i][count] == colorCode) {
					int rowCount = newRow;
					int colCount = newCol;
					for(int j = newRow; j > i; j--) {
						rowCount--;
						colCount--;
						List<Integer> coordinates = new ArrayList<>();
						coordinates.add(rowCount);
						coordinates.add(colCount);
						updatedSpaces.add(coordinates);
					}
					validDiagonal = true;
				}
			}
		}
			
		if (row + 1 > 7) {
			newRow = 5;
		} else {
			newRow = row;
		}
		
		if (col + 1 > 7) {
			newCol = 5;
		} else {
			newCol = col;
		}
		
 		// Top left
		if(board[newRow + 1][newCol + 1] == oppColorCode) {
			int count = newCol;
			for(int i = newRow + 1; i < 8; i++) {
				if (count + 1 > 7) {
					continue;
				} else {
					count++;
				}
				if(board[i][count] == 0) {
					break;
				} else if(board[i][count] == colorCode) {
					int rowCount = newRow;
					int colCount = newCol;
					for(int j = newRow; j < i; j++) {
						rowCount++;
						colCount++;
						List<Integer> coordinates = new ArrayList<>();
						coordinates.add(rowCount);
						coordinates.add(colCount);
						updatedSpaces.add(coordinates);
					}
					validDiagonal = true;
				}
			}
		}	
		
		if (col + 1 > 7) {
			newCol = 5;
		} else {
			newCol = col;
		}
		
		if (row - 1 < 0) {
			newRow = 2;
		} else {
			newRow = row;
		}
		
		// Top right
		if(board[newRow - 1][newCol + 1] == oppColorCode) {
			int count = newCol;
			for(int i = newRow - 1; i >= 0; i--) {
				if (count + 1 > 7) {
					continue;
				} else {
					count++;
				}
				if(board[i][count] == 0) {
					break;
				} else if(board[i][count] == colorCode) {
					int rowCount = newRow;
					int colCount = newCol;
					for(int j = newRow; j > i; j--) {
						rowCount--;
						colCount++;
						List<Integer> coordinates = new ArrayList<>();
						coordinates.add(rowCount);
						coordinates.add(colCount);
						updatedSpaces.add(coordinates);
					}
					validDiagonal = true;
				}
			}
		} 
	}

	/**
	 * This method iterates through the 2D array of spaces that's color need to be updated due to a human turn
	 * capturing those pieces. It then increments the turn. 
	 * 
	 * updatedSpaces is a 2D array which contains the coordinated of the spaces whose color needs to be updated
	 * EX: [ [2,1] , [2,2] ,[4,5], ...]
	 * 
	 * @param row current row of clicked location
	 * @param col current col of clicked location
	 */
	public void humanTurn(int row, int col) {
		updatedSpaces = new ArrayList<List<Integer>>();
		if(isMoveValid(row, col)) {
			for(int i = 0; i < updatedSpaces.size(); i++) {
				model.updateSpace(updatedSpaces.get(i).get(0), updatedSpaces.get(i).get(1), colorCode);
			}
			turn++;
		} else {
			System.out.println("Not  valid move");
		}
		
	}
	
	/**
	 * This method makes a move for a computer player. It goes through every possible spot on the board,
	 * and adds potential updated spaces to a 2D array. It then finds which move would provide
	 * the largest amount of updated spaces (captured pieces) and makes that move.
	 * 
	 * NOT COMPLETELY CORRECT YET
	 */
	public void computerTurn() {
		int maxLength = 0;
		//updatedSpaces2 = new ArrayList<List<Integer>>();
		List<List<Integer>> tempUpdatedSpaces = new ArrayList<List<Integer>>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				updatedSpaces = new ArrayList<List<Integer>>();
				if(isMoveValid(i, j)) {
					if(updatedSpaces.size() > maxLength) {
						maxLength = updatedSpaces.size();
						tempUpdatedSpaces = updatedSpaces;
					}
				}
				
			}
		}
		
		
		for(int i = 0; i < tempUpdatedSpaces.size(); i++) {
			model.updateSpace(tempUpdatedSpaces.get(i).get(0), tempUpdatedSpaces.get(i).get(1), colorCode);
		}
		
		turn++;

	}

	
	/**
	 * This method returns a boolean determining whether or not the game 
	 * is over.
	 * 
	 * @return boolean true if game is over, false otherwise
	 */
	public boolean isGameOver() {
		boolean gameOverWhite = false;
		boolean gameOverBlack = false;
		int boardCount = 0;
		placeHolder = 2;
		
		if (model.getTotWhite() == 0 || model.getTotBlack() == 0) {
			return true;
		}
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {		
				if (model.getBoard()[i][j] != 0) {
					boardCount++;
				}
			}
		}
		
		if (boardCount == 64) {
			return true;
		}
		
		// Check game over for white
		gameOverWhite = checkGameOver();
		
		placeHolder++;
		
		// Check game over for black
		gameOverBlack = checkGameOver();
		
		if (gameOverWhite || gameOverBlack) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Helper method for isGameOver().
	 * 
	 * @return boolean representing the status of the game based off 
	 * of if there are any valid moves left
	 */
	private boolean checkGameOver() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {		
				if(placeHolder % 2 == 0) {
					colorCode = 1; // White
					oppColorCode = 2; // Black
				} else {
					colorCode = 2;
					oppColorCode = 1;
				}
				
				// Call helper methods to check validity of move
				validOnRow(i, j);
				validOnCol(i, j);
				validOnDiagonal(i, j);
				
				if (validRow || validCol || validDiagonal) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * This method simply increments the current turn each time
	 * a move is played.
	 */
	public void incrementTurn() {
		turn++;
	}
	
	/**
	 * Getter method for the current turn.
	 * 
	 * @return int current turn
	 */
	public int getTurn() {
		return turn;
	}
}
