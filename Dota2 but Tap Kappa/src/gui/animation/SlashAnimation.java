package gui.animation;

import application.Main;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import logic.game.InsertAnimationPictureFailedException;

public class SlashAnimation extends Animation {

	private static final String[] slashListURL = { "image/animation/slash1.png", "image/animation/slash2.png",
			"image/animation/slash3.png", "image/animation/slash4.png", "image/animation/slash5.png" };
	private static boolean used; // Return true if that animation is running

	public SlashAnimation() {
		SlashAnimation.used = false;
	}

	public static void showAnimation() throws InsertAnimationPictureFailedException {
		if (!isUsed()) {
			Thread thread = new Thread(() -> {
				SlashAnimation.used = true;
				for (int i = 0; i < 5; i++) {
					String slash = ClassLoader.getSystemResource(slashListURL[i]).toString();
					ImageView imageViewSlash = new ImageView(slash);
					imageViewSlash.setFitWidth(200);
					imageViewSlash.setFitHeight(200);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Main.getGamePane().add(imageViewSlash, 1, 1);
						}
					});
					try {
						Thread.sleep(40);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								try {
									SlashAnimation.removeAnimation(imageViewSlash);
								} catch (InsertAnimationPictureFailedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				SlashAnimation.used = false;
			});
			thread.start();
		}
	}

	// GETTER/SETTER
	public static boolean isUsed() {
		return SlashAnimation.used;
	}

	public static void setUsed(boolean used) {
		SlashAnimation.used = used;
	}

	public static String[] getExplosionlisturl() {
		return SlashAnimation.slashListURL;
	}

}
