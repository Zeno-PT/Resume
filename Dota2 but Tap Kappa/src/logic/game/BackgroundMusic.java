package logic.game;

import javafx.animation.Animation;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class BackgroundMusic {

	private static int bossNumber = 0; // Count boss number to cycle boss songs
	// Background music for main menu scene
	private static AudioClip mainTheme = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Omae_Wa_Mou.mp3").toString()).getSource());
	// Background music for main menu scene
	private static AudioClip menuTheme = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/World_Map.mp3").toString()).getSource());
	// Background music for achievement scene
	private static AudioClip achievementTheme = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Achievement/Spanish_Waltz.mp3").toString()).getSource());
	// Background music for credit scene
	private static AudioClip creditTheme = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Krusty_Krab_Remix.mp3").toString()).getSource());
	// List of boss fight music URL
	private static String[] battleMusicListURL = { "sound/battle/Giorno_Giovanna.mp3",
			"sound/battle/Naruto_Battle_Theme.mp3", "sound/battle/Pokemon_Battle_Theme.mp3",
			"sound/battle/Naruto_Main_Theme.mp3", "sound/battle/pac.mp3" };
	// Background music for boss fighting
	private static AudioClip bossTheme = new AudioClip(
			new Media(ClassLoader.getSystemResource(BackgroundMusic.getBattleMusicListURL()[bossNumber]).toString())
					.getSource());

	// Set the boss song when boss appeared
	public static void setBossMusic() {
		mainTheme.stop();
		bossTheme = new AudioClip(
				new Media(ClassLoader.getSystemResource(BackgroundMusic.getBattleMusicListURL()[bossNumber]).toString())
						.getSource());
		BackgroundMusic.setBossNumber((BackgroundMusic.getBossNumber() + 1) % 5);
		bossTheme.setVolume(0.5);
		if (!BackgroundMusic.getMenuTheme().isPlaying() && !BackgroundMusic.getCreditTheme().isPlaying()
				&& !BackgroundMusic.getAchievementTheme().isPlaying()) {
			bossTheme.setCycleCount(Animation.INDEFINITE);
			bossTheme.play();
		}
	}

	// Set back to normal battle background music
	public static void stopBossMusic() {
		bossTheme.stop();
		if (!BackgroundMusic.getMenuTheme().isPlaying() && !BackgroundMusic.getCreditTheme().isPlaying()
				&& !BackgroundMusic.getAchievementTheme().isPlaying())
			mainTheme.play();
	}

	// GETTER/SETTER
	public static String[] getBattleMusicListURL() {
		return BackgroundMusic.battleMusicListURL;
	}

	public static AudioClip getBossTheme() {
		return BackgroundMusic.bossTheme;
	}

	public static int getBossNumber() {
		return BackgroundMusic.bossNumber;
	}

	public static void setBossNumber(int bossNumber) {
		BackgroundMusic.bossNumber = bossNumber;
	}

	public static AudioClip getMainTheme() {
		return BackgroundMusic.mainTheme;
	}

	public static AudioClip getMenuTheme() {
		return BackgroundMusic.menuTheme;
	}

	public static AudioClip getAchievementTheme() {
		return BackgroundMusic.achievementTheme;
	}

	public static AudioClip getCreditTheme() {
		return BackgroundMusic.creditTheme;
	}

	public static void setBossTheme(AudioClip bossTheme) {
		BackgroundMusic.bossTheme = bossTheme;
	}

}
