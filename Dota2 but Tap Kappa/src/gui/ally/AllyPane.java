package gui.ally;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import logic.game.GameManager;
import logic.game.InsertAnimationPictureFailedException;

public class AllyPane extends GridPane {

	private Ally ally;
	private Label allyInformation;
	private Button buyButton;
	private boolean appeared;
	private boolean isPurchased; // Set as true when you have enough money to buy ally
	private boolean isLocked; // Set as true when you locked that ally (cannot hired and level up)
	private Timeline timeline;

	public AllyPane(String allyName) {
		this.ally = new Ally(allyName);
		allyInformation = new Label();
		buyButton = new Button();
		GameManager.setAllyLabel(allyInformation, ally);
		allyInformation.setFont(Font.font(null, FontWeight.BOLD, 12));
		allyInformation.setPrefWidth(100);

		String image = ClassLoader.getSystemResource(this.ally.getAllyUrl()).toString();
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(48);
		imageView.setFitHeight(48);

		if (ally.getAllyName() == "BibleThump") {
			this.appeared = true;
		} else {
			this.appeared = false;
		}

		this.isPurchased = false;
		this.isLocked = false;

		this.setButtonLabel(ally, buyButton);
		buyButton.setAlignment(Pos.CENTER);
		buyButton.setTextAlignment(TextAlignment.CENTER);
		buyButton.setFont(Font.font(null, FontWeight.BOLD, 12));
		buyButton.setPrefWidth(120);

		this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.buyButton
				.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(8.0), Insets.EMPTY)));
		this.buyButton.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(8.0), BorderWidths.DEFAULT)));

		this.add(imageView, 0, 0);
		this.add(this.getAllyInformation(), 1, 0);
		this.add(buyButton, 2, 0);
		this.setHgap(10);
		this.setPrefWidth(320);

	}

	public void setTimeline() { // Set timeline for ally to attack() every second INDEFINITE time
		timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> {
			try {
				this.getAlly().attack();
			} catch (InsertAnimationPictureFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public void setTimelineStop() {
		timeline.stop();
	}

	public void setTimelinePause() {
		timeline.pause();
	}

	public void select() {
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, null)));
	}

	public void locked() {
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, null)));
	}

	// Set label on the button according to ally level and price
	public void setButtonLabel(Ally ally, Button button) {
		if (ally.getAllyLevel() == 0) {
			button.setText("Hire\n" + ally.getAllyPrice() + " golds");
		} else {
			button.setText("Level Up\n" + ally.getAllyPrice() + " golds");
		}
	}

	// GETTER/SETTER
	public Ally getAlly() {
		return this.ally;
	}

	public Label getAllyInformation() {
		return this.allyInformation;
	}

	public void setAllyInformation(Label allyInformation) {
		this.allyInformation = allyInformation;
	}

	public boolean isAppear() {
		return this.appeared;
	}

	public void setAppear(boolean appear) {
		this.appeared = appear;
	}

	public Button getButton() {
		return this.buyButton;
	}

	public void setButton(Button button) {
		this.buyButton = button;
	}

	public boolean isPurchase() {
		return this.isPurchased;
	}

	public void setPurchase(boolean purchase) {
		this.isPurchased = purchase;
	}

	public boolean isLocked() {
		return this.isLocked;
	}

	public void setLocked(boolean locked) {
		this.isLocked = locked;
	}

	public Timeline getTimeline() {
		return this.timeline;
	}

}
