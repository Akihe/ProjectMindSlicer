package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;

    Dialog dialog;
    static Skin skin;

    String open;

    levelButtons levelButton1;
    levelButtons levelButton2;
    levelButtons levelButton3;

    returnButton returnBtn;
    loungeButton loungeButton;


    public levelSelect (final Main host) {
        this.host = host;

        skin = host.skin;
        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);
        open = Main.getLevelText("startingDialog");

        Main.fightMusic.stop();
        if (defaultValues.musicOn) {
            Main.menuMusic.play();
        }

        defaultValues.levelInd = 0;

        backgroundTexture = new Texture("mainmenu_screen.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        levelButton1 = new levelButtons(160f, 150f, 1);
        gameStage.addActor(levelButton1);

        levelButton2 = new levelButtons(350f, 150f, 2);
        gameStage.addActor(levelButton2);

        levelButton3 = new levelButtons(540f, 150f, 3);
        gameStage.addActor(levelButton3);


        returnBtn = new returnButton(50f,50f, "LevelSelect");
        gameStage.addActor((returnBtn));

        loungeButton = new loungeButton();
        gameStage.addActor(loungeButton);

        openingDialog();
        if (!defaultValues.introShown) {
            dialog.setVisible(true);
            defaultValues.introShown = true;
        }
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
        dialog.setPosition(Main.WORLD_WIDTH/10, Main.WORLD_HEIGHT/4f);
        dialog.setVisible(false);
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
        host.setScreen(new mainMenuScreen(host, skin));
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
        gameStage.dispose();
    }
}
