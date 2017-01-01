import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A window for playing the game
 */
public class PlayView {
	Stage window;
	Scene playScene;
	Field field;
	long startTime;
	float totalTime;
	final int size = 20;
	final int margin = 3;

	public PlayView(int[] fieldSize, int numberOfMines) {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setResizable(false);
		field = new Field(fieldSize[0], fieldSize[1], numberOfMines);
		setupScene();
	}

	/**
	 * Sets up the playScene for the first time. Later it's
	 * updated with the updateScene method.
	 */
	public void setupScene() {
		playScene = new Scene(new Group());
		updateScene(playScene);
		window.setScene(playScene);
		window.show();
		startTime = System.currentTimeMillis();
	}

	/**
	 * Redraws all the squares and updates the scene specified
	 * with the updated field. Adds event listeners to all squares
	 * that either clear or flag the clicked square.
	 *
	 * @param scene the scene to update
	 */
	public void updateScene(Scene scene) {
		int padding = 10;
		Pane layout = new Pane();
		layout.setPadding(new Insets(0, padding, padding, 0));
		for (int x = 0; x < field.getField().length; x++) {
			for (int y = 0; y < field.getField()[0].length; y++) {
				SquareFigure figure = field.createSquareFigure(x, y, size);
				int currentX = x;
				int currentY = y;
				figure.setOnMouseClicked(event -> {
					if (event.getButton() == MouseButton.PRIMARY && event.isShiftDown()) {
						secondaryKey(currentX, currentY);
					} else if (event.getButton() == MouseButton.PRIMARY) {
						primaryKey(currentX, currentY);
					}
				});
				figure.setLayoutX(padding + x*size+x*margin);
				figure.setLayoutY(padding + y*size+y*margin);
				layout.getChildren().add(figure);
			}
		}
		scene.setRoot(layout);
		playScene = scene;
	}

	/**
	 * Tries to clear the clicked square. If it's possible to clear it then
	 * update the scene and play on. Else loose the game since the player
	 * tried to clear a mine.
	 *
	 * @param x the x coordinate of the square in the field
	 * @param y the y coordinate of the square in the field
	 */
	private void primaryKey(int x, int y) {
		boolean okay = field.click(x, y);
		if (okay) {
			updateScene(playScene);
			boolean won = field.checkWin();
			if (won) {
				totalTime = System.currentTimeMillis() - startTime;
				System.out.printf("%.2fs\n", totalTime/1000);
				FinishedGame.display(window, "Hero of the day!", "You did it!\nThe field is now a safe place.");
				new Scoreboard();
			}
		} else {
			FinishedGame.display(window, "Explosion!", "Sorry to say it but you hit\n a mine and lost " +
					"the game :(");
		}
	}

	/**
	 * Toggles if the square is flagged or not.
	 *
	 * @param x the x coordinate of the square in the field
	 * @param y the y coordinate of the square in the field
	 */
	private void secondaryKey(int x, int y) {
		field.toggleFlag(x, y);
		updateScene(playScene);
	}
}
