import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
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
	private Slider widthSlider;
	private Slider heightSlider;
	private Slider minesSlider;

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
		primaryStage.setTitle("Clear The Field");
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
		primaryStage.setScene(startScreen);
		primaryStage.show();
	}

	/**
	 * Opens a new window where the user can specify settings for
	 * the size of the field and the amount of mines in the field
	 */
	private void openSettings() {
		Stage window = new Stage();
		window.setTitle("Settings");
		window.initModality(Modality.APPLICATION_MODAL);

		VBox sliders = groupSliders();
		HBox difficulties = groupDifficulties();

		// Create save & exit button
		Button saveBtn = new Button("Save & Close");

		VBox layout = new VBox(20);
		layout.getChildren().addAll(difficulties, sliders, saveBtn);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20));

		window.setOnCloseRequest(event1 -> saveSettings((int) widthSlider.getValue(), (int) heightSlider.getValue(), (int) minesSlider.getValue()));
		saveBtn.setOnAction(event -> {
			saveSettings((int) widthSlider.getValue(), (int) heightSlider.getValue(), (int) minesSlider.getValue());
			window.close();
		});

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
	}

	/**
	 * Creates different difficulties as Buttons ans groups them
	 * together in a HBox
	 *
	 * @return HBox with all difficulties as children
	 */
	private HBox groupDifficulties() {
		HBox difficulties = new HBox(20);
		difficulties.setAlignment(Pos.CENTER);

		Button easy = createDifficulty("Easy", 10, 10);
		Button medium = createDifficulty("Medium", 15, 45);
		Button hard = createDifficulty("Hard", 20, 100);
		difficulties.getChildren().addAll(easy, medium, hard);

		return difficulties;
	}

	/**
	 * Specifies all the sliders values and limits and group them
	 * together with informative dynamic labels.
	 *
	 * @return VBox with all sliders as children
	 */
	private VBox groupSliders() {
		widthSlider = createSlider(2, 40, fieldSize[0]);
		heightSlider = createSlider(2, 40, fieldSize[1]);
		minesSlider = createSlider(1, (widthSlider.getMax() * heightSlider.getMax()) - 1, numberOfMines);

		Label widthLabel = createLabel("Width: ", widthSlider);
		Label heightLabel = createLabel("height: ", heightSlider);
		Label minesLabel = createLabel("Number of mines: ", minesSlider);
		
		VBox sliders = new VBox(20);
		sliders.getChildren().addAll(widthLabel, widthSlider, heightLabel, heightSlider, minesLabel, minesSlider);

		return sliders;
	}

	/**
	 * Creates a label and assigns a slider to it.
	 *
	 * @param info the constant part of the label
	 * @param slider the slider to connect the label to
	 */
	private Label createLabel(String info, Slider slider) {
		Label label = new Label(info + (int) slider.getValue());
		addSliderListener(slider, label, info);
		return label;
	}

	/**
	 * Adds a listener to update the current value in
	 * the label while sliding.
	 *
	 * @param slider the slider to add the listener to
	 * @param label the label to update
	 */
	private void addSliderListener(Slider slider, Label label, String info) {
		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			String updatedString = info + (int) slider.getValue();
			label.textProperty().setValue(updatedString);
			checkMinesValue();
		});
	}

	/**
	 * Checks to see if the current max value of the mines slider
	 * is higher than the maximum mines allowed in the field. Changes
	 * the max and current value of minesSlider if necessary.
	 */
	private void checkMinesValue() {
		int minesMaxValue = ((int) heightSlider.getValue() * (int) widthSlider.getValue()) - 1;
		if ((int) minesSlider.getMax() != minesMaxValue) {
			minesSlider.setMax(minesMaxValue);
			if ((int) minesSlider.getValue() > minesMaxValue) {
				minesSlider.setValue(minesMaxValue);
			}
		}
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
	 * @return slider with right bounds and current value
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

	/**
	 * Creates a button and updates the settings of the game
	 * to match the name of the button.
	 *
	 * @param name text displayed on the Button
	 * @param fieldSize new width and height of the field
	 * @param numberOfMines the amount of mines in the field
	 * @return a button with text and event handler
	 */
	private Button createDifficulty(String name, int fieldSize, int numberOfMines) {
		Button button = new Button(name);
		button.setOnAction(event -> {
			widthSlider.setValue(fieldSize);
			heightSlider.setValue(fieldSize);
			minesSlider.setValue(numberOfMines);
		});
		return button;
	}
}
