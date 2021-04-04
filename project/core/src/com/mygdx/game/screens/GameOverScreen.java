package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Main;
import com.mygdx.game.buttons.actionButton;
import com.mygdx.game.buttons.levelsButton;
import com.mygdx.game.buttons.playButton;
import com.mygdx.game.buttons.thinkButton;
import com.mygdx.game.enemyActor;
import com.mygdx.game.playerActor;

public class GameOverScreen implements Screen {


    Main host;
    SpriteBatch batch;
    private Texture BACKGROUND;
    Stage stage;
    private Stage gameStage;

private levelsButton levelsButton;

    public GameOverScreen(Main host) {
        BACKGROUND = new Texture("GameOver.png");

        this.host = host;
        batch = host.batch;

        this.gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        levelsButton = new levelsButton();
        gameStage.addActor(levelsButton);
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
