package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.game.BackgroundPicture;
import logic.game.FontUse;
import logic.game.GameManager;

public class PlayerInformationPane extends GridPane {

	private static Label playerTotalInformation = new Label();
	private Button backtoRootButton = new Button("Back");
	private Label moreLabel = new Label("More Information");

	public PlayerInformationPane() { // Player Total Information Scene
		// TODO Auto-generated constructor stub
		this.setPrefSize(Main.getScreenWidth(), Main.getScreenHeight());
		// Back to Game Pane
		Main.setupButton(backtoRootButton);
		backtoRootButton.setPrefSize(100, 50);
		backtoRootButton.setFont(FontUse.font30);
		backtoRootButton.setOnAction(click -> {
			Main.setScene(2); // Set scene as GamePane
		});
		BackgroundPicture.setMoreInformationBackgroundPicture(Main.getRoot()); // set Player Information Scene
																				// Background
		// PlayerInformationPane Headline
		moreLabel.setFont(FontUse.font35);
		moreLabel.setBackground(new Background(new BackgroundFill(Color.GOLD, new CornerRadii(10), Insets.EMPTY)));
		moreLabel.setOpacity(0.7);
		moreLabel.setPadding(new Insets(20));
		// Player Information
		playerTotalInformation.setFont(Font.font(null, FontWeight.BOLD, 17));
		playerTotalInformation
				.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		playerTotalInformation.setOpacity(0.7);
		playerTotalInformation.setPadding(new Insets(20));
		this.add(moreLabel, 0, 0);
		this.add(playerTotalInformation, 0, 1);
		this.add(backtoRootButton, 0, 2);
		this.setVgap(40);
		this.setAlignment(Pos.CENTER);
		GameManager.setPlayerTotalInformation(playerTotalInformation);
	}

	// GETTER/SETTER
	public static Label getPlayerTotalInformation() {
		return PlayerInformationPane.playerTotalInformation;
	}

	public Button getBacktoRootButton() {
		return this.backtoRootButton;
	}

	public Label getMoreLabel() {
		return this.moreLabel;
	}

}
