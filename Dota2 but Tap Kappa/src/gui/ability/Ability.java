package gui.ability;

import application.GamePane;
import application.PlayerInformationPane;
import gui.ally.AllyVBox;
import gui.monster.Monster;
import gui.player.PlayerButton;
import logic.game.Achievement;
import logic.game.GameManager;
import logic.game.InsertAnimationPictureFailedException;

public class Ability {
	private String abilityName;
	private String abilityUrl;
	private String description;
	private int unlockedLevel; // Unlock when PlayerLevel more than or equal to unlockedLevel
	private long abilityPrice;
	private boolean unlocked;
	private boolean used;
	private boolean afford; // Set as when player can buy ability
	private boolean owned;
	private int abilityCooldown;
	private int duration;

	private double goldIncrease;
	private double attackMultiplier; // 2 decimal number
	private double alliesDamageMultiplier; // 2 decimal number
	private double moreCriticalChance; // 2 decimal number

	public Ability(String abilityName) { // adjust ability here
		this.abilityName = abilityName;
		this.goldIncrease = 100;
		this.attackMultiplier = 1.50;
		this.alliesDamageMultiplier = 1.50;
		this.moreCriticalChance = 0.25;
		this.used = false;
		this.afford = false;
		switch (abilityName) {
		case "Tome of Knowledge":
			this.abilityUrl = "image/Ability/Tome_of_Knowledge_icon.png";
			this.description = "Increase your level";
			this.unlockedLevel = 1;
			this.abilityPrice = 300;
			this.owned = true;
			this.unlocked = true;
			this.abilityCooldown = 0;
			this.duration = 0;
			break;
		case "God Strength":
			this.abilityUrl = "image/Ability/God_Strength_icon.png";
			this.description = "Increase your DamagePerClick " + getAttackMultiplier() + " times for 10 seconds";
			this.unlockedLevel = 5;
			this.abilityPrice = 5000;
			this.owned = false;
			this.unlocked = false;
			this.abilityCooldown = 10;
			this.duration = 10;
			break;
		case "Howl":
			this.abilityUrl = "image/Ability/Howl_icon.png";
			this.description = "Increase your allies DPS " + this.getAlliesDamageMultiplier() + " times for 10 seconds";
			this.unlockedLevel = 10;
			this.abilityPrice = 10000;
			this.owned = false;
			this.unlocked = false;
			this.abilityCooldown = 10;
			this.duration = 10;
			break;
		case "Hand of Midas":
			this.abilityUrl = "image/Ability/Hand_of_Midas_icon.png";
			this.description = "Increase gold gain per click " + this.getGoldIncrease() + " golds for 10 seconds";
			this.unlockedLevel = 15;
			this.abilityPrice = 40000;
			this.owned = false;
			this.unlocked = false;
			this.abilityCooldown = 15;
			this.duration = 10;
			break;
		case "Critical Strike":
			this.abilityUrl = "image/Ability/critical.png";
			this.description = "Increase your critical Chance " + this.getMoreCriticalChance() + " for 10 seconds";
			this.unlockedLevel = 20;
			this.abilityPrice = 70000;
			this.owned = false;
			this.unlocked = false;
			this.abilityCooldown = 15;
			this.duration = 10;
			break;
		case "Finger of Death":
			this.abilityUrl = "image/Ability/Finger_of_Death_icon.png";
			this.description = "Suddenly kill the monster in the field";
			this.unlockedLevel = 25;
			this.abilityPrice = 100000;
			this.owned = false;
			this.unlocked = false;
			this.abilityCooldown = 20;
			this.duration = 0;
			break;
		case "Prestige":
			this.abilityUrl = "image/Ability/Reincarnation_icon.png";
			this.description = "Use prestige to start fresh and get more power";
			this.unlockedLevel = 30;
			this.abilityPrice = 0;
			this.owned = false;
			this.unlocked = false;
			this.abilityCooldown = 0;
			this.duration = 0;
			break;
		}

	}

