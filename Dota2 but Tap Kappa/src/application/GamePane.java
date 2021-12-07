package application;

import gui.ability.AbilityPane;
import gui.ally.AllyVBox;
import gui.animation.SwordMasterAnimation;
import gui.monster.Monster;
import gui.monster.MonsterPicture;
import gui.player.PlayerButton;
import javafx.animation.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.game.BackgroundMusic;
import logic.game.FontUse;
import logic.game.GameManager;
import logic.game.InsertAnimationPictureFailedException;

public class GamePane extends GridPane {

	private static Label playerInformation1 = new Label();
	private static Label playerInformation2 = new Label();
	private static Label monsterInformation = new Label();
	private static Label monsterCountLabel = new Label();
	private static Label allyLabel = new Label();
	private static Label moneyLabel = new Label();
	private Label abilityLabel = new Label("Ability");

	private static GridPane monsterZone = new GridPane();
	private static GridPane allyPicture = new GridPane();
	private static GridPane swordMasterPicture = new GridPane();
	private GridPane money = new GridPane();

	private static AllyVBox allyVBox = new AllyVBox();
	private static AbilityPane abilityPane = new AbilityPane();
	private static PlayerButton playerButton = new PlayerButton();
	private static ProgressBar monsterHealthBar;
	private static Monster monster = new Monster();
	private HBox monsterCount = new HBox();
	private static GridPane secondInformation = new GridPane();

	private ScrollPane abilityScrollPane = new ScrollPane();
	private ScrollPane allyScrollPane = new ScrollPane();

	private static Button achievementButton = new Button();
	private static Button moreButton = new Button("More");
	private static Button backtoMenuButton = new Button("Back");

	private static ImageView imageViewFirstSword;
	private ImageView imageViewMonster;
	private ImageView imageViewTrophy;
	private ImageView imageViewCoin;

