package gui.monster;

import java.util.ArrayList;

import application.GamePane;
import application.Main;
import javafx.scene.image.ImageView;

public class MonsterPicture {

	private static ArrayList<String> monsterPictureList;
	private static ImageView imageViewMonster; // monster image
	private static int monsterWidth;
	private static int monsterHeight;

	public MonsterPicture() {
		// 1-28 = monsters
		monsterPictureList = new ArrayList<>();
		monsterPictureList.add("image/Creeps/Melee_Creep_Radiant_model.png");
		monsterPictureList.add("image/Creeps/Melee_Creep_Dire_model.png");
		monsterPictureList.add("image/Creeps/Centaur_Conqueror_model.png");
		monsterPictureList.add("image/Creeps/Centaur_Courser_model.png");
		monsterPictureList.add("image/Creeps/Dark_Troll_Summoner_model.png");
		monsterPictureList.add("image/Creeps/Hellbear_model.png");
		monsterPictureList.add("image/Creeps/Hellbear_Smasher_model.png");
		monsterPictureList.add("image/Creeps/Hill_Troll_model.png");
		monsterPictureList.add("image/Creeps/Satyr_Banisher_model.png");
		monsterPictureList.add("image/Creeps/Satyr_Mindstealer_model.png");
		monsterPictureList.add("image/Creeps/Satyr_Tormenter_model.png");
		monsterPictureList.add("image/Creeps/Wildwing_model.png");
		monsterPictureList.add("image/Creeps/Wildwing_Ripper_model.png");
		monsterPictureList.add("image/Creeps/Fell_Spirit_model.png");
		monsterPictureList.add("image/Creeps/Ghost_model.png");
		monsterPictureList.add("image/Creeps/Harpy_Scout_model.png");
		monsterPictureList.add("image/Creeps/Harpy_Stormcrafter_model.png");
		monsterPictureList.add("image/Creeps/Hill_Troll_Berserker_model.png");
		monsterPictureList.add("image/Creeps/Kobold_Foreman_model.png");
		monsterPictureList.add("image/Creeps/Kobold_model.png");
		monsterPictureList.add("image/Creeps/Kobold_Soldier_model.png");
		monsterPictureList.add("image/Creeps/Vhoul_Assassin_model.png");
		monsterPictureList.add("image/Creeps/Hill_Troll_Priest_model.png");
		monsterPictureList.add("image/Creeps/Giant_Wolf_model.png");
		monsterPictureList.add("image/Creeps/Alpha_Wolf_model.png");
		monsterPictureList.add("image/Creeps/Ogre_Bruiser_model.png");
		monsterPictureList.add("image/Creeps/Ogre_Frostmage_model.png");
		monsterPictureList.add("image/Creeps/Mud_Golem_model.png");
		// 29+ = boss
		monsterPictureList.add("image/Boss/Ancient_Black_Dragon_model.png");
		monsterPictureList.add("image/Boss/Ancient_Black_Drake_model.png");
		monsterPictureList.add("image/Boss/Ancient_Granite_Golem_model.png");
		monsterPictureList.add("image/Boss/Ancient_Prowler_Acolyte_model.png");
		monsterPictureList.add("image/Boss/Ancient_Prowler_Shaman_model.png");
		monsterPictureList.add("image/Boss/Ancient_Rock_Golem_model.png");
		monsterPictureList.add("image/Boss/Ancient_Rumblehide_model.png");
		monsterPictureList.add("image/Boss/Ancient_Thunderhide_model.png");
		monsterPictureList.add("image/Boss/Roshan_model.png");

		MonsterPicture.monsterWidth = 250;
		MonsterPicture.monsterHeight = 250;
	}

	public static void setMonsterPicture() { // random non-boss monsters
		int number = (int) (Math.random() * 28);
		String monsterPicture = ClassLoader.getSystemResource(MonsterPicture.getMonsterPictureList().get(number))
				.toString();
		imageViewMonster = new ImageView(monsterPicture);
		imageViewMonster.setFitWidth(MonsterPicture.getMonsterWidth());
		imageViewMonster.setFitHeight(MonsterPicture.getMonsterHeight());
		GamePane.getPlayerButton().setGraphic(imageViewMonster);
	}

	public static void setMonsterBossPicture() { // random boss monsters
		int number = (int) ((Math.random() * 9) + 28);
		String monsterPicture = ClassLoader.getSystemResource(MonsterPicture.getMonsterPictureList().get(number))
				.toString();
		imageViewMonster = new ImageView(monsterPicture);
		imageViewMonster.setFitWidth(MonsterPicture.getMonsterWidth());
		imageViewMonster.setFitHeight(MonsterPicture.getMonsterHeight());
		GamePane.getPlayerButton().setGraphic(imageViewMonster);
	}

	// Remove monster picture from GamePane
	public static void removeMonsterPicture() {
		Main.getGamePane().getChildren().remove(imageViewMonster);
	}

	// Change monster position using Thread
	public static void changeMonsterPosition() {
		Thread s = new Thread(() -> {
			try {
				if (Math.random() <= 0.25) {
					imageViewMonster.setTranslateX((Math.random() * 20) + 5);
					Thread.sleep(100);
					imageViewMonster.setTranslateX(0);
				} else if ((Math.random() >= 0.25) && (Math.random() <= 0.50)) {
					imageViewMonster.setTranslateY((Math.random() * 20) + 5);
					Thread.sleep(100);
					imageViewMonster.setTranslateY(0);
				} else if ((Math.random() >= 0.50) && (Math.random() <= 0.75)) {
					imageViewMonster.setTranslateY(-(Math.random() * 20) + 5);
					Thread.sleep(100);
					imageViewMonster.setTranslateY(0);
				} else if ((Math.random() >= 0.75) && (Math.random() <= 1)) {
					imageViewMonster.setTranslateX(-(Math.random() * 20) + 5);
					Thread.sleep(100);
					imageViewMonster.setTranslateX(0);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		s.start();

	}

	// GETTER/SETTER
	public static ArrayList<String> getMonsterPictureList() {
		return MonsterPicture.monsterPictureList;
	}

	public static ImageView getImageViewMonster() {
		return MonsterPicture.imageViewMonster;
	}

	public static int getMonsterWidth() {
		return MonsterPicture.monsterWidth;
	}

	public static void setMonsterWidth(int monsterWidth) {
		MonsterPicture.monsterWidth = monsterWidth;
	}

	public static int getMonsterHeight() {
		return MonsterPicture.monsterHeight;
	}

	public static void setMonsterHeight(int monsterHeight) {
		MonsterPicture.monsterHeight = monsterHeight;
	}

}
