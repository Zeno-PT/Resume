package logic.game;

import application.GamePane;
import gui.ability.Prestige;
import gui.ally.Ally;
import gui.ally.AllyVBox;
import javafx.scene.control.Label;

public class GameManager {
	// set the first player stats text
	public static void setPlayerFirstInformation(Label playerInformation) {
		playerInformation.setText("Player Level: " + GamePane.getPlayerButton().getPlayerLevel()
				+ "\nPlayer Damage Per Click: " + GamePane.getPlayerButton().getPlayerDamagePerClick());
	}

	// Set the second player stats text
	public static void setPlayerSecondInformation(Label playerInformation) {
		playerInformation
				.setText("\nPlayer Critical Chance: " + (GamePane.getPlayerButton().getPlayerCriticalChance() * 100)
						+ " %" + "\nClick Per Second: " + GamePane.getPlayerButton().getCps());
	}

	// Set all player stats text in player information scene
	public static void setPlayerTotalInformation(Label playerInformation) {
		playerInformation.setText("Player Level: " + GamePane.getPlayerButton().getPlayerLevel()
				+ "\nPlayer Current Gold: " + GamePane.getPlayerButton().getPlayerCurrentGold()
				+ "\nPlayer Spending Gold: " + GamePane.getPlayerButton().getPlayerSpendingGold()
				+ "\nPlayer Total Gold: " + GamePane.getPlayerButton().getPlayerTotalGold()
				+ "\nPlayer Damage Per Click: " + GamePane.getPlayerButton().getPlayerDamagePerClick()
				+ "\nPlayer Critical Chance: " + (GamePane.getPlayerButton().getPlayerCriticalChance() * 100) + " %"
				+ "\nPlayer Critical Multiplier: " + GamePane.getPlayerButton().getPlayerCriticalMultiplier()
				+ "\nTotal Click: " + GamePane.getPlayerButton().getPlayerTotalClick() + "\nNumber of Allies hired : "
				+ AllyVBox.getCountAllyHired() + "\nPrestige Time : " + Prestige.getPrestigeTime());
	}

	public static void setMonsterLabel(Label monsterInformation) {
		monsterInformation.setText("Monster HP: " + GamePane.getMonster().getMonsterHealthPoint() + " / "
				+ GamePane.getMonster().getMonsterMaxHealthPoint() + "\nMonster Level: "
				+ GamePane.getMonster().getMonsterLevel());
	}
	// Set text for each ally’s DPS
	public static void setAllyLabel(Label allyInformation, Ally ally) {
		allyInformation.setText(
				ally.getAllyName() + "\nDPS :" + ally.getAllyDamagePerSecond() + "\nLevel : " + ally.getAllyLevel());
	}

	public static void setAllyUpperLabel(Label ally) {
		ally.setText("Ally " + " (Total DPS : " + AllyVBox.getTotalDPS(GamePane.getAllyVBox().getAllyPaneList()) + ")");
	}

	public static void setMoneyLabel(Label money) {
		money.setText((Long.toString(GamePane.getPlayerButton().getPlayerCurrentGold())));
	}

	public static void setMonsterCountLabel(Label monsterCount) {
		monsterCount.setText((" " + Integer.toString(GamePane.getMonster().getMonsterCount()) + " / 10"));
	}

}
