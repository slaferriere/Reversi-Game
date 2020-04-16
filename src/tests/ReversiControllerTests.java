package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import controller.ReversiController;
import model.ReversiModel;

/**
 * This class collects all of the test methods for ReversiController.
 * 
 * In eclipse, running it should run it under JUnit. Running the Reversi.java class (since
 * it is our main class) will actually run the program. If you're not using eclipse,
 * you'll need to run this under JUnit 5. 
 * 
 * @author Trevor Freudig, Scott LaFerriere
 * 
 */
public class ReversiControllerTests {
	
	@Test
	void testGetWhiteScore() {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		
		assertEquals(controller.getWhiteScore(), 2);
	}

	@Test
	void testGetBlackScore() {
		ReversiModel model = new ReversiModel();
		ReversiController controller = new ReversiController(model);
		
		assertEquals(controller.getBlackScore(), 2);
	}

	
	@Test
	void testValidMove() { 

	}
	
	@Test
	void testUpdateBoard() { 

	}
	
	@Test
	void testHumanTurn() { 

	}
	
	@Test
	void testComputerTurn() { 

	}
	
	@Test
	void testGameOver() { 

	}
	
	@Test
	void testGetTurn() { 

	}
	
	@Test
	void testGetChangedRow() { 

	}
	
	@Test
	void testGetChangedCol() { 

	}
}
