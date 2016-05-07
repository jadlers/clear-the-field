/**
 * The actual field that's built with squares in a rectangular
 * shape. It's this field that you want to make safer.
 */
public class Field {
	private Square[][] field;

	public Field(int width, int height) {
		field = createField(width, height);
	}

	private Square[][] createField(int width, int height) {
		Square[][] filledField = new Square[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				filledField[x][y] = new Square();
			}
		}
		return filledField;
	}
}
