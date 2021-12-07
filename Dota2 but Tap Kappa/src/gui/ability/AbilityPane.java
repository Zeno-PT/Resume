package gui.ability;

import java.util.ArrayList;

import application.GamePane;
import application.PlayerInformationPane;
import gui.ally.AllyPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import logic.game.Achievement;
import logic.game.GameManager;
import logic.game.InsertAnimationPictureFailedException;
import logic.game.SoundEffect;

public class AbilityPane extends GridPane {
	private static ArrayList<AbilityButton> abilityButtonList;

	public AbilityPane() {
		abilityButtonList = new ArrayList<>();
		AbilityButton levelUp = new AbilityButton("Tome of Knowledge");
		levelUp.unlocked();
		abilityButtonList.add(levelUp);
		abilityButtonList.add(new AbilityButton("God Strength"));
		abilityButtonList.add(new AbilityButton("Howl"));
		abilityButtonList.add(new AbilityButton("Hand of Midas"));
		abilityButtonList.add(new AbilityButton("Critical Strike"));
		abilityButtonList.add(new AbilityButton("Finger of Death"));
		abilityButtonList.add(new AbilityButton("Prestige"));
		for (AbilityButton abilityButton : abilityButtonList) {
			abilityButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if ((abilityButton.getAbility().getAbilityName().equals("Tome of Knowledge"))
							&& (abilityButton.getAbility().isUsed() == false)) {
						if ((abilityButton.getAbility().isAfford() == true)) {
							try {
								abilityButton.getAbility().setSkillEffect(GamePane.getPlayerButton(),
										abilityButton.getAbility());
							} catch (InsertAnimationPictureFailedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							abilityButton.setTooltip(abilityButton.getAbility());

							AudioClip levelUpSound = SoundEffect.getLevelUpSound();
							levelUpSound.play();

							if (Achievement.clearPlayerLevelAchievement()) {
								Achievement.setAchievementAlert();
							}
						} else {
							SoundEffect.getMibmabSound().play();
						}
					} else {
						if (abilityButton.getAbility().isOwned() == false) {
							if ((abilityButton.getAbility().isAfford() == true)
									&& (abilityButton.getAbility().isUnlocked() == true)
									&& (abilityButton.getAbility().isUsed() == false)) {

								abilityButton.owned();

								AudioClip purchaseAllySound = SoundEffect.getPurchaseAllySound();
								purchaseAllySound.play();

								GamePane.getPlayerButton()
										.setPlayerSpendingGold(GamePane.getPlayerButton().getPlayerSpendingGold()
												+ abilityButton.getAbility().getAbilityPrice());
								GamePane.getPlayerButton()
										.setPlayerCurrentGold(GamePane.getPlayerButton().getPlayerCurrentGold()
												- abilityButton.getAbility().getAbilityPrice());
								GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
								GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
								GameManager
										.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
								abilityButton.getAbility().setOwned(true);
								setAbilityButtonUnbuyable(GamePane.getAbilityPane().getAbilityButtonList());
								abilityButton.setTooltip(abilityButton.getAbility());

							} else {
								SoundEffect.getMibmabSound().play();
							}
						} else if (abilityButton.getAbility().isOwned() == true) {
							if ((abilityButton.getAbility().isUsed() == false)) {
								try {
									abilityButton.useSkill(abilityButton);
								} catch (InsertAnimationPictureFailedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								for (AllyPane allyPane : GamePane.getAllyVBox().getAllyPaneList()) {
									GameManager.setAllyLabel(allyPane.getAllyInformation(), allyPane.getAlly());
								}
								GameManager.setAllyUpperLabel(GamePane.getAllyLabel());
							}
						}
						if (Achievement.clearPlayerSpendingGoldAchievement()) {
							Achievement.setAchievementAlert();
						}
					}
					AbilityPane.setAbilityButtonState(GamePane.getAbilityPane().getAbilityButtonList());
					AbilityPane.setAbilityButtonUnbuyable(GamePane.getAbilityPane().getAbilityButtonList());
					GameManager.setMoneyLabel(GamePane.getMoneyLabel());
				}

			});
		}
		this.addRow(0, abilityButtonList.get(0), abilityButtonList.get(1), abilityButtonList.get(2));
		this.addRow(1, abilityButtonList.get(3), abilityButtonList.get(4), abilityButtonList.get(5));
		this.addRow(2, abilityButtonList.get(6));

	}

	// Set each abilityButton what background color should be inserted
	public static void setAbilityButtonState(ArrayList<AbilityButton> abilityButtonList) {
		for (AbilityButton abilitybutton : abilityButtonList) {
			if ((abilitybutton.getAbility().getAbilityName().equals("Tome of Knowledge"))
					&& (abilitybutton.getAbility().isUsed() == false)) {
				if (GamePane.getPlayerButton().getPlayerCurrentGold() >= abilitybutton.getAbility().getAbilityPrice()) {
					abilitybutton.afford();
					abilitybutton.getAbility().setAfford(true);
				}
			} else if ((abilitybutton.getAbility().isOwned() == false)
					&& (abilitybutton.getAbility().isUsed() == false)) {
				if ((GamePane.getPlayerButton().getPlayerLevel() >= abilitybutton.getAbility().getUnlockedLevel())
						&& (abilitybutton.getAbility().isUnlocked() == false)) {
					if (GamePane.getPlayerButton().getPlayerCurrentGold() >= abilitybutton.getAbility()
							.getAbilityPrice()) {
						abilitybutton.afford();
						abilitybutton.getAbility().setAfford(true);
						abilitybutton.getAbility().setUnlocked(true);
					} else {
						abilitybutton.unlocked();
						abilitybutton.getAbility().setUnlocked(true);
					}
				} else if ((abilitybutton.getAbility().isUnlocked() == true)) {
					if ((GamePane.getPlayerButton().getPlayerCurrentGold() >= abilitybutton.getAbility()
							.getAbilityPrice()) && (abilitybutton.getAbility().isOwned() == false)) {
						abilitybutton.afford();
						abilitybutton.getAbility().setAfford(true);
					}
				}
			}

		}
	}

	// Set Background to red when player do not have enough gold to buy that ability
	public static void setAbilityButtonUnbuyable(ArrayList<AbilityButton> abilityButtonList) {
		for (AbilityButton abilitybutton : abilityButtonList) {
			if ((abilitybutton.getAbility().isOwned() == false)
					|| (abilitybutton.getAbility().getAbilityName().equals("Tome of Knowledge"))) {
				if ((GamePane.getPlayerButton().getPlayerCurrentGold() < abilitybutton.getAbility().getAbilityPrice())
						&& (abilitybutton.getAbility().isUnlocked() == true)) {
					abilitybutton.unlocked();
					abilitybutton.getAbility().setAfford(false);
				}
			}

		}

	}

	// Lock ability Level Up
	public static void lockLevelUp(ArrayList<AbilityButton> abilityButtonList) {
		for (AbilityButton abilitybutton : abilityButtonList) {
			if (abilitybutton.getAbility().getAbilityName().equals("Tome of Knowledge")) {
				abilitybutton.unlocked();
				abilitybutton.getAbility().setUsed(true);
			}
		}
	}

	// Unlock ability Level Up
	public static void unlockLevelUp(ArrayList<AbilityButton> abilityButtonList) {
		for (AbilityButton abilitybutton : abilityButtonList) {
			if (abilitybutton.getAbility().getAbilityName().equals("Tome of Knowledge")) {
				if (abilitybutton.getAbility().isAfford() == true) {
					abilitybutton.afford();
				} else {
					abilitybutton.unlocked();
				}
				abilitybutton.getAbility().setUsed(false);
			}
		}
	}

	// Lock ability Prestige
	public static void lockPrestige(ArrayList<AbilityButton> abilityButtonList) {
		for (AbilityButton abilitybutton : abilityButtonList) {
			if (abilitybutton.getAbility().getAbilityName().equals("Prestige")) {
				abilitybutton.unlocked();
				abilitybutton.getAbility().setUsed(true);
			}
		}
	}

	// Return true when ability which is not level up and prestige is being used
	private static boolean checkUsing(ArrayList<AbilityButton> abilityButtonList) {
		for (AbilityButton abilitybutton : abilityButtonList) {
			if ((!abilitybutton.getAbility().getAbilityName().equals("Level Up"))
					&& (!abilitybutton.getAbility().getAbilityName().equals("Prestige"))
					&& (abilitybutton.getAbility().isUsed() == true)) {
				return false;
			}
		}
		return true;
	}

	// Unlock ability Prestige
	public static void unlockPrestige(ArrayList<AbilityButton> abilityButtonList) {
		for (AbilityButton abilitybutton : abilityButtonList) {
			if ((abilitybutton.getAbility().getAbilityName().equals("Prestige")
					&& (AbilityPane.checkUsing(GamePane.getAbilityPane().getAbilityButtonList())))) {
				if (abilitybutton.getAbility().isUnlocked() == true) {
					if (abilitybutton.getAbility().isOwned() == false) {
						abilitybutton.afford();
					} else {
						abilitybutton.owned();
					}
					abilitybutton.getAbility().setUsed(false);
				}
			}
		}

	}

	// Get abilityButtonList
	public ArrayList<AbilityButton> getAbilityButtonList() {
		return AbilityPane.abilityButtonList;
	}

}
