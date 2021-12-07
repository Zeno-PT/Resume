package gui.ability;

import application.GamePane;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.game.InsertAnimationPictureFailedException;
import logic.game.SoundEffect;

public class AbilityButton extends Button {

	private Ability ability;
	private Tooltip abilityTooltip = new Tooltip(); // Tooltip for ability
	private ImageView imageView; // Image of abilityButton

	public AbilityButton(String abilityName) {
		// TODO complete the constructor
		this.setPadding(new Insets(5));
		this.ability = new Ability(abilityName);
		String image = ClassLoader.getSystemResource(this.ability.getAbilityUrl()).toString();
		imageView = new ImageView(image);
		imageView.setFitWidth(60);
		imageView.setFitHeight(60);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setGraphic(imageView);
		this.setTooltip(ability);

		if (ability.isUnlocked()) {
			this.owned();
		}
	}

	public void useSkill(AbilityButton abilityButton) throws InsertAnimationPictureFailedException { // Set how each
																										// ability can
																										// be used
		long oldDamage = GamePane.getPlayerButton().getPlayerDamagePerClick();
		long oldGoldPerClick = GamePane.getPlayerButton().getPlayerGoldPerClick();
		double oldCriticalChance = GamePane.getPlayerButton().getPlayerCriticalChance();

		if (!abilityButton.getAbility().getAbilityName().equals("Finger of Death")
				&& !abilityButton.getAbility().getAbilityName().equals("Howl")
				&& !abilityButton.getAbility().getAbilityName().equals("Prestige")) {
			Thread thread = new Thread(() -> {
				try {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							AbilityPane.lockPrestige(GamePane.getAbilityPane().getAbilityButtonList());
							try {
								abilityButton.getAbility().setSkillEffect(GamePane.getPlayerButton(),
										abilityButton.getAbility());
							} catch (InsertAnimationPictureFailedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							abilityButton.use();
							abilityButton.getAbility().setUsed(true);
							if (abilityButton.getAbility().getAbilityName().equals("God Strength")) {
								AbilityPane.lockLevelUp(GamePane.getAbilityPane().getAbilityButtonList());
							}
						}
					});

					if (abilityButton.getAbility().getAbilityName().equals("God Strength")) {
						AudioClip increaseDamageSound = SoundEffect.getGodStrengthSound();
						increaseDamageSound.play();
					} else if (abilityButton.getAbility().getAbilityName().equals("Hand of Midas")) {
						AudioClip increaseGoldPerClickSound = SoundEffect.getMidasSound();
						increaseGoldPerClickSound.play();
					} else if (abilityButton.getAbility().getAbilityName().equals("Critical Strike")) {
						AudioClip increaseCriticalSound = SoundEffect.getBladeFurySound();
						increaseCriticalSound.setVolume(0.5);
						increaseCriticalSound.play();
					}
					Thread.sleep(1000 * abilityButton.getAbility().getDuration()); // durations
					if (abilityButton.getAbility().getAbilityName().equals("God Strength")) {
						GamePane.getPlayerButton().setPlayerDamagePerClick(oldDamage);
					} else if (abilityButton.getAbility().getAbilityName().equals("Hand of Midas")) {
						GamePane.getPlayerButton().setPlayerGoldPerClick(oldGoldPerClick);
					} else if (abilityButton.getAbility().getAbilityName().equals("Critical Strike")) {
						GamePane.getPlayerButton().setPlayerCriticalChance(oldCriticalChance);
					}
					abilityButton.unlocked();
					AbilityPane.unlockLevelUp(GamePane.getAbilityPane().getAbilityButtonList());
					Thread.sleep(1000 * abilityButton.getAbility().getAbilityCooldown()); // Cooldown
					abilityButton.owned();
					abilityButton.getAbility().setUsed(false);
					AbilityPane.unlockPrestige(GamePane.getAbilityPane().getAbilityButtonList());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			thread.start();
		} else if (abilityButton.getAbility().getAbilityName().equals("Finger of Death")) {
			Thread thread = new Thread(() -> {
				try {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							AbilityPane.lockPrestige(GamePane.getAbilityPane().getAbilityButtonList());
							try {
								abilityButton.getAbility().setSkillEffect(GamePane.getPlayerButton(),
										abilityButton.getAbility());
							} catch (InsertAnimationPictureFailedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							abilityButton.unlocked();
						}
					});

					AudioClip fingerOfDeathSound = SoundEffect.getFingerOfDeathSound();
					fingerOfDeathSound.play();

					abilityButton.getAbility().setUsed(true);
					Thread.sleep(1000 * abilityButton.getAbility().getAbilityCooldown()); // Cooldown
					abilityButton.getAbility().setUsed(false);
					AbilityPane.unlockPrestige(GamePane.getAbilityPane().getAbilityButtonList());
					abilityButton.owned();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			thread.start();
		} else if (abilityButton.getAbility().getAbilityName().equals("Howl")) {
			Thread thread = new Thread(() -> {
				Long[] oldDPS = new Long[GamePane.getAllyVBox().getAllyPaneList().size()];
				try {
					AbilityPane.lockPrestige(GamePane.getAbilityPane().getAbilityButtonList());
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							for (int i = 0; i < GamePane.getAllyVBox().getAllyPaneList().size(); i++) {
								if (GamePane.getAllyVBox().getAllyPaneList().get(i).getAlly().isUsed() == true) {
									oldDPS[i] = GamePane.getAllyVBox().getAllyPaneList().get(i).getAlly()
											.getAllyDamagePerSecond();
									GamePane.getAllyVBox().getAllyPaneList().get(i).getAlly()
											.setAllyDamagePerSecond((long) (GamePane.getAllyVBox().getAllyPaneList()
													.get(i).getAlly().getAllyDamagePerSecond()
													* abilityButton.getAbility().getAlliesDamageMultiplier()));
								}
								GamePane.getAllyVBox().getAllyPaneList().get(i).getButton()
										.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
												new CornerRadii(8.0), Insets.EMPTY)));
								GamePane.getAllyVBox().getAllyPaneList().get(i).setLocked(true);
							}
							abilityButton.use();
							abilityButton.getAbility().setUsed(true);
						}
					});

					AudioClip increaseAllyDamageSound = SoundEffect.getHowlSound();
					increaseAllyDamageSound.play();
					Thread.sleep(10000); // durations
					for (int i = 0; i < GamePane.getAllyVBox().getAllyPaneList().size(); i++) {
						if (GamePane.getAllyVBox().getAllyPaneList().get(i).getAlly().isUsed() == true) {
							GamePane.getAllyVBox().getAllyPaneList().get(i).getAlly().setAllyDamagePerSecond(oldDPS[i]);

						}
						GamePane.getAllyVBox().getAllyPaneList().get(i).setLocked(false);
						GamePane.getAllyVBox().getAllyPaneList().get(i).getButton().setBackground(
								new Background(new BackgroundFill(Color.GOLD, new CornerRadii(8.0), Insets.EMPTY)));
					}
					abilityButton.unlocked();
					Thread.sleep(10000); // Cooldown
					abilityButton.owned();
					abilityButton.getAbility().setUsed(false);
					AbilityPane.unlockPrestige(GamePane.getAbilityPane().getAbilityButtonList());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			thread.start();
		} else if (abilityButton.getAbility().getAbilityName().equals("Prestige")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Do you want to Prestige?");
			alert.setTitle("Message");
			alert.showAndWait();
			if (alert.getResult() == ButtonType.OK) {
				abilityButton.getAbility().setSkillEffect(GamePane.getPlayerButton(), abilityButton.getAbility());
				AudioClip prestigeSound = SoundEffect.getPrestigeSound();
				prestigeSound.play();
			}
		}
	}

	public void use() { // using
		this.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void owned() { // already buy
		this.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void afford() { // can buy
		this.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void unlocked() { // not enough gold
		this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void setTooltip(Ability ability) { // Set Tooltip of each ability
		abilityTooltip.setFont(new Font(16));
		if (ability.getAbilityName().equals("Tome of Knowledge")) {
			abilityTooltip
					.setText(ability.getAbilityName() + ability.getDescriptionText() + ability.getAbilityPriceText());
		} else if (ability.getAbilityName().equals("Prestige")) {
			if (ability.isOwned() == false) {
				abilityTooltip.setText(ability.getAbilityName() + ability.getDescriptionText()
						+ ability.getUnlockedLevelText()
						+ "\nNote : You can use Prestige after all abilities are not on cooldown and not in used.");
			} else {
				abilityTooltip.setText(ability.getAbilityName() + ability.getDescriptionText()
						+ "\nYou can use Prestige after all abilities are not on cooldown and not in used.");
			}
		} else {
			if (ability.isOwned() == false) {
				abilityTooltip.setText(ability.getAbilityName() + ability.getDescriptionText()
						+ ability.getUnlockedLevelText() + ability.getAbilityPriceText());
			} else {
				if (ability.getAbilityName().equals("Finger of Death")) {
					abilityTooltip.setText(ability.getAbilityName() + ability.getDescriptionText() + "\nCooldown : "
							+ ability.getAbilityCooldown());
				} else if (ability.getAbilityName().equals("God Strength")) {
					abilityTooltip.setText(ability.getAbilityName() + ability.getDescriptionText() + "\nCooldown : "
							+ ability.getAbilityCooldown() + "\nDuration : " + ability.getDuration()
							+ "\nNote : You cannot level up your hero while using this ability");
				} else if (ability.getAbilityName().equals("Howl")) {
					abilityTooltip.setText(ability.getAbilityName() + ability.getDescriptionText() + "\nCooldown : "
							+ ability.getAbilityCooldown() + "\nDuration : " + ability.getDuration()
							+ "\nNote : You cannot level up your ally while using this ability");
				} else {
					abilityTooltip.setText(ability.getAbilityName() + ability.getDescriptionText() + "\nCooldown : "
							+ ability.getAbilityCooldown() + "\nDuration : " + ability.getDuration());
				}

			}
		}
		this.setOnMouseMoved((MouseEvent e) -> {
			if (ability != null)
				abilityTooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			abilityTooltip.hide();
		});
	}

	// GETTER/SETTER
	public Ability getAbility() {
		return this.ability;
	}

	public ImageView getImageView() {
		return this.imageView;
	}

}
