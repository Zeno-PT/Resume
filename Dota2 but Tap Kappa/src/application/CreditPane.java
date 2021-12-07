package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.game.BackgroundMusic;
import logic.game.BackgroundPicture;
import logic.game.FontUse;

public class CreditPane extends GridPane {

	private Label head = new Label("Team Tap_Kappa");
	private Label name = new Label(
			"Peeranath\tTheerawatanachai\t\t6231343821\nVorapon\t\tKhunakornkorbkij\t\t6231356021");
	private Button backButtonFromCredit = new Button("Back");

	public CreditPane() { // Credit Scene
		// TODO Auto-generated constructor stub
		this.setPrefSize(Main.getScreenWidth(), Main.getScreenHeight());
		BackgroundPicture.setCreditBackgroundPicture(Main.getRoot());
		// Headline
		head.setFont(FontUse.font);
		head.setBackground(new Background(new BackgroundFill(Color.SALMON, CornerRadii.EMPTY, Insets.EMPTY)));
		head.setOpacity(0.8);
		head.setPadding(new Insets(15));
		// Group Member
		name.setFont(Font.font(null, FontWeight.BOLD, 20));
		name.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		name.setOpacity(0.8);
		name.setPadding(new Insets(15));
		// Back to menu button
		Main.setupButton(backButtonFromCredit);
		backButtonFromCredit.setPrefSize(100, 50);
		backButtonFromCredit.setFont(FontUse.font35);
		backButtonFromCredit.setOnAction(click -> {
			Main.setScene(0); //Set Scene as MenuPane
			BackgroundMusic.getCreditTheme().stop();
			BackgroundMusic.getMenuTheme().play();
		});
		this.add(head, 0, 0);
		this.add(name, 0, 1);
		this.add(backButtonFromCredit, 0, 2);
		this.setAlignment(Pos.CENTER);
		this.setVgap(40);
	}

	public Label getHead() {
		return this.head;
	}

	public Label getName() {
		return this.name;
	}

	public Button getBackButtonFromCredit() {
		return this.backButtonFromCredit;
	}

}
