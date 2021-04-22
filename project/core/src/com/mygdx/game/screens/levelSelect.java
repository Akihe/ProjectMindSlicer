package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.buttons.*;
import com.mygdx.game.*;


public class levelSelect implements Screen {

    static Main host;
    SpriteBatch batch;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;

    Dialog dialog;
    Skin skin;

    String open;

    levelButtons levelButtons;
    returnButton returnBtn;
    LevelLoungeButton LevelLoungeButton;


    public levelSelect (final Main host) {
        this.host = host;
        batch = host.batch;

        skin = host.skin;
        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);
        open = Main.getLevelText("startingDialog");

        defaultValues.levelInd = 0;

        backgroundTexture = new Texture("mainmenu_screen.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        levelButtons = new levelButtons(260f, 250f, 1);
        gameStage.addActor(levelButtons);

        levelButtons = new levelButtons(360f, 250f, 2);
        gameStage.addActor(levelButtons);

        levelButtons = new levelButtons(460f, 250f, 3);
        gameStage.addActor(levelButtons);


        returnBtn = new returnButton(100f,100f, "LevelSelect");
        gameStage.addActor((returnBtn));

        LevelLoungeButton = new LevelLoungeButton();
        gameStage.addActor(LevelLoungeButton);

        openingDialog();
    }

    private void openingDialog() {

        dialog = new Dialog("Työhakemus", skin, "default") {
            public void result(Object obj) {
                Gdx.app.log("nappi ", "nappi" + obj);

                if (obj.equals(true)) {
                    dialog.setVisible(false);
                }
            }
        };
        dialog.text(open);
        dialog.button("Okay", true); //sends "true" as the result
        //  dialog.button("esim. nappi", false); //sends "false" as the result
        dialog.pack();
        dialog.setPosition(Main.WORLD_WIDTH/4f, Main.WORLD_HEIGHT/4f);
        gameStage.addActor(dialog);
    }

    public static void setLevel1() {
        host.setScreen(new level1(host));
    }

    public static void setLevel2() {
        host.setScreen(new level2(host));
    }

    public static void setLevel3() {
        host.setScreen(new level3(host));
    }

    public static void setLevelUP() {
        host.setScreen(new lounge(host));
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
