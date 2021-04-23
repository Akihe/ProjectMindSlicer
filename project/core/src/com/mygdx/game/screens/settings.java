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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
        createTable();
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

    public void infoButton() {
        TextButton infoOpener;
        infoOpener = new TextButton("help", skin);

        infoOpener.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.setVisible(true);
            }
        });

        infoOpener.setPosition(250f, 250f);
        gameStage.addActor(infoOpener);
    }

    public void createTable() {
        infoButton();

        table = new Table();
        table.setSize(Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        window = new Window("", skin);
        window.setSize(table.getWidth(), table.getHeight());

        Image attack = new Image(new Texture("bubble.png"));
        Label attackText = new Label("Your basic attack,\nincreases inclusion by 15.", skin);

        window.add(attack);

        Image buff = new Image(new Texture("coffee_cup.png"));
        window.add(buff);
        Label buffText = new Label("You take a zip of coffee\nand re-focus, making your moves\nincrease inclusion even more", skin);
        window.row();
        window.add(attackText);
        window.add(buffText);
        window.row();

        Image shield = new Image(new Texture("shield_icon1.png"));
        Label shieldText = new Label("This will protect you\nfrom the monsters attacks", skin);
        window.add(shield);

        Image heal = new Image(new Texture("sweet_health.png"));
        Label healText = new Label("This will make you happier\nand raise your health", skin);
        window.add(heal);
        window.row();
        window.add(shieldText);
        window.add(healText);
        window.row();

        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.setVisible(false);
            }
        });

        window.add(close);
        window.setMovable(false);
        window.setVisible(false);

        table.add(window);

        window.setModal(true);
 /*       window.addAction(Actions.sequence(Actions.alpha(0)
                , Actions.fadeIn(1f)));

  */
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
