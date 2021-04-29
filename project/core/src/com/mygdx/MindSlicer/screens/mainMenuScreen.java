package com.mygdx.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.MindSlicer.Main;
import com.mygdx.MindSlicer.buttons.settingsButton;


public class mainMenuScreen implements Screen {

    static com.mygdx.MindSlicer.Main host;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;
    private Texture logoText;

    com.mygdx.MindSlicer.buttons.playButton playbutton;
    com.mygdx.MindSlicer.buttons.settingsButton settingsbutton;
    Skin skin;

    public mainMenuScreen(final com.mygdx.MindSlicer.Main host, Skin getSkin) {
        this.host = host;
        skin = getSkin;

        com.mygdx.MindSlicer.Main.fightMusic.stop();
        if (com.mygdx.MindSlicer.defaultValues.musicOn) {
            com.mygdx.MindSlicer.Main.menuMusic.play();
        }

        gameStage = new Stage(new StretchViewport(com.mygdx.MindSlicer.Main.WORLD_WIDTH, com.mygdx.MindSlicer.Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        backgroundTexture = new Texture("mainmenu_screen.png");
        logoText = new Texture("mindslicerlogo.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        playbutton = new com.mygdx.MindSlicer.buttons.playButton();
        gameStage.addActor(playbutton);

        settingsbutton = new settingsButton();
        gameStage.addActor(settingsbutton);

    }

    public static void setPlayScreen() {
        host.setScreen(new levelSelect(host));
    }

    public static void setSettingsScreen() {
        host.setScreen(new settings(host));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture, 0,0, com.mygdx.MindSlicer.Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        gameStage.getBatch().draw(logoText, 200,200,logoText.getWidth(), logoText.getHeight());
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

    }
}