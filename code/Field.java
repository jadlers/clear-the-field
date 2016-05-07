/**
 * The actual field that's built with squares in a rectangular
 * shape. It's this field that you want to make safer.
 */
public class Field {
	private Square[][] field;

	public Field(int width, int height) {
		if (width < 2 || height < 2) {
			throw new IllegalArgumentException("Both the width & height must be > 1");
		}
		field = createField(width, height);
	}

	/**
	 * Fills up the two-dimensional array of Squares that represents the field.
	 *
	 * @param width the number of squares horizontal
	 * @param height the number of squares vertical
	 * @return the field filled with Squares
	 */
	private Square[][] createField(int width, int height) {
		Square[][] filledField = new Square[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				filledField[x][y] = new Square();
			}
		}
		return filledField;
	}

	/**
	 * @return the Square[][] that represents the field
	 */
	public Square[][] getField() {
		return field;
	}
}
