import org.junit.*;
/**
 * Tests all methods in the Field class
 */
public class FieldTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testConstructorToSmallArea() {
		boolean rightExceptionThrown = false;
		try {
			new Field(0, 0);
		} catch (IllegalArgumentException e) {
			rightExceptionThrown = true;
		}
		Assert.assertTrue(rightExceptionThrown);
	}

	@Test
	public void testCreateField() {
		int width = 10;
		int height = 10;
		Field field = new Field(width, height);
		Square[][] area = field.getField();
		Assert.assertEquals(width, area.length);
		Assert.assertEquals(height, area[0].length);
	}

	@Test
	public void testPlaceMines() {
		int placedMines = 10;
		int foundMines = 0;
		Field field = new Field(10, 10, placedMines);
		Square[][] area = field.getField();
		for (Square[] x: area) {
			for (Square current : x) {
				if (current.mine()) {
					foundMines++;
				}
			}
		}
		Assert.assertEquals(placedMines, foundMines);
	}

	/**
	 *  | M | M | M | when trying to clear
	 *  ------------- the X spot it should
	 *  | M | X | M | return 8
	 *  ------------- X = uncleared
	 *  | M | M | M | M = mine
	 */
	@Test
	public void testMinesSurroundingMiddle() {
		// I want to place the mines myself
		Field field = new Field(10, 10, 0);
		Square[][] area = field.getField();
		area[0][0].placeMine();
		area[0][1].placeMine();
		area[0][2].placeMine();
		area[1][0].placeMine();
		area[1][2].placeMine();
		area[2][0].placeMine();
		area[2][1].placeMine();
		area[2][2].placeMine();
		int surrounding = field.minesSurrounding(1, 1);
		Assert.assertEquals(8, surrounding);
	}

	/**
	 *  | X | M | M | when trying to clear
	 *  ------------- the X spot it should
	 *  | M | M | M | return 3
	 *  ------------- X = uncleared
	 *  | M | M | M | M = mine
	 */
	@Test
	public void testMinesSurroundingCorner() {
		// I want to place the mines myself
		Field field = new Field(10, 10, 0);
		Square[][] area = field.getField();
		area[0][1].placeMine();
		area[1][1].placeMine();
		area[1][0].placeMine();
		int surrounding = field.minesSurrounding(0, 0);
		Assert.assertEquals(3, surrounding);
	}
}
