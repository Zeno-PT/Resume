package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.game.BackgroundPicture;
import logic.game.FontUse;
import logic.game.InsertAnimationPictureFailedException;

public class Main extends Application {

	private static ArrayList<GridPane> sceneList = new ArrayList<GridPane>(); // All scenes of the game
	private static GridPane root = new GridPane(); // The scene that will appear
	private static GridPane currentScene = new GridPane(); // The current scene of root

	private static double screenWidth = 940; // Adjust Screen Width
	private static double screenHeight = 575; // Adjust Screen Height

	private static MenuPane menuPane = new MenuPane();
	private static GamePane gamePane = new GamePane();
	private static CreditPane creditPane = new CreditPane();
	private static AchievementPane achievementPane = new AchievementPane();
	private static PlayerInformationPane playerInformationPane = new PlayerInformationPane();

	@Override
	public void start(Stage primaryStage) throws InsertAnimationPictureFailedException {

		root.setPrefSize(Main.getScreenWidth(), Main.getScreenHeight());
		Main.sceneList.add(menuPane); // sceneList[0]
		Main.sceneList.add(creditPane); // sceneList[1]
		Main.sceneList.add(gamePane); // sceneList[2]
		Main.sceneList.add(achievementPane); // sceneList[3]
		Main.sceneList.add(playerInformationPane); // sceneList[4]

		for (int i = 0; i < 5; i++) {
			root.getChildren().add(sceneList.get(i));
			root.getChildren().get(i).setVisible(false);
			root.getChildren().get(i).setDisable(true);
		}
		Main.setScene(0); // Set Scene as MenuPane
		currentScene = menuPane;
		Scene scene = new Scene(root, getScreenWidth(), getScreenHeight());

		String image = ClassLoader.getSystemResource("image/Logo3.png").toString();
		Image imageView = new Image(image);

		primaryStage.getIcons().add(imageView);
		primaryStage.setTitle("Dota 2 But… Tap Kappa!!!");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void setupButton(Button button) { // Set FontStyle and set MouseEvent on button
		button.setFont(FontUse.font);
		button.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.NONE, CornerRadii.EMPTY, null)));
		button.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		button.setOpacity(0.6);
		button.setOnMouseEntered(event -> {
			button.setOpacity(0.9);
			button.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		button.setOnMouseExited(event -> {
			button.setOpacity(0.6);
			button.setBackground(
					new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		});
	}

	public static void setScene(int index) { // Set scene as sceneList[index]
		root.getChildren().get(index).setVisible(true);
		root.getChildren().get(index).setDisable(false);
		currentScene.setVisible(false);
		currentScene.setDisable(true);
		currentScene = sceneList.get(index);
		Main.playBackground(index);
	}

	public static void playBackground(int index) { // Set Background for each pane
		if (root.getChildren().get(index).equals(menuPane)) {
			BackgroundPicture.setMenuBackgroundPicture(root);
		} else if (root.getChildren().get(index).equals(gamePane)) {
			BackgroundPicture.setMainBackgroundPicture(root);
		} else if (root.getChildren().get(index).equals(creditPane)) {
			BackgroundPicture.setCreditBackgroundPicture(root);
		} else if (root.getChildren().get(index).equals(achievementPane)) {
			BackgroundPicture.setAchievementBackgroundPicture(root);
		} else if (root.getChildren().get(index).equals(playerInformationPane)) {
			BackgroundPicture.setMoreInformationBackgroundPicture(root);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	// GETTER/SETTER
	public static GridPane getRoot() {
		return Main.root;
	}

	public static MenuPane getMenuPane() {
		return Main.menuPane;
	}

	public static GamePane getGamePane() {
		return Main.gamePane;
	}

	public static CreditPane getCreditPane() {
		return Main.creditPane;
	}

	public static AchievementPane getAchievementPane() {
		return Main.achievementPane;
	}

	public static PlayerInformationPane getPlayerInformationPane() {
		return Main.playerInformationPane;
	}

	public static double getScreenWidth() {
		return Main.screenWidth;
	}

	public static double getScreenHeight() {
		return Main.screenHeight;
	}
}
