import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A window for playing the game
 */
public class PlayView {
	Stage window;
	Field field;
	final int size = 40;
	final int margin = 5;

	public PlayView() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		field = new Field(10, 10, 2);
		updateScene();
	}

	public void updateScene() {
		Scene scene = updateField();
		window.setScene(scene);
		window.show();
	}

	public Scene updateField() {
		Group layout = new Group();
		for (int x = 0; x < field.getField().length; x++) {
			for (int y = 0; y < field.getField()[0].length; y++) {
				SquareFigure figure = field.createSquareFigure(x, y, size, size);
				int currentX = x;
				int currentY = y;
				figure.setOnMouseClicked(event -> {
					field.click(currentX, currentY);
					updateScene();
				});
				figure.setLayoutX(x*size+x*margin);
				figure.setLayoutY(y*size+y*margin);
				layout.getChildren().add(figure);
			}
		}
		return new Scene(layout, 600, 500);
	}
}
