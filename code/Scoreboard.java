/**
 * Keeps the best local scores of all time. The scores with name
 * is saved in a file called highScores. Will show the top 10? scores.
 */
public class Scoreboard {

	/**
	 * Shows the current scoreboard.
	 */
	public Scoreboard() {

	}

	/**
	 * Class that holds all the information on one high score entry.
	 */
	private class Score {
		public String name;
		public float time;

		public Score(String name, float time) {
			this.name = name;
			this.time = time;
		}
	}
}
