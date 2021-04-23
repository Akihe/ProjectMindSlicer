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

	public SpriteBatch batch;
	public static BitmapFont font;

	public static final int WORLD_WIDTH = 800;
	public static final int WORLD_HEIGHT = 480;
	public Skin skin;
	public static Locale locale;
	public boolean finnish;

	public static Music menuMusic;
	public static Music fightMusic;

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void create () {
		fightMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/fightmusic.wav"));
		fightMusic.setLooping(true);
		fightMusic.play();

		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/menumusic.wav"));
		menuMusic.setLooping(true);
		menuMusic.play();
		batch = new SpriteBatch();
		open("player");

		skin = new Skin(Gdx.files.internal("skin.json"));

		font = skin.getFont("chilanka-normal");
		locale = new Locale("");
		finnish = true;

		setScreen(new mainMenuScreen(this));

	}

	public static final void setToEnglish() {
		locale = new Locale("en_US");
	}

	public static final void setToFinnish() {
		locale = new Locale("");
	}


		// String text = getLevelText("level1");
	public static final String getLevelText(String key) {
		// Kysy käyttikseltä millä kielellä mennään
		I18NBundle myBundleFin =
				I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale, "utf-8");

		I18NBundle myBundleEng =
				I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale, "utf-8");

		return myBundle.get(key);
	}

	public static void save(String name) {
		Preferences prefs =
				Gdx.app.getPreferences("MyPreferencesSetti");  // MyPreferencesSetti.xml

		prefs.putString("name", name);                           // <name>Jack</name>
		prefs.putInteger("money", playerActor.MONEY);
		prefs.flush();
	}

	public static String open(String key) {
		Preferences prefs =
				Gdx.app.getPreferences("MyPreferencesSetti");

		String name = prefs.getString("name", "No name stored");

		return name;
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
