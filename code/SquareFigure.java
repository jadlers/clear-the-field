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
	private int x, y;
	private final Paint notClearedPaint = Paint.valueOf(Color.LIGHTGRAY.toString());
	private final Paint clearedPaint = Paint.valueOf(Color.GRAY.toString());
	private final Paint textPaint = Paint.valueOf(Color.BLACK.toString());

	public SquareFigure(String info, int x, int y, int width, int height, boolean cleared) {
		this.x = x;
		this.y = y;
		Rectangle rectangle = new Rectangle(width, height);
		Text label = new Text(info);

		// Begin styling
		if (cleared) {
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
