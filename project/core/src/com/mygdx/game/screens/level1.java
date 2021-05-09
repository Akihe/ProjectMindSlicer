package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.buttons.*;
import com.mygdx.game.*;


public class level1 implements Screen {

    Main host;
    SpriteBatch batch;
    private Texture BACKGROUND;

    private actionButton actionbutton;
    private thinkButton thinkbutton;
    private shieldButton shieldButton;
    private healButton healbutton;
    private settingsIngameButton settingsingame;
    kidActor kid;
    public static Table table;
    boolean winScreenShown = false;
    Dialog openDialog;
    Dialog winDialog;

    Skin skin;
    String winner;

    float timeSinceAttack = 0;

    public static Label playerTurn;

    private final Stage gameStage;
    public static playerActor player;
    public static enemyActor enemy;

    public level1(Main host) {
        defaultValues.levelInd = 1;
        this.host = host;
        skin = host.skin;
        batch = host.batch;


        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        BACKGROUND = new Texture("taustakoulu.png");

        Main.menuMusic.stop();
        if (defaultValues.musicOn) {
            Main.fightMusic.play();
        }

        winner = Main.getLevelText("winner1");

        playerTurn = new Label("Its your turn!", skin, "chilanka-header", "RGBA_0_0_0_255");
        playerTurn.setPosition(320f, Main.WORLD_HEIGHT/1.5f);
        playerTurn.setVisible(false);

        player = new playerActor(1);
        enemy = new enemyActor(1);
        actionbutton = new actionButton();
        thinkbutton = new thinkButton();
        shieldButton = new shieldButton();
        healbutton = new healButton();
        kid = new kidActor(1);
        settingsingame = new settingsIngameButton(host, skin, gameStage);

        gameStage.addActor(player);
        gameStage.addActor(enemy);
        gameStage.addActor(actionbutton);
        gameStage.addActor(thinkbutton);
        gameStage.addActor(shieldButton);
        gameStage.addActor(healbutton);
        gameStage.addActor(kid);
        gameStage.addActor(settingsingame);
        gameStage.addActor(playerTurn);

        winPopup();
        openingDialog();
    }

    public static void playerTurnTeller() {
        if(enemyActor.allowPlayerAttack) {
            playerTurn.setVisible(true);
            playerTurn.addAction(Actions.sequence(Actions.alpha(0)
                    , Actions.fadeIn(2f), Actions.fadeOut(2f)));
        }
    }

    private void openingDialog() {
        String introduceKid = Main.getLevelText("kid1");

        openDialog = new Dialog("", skin, "default") {
            public void result(Object obj) {
                Gdx.app.log("nappi ", "nappi" + obj);

                if (obj.equals(true)) {
                    openDialog.setVisible(false);
                    playerTurnTeller();
                }
            }};
        openDialog.text(introduceKid);
        openDialog.button("Okay", true); //sends "true" as the result
        openDialog.pack();
        openDialog.setPosition(Main.WORLD_WIDTH/10f, Main.WORLD_HEIGHT/4f);
        openDialog.setMovable(false);
        gameStage.addActor(openDialog);
    }

    public void winPopup() {

        winDialog = new Dialog("Success!", skin, "default") {
            public void result(Object obj) {
                Gdx.app.log("nappi ", "nappi" + obj);
                if (obj.equals(true)) {
                    host.setScreen(new levelSelect(host));
                    player.MONEY += 200;
                }
            }
        };

        winDialog.text(winner);
        winDialog.button("Okay", true); //sends "true" as the result
        winDialog.pack();
        winDialog.setPosition(Main.WORLD_WIDTH/10f, Main.WORLD_HEIGHT/4f);
        winDialog.setVisible(false);
        winDialog.setMovable(false);
        gameStage.addActor(winDialog);
    }

    public void fight() {

        //Enemy attacks only after the player has attacked, and DeltaTime counts a delay for the enemies move, based on how long the players invidual attack lasts.
        if(player.playerActionDone && enemy.ENEMY_HEALTH > 0){
            timeSinceAttack += Gdx.graphics.getDeltaTime();
            if (timeSinceAttack > player.enemyAttacksAfter) {
                enemy.randomAttack();
                enemy.chooseAttack();
                timeSinceAttack = 0;
                player.enemyAttacksAfter = 0;
                player.resetPlayer();
            }
        }
    }

    public void loseWinCheck() {

        if(enemy.ENEMY_HEALTH <= 0){
            defaultValues.level1Defeated = true;
            enemy.enemyDie();
            kid.appear();
            gameStage.getActors().removeValue(settingsingame, true);

            float delay = 2;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameStage.getActors().removeValue(enemy, true);
                    player.resetPlayer();

                }
            }, delay);

            if (!winScreenShown) {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        winDialog.setVisible(true);
                        winScreenShown = true;
                    }
                }, delay*2);
            }
        }

        if(player.PLAYER_HEALTH <= 0){
            player.resetStats();
            host.setScreen(new GameOverScreen(host));
        }
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

        fight();
        loseWinCheck();

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
        batch.dispose();
        gameStage.dispose();
        Main.menuMusic.dispose();
        Main.fightMusic.dispose();
        skin.dispose();
    }
}
