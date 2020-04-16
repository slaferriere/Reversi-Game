package view;

import model.ReversiModel;

public class ReversiBoard {


	private int[][] board;
	private ReversiModel model;
	
	
	public ReversiBoard(ReversiModel model) {
		this.model = model;
	}
	
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
	
	public int getColor(int row, int col) {
		board = model.getBoard();
		return board[row][col];
	}
	
	
}
