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

public class fightingStageScreen implements Screen {

    Main host;
    SpriteBatch batch;
    private Texture BACKGROUND;


    float timeSinceAttack = 0;

    private Stage gameStage;
    public static playerActor player;
    //vaihdettu staticiksi enemyn hyökkäystä  varten
    public static enemyActor enemy;


    public fightingStageScreen(Main host) {

        BACKGROUND = new Texture("taustakoulu.png");

        this.host = host;
        batch = host.batch;

        gameStage = new Stage(new FitViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT), batch);
        Gdx.input.setInputProcessor(gameStage);

        player = new playerActor();
        gameStage.addActor(player);

        enemy = new enemyActor();
        gameStage.addActor(enemy);

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        ImageButton button2 = new ImageButton(mySkin);
       // button2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg"))));

        button2.setTransform(true);
        button2.setScale(0.5f);
        button2.setPosition(10,300 );

        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                player.hitAction();

                return true;
            }
        });

        ImageButton button3 = new ImageButton(mySkin);
       // button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("garmfiel.png"))));
        button3.setTransform(true);
        button3.setScale(0.5f);
        button3.setPosition(10,370 );

        button3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                player.thinkAction();

                return true;
            }
        });

        gameStage.addActor(button2);
        gameStage.addActor(button3);

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
        if(player.playerActionDone==true){
            timeSinceAttack += Gdx.graphics.getDeltaTime();
            if (timeSinceAttack > 2.0f) {
                enemy.enemyHit();
                timeSinceAttack = 0;
            }
        }
        // MITEN VASTUSTAJAN HYÖKKÄYKSEN SAA TEHTYÄ VASTA PELAAJAN JÄLKEEN

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

    }
}
