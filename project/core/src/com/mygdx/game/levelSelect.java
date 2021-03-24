package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class levelSelect implements Screen {

    static Main host;
    SpriteBatch batch;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;

    level1Button level1button;


    public levelSelect (final Main host) {
        this.host = host;
        batch = host.batch;

        gameStage = new Stage(new FitViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT), batch);
        Gdx.input.setInputProcessor(gameStage);

        backgroundTexture = new Texture("mainmenu_screen.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        level1button = new level1Button();
        gameStage.addActor(level1button);

    }

    public static void setLevel1() {
        host.setScreen(new fightingStageScreen(host));
    }

    public static void setMainMenu() {
        host.setScreen(new mainMenuScreen(host));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture, 0,0, Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        gameStage.getBatch().end();

        //backStage.act();
        gameStage.act();

        //backStage.draw();
        gameStage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, false);
        //background.setPosition(0, 0);

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
