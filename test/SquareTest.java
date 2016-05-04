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
}
