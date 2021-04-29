package com.mygdx.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.MindSlicer.Main;
import com.mygdx.MindSlicer.buttons.actionButton;


public class level2 implements Screen {

    com.mygdx.MindSlicer.Main host;
    SpriteBatch batch;
    private Texture BACKGROUND;

    private com.mygdx.MindSlicer.buttons.actionButton actionbutton;
    private com.mygdx.MindSlicer.buttons.thinkButton thinkbutton;
    private com.mygdx.MindSlicer.buttons.shieldButton shieldButton;
    private com.mygdx.MindSlicer.buttons.healButton healbutton;
    private com.mygdx.MindSlicer.buttons.settingsIngameButton settingsingame;
    com.mygdx.MindSlicer.kidActor kid;
    public static Table table;
    boolean winScreenShown = false;
    Dialog openDialog;
    Dialog winDialog;

    Skin skin;
    String winner;

    float timeSinceAttack = 0;

    public static Label playerTurn;

    private final Stage gameStage;
    public static com.mygdx.MindSlicer.playerActor player;
    public static com.mygdx.MindSlicer.enemyActor enemy;

    public level2(com.mygdx.MindSlicer.Main host) {
        com.mygdx.MindSlicer.Main.save();
        com.mygdx.MindSlicer.defaultValues.levelInd = 2;
        this.host = host;
        skin = host.skin;


        gameStage = new Stage(new StretchViewport(com.mygdx.MindSlicer.Main.WORLD_WIDTH, com.mygdx.MindSlicer.Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        BACKGROUND = new Texture("puisto.png");


        com.mygdx.MindSlicer.Main.menuMusic.stop();
        if (com.mygdx.MindSlicer.defaultValues.musicOn) {
            com.mygdx.MindSlicer.Main.fightMusic.play();
        }

        winner = Main.getLevelText("winner2");

        playerTurn = new Label("Its your turn!", skin, "chilanka-header", "RGBA_0_0_0_255");
        playerTurn.setPosition(320f, com.mygdx.MindSlicer.Main.WORLD_HEIGHT/1.5f);
        playerTurn.setVisible(false);

        player = new com.mygdx.MindSlicer.playerActor(2);
        enemy = new com.mygdx.MindSlicer.enemyActor(2);
        actionbutton = new actionButton();
        thinkbutton = new com.mygdx.MindSlicer.buttons.thinkButton();
        shieldButton = new com.mygdx.MindSlicer.buttons.shieldButton();
        healbutton = new com.mygdx.MindSlicer.buttons.healButton();
        kid = new com.mygdx.MindSlicer.kidActor(2);
        settingsingame = new com.mygdx.MindSlicer.buttons.settingsIngameButton(host, skin, gameStage);

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
        if(com.mygdx.MindSlicer.enemyActor.allowPlayerAttack) {
            playerTurn.setVisible(true);
            playerTurn.addAction(Actions.sequence(Actions.alpha(0)
                    , Actions.fadeIn(2f), Actions.fadeOut(2f)));
        }
    }

    private void openingDialog() {
        String introduceKid = com.mygdx.MindSlicer.Main.getLevelText("kid2");

        openDialog = new Dialog("", skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    openDialog.setVisible(false);
                    playerTurnTeller();
                }
            }};
        openDialog.text(introduceKid);
        openDialog.button("Okay", true); //sends "true" as the result
        openDialog.pack();
        openDialog.setPosition(com.mygdx.MindSlicer.Main.WORLD_WIDTH/10f, com.mygdx.MindSlicer.Main.WORLD_HEIGHT/4f);
        openDialog.setMovable(false);
        gameStage.addActor(openDialog);
    }

    public void winPopup() {

        winDialog = new Dialog("Success!", skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    host.setScreen(new levelSelect(host));
                    player.MONEY += 250;
                    com.mygdx.MindSlicer.Main.save();
                }
            }
        };

        winDialog.text(winner);
        winDialog.button("Okay", true); //sends "true" as the result
        winDialog.pack();
        winDialog.setPosition(com.mygdx.MindSlicer.Main.WORLD_WIDTH/10f, com.mygdx.MindSlicer.Main.WORLD_HEIGHT/4f);
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
            com.mygdx.MindSlicer.defaultValues.level2Defeated = true;
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
        gameStage.getBatch().draw(BACKGROUND,0,0, com.mygdx.MindSlicer.Main.WORLD_WIDTH, com.mygdx.MindSlicer.Main.WORLD_HEIGHT);
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
        com.mygdx.MindSlicer.Main.menuMusic.dispose();
        skin.dispose();
    }
}