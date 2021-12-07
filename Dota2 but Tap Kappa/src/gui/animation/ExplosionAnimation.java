package gui.animation;

import application.GamePane;
import application.Main;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import logic.game.InsertAnimationPictureFailedException;

public class ExplosionAnimation extends Animation {

	private static final String[] explosionListURL = { "image/animation/explosion1.png",
			"image/animation/explosion2.png", "image/animation/explosion3.png", "image/animation/explosion4.png",
			"image/animation/explosion5.png", "image/animation/explosion6.png", "image/animation/explosion7.png" };

	public static void showAnimation() throws InsertAnimationPictureFailedException {
		if (GamePane.getMonster().getMonsterHealthPoint() <= 0) {
			Thread thread = new Thread(() -> {
				for (int i = 0; i < 7; i++) {
					String explosion = ClassLoader.getSystemResource(explosionListURL[i]).toString();
					ImageView imageViewExplosion = new ImageView(explosion);
					imageViewExplosion.setFitWidth(200);
					imageViewExplosion.setFitHeight(200);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Main.getGamePane().add(imageViewExplosion, 1, 1);
						}
					});
					try {
						Thread.sleep(100);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								try {
									ExplosionAnimation.removeAnimation(imageViewExplosion);
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

			});
			thread.start();

		}
	}

	// GETTER/SETTER
	public String[] getExplosionListURL() {
		return ExplosionAnimation.explosionListURL;
	}

}
