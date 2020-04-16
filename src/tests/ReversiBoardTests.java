package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import model.ReversiModel;
import view.ReversiBoard;

/**
 * This class collects all of the test methods for ReversiBoard.
 * 
 * In eclipse, running it should run it under JUnit. Running the Reversi.java class (since
 * it is our main class) will actually run the program. If you're not using eclipse,
 * you'll need to run this under JUnit 5. 
 * 
 * @author Trevor Freudig, Scott LaFerriere
 *
 */
public class ReversiBoardTests {

	@Test
	void testGetBoard() { 
		ReversiModel model = new ReversiModel();
		ReversiBoard board = new ReversiBoard(model);
		
		assertArrayEquals(board.getBoard(), model.getBoard());
	}
	
	@Test
	void testPrintStuff() { 
		ReversiModel model = new ReversiModel();
		ReversiBoard board = new ReversiBoard(model);
		
		board.printstuff();
		
		int[][] testBoard = model.getBoard();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				assertEquals(testBoard[i][j], model.getBoard()[i][j]);
			}
		}
	}
	
	@Test
	public void testGetColor() {
		ReversiModel model = new ReversiModel();
		ReversiBoard board = new ReversiBoard(model);
		
		assertEquals(board.getColor(3, 3), model.getBoard()[3][3]);
	}
}
