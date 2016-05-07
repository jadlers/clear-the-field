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
}
