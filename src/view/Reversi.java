package view;

import javafx.application.Application;

/**
 * This is the entry point to the program. This class starts the initial creation of
 * the Reversi GUI and the game.
 * 
 * @author Trevor Freudig, Scott LaFerriere
 *
 */
public class Reversi {	
	public static void main(String[] args) {
		Application.launch(ReversiView.class, args);
	}
}
