package controller;

import model.ReversiModel;

public class ReversiController {
	
	private ReversiModel model;
	private int whiteScore;
	private int blackScore;
	
	public ReversiController(ReversiModel model) {
		this.model = model;
	}

	public int getWhiteScore() {
		return whiteScore;
	}

	public int getBlackScore() {
		return blackScore;
	}
	
	

}
