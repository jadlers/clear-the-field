import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Showing the game graphically using JavaFX. This class is responsible for starting
 * the graphical view and updating it.
 */
public class Game extends Application {
	private int[] fieldSize; // indices 0: width, 1: height
	private int numberOfMines;

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
		Stage window = primaryStage;
		window.setTitle("Clear The Field");
		setDefaultSettings();

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		Label gameTitle = new Label("Welcome to Clear the Field!");
		Button playButton = new Button("Play");
		playButton.setOnAction(event -> new PlayView(fieldSize, numberOfMines));
		Button settingsBtn = new Button("Settings");
		settingsBtn.setOnAction(event -> openSettings());
		layout.getChildren().addAll(gameTitle, playButton, settingsBtn);

		Scene startScreen = new Scene(layout, 400, 300);
		window.setScene(startScreen);
		window.show();

	}

	/**
	 * Opens a new window where the user can specify settings for
	 * the size of the field and the amount of mines in the field
	 */
	private void openSettings() {
		Stage window = new Stage();
		window.setTitle("Settings");
		window.initModality(Modality.APPLICATION_MODAL);

		Slider widthSlider = createSlider(2, 40, fieldSize[0]);
		Slider heightSlider = createSlider(2, 40, fieldSize[1]);
		Slider minesSlider = createSlider(1, 400, numberOfMines);
		Button saveBtn = new Button("Save & Close");

		window.setOnCloseRequest(event1 -> {
			saveSettings((int) widthSlider.getValue(), (int) heightSlider.getValue(), (int) minesSlider.getValue());
		});
		saveBtn.setOnAction(event -> {
			saveSettings((int) widthSlider.getValue(), (int) heightSlider.getValue(), (int) minesSlider.getValue());
			window.close();
		});

		VBox layout = new VBox(20);
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(widthSlider, heightSlider, minesSlider, saveBtn);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
	}

	/**
	 * Uses the parameters to update the settings.
	 *
	 * @param newWidth new width to set
	 * @param newHeight new height to set
	 * @param newMines new amount of mines to set
	 */
	private void saveSettings(int newWidth, int newHeight, int newMines) {
		fieldSize[0] = newWidth;
		fieldSize[1] = newHeight;
		numberOfMines = newMines;
	}

	/**
	 * Creates a slider with the specified min, max and current and
	 * sets the width as well as the major tick distance
	 *
	 * @param min the sliders minimum value
	 * @param max the sliders maximum value
	 * @param current the sliders current value
	 * @return
	 */
	private Slider createSlider(double min, double max, double current) {
		Slider slider = new Slider(min, max, current);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit((max-min)/4);
		slider.setMinWidth(400);
		return slider;
	}

	/**
	 * Sets the default settings for the game.
	 */
	private void setDefaultSettings() {
		fieldSize = new int[]{10, 10};
		numberOfMines = 10;
	}
}
