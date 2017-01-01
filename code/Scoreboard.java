import java.io.*;

/**
 * Keeps the best local scores of all time. The scores with name
 * is saved in a file called highScores. Will show the top 10? scores.
 */
public class Scoreboard {
	private Reader reader;
	private Writer writer;
	private String FILENAME = "highScore.txt";

	/**
	 * Shows the current scoreboard.
	 */
	public Scoreboard() {
		createReader();
		//createWriter();
		try {
			importScores();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private void importScores() throws IOException {
		System.out.println(reader.read());
	}

	/**
	 * Initializes the reader for use when importing the current high scores.
	 * @return initialised reader
	 */
	private void createReader() {
		try{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME), "utf-8"));
		} catch (IOException e) {/* Report */}
	}

	/**
	 * Initializes the writer for use when updating the high scores.
	 * @return initialised writer
	 */
	private void createWriter() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(FILENAME), "utf-8"));
		} catch (IOException ex) {/* Report */}
	}
}
