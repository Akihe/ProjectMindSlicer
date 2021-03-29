package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class fightingStageScreen implements Screen {

    Main host;
    SpriteBatch batch;
    private Texture BACKGROUND;

    private actionButton actionbutton;
    private thinkButton thinkbutton;


    float timeSinceAttack = 0;

    private Stage gameStage;
    public static playerActor player;
    //vaihdettu staticiksi enemyn hyökkäystä  varten
    public static enemyActor enemy;


    public fightingStageScreen(Main host) {

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

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(50, 50, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(BACKGROUND,0,0,Main.WORLD_WIDTH,Main.WORLD_HEIGHT);
        batch.end();


        gameStage.act();
        gameStage.draw();

        //Enemy attacks only after the player attacks, and DeltaTime counts a 2 second delay for the enemies move.
        if(player.playerActionDone){
            timeSinceAttack += Gdx.graphics.getDeltaTime();
            if (timeSinceAttack > 2.0f) {
                enemy.enemyHit();
                timeSinceAttack = 0;
            }
        }
        if(enemy.ENEMY_HEALTH<=0){
            enemy.enemyDie();
        }
        // MITEN VASTUSTAJAN HYÖKKÄYKSEN SAA TEHTYÄ VASTA PELAAJAN JÄLKEEN

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
