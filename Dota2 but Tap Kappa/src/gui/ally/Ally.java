package gui.ally;

import application.GamePane;
import application.PlayerInformationPane;
import gui.monster.Monster;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import logic.game.Attackable;
import logic.game.BackgroundMusic;
import logic.game.GameManager;
import logic.game.InsertAnimationPictureFailedException;
import logic.game.SoundEffect;

public class Ally implements Attackable {

	private boolean used; // Set as true when player already hired that ally
	private int allyLevel;
	private long allyDamagePerSecond;
	private long allyTotalDamagePerSecond;
	private long allyPrice;
	private String allyName;
	private String allyUrl;
	private double levelUpMultiplier;
	private double levelUpCostMultiplier;

	private boolean firstTime = true; // Use for showing ally picture below the monster
	private String image; // ally image URL
	private ImageView imageView; // ally ImageView

	public Ally(String allyName) {
		this.allyName = allyName;
		this.used = false;
		this.allyLevel = 0; // not buy yet
		this.levelUpMultiplier = 1.4;
		this.levelUpCostMultiplier = 1.2;
		switch (allyName) {
		case "BibleThump":
			this.allyUrl = "image/Emotes/BibleThump.png";
			this.allyPrice = 500;
			this.allyDamagePerSecond = 100;
			break;
		case "CmonBruh":
			this.allyUrl = "image/Emotes/CmonBruh.png";
			this.allyPrice = 1000;
			this.allyDamagePerSecond = 250;
			break;
		case "Kappa":
			this.allyUrl = "image/Emotes/Kappa.png";
			this.allyPrice = 2500;
			this.allyDamagePerSecond = 500;
			break;
		case "Kreygasm":
			this.allyUrl = "image/Emotes/Kreygasm.png";
			this.allyPrice = 5000;
			this.allyDamagePerSecond = 1000;
			break;
		case "LUL":
			this.allyUrl = "image/Emotes/LUL.png";
			this.allyPrice = 10000;
			this.allyDamagePerSecond = 2000;
			break;
		case "PogChamp":
			this.allyUrl = "image/Emotes/PogChamp.png";
			this.allyPrice = 20000;
			this.allyDamagePerSecond = 4000;
			break;
		case "TriHard":
			this.allyUrl = "image/Emotes/TriHard.png";
			this.allyPrice = 50000;
			this.allyDamagePerSecond = 8000;
			break;
		}
	}

	// Method that ally attacks monster 1 time (will be used in Class AllyPane)
	@Override
	public void attack() throws InsertAnimationPictureFailedException {
		GamePane.getMonster().setMonsterHealthPoint(
				(int) (GamePane.getMonster().getMonsterHealthPoint() - this.getAllyDamagePerSecond()));
		GamePane.getMonsterHealthBar().setProgress((double) GamePane.getMonster().getMonsterHealthPoint()
				/ GamePane.getMonster().getMonsterMaxHealthPoint());

		Monster.setNewMonster(GamePane.getMonster());

		AudioClip allyAttackSound = SoundEffect.getAllyAttackSound();

		if (BackgroundMusic.getMainTheme().isPlaying() || BackgroundMusic.getBossTheme().isPlaying()) {
			allyAttackSound.setVolume(0.15);
			allyAttackSound.play();
		}

		for (AllyPane allyPane : GamePane.getAllyVBox().getAllyPaneList()) {
			GameManager.setAllyLabel(allyPane.getAllyInformation(), allyPane.getAlly());
		}

		if (this.isFirstTime()) {
			image = ClassLoader.getSystemResource(this.getAllyUrl()).toString();
			imageView = new ImageView(image);
			imageView.setFitWidth(48);
			imageView.setFitHeight(48);
			if ((AllyVBox.getSlotCount() < 4) && (this.isFirstTime())) {
				GamePane.getAllyPicture().add(imageView, AllyVBox.getSlotCount(), 0);
			} else if ((AllyVBox.getSlotCount() >= 4) && (this.isFirstTime())) {
				GamePane.getAllyPicture().add(imageView, AllyVBox.getSlotCount() - 4, 1);
			}
			AllyVBox.setSlotCount(AllyVBox.getSlotCount() + 1);
			this.setFirstTime(false);
		} // else {
//			Thread s = new Thread(() -> {
//				try {
//					imageView.setTranslateY(5);
//					Thread.sleep(20);
//					imageView.setTranslateY(0);
//				} catch (InterruptedException e) {
//					System.out.println("Error");
//				}
//			});
//			s.start();
//		}

		GameManager.setAllyUpperLabel(GamePane.getAllyLabel());
		GameManager.setMonsterLabel(GamePane.getMonsterInformation());
		GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
		GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
		GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
		System.out
				.println(this.getAllyName() + " attacks a monster with " + this.getAllyDamagePerSecond() + " damages.");
	}

	// GETTER/SETTER
	public int getAllyLevel() {
		return this.allyLevel;
	}

	public void setAllyLevel(int unlockedLevel) {
		this.allyLevel = unlockedLevel;
	}

	public long getAllyDamagePerSecond() {
		return allyDamagePerSecond;
	}

	public void setAllyDamagePerSecond(long allyDamagePerSecond) {
		this.allyDamagePerSecond = allyDamagePerSecond;
	}

	public long getAllyPrice() {
		return this.allyPrice;
	}

	public void setAllyPrice(long allyPrice) {
		this.allyPrice = allyPrice;
	}

	public String getAllyName() {
		return this.allyName;
	}

	public void setAllyName(String allyName) {
		this.allyName = allyName;
	}

	public String getAllyUrl() {
		return this.allyUrl;
	}

	public void setAllyUrl(String allyUrl) {
		this.allyUrl = allyUrl;
	}

	public boolean isUsed() {
		return this.used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public long getAllyTotalDamagePerSecond() {
		return this.allyTotalDamagePerSecond;
	}

	public void setAllyTotalDamagePerSecond(long allyTotalDamagePerSecond) {
		this.allyTotalDamagePerSecond = allyTotalDamagePerSecond;
	}

	public double getLevelUpMultiplier() {
		return this.levelUpMultiplier;
	}

	public void setLevelUpMultiplier(long levelUpMultiplier) {
		this.levelUpMultiplier = levelUpMultiplier;
	}

	public double getLevelUpCostMultiplier() {
		return this.levelUpCostMultiplier;
	}

	public void setLevelUpCostMultiplier(double levelUpCostMultiplier) {
		this.levelUpCostMultiplier = levelUpCostMultiplier;
	}

	public boolean isFirstTime() {
		return this.firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	public ImageView getImageView() {
		return this.imageView;
	}

}
