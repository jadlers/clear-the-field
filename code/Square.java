/**
 * A single square that has information about whether it contains a mine, if
 * it's flagged or already cleared and the amount of squares surrounding it
 * that contains a mine.
 *
 * @author Jacob Adlers
 */
public class Square {
	private boolean mine;

	public Square() {
		mine = false;
	}

	public boolean mine() {
		return mine;
	}

	public void placeMine() {
		mine = true;
	}

	public boolean flagged() {
	}

	public void placeFlag() {
	}

	public void removeFlag() {
	}
}