	public void setSkillEffect(PlayerButton playerButton, Ability ability)
			throws InsertAnimationPictureFailedException { // Set skill effects for each ability when they are used
		String abilityName = ability.getAbilityName();
		if ("Tome of Knowledge".equals(abilityName)) {
			playerButton.setPlayerLevel(GamePane.getPlayerButton().getPlayerLevel() + 1);
			playerButton.setPlayerDamagePerClick(GamePane.getPlayerButton().getPlayerDamagePerClick() + 50);
			AbilityPane.setAbilityButtonState(GamePane.getAbilityPane().getAbilityButtonList());
			playerButton.setPlayerSpendingGold(
					GamePane.getPlayerButton().getPlayerSpendingGold() + ability.getAbilityPrice());
			playerButton.setPlayerCurrentGold(
					GamePane.getPlayerButton().getPlayerCurrentGold() - ability.getAbilityPrice());
			ability.setAbilityPrice(
					ability.getAbilityPrice() + 40 * (GamePane.getPlayerButton().getPlayerLevel() - 1));

		} else if ("God Strength".equals(abilityName)) {
			playerButton.setPlayerDamagePerClick(
					(long) (ability.getAttackMultiplier() * GamePane.getPlayerButton().getPlayerDamagePerClick()));
		} else if ("Hand of Midas".equals(abilityName)) {
			playerButton.setPlayerGoldPerClick(
					(long) (GamePane.getPlayerButton().getPlayerGoldPerClick() + this.getGoldIncrease()));
		} else if ("Critical Strike".equals(abilityName)) {
			playerButton.setPlayerCriticalChance(
					this.getMoreCriticalChance() + GamePane.getPlayerButton().getPlayerCriticalChance());
		} else if ("Finger of Death".equals(abilityName)) {
			GamePane.getMonster().setMonsterHealthPoint(0);
			Monster.setNewMonster(GamePane.getMonster());
		} else if ("Prestige".equals(abilityName)) {
			Prestige.setPrestigeTime(Prestige.getPrestigeTime() + 1);
			if (Achievement.clearPrestigeAchievement() == true) {
				Achievement.setAchievementAlert();
			}
			Prestige.setPrestige();
		}
		GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
		GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
		GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
		GameManager.setMoneyLabel(GamePane.getMoneyLabel());
		AllyVBox.setAllyPaneUnbuyable(GamePane.getAllyVBox().getAllyPaneList());
	}

	// GETTER/SETTER
	public String getAbilityName() {
		return this.abilityName;
	}

	public String getAbilityUrl() {
		return this.abilityUrl;
	}

	public String getDescription() {
		return this.description;
	}

	public int getUnlockedLevel() {
		return this.unlockedLevel;
	}

	public long getAbilityPrice() {
		return this.abilityPrice;
	}

	public String getDescriptionText() {
		return "\nDescription: " + this.getDescription();
	}

	public String getUnlockedLevelText() {
		return "\nUnlocked Level: " + this.getUnlockedLevel();
	}

	public String getAbilityPriceText() {
		return "\nPrice: " + this.getAbilityPrice();
	}

	public double getGoldIncrease() {
		return this.goldIncrease;
	}

	public double getAttackMultiplier() {
		return this.attackMultiplier;
	}

	public double getAlliesDamageMultiplier() {
		return this.alliesDamageMultiplier;
	}

	public double getMoreCriticalChance() {
		return this.moreCriticalChance;
	}

	public boolean isUnlocked() {
		return this.unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public long setAbilityPrice(long abilityPrice) {
		return this.abilityPrice = abilityPrice;
	}

	public boolean isUsed() {
		return this.used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public int getAbilityCooldown() {
		return this.abilityCooldown;
	}

	public void setAbilityCooldown(int abilityCooldown) {
		this.abilityCooldown = abilityCooldown;
	}

	public boolean isAfford() {
		return this.afford;
	}

	public void setAfford(boolean afford) {
		this.afford = afford;
	}

	public boolean isOwned() {
		return this.owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
