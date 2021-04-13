package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.I18NBundle;
import com.mygdx.game.screens.*;

import java.util.Locale;


public class Main extends Game {

	public SpriteBatch batch;
	public static BitmapFont font;

	public static final int WORLD_WIDTH = 800;
	public static final int WORLD_HEIGHT = 480;

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new mainMenuScreen(this));

		font = new BitmapFont(Gdx.files.internal("Chilanka-Regular.fnt"));
		font.getData().setScale(2);
	}

	// String text = getLevelText("level1");
	public static String getLevelText(String key) {
		// Kysy käyttikseltä millä kielellä mennään
		Locale locale = Locale.getDefault();

		I18NBundle myBundle =
				I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale);
		return myBundle.get(key);
	}

	public static void save(String name) {
		Preferences prefs =
				Gdx.app.getPreferences("MyPreferencesSetti");  // MyPreferencesSetti.xml

		prefs.putString("name", name);                           // <name>Jack</name>
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
