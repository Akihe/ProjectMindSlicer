package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.buttons.*;
import com.mygdx.game.*;


public class levelSelect implements Screen {

    static Main host;
    SpriteBatch batch;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;

    level1Button level1button;
    returnButton returnBtn;
    LevelLoungeButton LevelLoungeButton;


    public levelSelect (final Main host) {
        this.host = host;
        batch = host.batch;

        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        backgroundTexture = new Texture("mainmenu_screen.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        level1button = new level1Button();
        gameStage.addActor(level1button);

        returnBtn = new returnButton(100f,100f, "LevelSelect");
        gameStage.addActor((returnBtn));

        LevelLoungeButton = new LevelLoungeButton();
        gameStage.addActor(LevelLoungeButton);

    }

    public static void setLevel1() {
        host.setScreen(new level1(host));
    }

    public static void setLevelUP() {
        host.setScreen(new LevelUpLounge(host));
    }

    public static void setMainMenu() {
        host.setScreen(new mainMenuScreen(host));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture, 0,0, Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        gameStage.getBatch().end();

        gameStage.act();
        gameStage.draw();


    }

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
        batch.dispose();
        gameStage.dispose();
    }
}