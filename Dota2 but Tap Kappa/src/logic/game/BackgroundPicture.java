package logic.game;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

public class BackgroundPicture {

	private static final String[] backgroundListURL = { "image/Background/1.jpg", "image/Background/2.jpg",
			"image/Background/5.jpg", "image/Background/6.jpg", "image/Background/8.png" };

	public static void setMainBackgroundPicture(GridPane gridpane) {
		int number = (int) (Math.random() * 5);
		String backgroundImage = ClassLoader.getSystemResource(backgroundListURL[number]).toString();
		Image backgroundImageView = new Image(backgroundImage);

		BackgroundImage bg = new BackgroundImage(backgroundImageView, null, null, null,
				new BackgroundSize(Main.getScreenWidth(), Main.getScreenHeight(), false, false, false, false));
		gridpane.setBackground(new Background(bg));
	}

	public static void setMenuBackgroundPicture(GridPane gridpane) {
		String backgroundImage = ClassLoader.getSystemResource("image/Background/3.png").toString();
		Image backgroundImageView = new Image(backgroundImage);

		BackgroundImage bg = new BackgroundImage(backgroundImageView, null, null, null,
				new BackgroundSize(Main.getScreenWidth(), Main.getScreenHeight(), false, false, false, false));
		gridpane.setBackground(new Background(bg));
	}

	public static void setMoreInformationBackgroundPicture(GridPane gridpane) {
		String backgroundImage = ClassLoader.getSystemResource("image/Background/9.jpg").toString();
		Image backgroundImageView = new Image(backgroundImage);

		BackgroundImage bg = new BackgroundImage(backgroundImageView, null, null, null,
				new BackgroundSize(Main.getScreenWidth(), Main.getScreenHeight(), false, false, false, false));
		gridpane.setBackground(new Background(bg));
	}

	public static void setAchievementBackgroundPicture(GridPane gridpane) {
		String backgroundImage = ClassLoader.getSystemResource("image/Background/7.jpg").toString();
		Image backgroundImageView = new Image(backgroundImage);

		BackgroundImage bg = new BackgroundImage(backgroundImageView, null, null, null,
				new BackgroundSize(Main.getScreenWidth(), Main.getScreenHeight(), false, false, false, false));
		gridpane.setBackground(new Background(bg));
	}

	public static void setCreditBackgroundPicture(GridPane gridpane) {
		String backgroundImage = ClassLoader.getSystemResource("image/Background/4.jpg").toString();
		Image backgroundImageView = new Image(backgroundImage);

		BackgroundImage bg = new BackgroundImage(backgroundImageView, null, null, null,
				new BackgroundSize(Main.getScreenWidth(), Main.getScreenHeight(), false, false, false, false));
		gridpane.setBackground(new Background(bg));
	}

	// Get backgroundListURL
	public static String[] getBackgroundListURL() {
		return BackgroundPicture.backgroundListURL;
	}

}
