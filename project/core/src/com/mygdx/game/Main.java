package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.mygdx.game.screens.*;

import java.util.Locale;


public class Main extends Game {

	public static boolean finnish;
	public SpriteBatch batch;
	public static BitmapFont font;

	public static final int WORLD_WIDTH = 800;
	public static final int WORLD_HEIGHT = 480;
	public Skin skin;

	public static Music menuMusic;
	public static Music fightMusic;

	public static Locale localeEN;
	public static Locale localeFI;

	public static I18NBundle myBundleFin;
	public static I18NBundle myBundleEng;

	public static Preferences prefs;

	@Override
	public void create () {
		fightMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/fightmusic.wav"));
		fightMusic.setLooping(true);

		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/menumusic.wav"));
		menuMusic.setLooping(true);
		menuMusic.play();

		prefs = Gdx.app.getPreferences("MyPreferencesSetti");

		batch = new SpriteBatch();

		skin = new Skin(Gdx.files.internal("skin.json"));

		font = skin.getFont("chilanka-header");

		localeEN = new Locale("en_US");
		localeFI = new Locale("");
		finnish = true;

		myBundleFin = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), localeFI, "utf-8");
		myBundleEng = I18NBundle.createBundle(Gdx.files.internal("MyBundle_en_US"), localeEN, "utf-8");

		setScreen(new mainMenuScreen(this, skin));

		try {
			open();
		} catch (Exception ignored) {

		}
	}

	public static final String getLevelText(String key) {
		if (finnish) {
			return myBundleFin.get(key);
		} else {
			return myBundleEng.get(key);
		}
	}

	public static void save() {

		prefs.putInteger("money", playerActor.MONEY);
		prefs.putBoolean("lvl1", defaultValues.level1Defeated);
		prefs.putBoolean("lvl2", defaultValues.level2Defeated);
		prefs.putBoolean("lvl3", defaultValues.level3Defeated);
		prefs.flush();
	}

	public static void open() {

		int raha = prefs.getInteger("money");
		boolean lvl1 = prefs.getBoolean("lvl1");
		boolean lvl2 = prefs.getBoolean("lvl2");
		boolean lvl3 = prefs.getBoolean("lvl3");

		Gdx.app.log("save", "rahaa on " + prefs.getInteger("money"));
		Gdx.app.log("save", "level 1 tapettu " + prefs.getBoolean("lvl1"));
		Gdx.app.log("save", "level 2 tapettu " + prefs.getBoolean("lvl2"));
		Gdx.app.log("save", "level 3 tapettu " + prefs.getBoolean("lvl3"));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
