import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Showing the game graphically using JavaFX. This class is responsible for starting
 * the graphical view and updating it.
 */
public class GraphicView extends Application {
	private Stage window;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The main entry point for all JavaFX applications.
	 * The start method is called after the init method has returned,
	 * and after the system is ready for the application to begin running.
	 *
	 * NOTE: This method is called on the JavaFX Application Thread.
	 *
	 * @param primaryStage the primary stage for this application, onto which
	 *                     the application scene can be set. The primary stage will be embedded in
	 *                     the browser if the application was launched as an applet.
	 *                     Applications may create other stages, if needed, but they will not be
	 *                     primary stages and will not be embedded in the browser.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Clear The Field");

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		Label gameTitle = new Label("Welcome to Clear the Field!");
		Button playButton = new Button("Play");
		layout.getChildren().addAll(gameTitle, playButton);


		Scene startScreen = new Scene(layout, 600, 400);
		window.setScene(startScreen);
		window.show();

	}
}
