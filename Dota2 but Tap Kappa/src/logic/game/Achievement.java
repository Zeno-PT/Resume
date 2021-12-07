package logic.game;

import application.GamePane;
import application.Main;
import gui.ability.Prestige;
import gui.ally.AllyVBox;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class Achievement {
	private static String achievementDescription;
	private static int countPlayerTotalGoldAchievement;
	private static int countPlayerSpendingGoldAchievement;
	private static int countPlayerTotalClickAchievement;
	private static int countPlayerLevelAchievement;
	private static int countStageClearedAchievement;
	private static int countClickPerSecondAchievement;
	private static int countAllyHiredAchievement;
	private static int countPrestigeAchievement;
	private static String imageAchievement; // Achievement picture URL
	private static ImageView imageViewAchievement; // Achievement picture

	public Achievement() {
		Achievement.countPlayerTotalGoldAchievement = 0;
		Achievement.countPlayerSpendingGoldAchievement = 0;
		Achievement.countPlayerTotalClickAchievement = 0;
		Achievement.countPlayerLevelAchievement = 0;
		Achievement.countStageClearedAchievement = 0;
		Achievement.countClickPerSecondAchievement = 0;
		Achievement.countAllyHiredAchievement = 0;
		Achievement.countPrestigeAchievement = 0;
	}

	// Set Alert Box to be shown and add VBox to Achievement Pane when achievement
	// completed
	public static void setAchievementAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Achievement Unlocked");
		alert.setTitle("Congratulations!");
		alert.setContentText(Achievement.getAchievementDescription());
		String image = ClassLoader.getSystemResource("image/Achievement/trophy-cartoon.png").toString();
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(60);
		imageView.setFitHeight(60);
		alert.setGraphic(imageView);
		alert.show();

		GridPane ac = new GridPane();
		Label label = new Label();
		label.setText(achievementDescription);
		label.setFont(FontUse.font26);
		label.setTextFill(Color.BLACK);
		label.setPadding(new Insets(20));
		label.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
		label.setBorder(
				new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
		label.setPrefWidth(380);
		imageViewAchievement = new ImageView(imageAchievement);
		imageViewAchievement.setFitWidth(60);
		imageViewAchievement.setFitHeight(60);
		ac.add(imageViewAchievement, 0, 0);
		ac.add(label, 1, 0);
		ac.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		Main.getAchievementPane().getAchievementVBox().getChildren().add(ac);
	}

	public static boolean clearPlayerTotalGoldAchievement() {
		imageAchievement = ClassLoader.getSystemResource("image/gold-coin.png").toString();
		if ((GamePane.getPlayerButton().getPlayerTotalGold() >= 10000)
				&& Achievement.getCountPlayerTotalGoldAchievement() == 0) {
			Achievement.setAchievementDescription("Congratulations, you got 10,000 golds");
			Achievement.setCountPlayerTotalGoldAchievement(getCountPlayerTotalGoldAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getPlayerTotalGold() >= 100000)
				&& Achievement.getCountPlayerTotalGoldAchievement() == 1) {
			Achievement.setAchievementDescription("Congratulations, you got 100,000 golds");
			Achievement.setCountPlayerTotalGoldAchievement(getCountPlayerTotalGoldAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getPlayerTotalGold() >= 1000000)
				&& Achievement.getCountPlayerTotalGoldAchievement() == 2) {
			Achievement.setAchievementDescription("Congratulations, you got 1,000,000 golds");
			Achievement.setCountPlayerTotalGoldAchievement(getCountPlayerTotalGoldAchievement() + 1);
			return true;
		}
		return false;
	}

	public static boolean clearPlayerSpendingGoldAchievement() {
		imageAchievement = ClassLoader.getSystemResource("image/gold-coin.png").toString();
		if ((GamePane.getPlayerButton().getPlayerSpendingGold() >= 10000)
				&& Achievement.getCountPlayerSpendingGoldAchievement() == 0) {
			Achievement.setAchievementDescription("Congratulations, you spend 10,000 golds");
			Achievement.setCountPlayerSpendingGoldAchievement(getCountPlayerSpendingGoldAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getPlayerSpendingGold() >= 100000)
				&& Achievement.getCountPlayerSpendingGoldAchievement() == 1) {
			Achievement.setAchievementDescription("Congratulations, you spend 100,000 golds");
			Achievement.setCountPlayerSpendingGoldAchievement(getCountPlayerSpendingGoldAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getPlayerSpendingGold() >= 1000000)
				&& Achievement.getCountPlayerSpendingGoldAchievement() == 2) {
			Achievement.setAchievementDescription("Congratulations, you spend 1,000,000 golds");
			Achievement.setCountPlayerSpendingGoldAchievement(getCountPlayerSpendingGoldAchievement() + 1);
			return true;
		}
		return false;
	}

	public static boolean clearPlayerTotalClickAchievement() {
		imageAchievement = ClassLoader.getSystemResource("image/Achievement/sword.png").toString();
		if ((GamePane.getPlayerButton().getPlayerTotalClick() >= 100)
				&& Achievement.getCountPlayerTotalClickAchievement() == 0) {
			Achievement.setAchievementDescription("Congratulations, you have done 100 clicks");
			Achievement.setCountPlayerTotalClickAchievement(Achievement.getCountPlayerTotalClickAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getPlayerTotalClick() >= 500)
				&& Achievement.getCountPlayerTotalClickAchievement() == 1) {
			Achievement.setAchievementDescription("Congratulations, you have done 500 clicks");
			Achievement.setCountPlayerTotalClickAchievement(Achievement.getCountPlayerTotalClickAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getPlayerTotalClick() >= 1000)
				&& Achievement.getCountPlayerTotalClickAchievement() == 2) {
			Achievement.setAchievementDescription("Congratulations, you have done 1000 clicks");
			Achievement.setCountPlayerTotalClickAchievement(Achievement.getCountPlayerTotalClickAchievement() + 1);
			return true;
		}
		return false;
	}

	public static boolean clearPlayerLevelAchievement() {
		imageAchievement = ClassLoader.getSystemResource("image/Ability/Tome_of_Knowledge_icon.png").toString();
		if ((GamePane.getPlayerButton().getPlayerLevel() >= 10) && Achievement.getCountPlayerLevelAchievement() == 0) {
			Achievement.setAchievementDescription("Congratulations, you reached level 10");
			Achievement.setCountPlayerLevelAchievement(getCountPlayerLevelAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getPlayerLevel() >= 15)
				&& Achievement.getCountPlayerLevelAchievement() == 1) {
			Achievement.setAchievementDescription("Congratulations, you reached level 15");
			Achievement.setCountPlayerLevelAchievement(Achievement.getCountPlayerLevelAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getPlayerLevel() >= 20)
				&& Achievement.getCountPlayerLevelAchievement() == 2) {
			Achievement.setAchievementDescription("Congratulations, you reached level 20");
			Achievement.setCountPlayerLevelAchievement(Achievement.getCountPlayerLevelAchievement() + 1);
			return true;
		}
		return false;
	}

	public static boolean clearClickPerSecondAchievement() {
		imageAchievement = ClassLoader.getSystemResource("image/Achievement/sword.png").toString();
		if ((GamePane.getPlayerButton().getMaxCps() >= 7) && Achievement.getCountClickPerSecondAchievement() == 0) {
			Achievement.setAchievementDescription("Hmm, you got some speed.\n(You have reached 7 clicks per second.)");
			Achievement.setCountClickPerSecondAchievement(Achievement.getCountClickPerSecondAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getMaxCps() >= 10)
				&& Achievement.getCountClickPerSecondAchievement() == 1) {
			Achievement.setAchievementDescription("You're a Madman.\n(You have reached 10 clicks per second.)");
			Achievement.setCountClickPerSecondAchievement(Achievement.getCountClickPerSecondAchievement() + 1);
			return true;
		} else if ((GamePane.getPlayerButton().getMaxCps() >= 15)
				&& Achievement.getCountClickPerSecondAchievement() == 2) {
			Achievement.setAchievementDescription("Are you crazy?\n(You have reached 15 clicks per second.)");
			Achievement.setCountClickPerSecondAchievement(Achievement.getCountClickPerSecondAchievement() + 1);
			return true;
		}
		return false;
	}

	public static boolean clearAllyHiredAchievement() {
		imageAchievement = ClassLoader.getSystemResource("image/Emotes/CmonBruh.png").toString();
		if ((AllyVBox.getCountAllyHired() == 1) && Achievement.getCountAllyHiredAchievement() == 0) {
			Achievement.setAchievementDescription("Congratulations, you hired your first ally!!");
			Achievement.setCountAllyHiredAchievement(Achievement.getCountAllyHiredAchievement() + 1);
			return true;
		} else if ((AllyVBox.getCountAllyHired() == GamePane.getAllyVBox().getAllyPaneList().size())
				&& Achievement.getCountAllyHiredAchievement() == 1) {
			Achievement.setAchievementDescription("Congratulations, you hired all allies!!");
			Achievement.setCountAllyHiredAchievement(Achievement.getCountAllyHiredAchievement() + 1);
			return true;
		}
		return false;
	}

	public static boolean clearStageclearedAchievement() {
		imageAchievement = ClassLoader.getSystemResource("image/Boss/Ancient_Black_Dragon_model.png").toString();
		if ((GamePane.getMonster().getMonsterBossCount() == 1) && Achievement.getCountStageClearedAchievement() == 0) {
			Achievement.setAchievementDescription("You killed your first boss!!");
			Achievement.setCountStageClearedAchievement(Achievement.getCountStageClearedAchievement() + 1);
			return true;
		} else if ((GamePane.getMonster().getMonsterBossCount() == 10)
				&& Achievement.getCountStageClearedAchievement() == 1) {
			Achievement.setAchievementDescription("You have cleared stage 10!!");
			Achievement.setCountStageClearedAchievement(Achievement.getCountStageClearedAchievement() + 1);
			return true;
		} else if ((GamePane.getMonster().getMonsterBossCount() == 50)
				&& Achievement.getCountStageClearedAchievement() == 2) {
			Achievement.setAchievementDescription("You have cleared staage 50!!");
			Achievement.setCountStageClearedAchievement(Achievement.getCountStageClearedAchievement() + 1);
			return true;
		}
		return false;
	}

	public static boolean clearPrestigeAchievement() {
		imageAchievement = ClassLoader.getSystemResource("image/Ability/Reincarnation_icon.png").toString();
		if ((Prestige.getPrestigeTime() == 1) && Achievement.getCountPrestigeAchievement() == 0) {
			Achievement.setAchievementDescription("You have done first prestige!!");
			Achievement.setCountPrestigeAchievement(Achievement.getCountPrestigeAchievement() + 1);
			return true;
		} else if ((Prestige.getPrestigeTime() == 5) && Achievement.getCountPrestigeAchievement() == 1) {
			Achievement.setAchievementDescription("You rebirth five times");
			Achievement.setCountPrestigeAchievement(Achievement.getCountPrestigeAchievement() + 1);
			return true;
		} else if ((Prestige.getPrestigeTime() == 10) && Achievement.getCountPrestigeAchievement() == 2) {
			Achievement.setAchievementDescription("You rebirth ten times!!");
			Achievement.setCountPrestigeAchievement(Achievement.getCountPrestigeAchievement() + 1);
			return true;
		}
		return false;
	}

	// GETTER/SETTER
	public static String getAchievementDescription() {
		return Achievement.achievementDescription;
	}

	public static void setAchievementDescription(String achievementDescription) {
		Achievement.achievementDescription = achievementDescription;
	}

	public static int getCountPlayerTotalGoldAchievement() {
		return Achievement.countPlayerTotalGoldAchievement;
	}

	public static void setCountPlayerTotalGoldAchievement(int countTotalGoldAchievement) {
		Achievement.countPlayerTotalGoldAchievement = countTotalGoldAchievement;
	}

	public static int getCountPlayerSpendingGoldAchievement() {
		return Achievement.countPlayerSpendingGoldAchievement;
	}

	public static void setCountPlayerSpendingGoldAchievement(int countSpendingGoldAchievement) {
		Achievement.countPlayerSpendingGoldAchievement = countSpendingGoldAchievement;
	}

	public static int getCountPlayerTotalClickAchievement() {
		return Achievement.countPlayerTotalClickAchievement;
	}

	public static void setCountPlayerTotalClickAchievement(int countTotalClickAchievement) {
		Achievement.countPlayerTotalClickAchievement = countTotalClickAchievement;
	}

	public static int getCountPlayerLevelAchievement() {
		return Achievement.countPlayerLevelAchievement;
	}

	public static void setCountPlayerLevelAchievement(int countPlayerLevelAchievement) {
		Achievement.countPlayerLevelAchievement = countPlayerLevelAchievement;
	}

	public static int getCountClickPerSecondAchievement() {
		return Achievement.countClickPerSecondAchievement;
	}

	public static void setCountClickPerSecondAchievement(int countClickPerSecondAchievement) {
		Achievement.countClickPerSecondAchievement = countClickPerSecondAchievement;
	}

	public static int getCountAllyHiredAchievement() {
		return Achievement.countAllyHiredAchievement;
	}

	public static void setCountAllyHiredAchievement(int countAllyHiredAchievement) {
		Achievement.countAllyHiredAchievement = countAllyHiredAchievement;
	}

	public static int getCountStageClearedAchievement() {
		return Achievement.countStageClearedAchievement;
	}

	public static void setCountStageClearedAchievement(int countStageClearedAchievement) {
		Achievement.countStageClearedAchievement = countStageClearedAchievement;
	}

	public static int getCountPrestigeAchievement() {
		return Achievement.countPrestigeAchievement;
	}

	public static void setCountPrestigeAchievement(int countPrestigeAchievement) {
		Achievement.countPrestigeAchievement = countPrestigeAchievement;
	}

}
