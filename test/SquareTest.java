import org.junit.*;

/**
 * Tests all methods in the Square class
 */
public class SquareTest {
	public Square square;

	@Before
	public void setUp() throws Exception {
		square = new Square();
	}

	@Test
	public void testMine() throws Exception{
		Assert.assertFalse(square.mine());
		square.placeMine();
		Assert.assertTrue(square.mine());
	}

	@Test
	public void testFlag() {
		Assert.assertFalse(square.flagged());
		square.placeFlag();
		Assert.assertTrue(square.flagged());
		square.removeFlag();
		Assert.assertFalse(square.flagged());
	}

	/**
	 * Tests the check method if there is no mine on the square.
	 */
	@Test
	public void testCheckNoMine() {
		Assert.assertFalse(square.cleared());
		Assert.assertTrue(square.clear());      // It should be possible to clear
	}

	/**
	 * Tests the check method if the is a mine on the square.
	 */
	@Test
	public void testCheckWithMine() {
		Assert.assertFalse(square.cleared());
		square.placeMine();
		Assert.assertFalse(square.clear());     // It should not be possible to clear
	}
}
