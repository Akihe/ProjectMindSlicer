package fi.tuni.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import fi.tuni.MindSlicer.Main;

/**
 * Screen that is displayed when the player loses the fight.
 *
 * <p>A very simple screen due to not having enough time to make anything more special.
 *    This screen will be displayed when the players HP goes to 0 or below.</p>
 */
public class GameOverScreen implements Screen {

    private Main host;
    private Texture BACKGROUND;
    private Skin skin;
    private Stage gameStage;

    /**
     * Setting background and creating a gamestage in the constructor
     * @param host comes from the Main class
     */
    public GameOverScreen(Main host) {
        BACKGROUND = new Texture("gameover.png");

        this.host = host;
        skin = host.skin;

        this.gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH, Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        retryButton();
    }

    /**
     * A Textbutton that takes you to the main menu when pressed.
     */
    public void retryButton() {
        TextButton retry;
        retry = new TextButton(Main.getLevelText("retryText"), skin);

        retry.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.setPlayScreen();
            }
        });

        retry.setPosition(350f, 50f);
        gameStage.addActor(retry);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(BACKGROUND, 0, 0, Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        gameStage.getBatch().end();

        gameStage.act();
        gameStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
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
