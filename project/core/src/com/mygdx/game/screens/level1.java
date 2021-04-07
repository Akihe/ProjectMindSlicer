package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.buttons.*;
import com.mygdx.game.*;


public class level1 implements Screen {

    Main host;
    SpriteBatch batch;
    private Texture BACKGROUND;

    private actionButton actionbutton;
    private thinkButton thinkbutton;
    Stage stage;


    float timeSinceAttack = 0;

    private Stage gameStage;
    public static playerActor player;
    //vaihdettu staticiksi enemyn hyökkäystä  varten
    public static enemyActor enemy;


    public level1(Main host) {

        BACKGROUND = new Texture("taustakoulu.png");

        this.host = host;
        batch = host.batch;

        this.gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        player = new playerActor();
        gameStage.addActor(player);

        enemy = new enemyActor();
        gameStage.addActor(enemy);

        actionbutton = new actionButton();
        gameStage.addActor(actionbutton);

        thinkbutton = new thinkButton();
        gameStage.addActor(thinkbutton);

    }

    @Override
    public void show() {

    }

    public void winPopup() {

        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("test-skin.json"));
        Dialog dialog = new Dialog("Congratz!", skin, "window-popup") {
            public void result(Object obj) {
                Gdx.app.log("nappi ", "nappi" + obj);
            }
        };
        dialog.text("You won the fight! \n Your award is 500 coins");
        dialog.button("Okay", true); //sends "true" as the result
        dialog.button("esim. nappi", false); //sends "false" as the result
        dialog.pack();
        dialog.setPosition(Main.WORLD_WIDTH/4, Main.WORLD_HEIGHT/4);
        gameStage.addActor(dialog);
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

        //Enemy attacks only after the player attacks, and DeltaTime counts a 2 second delay for the enemies move.
        if(player.playerActionDone ){
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
            winPopup();
        }
        if(player.PLAYER_HEALTH<=0){
            host.setScreen(new GameOverScreen(host));
        }

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
