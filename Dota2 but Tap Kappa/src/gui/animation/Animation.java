package gui.animation;

import application.Main;
import javafx.scene.image.ImageView;
import logic.game.InsertAnimationPictureFailedException;

public class Animation {

	public static void showAnimation() throws InsertAnimationPictureFailedException {
	}

	// Remove ImageView from Game Pane
	public static void removeAnimation(ImageView i) throws InsertAnimationPictureFailedException {
		Main.getGamePane().getChildren().remove(i);
	}

}
