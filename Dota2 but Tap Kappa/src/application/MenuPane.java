package application;

import javafx.animation.Animation;
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
import javafx.stage.Stage;
import logic.game.BackgroundMusic;
import logic.game.FontUse;

public class MenuPane extends GridPane {

	private Button start = new Button("Start");
	private Button exit = new Button("Exit");
	private Button creditButton = new Button("Credits");
	private Label gameName = new Label();
	private ImageView imageViewLogo;

	public MenuPane() { // Main Menu Scene
		// TODO Auto-generated constructor stub
		this.setPrefSize(Main.getScreenWidth(), Main.getScreenHeight());
		// Set Background Music
		BackgroundMusic.getMenuTheme().setVolume(0.25);
		BackgroundMusic.getMenuTheme().setCycleCount(Animation.INDEFINITE);
		BackgroundMusic.getMenuTheme().play();
		// Start Button
		Main.setupButton(start);
		start.setPrefSize(200, 20);
		start.setMinWidth(200);
		start.setOnAction(click -> {
			Main.setScene(2); // Set scene as GamePane
			BackgroundMusic.getMenuTheme().stop();
			BackgroundMusic.getMainTheme().setVolume(0.5);
			BackgroundMusic.getMainTheme().setCycleCount(Animation.INDEFINITE);

			if (GamePane.getMonster().getMonsterCount() == 10) {
				BackgroundMusic.setBossNumber(BackgroundMusic.getBossNumber() - 1);
				BackgroundMusic.setBossMusic();
			} else {
				BackgroundMusic.getMainTheme().play();
			}

		});
		// Exit Button
		Main.setupButton(exit);
		exit.setPrefSize(200, 20);
		exit.setMinWidth(200);
		exit.setOnAction(click -> {
			Stage stage = (Stage) exit.getScene().getWindow(); // Exit the Program
			stage.close();
		});
		// Credit Button
		Main.setupButton(creditButton);
		creditButton.setPrefSize(200, 20);
		creditButton.setMinWidth(200);
		creditButton.setOnAction(click -> {
			Main.setScene(1); // Set Scene as CreditPane
			BackgroundMusic.getMenuTheme().stop();
			BackgroundMusic.getCreditTheme().setVolume(0.4);
			BackgroundMusic.getCreditTheme().setCycleCount(Animation.INDEFINITE);
			BackgroundMusic.getCreditTheme().play();
		});
		// Set Game Name
		gameName = new Label("DOTA2 BUT...\n      TAP KAPPA!!!");
		gameName.setFont(FontUse.font);
		gameName.setTextFill(Color.BLACK);
		gameName.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10))));
		gameName.setBackground(
				new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		gameName.setMinHeight(100);
		gameName.setMinWidth(300);
		gameName.setOpacity(0.6);
		// Set Game Logo
		String image = ClassLoader.getSystemResource("image/Logo3.png").toString();
		imageViewLogo = new ImageView(image);
		imageViewLogo.setFitWidth(150);
		imageViewLogo.setFitHeight(150);
		imageViewLogo.setTranslateX(30);
		this.setVgap(30);
		this.setHgap(360);
		this.add(start, 0, 1);
		this.add(creditButton, 0, 2);
		this.add(exit, 0, 3);
		this.add(gameName, 1, 0);
		this.add(imageViewLogo, 0, 0);
		this.setPadding(new Insets(50));
		this.setAlignment(Pos.CENTER);

	}

	// GETTER/SETTER
	public Button getStart() {
		return this.start;
	}

	public Button getExit() {
		return this.exit;
	}

	public Button getCreditButton() {
		return this.creditButton;
	}

	public Label getGameName() {
		return this.gameName;
	}

	public ImageView getImageViewLogo() {
		return this.imageViewLogo;
	}

}
