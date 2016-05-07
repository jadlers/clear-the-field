import java.util.Random;

/**
 * The actual field that's built with squares in a rectangular
 * shape. It's this field that you want to make safer.
 */
public class Field {
	private Square[][] field;

	/**
	 * Creates a field with the given dimensions if both the width and the height
	 * values are > 1. Also places mines in approximately 20% of the Squares
	 * in the field.
	 *
	 * @param width the width of the field
	 * @param height the height of the field
	 */
	public Field(int width, int height) {
		checkDimension(width, height);
		field = createField(width, height);
		int numberOfMines = (width*height)/5;
		if (numberOfMines < 1) {
			numberOfMines = 1;
		}
		placeMines(numberOfMines);
	}

	/**
	 * Creates a field with the given dimensions if both the width and the height
	 * values are > 1. Also places mines in the given amount of Squares
	 *
	 * @param width the width of the field
	 * @param height the height of the field
	 * @param numberOfMines the amount of mines to be placed in the field
	 */
	public Field(int width, int height, int numberOfMines) {
		checkDimension(width, height);
		field = createField(width, height);
		placeMines(numberOfMines);
	}

	/**
	 * Places the number of mines randomly in the field.
	 *
	 * @param numberOfMines
	 */
	private void placeMines(int numberOfMines) {
		int leftToPlace = numberOfMines;
		Random rand = new Random();
		while (leftToPlace > 0) {
			int x = rand.nextInt(field.length);
			int y = rand.nextInt(field[0].length);
			// If it's not a mine => place one
			if (!field[x][y].mine()) {
				field[x][y].placeMine();
				leftToPlace--;
			}
		}
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

	/**
	 * Ensures that the two-dimensional field array can be created correctly
	 * else it throws an IllegalArgumentException.
	 *
	 * @param width the width to check
	 * @param height the height to check
	 * @throws IllegalArgumentException
	 */
	private void checkDimension(int width, int height) throws IllegalArgumentException {
		if (width < 2 || height < 2) {
			throw new IllegalArgumentException("Both the width & height must be > 1");
		}
	}
}
