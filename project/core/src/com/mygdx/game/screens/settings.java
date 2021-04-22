package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.buttons.*;
import com.mygdx.game.*;
import com.mygdx.game.screens.*;


public class settings implements Screen {

    static Main host;
    SpriteBatch batch;
    private Stage gameStage;
    private Texture BACKGROUND;
    Skin skin;
    private Window window;
    private Table table;

    private returnButton returnbutton;
    String languagebtn;

    public settings(final Main host) {
        this.host = host;
        batch = host.batch;
        skin = host.skin;
        languagebtn = Main.getLevelText("languagebtn");

        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        BACKGROUND = new Texture("mainmenu_screen.png");

        returnbutton = new returnButton(100f, 100f, "Settings");
        gameStage.addActor(returnbutton);
        languageButton();
        //createTable();
    }

    public static void setMainMenuScreen() {
        host.setScreen(new mainMenuScreen(host));
    }

    private void languageButton() {
        TextButton textbtn;
        textbtn = new TextButton(languagebtn, skin);

        textbtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (host.finnish) {
                    host.setToEnglish();
                    host.finnish = false;
                    Gdx.app.log("nappi", "suomeksi");
                } else if (!host.finnish) {
                    host.setToFinnish();
                    host.finnish = true;
                    Gdx.app.log("nappi", "vaihtuu enkuksi");
                }
                gameStage.clear();
                host.setScreen(new settings(host));
            }
        });

        textbtn.setPosition(250f, 200f);
        gameStage.addActor(textbtn);
    }



    public void createTable() {
        table = new Table();
        table.setSize(Gdx.graphics.getWidth() / 2
                , Gdx.graphics.getHeight() / 5);
        window = new Window("", skin);
        window.setSize(table.getWidth(), table.getHeight());

        Button btnWindow = new Button(skin);
        btnWindow.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.setVisible(false);
            }
        });

        window.add(btnWindow);
        btnWindow.setSize(50, 50);
        btnWindow.setPosition(window.getWidth() - btnWindow.getWidth()
                , window.getHeight() - btnWindow.getHeight());

        table.add(window);

        window.setModal(true);
        table.setPosition(Gdx.graphics.getWidth() / 2 - window.getWidth() / 2
                , Gdx.graphics.getHeight() / 2 - window.getHeight() / 2 +
                        100);
        window.addAction(Actions.sequence(Actions.alpha(0)
                , Actions.fadeIn(1f)));
        gameStage.addActor(table);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(BACKGROUND,0,0,Main.WORLD_WIDTH,Main.WORLD_HEIGHT);
        gameStage.getBatch().end();

        gameStage.act();
        gameStage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
