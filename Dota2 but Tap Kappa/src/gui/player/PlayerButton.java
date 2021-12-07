package gui.player;

import java.text.DecimalFormat;

import application.GamePane;
import application.PlayerInformationPane;
import gui.animation.SlashAnimation;
import gui.animation.SwordMasterAnimation;
import gui.monster.Monster;
import gui.monster.MonsterPicture;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import logic.game.Achievement;
import logic.game.Attackable;
import logic.game.BackgroundMusic;
import logic.game.GameManager;
import logic.game.InsertAnimationPictureFailedException;
import logic.game.SoundEffect;

public class PlayerButton extends Button implements Attackable {
	private int playerLevel;
	private long playerTotalGold;
	private long playerSpendingGold;
	private long playerTotalClick;
	private long playerDamagePerClick;
	private long playerCurrentGold;
	private long playerGoldPerClick;
	private double playerCriticalChance; // 2 decimal number
	private double playerCriticalMultiplier; // 2 decimal number

	private long initialCps;
	private long cps; // Current click per second
	private long maxCps; // Max click per second for achievement

	public PlayerButton() { // adjust player here
		this.playerDamagePerClick = 100;
		this.playerGoldPerClick = 0;
		this.playerCriticalChance = 0.15;
		this.playerCriticalMultiplier = 1.5;
		this.playerLevel = 1;
		this.playerCurrentGold = 0;
		this.playerSpendingGold = 0;
		this.playerTotalGold = this.getPlayerCurrentGold();
		this.playerTotalClick = 0;
		this.maxCps = 0;

		this.setOnAction(e -> { // mouse click or space bar

			try {
				this.attack();
			} catch (InsertAnimationPictureFailedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (GamePane.getMonster().getMonsterHealthPoint() <= 0)
				if (Achievement.clearPlayerTotalGoldAchievement()) {
					Achievement.setAchievementAlert();
				}
			if (Achievement.clearPlayerTotalClickAchievement()) {
				Achievement.setAchievementAlert();
			}
			GameManager.setMonsterLabel(GamePane.getMonsterInformation());
			GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
			GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
			GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
		});

		this.setOnKeyReleased(e -> { // press z or x
			if (e.getCode() == KeyCode.Z || e.getCode() == KeyCode.X) {
				try {
					this.attack();
				} catch (InsertAnimationPictureFailedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (GamePane.getMonster().getMonsterHealthPoint() <= 0)
					if (Achievement.clearPlayerTotalGoldAchievement()) {
						Achievement.setAchievementAlert();
					}
				if (Achievement.clearPlayerTotalClickAchievement()) {
					Achievement.setAchievementAlert();
				}
				GameManager.setMonsterLabel(GamePane.getMonsterInformation());
				GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
				GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
				GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
			}

		});
	}

	// PlayerButton attack 1 click
	@Override
	public void attack() throws InsertAnimationPictureFailedException { // attack per click or press
		// TODO Auto-generated method stub
		long oldDamage = this.getPlayerDamagePerClick();

		if (setCriticalRate(this) == true) {
			AudioClip criticalAttackSound = SoundEffect.getCriticalAttackSound();
			if (criticalAttackSound != null) {
				criticalAttackSound.setVolume(0.3);
				criticalAttackSound.play();
			}
		} else {
			AudioClip attackSound = SoundEffect.getAttackSound();
			attackSound.setVolume(0.2);
			attackSound.play();
		}
		// keep music always playing
		if (GamePane.getMonster().getMonsterCount() < 10 && !BackgroundMusic.getMainTheme().isPlaying())

			BackgroundMusic.getMainTheme().play();
		else if (GamePane.getMonster().getMonsterCount() == 10 && !BackgroundMusic.getBossTheme().isPlaying())
			BackgroundMusic.getBossTheme().play();

		System.out.println(
				"Player attacks a monster with " + GamePane.getPlayerButton().getPlayerDamagePerClick() + " damages.");

		GamePane.getMonster().setMonsterHealthPoint(
				(int) (GamePane.getMonster().getMonsterHealthPoint() - this.getPlayerDamagePerClick()));
		GamePane.getMonsterHealthBar().setProgress((double) GamePane.getMonster().getMonsterHealthPoint()
				/ GamePane.getMonster().getMonsterMaxHealthPoint());
		MonsterPicture.changeMonsterPosition();
		Monster.setNewMonster(GamePane.getMonster());

		if (SwordMasterAnimation.isUsed() == false) {
			SwordMasterAnimation.showAnimation();
		}

		if (SlashAnimation.isUsed() == false) {
			SlashAnimation.showAnimation();
		}

		this.setPlayerCurrentGold(this.getPlayerCurrentGold() + this.getPlayerGoldPerClick());
		this.setPlayerTotalGold(this.getPlayerTotalGold() + this.getPlayerGoldPerClick());
		this.setPlayerDamagePerClick(oldDamage);
		this.setPlayerTotalClick((long) (this.getPlayerTotalClick() + 0.5));

		this.setPlayerCurrentGold(this.getPlayerCurrentGold() + this.getPlayerGoldPerClick());
		this.setPlayerTotalGold(this.getPlayerTotalGold() + this.getPlayerGoldPerClick());
		this.setPlayerDamagePerClick(oldDamage);
		this.setPlayerTotalClick(this.getPlayerTotalClick() + 1);

	}

	// Check how much click player can do in 1 seconds
	public static void checkClickPerSecond(PlayerButton playerButton) { // Check for player label and Player Information
																		// Scene
		playerButton.setInitialCps(playerButton.getPlayerTotalClick());
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ae -> {
			playerButton.setCps((playerButton.getPlayerTotalClick() - playerButton.getInitialCps()));
			if (playerButton.getCps() < 0) { // debug when prestige
				playerButton.setCps(0);
			}
			if (playerButton.getCps() >= playerButton.getMaxCps())
				playerButton.setMaxCps(playerButton.getCps());
			playerButton.setInitialCps(playerButton.getPlayerTotalClick());
			if (Achievement.clearClickPerSecondAchievement())
				Achievement.setAchievementAlert();
			GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
			GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
			GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	// Return true if player get critical hit
	public boolean setCriticalRate(PlayerButton playerButton) { // set critical chance
		double d = Math.random();
		DecimalFormat f = new DecimalFormat("##.00"); // set decimal to 2 digit
		double critical = Double.valueOf(f.format(d));
		if (critical < playerButton.getPlayerCriticalChance()) {
			playerButton.setPlayerDamagePerClick(
					(long) (playerButton.getPlayerDamagePerClick() * playerButton.getPlayerCriticalMultiplier()));
			return true;
		}
		return false;

	}

	// GETTER/SETTER
	public long getPlayerDamagePerClick() {
		return this.playerDamagePerClick;
	}

	public int getPlayerLevel() {
		return this.playerLevel;
	}

	public long getPlayerCurrentGold() {
		return this.playerCurrentGold;
	}

	public long getPlayerGoldPerClick() {
		return this.playerGoldPerClick;
	}

	public long getPlayerTotalGold() {
		return this.playerTotalGold;
	}

	public long getPlayerSpendingGold() {
		return this.playerSpendingGold;
	}

	public double getPlayerCriticalChance() {
		return this.playerCriticalChance;
	}

	public double getPlayerCriticalMultiplier() {
		return this.playerCriticalMultiplier;
	}

	public long getPlayerTotalClick() {
		return this.playerTotalClick;
	}

	public long getCps() {
		return this.cps;
	}

	public long getMaxCps() {
		return this.maxCps;
	}

	public long getInitialCps() {
		return initialCps;
	}

	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}

	public void setPlayerTotalGold(long playerTotalGold) {
		this.playerTotalGold = playerTotalGold;
	}

	public void setPlayerSpendingGold(long playerSpendingGold) {
		this.playerSpendingGold = playerSpendingGold;
	}

	public void setPlayerTotalClick(long playerTotalClick) {
		this.playerTotalClick = playerTotalClick;
	}

	public void setPlayerDamagePerClick(long playerDamagePerClick) {
		this.playerDamagePerClick = playerDamagePerClick;
	}

	public void setPlayerCurrentGold(long playerCurrentGold) {
		this.playerCurrentGold = playerCurrentGold;
	}

	public void setPlayerGoldPerClick(long playerGoldPerClick) {
		this.playerGoldPerClick = playerGoldPerClick;
	}

	public void setPlayerCriticalChance(double playerCriticalChance) {
		this.playerCriticalChance = playerCriticalChance;
	}

	public void setPlayerCriticalMultiplier(double playerCriticalMultiplier) {
		this.playerCriticalMultiplier = playerCriticalMultiplier;
	}

	public void setCps(long cps) {
		this.cps = cps;
	}

	public void setMaxCps(long maxCps) {
		this.maxCps = maxCps;
	}

	public void setInitialCps(long initialCps) {
		this.initialCps = initialCps;
	}

}
