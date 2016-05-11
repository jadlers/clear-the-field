import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Shows a message and then closes the window it was called from
 * as well as itself when the "Back to the menu" button is clicked
 */
public class FinishedGame  {

	public static void display(Stage gameWindow, String title, String message) {
		Stage window = new Stage();
		window.setTitle(title);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		window.setResizable(false);
		window.setOnCloseRequest(event1 -> gameWindow.close());
		Label label = new Label(message);
		label.setTextAlignment(TextAlignment.CENTER);
		Button closeButton = new Button("Back to the menu");
		closeButton.setOnAction(event -> {
			window.close();
			gameWindow.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20));

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
