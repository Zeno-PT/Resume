package gui.animation;

import application.GamePane;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import logic.game.InsertAnimationPictureFailedException;

public class SwordMasterAnimation extends Animation { // more animation

	private static String[] swordMasterListURL = { "image/animation/SwordAttack1.png",
			"image/animation/SwordAttack2.png", "image/animation/SwordAttack3.png", "image/animation/SwordAttack4.png",
			"image/animation/SwordAttack5.png", "image/animation/SwordAttack6.png" };
	private static int count; // Represent number of animation frame
	private static boolean used; // Return true if that animation is running
	private static int width; // sword master width
	private static int height; // sword master height

	public SwordMasterAnimation() {
		SwordMasterAnimation.count = 0;
		SwordMasterAnimation.used = false;
		SwordMasterAnimation.width = 150;
		SwordMasterAnimation.height = 150;
	}

	public static void showAnimation() throws InsertAnimationPictureFailedException {
		if (!isUsed()) {
			Thread thread = new Thread(() -> {
				count = 0;
				setUsed(true);
				for (int i = 0; i < 6; i++) {
					String swordURL = ClassLoader.getSystemResource(swordMasterListURL[i]).toString();
					ImageView imageViewSword = new ImageView(swordURL);
					imageViewSword.setFitWidth(SwordMasterAnimation.getWidth());
					imageViewSword.setFitHeight(SwordMasterAnimation.getHeight());
					imageViewSword.setTranslateY(20);
					imageViewSword.setTranslateX(70);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (count > 0) {
								GamePane.getSwordMasterPicture().add(imageViewSword, 0, 1);
							}
						}
					});
					try {
						Thread.sleep(30);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								if (count == 0) {
									SwordMasterAnimation.removeAnimation(GamePane.getImageViewFirstSword());
								} else {
									SwordMasterAnimation.removeAnimation(imageViewSword);
								}
								count += 1;
							}
						});

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						GamePane.getSwordMasterPicture().add(GamePane.getImageViewFirstSword(), 0, 1);
					}
				});
				try {
					Thread.sleep(100);
					SwordMasterAnimation.setUsed(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			thread.start();
		}
	}

	// Remove sword master picture in GamePane
	public static void removeAnimation(ImageView i) {
		GamePane.getSwordMasterPicture().getChildren().remove(i);
	}

	// GETTER/SETTER
	public String[] getSwordMasterListURL() {
		return SwordMasterAnimation.swordMasterListURL;
	}

	public static String[] getSwordmasterlisturl() {
		return SwordMasterAnimation.swordMasterListURL;
	}

	public static boolean isUsed() {
		return SwordMasterAnimation.used;
	}

	public static void setUsed(boolean used) {
		SwordMasterAnimation.used = used;
	}

	public static int getWidth() {
		return SwordMasterAnimation.width;
	}

	public static void setWidth(int width) {
		SwordMasterAnimation.width = width;
	}

	public static int getHeight() {
		return SwordMasterAnimation.height;
	}

	public static void setHeight(int height) {
		SwordMasterAnimation.height = height;
	}

}
