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
		if (numberOfMines >= width*height) {
			throw new IllegalArgumentException("Amount of mines must be < total area");
		}
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
		Square[][] copy = field.clone();
		return copy;
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

	/**
	 * Checks all the spots right next to the given Square for mines and returns
	 * the amount it finds. It can be from 0 to 8 mines surrounding each Square.
	 *
	 * @param x the x position of the Square
	 * @param y the y position of the Square
	 * @return the amount of mines surrounding the Square
	 */
	public int minesSurrounding(int x, int y) {
		int surrounding = 0;

		int[] bounds = defineBounds(x, y);
		for (int curX = bounds[0]; curX <= bounds[1]; curX++) {
			for (int curY = bounds[2]; curY <= bounds[3]; curY++) {
				if (field[curX][curY].mine()) {
					surrounding++;
				}
			}
		}
		return surrounding;
	}

	/**
	 * Defines the max and min values of x and y when checking the
	 * surrounding 8 Squares of one specific Square. This is to
	 * prevent any ArrayIndexOutOfBounds exceptions.
	 *
	 * Indices in bounds array:
	 * 0 = xMin, 1 = xMax, 2 = yMin, 3 = yMax
	 *
	 * @param x the x value to check around
	 * @param y the y value to check around
	 * @return valid bounds around the specified x & y
	 */
	private int[] defineBounds(int x, int y) {
		int[] bounds = new int[4];
		bounds[0] = x-1;
		bounds[1] = x+1;
		bounds[2] = y-1;
		bounds[3] = y+1;

		if (bounds[0] < 0) {
			bounds[0] = 0;
		} else if (bounds[1] == field.length) {
			bounds[1] = x;
		}
		if (bounds[2] < 0) {
			bounds[2] = 0;
		} else if (bounds[3] == field[0].length) {
			bounds[3] = y;
		}

		return bounds;
	}

	/**
	 * Gets a specific Square from the field
	 *
	 * @param x the x coordinate of the square in the field
	 * @param y the y coordinate of the square in the field
	 * @return the Square on the position
	 */
	private Square getSquare(int x, int y) {
		return field[x][y];
	}
}
