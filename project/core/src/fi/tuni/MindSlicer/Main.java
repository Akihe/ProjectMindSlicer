package fi.tuni.MindSlicer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import fi.tuni.MindSlicer.screens.mainMenuScreen;

import java.util.Locale;

/**
 * Main class handles the savefile and language swapping. Switches the screen to main Menu at open.
 *
 * @author  Aki Helin aki.helin@tuni.fi
 * @author Veeti Karilainen veeti.karilainen@tuni.fi
 * @version 2021.1.0
 *
 */
public class Main extends Game {

	public static boolean finnish;
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

	/**
	 * Creates a few important variables here that are used widely in the game. I.e. music.
	 */
	@Override
	public void create () {
		fightMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/fightmusic.wav"));
		fightMusic.setLooping(true);

		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/menumusic.wav"));
		menuMusic.setLooping(true);
		menuMusic.play();

		prefs = Gdx.app.getPreferences("MyPreferencesSetti");

		skin = new Skin(Gdx.files.internal("skin.json"));

		font = skin.getFont("chilanka-header");

		localeEN = new Locale("en_US");
		localeFI = new Locale("");
		finnish = true;

		myBundleFin = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), localeFI, "utf-8");
		myBundleEng = I18NBundle.createBundle(Gdx.files.internal("MyBundle_en_US"), localeEN, "utf-8");


	/*	Tries to open one value from the save file, which is the boolean firstSaveDone.
		Ignoring the exception if the file isnt found.	*/
		try {
			openFirstSave();
		} catch (Exception ignored) {

		}

	/*	Here if the first save has been found and the game has been saved for the first time,
		we'll open up all the remaining saved values.	*/
		if(defaultValues.firstSaveDone) {
			open();
		}

		setScreen(new mainMenuScreen(this, skin));
	}

	/**
	 * Method that is called for every text sentence. Gets the texts from the English or Finnish file based on the current language.
	 * @param key searches the text files using the key input
	 * @return
	 */
	public static final String getLevelText(String key) {
		if (finnish) {
			return myBundleFin.get(key);
		} else {
			return myBundleEng.get(key);
		}
	}

	/**
	 * Saves information to a file MyPreferencesSetti.xml.
	 */
	public static void save() {
		prefs.putInteger("money", playerActor.MONEY);
		prefs.putInteger("currentAttack", defaultValues.currentAttack);
		prefs.putInteger("currentDefence", defaultValues.currentDefence);
		prefs.putBoolean("saved", defaultValues.firstSaveDone);
		prefs.putBoolean("lvl1", defaultValues.level1Defeated);
		prefs.putBoolean("lvl2", defaultValues.level2Defeated);
		prefs.putBoolean("lvl3", defaultValues.level3Defeated);
		prefs.putBoolean("intro", defaultValues.introShown);
		prefs.putBoolean("lounge", defaultValues.loungeEntry);
		prefs.flush();
	}

	/**
	 * Opens the save-file and gets the values needed.
	 */
	public static void open() {
		playerActor.MONEY = prefs.getInteger("money");
		defaultValues.currentAttack = prefs.getInteger("currentAttack");
		defaultValues.currentDefence = prefs.getInteger("currentDefence");
		defaultValues.level1Defeated = prefs.getBoolean("lvl1");
		defaultValues.level2Defeated = prefs.getBoolean("lvl2");
		defaultValues.level3Defeated = prefs.getBoolean("lvl3");
		defaultValues.introShown = prefs.getBoolean("intro");
		defaultValues.loungeEntry = prefs.getBoolean("lounge");
	}

	private void openFirstSave() {
		defaultValues.firstSaveDone = prefs.getBoolean("saved");
	}

	/**
	 * super.render is used to run the current Screens render-method.
	 */
	@Override
	public void render () {
		super.render();
	}

	/**
	 * super.dispose is used to run the current Screens dispose-method.
	 */
	@Override
	public void dispose () {
		super.dispose();
	}
}