	public GamePane() { // Game Scene
		// TODO Auto-generated constructor stub
		this.setPrefSize(Main.getScreenWidth(), Main.getScreenHeight());
		// Monster //
		// Monster Health Bar
		monsterHealthBar = new ProgressBar((double) GamePane.getMonster().getMonsterHealthPoint()
				/ GamePane.getMonster().getMonsterMaxHealthPoint());
		monsterHealthBar.setStyle("-fx-accent: red;");
		monsterHealthBar.setPrefWidth(250);
		try {
			Monster.setNewMonster(GamePane.getMonster());
		} catch (InsertAnimationPictureFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Monster Label
		String imageMonster = ClassLoader.getSystemResource("image/Creeps/Centaur_Conqueror_model.png").toString();
		imageViewMonster = new ImageView(imageMonster);
		imageViewMonster.setFitWidth(30);
		imageViewMonster.setFitHeight(30);
		monsterCount.getChildren().addAll(imageViewMonster, monsterCountLabel);

		monsterZone.add(monsterHealthBar, 0, 0);
		monsterZone.add(monsterInformation, 0, 1);
		monsterZone.add(monsterCount, 0, 2);
		monsterInformation.setFont(Font.font(null, FontWeight.BOLD, 16));
		monsterInformation.setTextFill(Color.WHITE);
		monsterCountLabel.setFont(Font.font(null, FontWeight.BOLD, 16));
		monsterCountLabel.setTextFill(Color.WHITE);
		monsterZone.setAlignment(Pos.TOP_CENTER);
		swordMasterPicture.add(monsterCount, 0, 0);
		monsterCount.setAlignment(Pos.TOP_LEFT);
		GameManager.setMonsterCountLabel(monsterCountLabel);
		GameManager.setMonsterLabel(monsterInformation);

		// Player //
		// PlayerButton
		playerButton
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		playerButton.setBorder(new Border(
				new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		playerButton.setPrefWidth(MonsterPicture.getMonsterWidth()); // set same as monsterPicture size
		playerButton.setPrefHeight(MonsterPicture.getMonsterHeight()); // set same as monsterPicture size
		MonsterPicture.setMonsterPicture();
		// Player Information
		playerInformation1.setFont(Font.font(null, FontWeight.BOLD, 16));
		playerInformation1.setTextFill(Color.WHITE);
		playerInformation2.setFont(Font.font(null, FontWeight.BOLD, 16));
		playerInformation2.setTextFill(Color.WHITE);
		GameManager.setPlayerFirstInformation(playerInformation1);
		GameManager.setPlayerSecondInformation(playerInformation2);
		GameManager.setPlayerTotalInformation(PlayerInformationPane.getPlayerTotalInformation());
		PlayerButton.checkClickPerSecond(GamePane.getPlayerButton());
		// Back to main menu
		Main.setupButton(backtoMenuButton);
		backtoMenuButton.setFont(FontUse.font30);
		backtoMenuButton.setMaxSize(100, 30);
		backtoMenuButton.setOnAction(click -> {
			Main.setScene(0); // Go to MenuPane
			BackgroundMusic.getMainTheme().stop();
			BackgroundMusic.getBossTheme().stop();
			BackgroundMusic.getMenuTheme().play();

		});
		// Show more information
		Main.setupButton(moreButton);
		moreButton.setFont(FontUse.font30);
		moreButton.setMaxSize(100, 30);
		moreButton.setOnAction(click -> {
			Main.setScene(4); // Set Scene as PlayerInformationPane
		});
		// Show achievement
		achievementButton
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		achievementButton.setOpacity(0.75);
		achievementButton.setOnMouseEntered(e -> {
			achievementButton.setOpacity(1);
		});
		achievementButton.setOnMouseExited(e -> {
			achievementButton.setOpacity(0.75);
		});
		String imageTrophy = ClassLoader.getSystemResource("image/Achievement/trophy-cartoon.png").toString();
		imageViewTrophy = new ImageView(imageTrophy);
		imageViewTrophy.setFitWidth(40);
		imageViewTrophy.setFitHeight(40);
		achievementButton.setGraphic(imageViewTrophy);
		achievementButton.setOnAction(click -> {
			Main.setScene(3); // Set Scene as AchievementPane
			BackgroundMusic.getMainTheme().stop();
			BackgroundMusic.getBossTheme().stop();
			BackgroundMusic.getAchievementTheme().setVolume(0.6);
			BackgroundMusic.getAchievementTheme().setCycleCount(Animation.INDEFINITE);
			BackgroundMusic.getAchievementTheme().play();
		});

		secondInformation.add(playerInformation2, 0, 0);
		secondInformation.add(moreButton, 0, 1);
		secondInformation.add(backtoMenuButton, 0, 2);
		secondInformation.add(achievementButton, 0, 3);
		secondInformation.setVgap(20);

		String firstURL = ClassLoader.getSystemResource(SwordMasterAnimation.getSwordmasterlisturl()[0]).toString();
		imageViewFirstSword = new ImageView(firstURL);
		imageViewFirstSword.setFitWidth(SwordMasterAnimation.getWidth());
		imageViewFirstSword.setFitHeight(SwordMasterAnimation.getHeight());
		imageViewFirstSword.setTranslateY(20);
		imageViewFirstSword.setTranslateX(70);
		GamePane.getSwordMasterPicture().add(imageViewFirstSword, 0, 1);

		// Money Label //
		String image = ClassLoader.getSystemResource("image/gold-coin.png").toString(); // Coin
		imageViewCoin = new ImageView(image);
		imageViewCoin.setFitWidth(30);
		imageViewCoin.setFitHeight(30);
		money.add(imageViewCoin, 0, 0);
		money.add(moneyLabel, 1, 0);
		GameManager.setMoneyLabel(GamePane.getMoneyLabel());
		moneyLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
		moneyLabel.setTextFill(Color.WHITE);
		money.setAlignment(Pos.TOP_CENTER);
		// Ability //
		abilityLabel.setFont(Font.font(null, FontWeight.BOLD, 18));
		abilityLabel.setTextFill(Color.WHITE);
		abilityPane.setAlignment(Pos.BASELINE_LEFT);
		abilityScrollPane.setContent(abilityPane);
		abilityScrollPane.setFitToWidth(true);
		abilityScrollPane.setMaxWidth(231);
		abilityScrollPane.setPrefHeight(145);
		// Ally //
		allyPicture.setVgap(30);
		allyPicture.setHgap(10);
		GameManager.setAllyUpperLabel(allyLabel);
		allyLabel.setFont(Font.font(null, FontWeight.BOLD, 18));
		allyLabel.setTextFill(Color.WHITE);
		allyVBox.setPrefWidth(250);

		allyScrollPane.setContent(allyVBox);
		allyScrollPane.setFitToWidth(true);
		allyScrollPane.setPrefWidth(310);
		allyScrollPane.setPrefHeight(145);

		this.add(monsterZone, 0, 0);
		this.add(swordMasterPicture, 0, 1);
		this.add(abilityLabel, 0, 2);
		this.add(abilityScrollPane, 0, 3);
		this.add(money, 1, 0);
		this.add(playerButton, 1, 1);
		this.add(allyPicture, 1, 3);
		this.add(playerInformation1, 2, 0);
		this.add(secondInformation, 2, 1);
		this.add(allyLabel, 2, 2);
		this.add(allyScrollPane, 2, 3);
		this.setVgap(10);
		this.setHgap(45);
		this.setPadding(new Insets(15));

	}

	// GETTER/SETTER
	public static PlayerButton getPlayerButton() {
		return GamePane.playerButton;
	}

	public static Monster getMonster() {
		return GamePane.monster;
	}

	public static AbilityPane getAbilityPane() {
		return GamePane.abilityPane;
	}

	public static ImageView getImageViewFirstSword() {
		return GamePane.imageViewFirstSword;
	}

	public static GridPane getAllyPicture() {
		return GamePane.allyPicture;
	}

	public static GridPane getSwordMasterPicture() {
		return GamePane.swordMasterPicture;
	}

	public static Label getPlayerFirstInformation() {
		return playerInformation1;
	}

	public static Label getPlayerSecondInformation() {
		return GamePane.playerInformation2;
	}

	public static Label getMonsterInformation() {
		return GamePane.monsterInformation;
	}

	public static AllyVBox getAllyVBox() {
		return GamePane.allyVBox;
	}

	public static Label getAllyLabel() {
		return GamePane.allyLabel;
	}

	public static Label getMoneyLabel() {
		return GamePane.moneyLabel;
	}

	public static ProgressBar getMonsterHealthBar() {
		return GamePane.monsterHealthBar;
	}

	public static Label getMonsterCountLabel() {
		return GamePane.monsterCountLabel;
	}

	public static GridPane getMonsterZone() {
		return GamePane.monsterZone;
	}

	public Label getAbilityLabel() {
		return this.abilityLabel;
	}

	public GridPane getMoney() {
		return this.money;
	}

	public HBox getMonsterCount() {
		return this.monsterCount;
	}

	public static GridPane getSecondInformation() {
		return GamePane.secondInformation;
	}

	public ScrollPane getAbilityScrollPane() {
		return this.abilityScrollPane;
	}

	public ScrollPane getAllyScrollPane() {
		return this.allyScrollPane;
	}

	public static Button getAchievementButton() {
		return GamePane.achievementButton;
	}

	public static Button getMoreButton() {
		return GamePane.moreButton;
	}

	public static Button getBacktoMenuButton() {
		return GamePane.backtoMenuButton;
	}

	public ImageView getImageViewMonster() {
		return this.imageViewMonster;
	}

	public ImageView getImageViewTrophy() {
		return this.imageViewTrophy;
	}

	public ImageView getImageViewCoin() {
		return this.imageViewCoin;
	}

}
