package view;

import model.ReversiModel;

/**
 * This class captures the row, column, and color of a move and passes
 * it from the model to the view.
 * 
 * @author Trevor Freudig, Scott LaFerriere
 *
 */
public class ReversiBoard {

	private int[][] board;
	private ReversiModel model;
	
	/**
	 * Constructor for ReversiBoard.
	 * 
	 * @param model instance of ReversiModel
	 */
	public ReversiBoard(ReversiModel model) {
		this.model = model;
	}
	
	/**
	 * This method returns the current instance of the board.
	 * 
	 * @return int[][] current instance of board
	 */
	public int[][] getBoard() {
		board = model.getBoard();
		return board;
	}
	
	public void printstuff() {
		board = model.getBoard();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(board[i][j]);
			}
		}
	}
	
	/**
	 * This method gets and returns the color of the token
	 * at the given location.
	 * 
	 * @param row row position
	 * @param col col position
	 * @return int color at the position (row, col)
	 */
	public int getColor(int row, int col) {
		board = model.getBoard();
		return board[row][col];
	}
	
	
}
