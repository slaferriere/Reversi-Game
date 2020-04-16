package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import model.ReversiModel;

/**
 * This class collects all of the test methods for ReversiModel.
 * 
 * In eclipse, running it should run it under JUnit. Running the Reversi.java class (since
 * it is our main class) will actually run the program. If you're not using eclipse,
 * you'll need to run this under JUnit 5. 
 * 
 * @author Trevor Freudig, Scott LaFerriere
 *
 */
public class ReversiModelTests {
	
	@Test
	void testUpdateSpace() {
		ReversiModel model = new ReversiModel();
		
		model.updateSpace(3, 3, 2);
		int[][] arr = model.getBoard();
		assertEquals(arr[3][3], 2);
		
		model.updateSpace(4, 4, 2);
		int[][] arr2 = model.getBoard();
		assertEquals(arr2[4][4], 2);
	}

	@Test
	void testGetWhite() {
		ReversiModel model = new ReversiModel();
		
		assertEquals(model.getTotWhite(), 2);
		
		model.setTotWhite(4);
		assertEquals(model.getTotWhite(), 4);
		
		assertEquals(model.incrementWhite(), 5);
	}

	@Test
	void testGetBlack() { 
		ReversiModel model = new ReversiModel();
		
		assertEquals(model.getTotBlack(), 2);
		
		model.setTotBlack(4);
		assertEquals(model.getTotBlack(), 4);
		
		assertEquals(model.incrementBlack(), 5);
	}
	
	@Test
	void testGetBoard() { 
		ReversiModel model = new ReversiModel();
		int[][] arr = new int[8][8];
		arr[3][3] = 1;
		arr[4][4] = 1;
		arr[4][3] = 2;
		arr[3][4] = 2;
		
		assertArrayEquals(model.getBoard(), arr);
	}

}
