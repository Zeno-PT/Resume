package gui.ability;

import application.GamePane;
import application.Main;
import application.PlayerInformationPane;
import gui.ally.AllyPane;
import gui.ally.AllyVBox;
import gui.monster.Monster;
import gui.monster.MonsterPicture;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import logic.game.BackgroundPicture;
import logic.game.GameManager;
import logic.game.InsertAnimationPictureFailedException;

public class Prestige {
	private static int prestigeTime; // Number of How many time you Prestige

	// Set some field to its initial value after ability “Prestige” is being used
	public static void setPrestige() throws InsertAnimationPictureFailedException {
		// 1) Set Player
		GamePane.getPlayerButton().setPlayerDamagePerClick((long) (100 * Math.pow(2.5, getPrestigeTime())));
		GamePane.getPlayerButton().setPlayerGoldPerClick(0);
		GamePane.getPlayerButton().setPlayerLevel(1);
		GamePane.getPlayerButton().setPlayerCurrentGold(0);
		GamePane.getPlayerButton().setCps(0);
		// 2) Set Ability
		for (AbilityButton abilityButton : GamePane.getAbilityPane().getAbilityButtonList()) {
			abilityButton.getAbility().setUsed(false);
			abilityButton.getAbility().setAfford(false);
			if (abilityButton.getAbility().getAbilityName().equals("Tome of Knowledge")) {
				abilityButton.getAbility().setOwned(true);
				abilityButton.getAbility().setUnlocked(true);
				abilityButton.getAbility().setAbilityPrice(300);
			} else {
				abilityButton.getAbility().setOwned(false);
				abilityButton.getAbility().setUnlocked(false);
				abilityButton
						.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
			}
			abilityButton.setTooltip(abilityButton.getAbility());
		}
		// 3) Set Allies
		AllyVBox.setCountAllyHired(0);
		AllyVBox.setSlotCount(0);
		for (AllyPane allyPane : GamePane.getAllyVBox().getAllyPaneList()) {
			allyPane.getAlly().setAllyLevel(0);
			allyPane.getAlly().setUsed(false);
			allyPane.getAlly().setFirstTime(true);
			allyPane.locked();
			allyPane.setBackground(
					new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			if (allyPane.getAlly().getAllyName().equals("BibleThump")) {
				allyPane.getAlly().setAllyPrice(500);
				allyPane.getAlly().setAllyDamagePerSecond(100);
			} else if (allyPane.getAlly().getAllyName().equals("CmonBruh")) {
				allyPane.getAlly().setAllyPrice(1000);
				allyPane.getAlly().setAllyDamagePerSecond(250);
			} else if (allyPane.getAlly().getAllyName().equals("Kappa")) {
				allyPane.getAlly().setAllyPrice(2500);
				allyPane.getAlly().setAllyDamagePerSecond(500);
			} else if (allyPane.getAlly().getAllyName().equals("Kreygasm")) {
				allyPane.getAlly().setAllyPrice(5000);
				allyPane.getAlly().setAllyDamagePerSecond(1000);
			} else if (allyPane.getAlly().getAllyName().equals("LUL")) {
				allyPane.getAlly().setAllyPrice(10000);
				allyPane.getAlly().setAllyDamagePerSecond(2000);
			} else if (allyPane.getAlly().getAllyName().equals("PogChamp")) {
				allyPane.getAlly().setAllyPrice(20000);
				allyPane.getAlly().setAllyDamagePerSecond(4000);
			} else if (allyPane.getAlly().getAllyName().equals("TriHard")) {
				allyPane.getAlly().setAllyPrice(50000);
				allyPane.getAlly().setAllyDamagePerSecond(8000);
			}
			allyPane.setButtonLabel(allyPane.getAlly(), allyPane.getButton());
			if (allyPane.getAlly().getAllyName() == "BibleThump") {
				allyPane.setAppear(true);
			} else {
				allyPane.setAppear(false);
			}
			allyPane.setPurchase(false);
			allyPane.setLocked(false);
			if (allyPane.getTimeline() != null) {
				allyPane.getTimeline().stop();
			}
			GamePane.getAllyPicture().getChildren().remove(allyPane.getAlly().getImageView());
			GamePane.getAllyVBox().getChildren().remove(allyPane);
			if (allyPane.getAlly().getAllyName() == "BibleThump") {
				GamePane.getAllyVBox().getChildren().add(allyPane);
			}
		}
		// 4) Set Monster
		GamePane.getMonster().setMonsterLevel(1);
		GamePane.getMonster().setMonsterMaxHealthPoint(1000);
		GamePane.getMonster().setMonsterHealthPoint(GamePane.getMonster().getMonsterMaxHealthPoint());
		GamePane.getMonster().setMonsterCount(1);
		GamePane.getMonster().setMonsterBossCount(0);
		GamePane.getMonster().setMonsterStage(1);
		Monster.setNewMonster(GamePane.getMonster());
		BackgroundPicture.setMainBackgroundPicture(Main.getRoot());
		// 5) Set Label
		GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
		GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
		GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
		GameManager.setMonsterCountLabel(GamePane.getMonsterCountLabel());
		GameManager.setMonsterLabel(GamePane.getMonsterInformation());
		GameManager.setMoneyLabel(GamePane.getMoneyLabel());
		AllyVBox.setAllyPaneUnbuyable(GamePane.getAllyVBox().getAllyPaneList());
		AllyVBox.setAllyPaneBuyable(GamePane.getAllyVBox().getAllyPaneList());
		AbilityPane.setAbilityButtonState(GamePane.getAbilityPane().getAbilityButtonList());
		AbilityPane.setAbilityButtonUnbuyable(GamePane.getAbilityPane().getAbilityButtonList());
		MonsterPicture.setMonsterPicture();
	}

	// GETTER/SETTER
	public static int getPrestigeTime() {
		return Prestige.prestigeTime;
	}

	public static void setPrestigeTime(int prestigeTime) {
		Prestige.prestigeTime = prestigeTime;
	}

}
