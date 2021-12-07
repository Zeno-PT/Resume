package gui.monster;

import application.GamePane;
import application.Main;
import application.PlayerInformationPane;
import gui.ability.AbilityPane;
import gui.ally.AllyVBox;
import gui.animation.ExplosionAnimation;
import javafx.scene.media.AudioClip;
import logic.game.Achievement;
import logic.game.BackgroundMusic;
import logic.game.BackgroundPicture;
import logic.game.GameManager;
import logic.game.InsertAnimationPictureFailedException;
import logic.game.SoundEffect;

public class Monster extends MonsterPicture {

	private int monsterLevel;
	private long monsterMaxHealthPoint;
	private long monsterHealthPoint;
	private int monsterCount;
	private int monsterStage; // monster stage (Use for checking boss monster)
	private int monsterBossCount;

	public Monster() { // adjust monster level here
		this.monsterLevel = 1;
		this.monsterMaxHealthPoint = 1000;
		this.monsterHealthPoint = this.getMonsterMaxHealthPoint();
		this.monsterCount = 1;
		this.monsterBossCount = 0;
		this.monsterStage = 1;
	}

	public static void setNewMonster(Monster monster) throws InsertAnimationPictureFailedException {
		if ((monster.getMonsterCount() < 10) && (monster.getMonsterCount() >= 1)) {
			if (monster.getMonsterHealthPoint() <= 0) {
				ExplosionAnimation.showAnimation();
				MonsterPicture.removeMonsterPicture();
				MonsterPicture.setMonsterPicture();
				// play death sound
				AudioClip monsterDeathSound = SoundEffect.getMonsterDeathSound();
				if (BackgroundMusic.getMainTheme().isPlaying() || BackgroundMusic.getBossTheme().isPlaying()) {
					monsterDeathSound.setVolume(0.4);
					monsterDeathSound.play();
				}
				monster.setMonsterMaxHealthPoint((int) (monster.getMonsterMaxHealthPoint() + 100 * (Math.random())));
				monster.setMonsterHealthPoint(monster.getMonsterMaxHealthPoint());
				GamePane.getMonsterHealthBar()
						.setProgress((double) monster.getMonsterHealthPoint() / monster.getMonsterMaxHealthPoint());
				monster.setMonsterCount(monster.getMonsterCount() + 1);
				GamePane.getPlayerButton().setPlayerCurrentGold(
						GamePane.getPlayerButton().getPlayerCurrentGold() + monster.getMonsterLevel() * 150);
				GamePane.getPlayerButton().setPlayerTotalGold(
						GamePane.getPlayerButton().getPlayerTotalGold() + monster.getMonsterLevel() * 150);
			}
		}
		Monster.setBossMonster(monster);
		AllyVBox.setAllyPaneBuyable(GamePane.getAllyVBox().getAllyPaneList());
		AllyVBox.setAllyPaneUnbuyable(GamePane.getAllyVBox().getAllyPaneList());
		AbilityPane.setAbilityButtonState(GamePane.getAbilityPane().getAbilityButtonList());
		AbilityPane.setAbilityButtonUnbuyable(GamePane.getAbilityPane().getAbilityButtonList());
		GameManager.setMoneyLabel(GamePane.getMoneyLabel());
		GameManager.setMonsterCountLabel(GamePane.getMonsterCountLabel());
		GameManager.setMonsterLabel(GamePane.getMonsterInformation());
	}

	public static void setBossMonster(Monster monster) throws InsertAnimationPictureFailedException {

		if ((monster.getMonsterCount() == 10) && (monster.getMonsterLevel() == monster.getMonsterStage())) {
			MonsterPicture.removeMonsterPicture();
			MonsterPicture.setMonsterBossPicture();
			double oldMaxHP = monster.getMonsterMaxHealthPoint();
			monster.setMonsterMaxHealthPoint((int) (oldMaxHP * 10)); // Strength of boss
			monster.setMonsterHealthPoint(monster.getMonsterMaxHealthPoint());
			GamePane.getMonsterHealthBar()
					.setProgress((double) monster.getMonsterHealthPoint() / monster.getMonsterMaxHealthPoint());
			monster.setMonsterStage(monster.getMonsterStage() + 1);
			// Boss Music
			BackgroundMusic.setBossMusic();

		}
		if (monster.getMonsterHealthPoint() <= 0) {
			ExplosionAnimation.showAnimation();
			MonsterPicture.removeMonsterPicture();
			MonsterPicture.setMonsterPicture();

			BackgroundMusic.stopBossMusic();
			BackgroundPicture.setMainBackgroundPicture(Main.getRoot());

			AudioClip bossDeathSound = SoundEffect.getBossDeathSound();
			if (BackgroundMusic.getMainTheme().isPlaying() || BackgroundMusic.getBossTheme().isPlaying()) {
				bossDeathSound.setVolume(0.45);
				bossDeathSound.play();
			}

			monster.setMonsterBossCount(monster.getMonsterBossCount() + 1);
			if (Achievement.clearStageclearedAchievement()) {
				Achievement.setAchievementAlert();
			}
			monster.setMonsterCount(1);
			monster.setMonsterMaxHealthPoint(
					(int) ((int) (1000 * Math.pow(1.5, monster.getMonsterLevel())) + 100 * (Math.random())));
			monster.setMonsterHealthPoint(monster.getMonsterMaxHealthPoint());
			GamePane.getMonsterHealthBar().setProgress(monster.getMonsterMaxHealthPoint());
			Monster.setMonsterLevelUp(monster);
			GamePane.getPlayerButton().setPlayerCurrentGold(
					GamePane.getPlayerButton().getPlayerCurrentGold() + monster.getMonsterLevel() * 1000);
			GamePane.getPlayerButton().setPlayerTotalGold(
					GamePane.getPlayerButton().getPlayerTotalGold() + monster.getMonsterLevel() * 1000);
			GameManager.setMonsterLabel(GamePane.getMonsterInformation());
			GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
			GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
			GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
		}
	}

	// Increase monster level by 1
	public static void setMonsterLevelUp(Monster monster) {
		monster.setMonsterLevel(monster.getMonsterLevel() + 1);
	}

	// GETTER/SETTER
	public int getMonsterLevel() {
		return this.monsterLevel;
	}

	public void setMonsterLevel(int monsterLevel) {
		this.monsterLevel = monsterLevel;
	}

	public long getMonsterHealthPoint() {
		return this.monsterHealthPoint;
	}

	public void setMonsterHealthPoint(long monsterHealthPoint) {
		this.monsterHealthPoint = monsterHealthPoint;
	}

	public long getMonsterMaxHealthPoint() {
		return this.monsterMaxHealthPoint;
	}

	public void setMonsterMaxHealthPoint(long monsterMaxHealthPoint) {
		this.monsterMaxHealthPoint = monsterMaxHealthPoint;
	}

	public int getMonsterCount() {
		return this.monsterCount;
	}

	public void setMonsterCount(int monsterCount) {
		this.monsterCount = monsterCount;
	}

	public int getMonsterStage() {
		return this.monsterStage;
	}

	public void setMonsterStage(int monsterStage) {
		this.monsterStage = monsterStage;
	}

	public int getMonsterBossCount() {
		return this.monsterBossCount;
	}

	public void setMonsterBossCount(int monsterBossCount) {
		this.monsterBossCount = monsterBossCount;
	}

}
