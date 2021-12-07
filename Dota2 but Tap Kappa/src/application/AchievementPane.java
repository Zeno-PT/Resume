package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.game.BackgroundMusic;
import logic.game.BackgroundPicture;
import logic.game.FontUse;

public class AchievementPane extends GridPane {

	private VBox achievementVBox = new VBox();
	private Label achievementLabel = new Label("Achievement Unlocked");
	private ScrollPane achievementScrollPane = new ScrollPane();
	private Button backButtonFromAchievement = new Button("Back");

	public AchievementPane() { // Achievement Scene
		// TODO Auto-generated constructor stub
		this.setPrefSize(Main.getScreenWidth(), Main.getScreenHeight());
		BackgroundPicture.setAchievementBackgroundPicture(Main.getRoot());
		// Back to Game Pane
		Main.setupButton(backButtonFromAchievement);
		backButtonFromAchievement.setPrefSize(100, 50);
		backButtonFromAchievement.setFont(FontUse.font35);
		backButtonFromAchievement.setOnAction(click -> {
			Main.setScene(2); // Set scene as GamePane
			BackgroundMusic.getAchievementTheme().stop();
			if (GamePane.getMonster().getMonsterCount() == 10) {
				BackgroundMusic.setBossNumber(BackgroundMusic.getBossNumber() - 1);
				BackgroundMusic.setBossMusic();
			} else {
				BackgroundMusic.getMainTheme().play();
			}
		});
		// Headline
		achievementLabel.setFont(FontUse.font35);
		achievementLabel.setAlignment(Pos.CENTER);
		achievementLabel
				.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		achievementLabel.setOpacity(0.75);
		achievementLabel.setPadding(new Insets(15));
		// Achievement Detail
		achievementScrollPane.setContent(achievementVBox);
		achievementScrollPane.setFitToWidth(true);
		achievementScrollPane.setPrefHeight(280);
		achievementScrollPane.setOpacity(0.9);
		this.setVgap(50);
		this.add(achievementLabel, 0, 0);
		this.add(achievementScrollPane, 0, 1);
		this.add(backButtonFromAchievement, 0, 2);
		this.setAlignment(Pos.CENTER);
	}

	// GETTER/SETTER
	public VBox getAchievementVBox() {
		return this.achievementVBox;
	}

	public Label getAchievementLabel() {
		return this.achievementLabel;
	}

	public ScrollPane getAchievementScrollPane() {
		return this.achievementScrollPane;
	}

	public Button getBackButtonFromAchievement() {
		return this.backButtonFromAchievement;
	}

}
