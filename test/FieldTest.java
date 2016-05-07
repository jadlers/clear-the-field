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
}
