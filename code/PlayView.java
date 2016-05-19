import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A window for playing the game
 */
public class PlayView {
	Stage window;
	Scene playScene;
	Field field;
	int size = 40;
	int margin = 3; // (0.1 * size) + 1

	public PlayView(int[] fieldSize, int numberOfMines) {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		field = new Field(fieldSize[0], fieldSize[1], numberOfMines);
		setupScene();
	}

	/**
	 * Sets up the playScene for the first time. Later it's
	 * updated with the updateScene method.
	 */
	public void setupScene() {
		playScene = new Scene(new Group());

		playScene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
			updateSquareSize();
			updateScene(playScene);
		});
		playScene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
			updateSquareSize();
			updateScene(playScene);
		});

		updateScene(playScene);
		window.setScene(playScene);
		window.show();
	}

	/**
	 * Sets the size of the squares so that they all fit into the
	 * window size.
	 */
	private void updateSquareSize() {
		int windowWidth = (int) window.getWidth();
		int windowHeight = (int) window.getHeight();

		if (windowWidth < windowHeight) {

			/**
			 * width = amount*size + (amount - 1) * (size * 0.1 + 1)
			 * width = amount*size + (amount - 1) * size * 0.1 + (amount - 1)
			 * width - (amount - 1) = amount * size + amount * size * 0.1 - size * 0.1
			 * width - (amount - 1) = size * (amount + amount * 0.1 - 0.1)
			 * size = (width - amount + 1) / (amount + amount * 0.1 - 0.1)
			 */

			int fieldWidth = field.getField().length;
			int newSize = (windowWidth - fieldWidth + 1)/(fieldWidth + (int) ((fieldWidth * 0.1) - 0.1));
			int newMargin = (int) (newSize*0.1) + 1;
			size = newSize;
			margin = newMargin;
		} else {
			int fieldHeight = field.getField()[0].length;
		}
	}

	/**
	 * Redraws all the squares and updates the scene specified
	 * with the updated field. Adds event listeners to all squares
	 * that either clear or flag the clicked square.
	 *
	 * @param scene the scene to update
	 */
	public void updateScene(Scene scene) {
		Group layout = new Group();
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
				figure.setLayoutX(x*size+x*margin);
				figure.setLayoutY(y*size+y*margin);
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
				FinishedGame.display(window, "Hero of the day!", "You did it!\nThe field is now a safe place.");
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
