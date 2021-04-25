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
	public static Locale localeEN;
	public static Locale localeFI;

	public static Music menuMusic;
	public static Music fightMusic;

	public static I18NBundle myBundleFin;
	public static I18NBundle myBundleEng;

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void create () {
		fightMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/fightmusic.wav"));
		fightMusic.setLooping(true);

		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/menumusic.wav"));
		menuMusic.setLooping(true);
		menuMusic.play();

		batch = new SpriteBatch();
		open("player");

		skin = new Skin(Gdx.files.internal("skin.json"));

		font = skin.getFont("chilanka-normal");
		localeEN = new Locale("en_US");
		localeFI = new Locale("");
		finnish = true;

		myBundleFin = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), localeFI, "utf-8");
		myBundleEng = I18NBundle.createBundle(Gdx.files.internal("MyBundle_en_US"), localeEN, "utf-8");

		setScreen(new mainMenuScreen(this, skin));
	}

	public static final String getLevelText(String key) {
		if (finnish) {
			return myBundleFin.get(key);
		} else {
			return myBundleEng.get(key);
		}
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
