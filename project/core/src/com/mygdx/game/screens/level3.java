package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.buttons.*;
import com.mygdx.game.*;


public class level3 implements Screen {

    Main host;
    SpriteBatch batch;
    private Texture BACKGROUND;

    private actionButton actionbutton;
    private thinkButton thinkbutton;
    private shieldButton shieldButton;
    private HealButton HealButton;
    kidActor kid;

    Stage stage;
    String winner;

    float timeSinceAttack = 0;

    private final Stage gameStage;
    public static playerActor player;
    public static enemyActor enemy;

    public level3(Main host) {
        defaultValues.levelInd = 3;

        BACKGROUND = new Texture("taustakoulu.png");

        this.host = host;
        batch = host.batch;

        winner = Main.getLevelText("winner");

        this.gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        player = new playerActor(3);
        gameStage.addActor(player);

        enemy = new enemyActor(3);
        gameStage.addActor(enemy);

        actionbutton = new actionButton();
        gameStage.addActor(actionbutton);

        thinkbutton = new thinkButton();
        gameStage.addActor(thinkbutton);

        shieldButton = new shieldButton();
        gameStage.addActor(shieldButton);

        HealButton = new HealButton();
        gameStage.addActor(HealButton);

        kid = new kidActor();
        gameStage.addActor(kid);

    }

    public void winPopup() {

        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("test-skin.json"));
        Dialog dialog = new Dialog("Congratz!", skin, "window-popup") {
            public void result(Object obj) {
                Gdx.app.log("nappi ", "nappi" + obj);
            }
        };
        dialog.text(winner);
        dialog.button("Okay", true); //sends "true" as the result
        dialog.button("esim. nappi", false); //sends "false" as the result
        dialog.pack();
        dialog.setPosition(Main.WORLD_WIDTH/4f, Main.WORLD_HEIGHT/4f);
        gameStage.addActor(dialog);
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


        //Enemy attacks only after the player attacks, and DeltaTime counts a 2 second delay for the enemies move.
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

        if(enemy.ENEMY_HEALTH <= 0){
            enemy.enemyDie();
            kid.appear();
            defaultValues.startingMoney= defaultValues.startingMoney+500;

            float delay = 2;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameStage.getActors().removeValue(enemy, true);
                    winPopup();
                }
            }, delay);
        }

        if(player.PLAYER_HEALTH<=0){
            player.resetStats();
            host.setScreen(new GameOverScreen(host));
            defaultValues.levelInd=0;
        }

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
    }
}