/**
 * A single square that has information about whether it contains a mine, if
 * it's flagged or already cleared and the amount of squares surrounding it
 * that contains a mine.
 *
 * @author Jacob Adlers
 */
public class Square {
	private boolean mine;
	private boolean flagged;
	private boolean cleared;

	public Square() {
		mine = false;
		flagged = false;
		cleared = false;
	}

	public boolean mine() {
		return mine;
	}

	public void placeMine() {
		mine = true;
	}

	public boolean flagged() {
		return flagged;
	}

	public void placeFlag() {
		flagged = true;
	}

	public void removeFlag() {
		flagged = false;
	}

	public boolean cleared() {
		return cleared;
	}

	public boolean clear() {
		if (mine) {
			return false;
		} else {
			cleared = true;
			return true;
		}
	}
}
