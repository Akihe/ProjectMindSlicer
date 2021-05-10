package fi.tuni.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import fi.tuni.MindSlicer.main;
import fi.tuni.MindSlicer.buttons.settingsButton;
import fi.tuni.MindSlicer.buttons.playButton;
import fi.tuni.MindSlicer.defaultValues;

/**
 * Screen for starting the game, the mainMenu
 * <p>Houses assets and buttons necessary for the mainMenuScreen</p>
 */

public class mainMenuScreen implements Screen {

    static main host;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;
    private Texture logoText;

    playButton playbutton;
    settingsButton settingsbutton;
    Skin skin;

    /**
     * constructor for the main menu
     * @param host
     * @param getSkin
     * <p>Has a checker for music configurations. Creates screen accordingly, with provided assets</p>
     */
    public mainMenuScreen(final main host, Skin getSkin) {
        this.host = host;
        skin = getSkin;

        main.fightMusic.stop();
        if (defaultValues.musicOn) {
            main.menuMusic.play();
        }

        gameStage = new Stage(new StretchViewport(main.WORLD_WIDTH, main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        backgroundTexture = new Texture("mainmenu_screen.png");
        logoText = new Texture("mindslicerlogo.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        playbutton = new playButton();
        gameStage.addActor(playbutton);

        settingsbutton = new settingsButton();
        gameStage.addActor(settingsbutton);

    }

    /**
     * method for changing to the levelselect
     * <p>A method used by the start button, that moves the user to the desired screen</p>
     */
    public static void setPlayScreen() {
        host.setScreen(new levelSelect(host));
    }

    /**
     * method for changing to the settingScreen
     * <p>A method used by the settings button, that moves the user to the desired screen</p>
     */

    public static void setSettingsScreen() {
        host.setScreen(new settings(host));
    }

    @Override
    public void show() {
    }

    /**
     * a method for updating and playing the starting screen
     * @param delta
     */

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture, 0,0, main.WORLD_WIDTH, main.WORLD_HEIGHT);
        gameStage.getBatch().draw(logoText, 200,200,logoText.getWidth(), logoText.getHeight());
        gameStage.getBatch().end();

        gameStage.act();
        gameStage.draw();
    }

    /**
     * a method for adjusting the screen size
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
