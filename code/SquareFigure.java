import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * A visual representation of a Square, will get updated for every
 * frame in the game.
 */
public class SquareFigure extends StackPane {
	private final Paint notClearedPaint = Paint.valueOf(Color.LIGHTGRAY.toString());
	private final Paint clearedPaint = Paint.valueOf(Color.GRAY.toString());
	private final Paint textPaint = Paint.valueOf(Color.BLACK.toString());

	public SquareFigure(String info, int size, boolean notCleared) {
		Rectangle rectangle = new Rectangle(size, size);
		Text label = new Text(info);

		// Begin styling
		if (notCleared) {
			rectangle.setFill(clearedPaint);
		} else {
			rectangle.setFill(notClearedPaint);
		}
		rectangle.setStroke(textPaint);
		rectangle.setArcHeight(5);
		rectangle.setArcWidth(5);
		label.setFill(textPaint);
		// End styling

		getChildren().addAll(rectangle, label);
	}
}
