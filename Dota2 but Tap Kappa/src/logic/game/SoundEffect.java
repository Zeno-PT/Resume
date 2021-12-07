package logic.game;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class SoundEffect {
	// Audio Clip when player attack
	private static AudioClip attackSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Juggernaut_attack.mp3").toString()).getSource());
	// Audio Clip when player get critical chance
	private static AudioClip criticalAttackSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Juggernaut_Blade_Dance.mp3").toString()).getSource());
	// Audio Clip when ally attacks
	private static AudioClip allyAttackSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/attack.mp3").toString()).getSource());
	// Audio Clip when ally is purchased
	private static AudioClip purchaseAllySound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/zelda_2_level_up.mp3").toString()).getSource());
	// Audio Clip when level up ability is used
	private static AudioClip levelUpSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Poof.mp3").toString()).getSource());
	// Audio Clip when god strength ability is used
	private static AudioClip godStrengthSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/God's_Strength.mp3").toString()).getSource());
	// Audio Clip when hand of midas ability is used
	private static AudioClip midasSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Hand_of_Midas.mp3").toString()).getSource());
	// Audio Clip when blade fury ability is used
	private static AudioClip bladeFurySound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Juggernaut_Blade_Fury_1.mp3").toString()).getSource());
	// Audio Clip when finger of death ability is used
	private static AudioClip fingerOfDeathSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Finger_of_Death.mp3").toString()).getSource());
	// Audio Clip when howl ability is used
	private static AudioClip howlSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Howl.mp3").toString()).getSource());
	// Audio Clip when prestige ability is used
	private static AudioClip prestigeSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Reincarnation.mp3").toString()).getSource());
	// Audio Clip when monster dead
	private static AudioClip monsterDeathSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Land_Mines_explosion.mp3").toString()).getSource());
	// Audio Clip when boss monster dead
	private static AudioClip bossDeathSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Pinpoint_Detonate.mp3").toString()).getSource());
	// Audio Clip when you cannot use ability, hire or level up ally
	private static AudioClip mibmabSound = new AudioClip(
			new Media(ClassLoader.getSystemResource("sound/Invulnerable.wav").toString()).getSource());

	// GETTER
	public static AudioClip getAttackSound() {
		return SoundEffect.attackSound;
	}

	public static AudioClip getCriticalAttackSound() {
		return SoundEffect.criticalAttackSound;
	}

	public static AudioClip getAllyAttackSound() {
		return SoundEffect.allyAttackSound;
	}

	public static AudioClip getPurchaseAllySound() {
		return SoundEffect.purchaseAllySound;
	}

	public static AudioClip getLevelUpSound() {
		return SoundEffect.levelUpSound;
	}

	public static AudioClip getGodStrengthSound() {
		return SoundEffect.godStrengthSound;
	}

	public static AudioClip getMidasSound() {
		return SoundEffect.midasSound;
	}

	public static AudioClip getBladeFurySound() {
		return SoundEffect.bladeFurySound;
	}

	public static AudioClip getFingerOfDeathSound() {
		return SoundEffect.fingerOfDeathSound;
	}

	public static AudioClip getHowlSound() {
		return SoundEffect.howlSound;
	}

	public static AudioClip getPrestigeSound() {
		return SoundEffect.prestigeSound;
	}

	public static AudioClip getMonsterDeathSound() {
		return SoundEffect.monsterDeathSound;
	}

	public static AudioClip getBossDeathSound() {
		return SoundEffect.bossDeathSound;
	}

	public static AudioClip getMibmabSound() {
		return SoundEffect.mibmabSound;
	}

}
