package gui.ally;

import java.util.ArrayList;

import application.GamePane;
import application.PlayerInformationPane;
import gui.ability.AbilityPane;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.game.Achievement;
import logic.game.GameManager;
import logic.game.SoundEffect;

public class AllyVBox extends VBox {

	private static ArrayList<AllyPane> allyPaneList; // List that contains all Ally Pane
	private static int countAllyHired; // Number of ally that player hired
	private static int slotCount; // Number of slot for Ally Picture under monster picture

	public AllyVBox() {
		AllyVBox.countAllyHired = 0;
		AllyVBox.slotCount = 0;
		allyPaneList = new ArrayList<>();
		allyPaneList.add(new AllyPane("BibleThump"));
		allyPaneList.add(new AllyPane("CmonBruh"));
		allyPaneList.add(new AllyPane("Kappa"));
		allyPaneList.add(new AllyPane("Kreygasm"));
		allyPaneList.add(new AllyPane("LUL"));
		allyPaneList.add(new AllyPane("PogChamp"));
		allyPaneList.add(new AllyPane("TriHard"));

		for (int i = 0; i < allyPaneList.size(); i++) {
			if (allyPaneList.get(i).isAppear() == true) {
				this.getChildren().add(allyPaneList.get(i));
			}
		}

		for (AllyPane allyPane : allyPaneList) {
			allyPane.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					if (allyPane.isLocked() == false) {
						if ((allyPane.isPurchase() == true) && (allyPane.getAlly().getAllyLevel() == 0)) {
							if (allyPane.getAlly().isUsed() == false) {
								allyPane.select();
								allyPane.getAlly().setUsed(true);

								GamePane.getPlayerButton()
										.setPlayerSpendingGold(GamePane.getPlayerButton().getPlayerSpendingGold()
												+ allyPane.getAlly().getAllyPrice());
								GamePane.getPlayerButton()
										.setPlayerCurrentGold(GamePane.getPlayerButton().getPlayerCurrentGold()
												- allyPane.getAlly().getAllyPrice());
								GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
								GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
								GameManager
										.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());

								allyPane.getAlly().setAllyLevel(allyPane.getAlly().getAllyLevel() + 1);
								allyPane.getAlly()
										.setAllyPrice((long) (allyPane.getAlly().getAllyPrice()
												* Math.pow(allyPane.getAlly().getLevelUpCostMultiplier(),
														allyPane.getAlly().getAllyLevel())));
								allyPane.setButtonLabel(allyPane.getAlly(), allyPane.getButton());
								allyPane.setTimeline();
								GameManager.setAllyLabel(allyPane.getAllyInformation(), allyPane.getAlly());
								AllyVBox.setAllyPaneUnbuyable(GamePane.getAllyVBox().getAllyPaneList());
								AbilityPane.setAbilityButtonUnbuyable(GamePane.getAbilityPane().getAbilityButtonList());

								AllyVBox.setCountAllyHired(AllyVBox.getCountAllyHired() + 1);
								if (Achievement.clearAllyHiredAchievement())
									Achievement.setAchievementAlert();
								// check and show next ally
								if (AllyVBox.getCountAllyHired() < GamePane.getAllyVBox().getAllyPaneList().size()) {
									GamePane.getAllyVBox().getAllyPaneList().get(AllyVBox.getCountAllyHired())
											.setAppear(true);
									GamePane.getAllyVBox().getChildren().add(
											GamePane.getAllyVBox().getAllyPaneList().get(AllyVBox.getCountAllyHired()));
								}
								AudioClip purchaseAllySound = SoundEffect.getPurchaseAllySound();
								purchaseAllySound.play();

							}

						} else if ((allyPane.isPurchase() == true) && (allyPane.getAlly().getAllyLevel() >= 1)) {

							GamePane.getPlayerButton()
									.setPlayerSpendingGold(GamePane.getPlayerButton().getPlayerSpendingGold()
											+ allyPane.getAlly().getAllyPrice());
							GamePane.getPlayerButton()
									.setPlayerCurrentGold(GamePane.getPlayerButton().getPlayerCurrentGold()
											- allyPane.getAlly().getAllyPrice());
							GameManager.setPlayerFirstInformation(GamePane.getPlayerFirstInformation());
							GameManager.setPlayerSecondInformation(GamePane.getPlayerSecondInformation());
							GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());

							allyPane.getAlly()
									.setAllyDamagePerSecond(((long) (allyPane.getAlly().getAllyDamagePerSecond()
											* Math.pow(allyPane.getAlly().getLevelUpMultiplier(),
													allyPane.getAlly().getAllyLevel()))));
							allyPane.getAlly().setAllyLevel(allyPane.getAlly().getAllyLevel() + 1);
							allyPane.getAlly()
									.setAllyPrice((long) (allyPane.getAlly().getAllyPrice()
											* Math.pow(allyPane.getAlly().getLevelUpCostMultiplier(),
													allyPane.getAlly().getAllyLevel())));
							allyPane.setButtonLabel(allyPane.getAlly(), allyPane.getButton());
							GameManager.setAllyLabel(allyPane.getAllyInformation(), allyPane.getAlly());

							AllyVBox.setAllyPaneUnbuyable(GamePane.getAllyVBox().getAllyPaneList());
							AbilityPane.setAbilityButtonUnbuyable(GamePane.getAbilityPane().getAbilityButtonList());

							AudioClip purchaseAllySound = SoundEffect.getPurchaseAllySound();
							purchaseAllySound.play();

						} else {
							SoundEffect.getMibmabSound().play();
						}

					} else {
						SoundEffect.getMibmabSound().play();
					}
					GameManager.setAllyUpperLabel(GamePane.getAllyLabel());
				}

			});
			if (allyPane.isLocked() == false) {
				allyPane.getButton().setOnMouseEntered(event -> {
					allyPane.getButton().setOpacity(0.85);
				});
				allyPane.getButton().setOnMouseExited(event -> {
					allyPane.getButton().setOpacity(1);
				});
			}
		}
	}

	// Set Background as Color GOLD when player have enough gold to hire or level up
	// ally and that ally is not locked
	public static void setAllyPaneBuyable(ArrayList<AllyPane> allyPaneList) { // set button color
		for (AllyPane allyPane : allyPaneList) {
			if ((allyPane.getAlly().getAllyPrice() <= GamePane.getPlayerButton().getPlayerCurrentGold())
					&& (allyPane.isLocked() == false)) {
				allyPane.getButton().setBackground(
						new Background(new BackgroundFill(Color.GOLD, new CornerRadii(8.0), Insets.EMPTY)));
				allyPane.setPurchase(true);
			}
		}
	}

	// Set Background as Color LIGHTGRAY when player do not have enough gold to hire
	// or level up ally
	public static void setAllyPaneUnbuyable(ArrayList<AllyPane> allyPaneList) {
		for (AllyPane allyPane : allyPaneList) {
			if ((allyPane.getAlly().getAllyPrice() > GamePane.getPlayerButton().getPlayerCurrentGold())
					&& (allyPane.isLocked() == false)) {
				allyPane.getButton().setBackground(
						new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(8.0), Insets.EMPTY)));
				allyPane.setPurchase(false);
			}
		}
	}

	// Return total DPS that player have (count from ally DPS that is bought)
	public static long getTotalDPS(ArrayList<AllyPane> allyPaneList) {
		int sum = 0;
		for (AllyPane allyPane : allyPaneList) {
			if (allyPane.getAlly().isUsed() == true) {
				sum += allyPane.getAlly().getAllyDamagePerSecond();
			}
		}
		return sum;
	}

	// GETTER/SETTER
	public static int getCountAllyHired() {
		return AllyVBox.countAllyHired;
	}

	public static void setCountAllyHired(int countAllyHired) {
		AllyVBox.countAllyHired = countAllyHired;
	}

	public static int getSlotCount() {
		return AllyVBox.slotCount;
	}

	public static void setSlotCount(int slotCount) {
		AllyVBox.slotCount = slotCount;
	}

	public ArrayList<AllyPane> getAllyPaneList() {
		return AllyVBox.allyPaneList;
	}

}
