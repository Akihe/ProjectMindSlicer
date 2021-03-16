package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class mainMenuScreen implements Screen {

    Main host;
    SpriteBatch batch;
    private Stage gameStage;



    public mainMenuScreen(final Main host) {
        this.host = host;
        batch = host.batch;

        gameStage = new Stage(new FitViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT), batch);
        Gdx.input.setInputProcessor(gameStage);


        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        ImageButton button2 = new ImageButton(mySkin);
        button2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Garmfiel.png"))));
        button2.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg"))));
        button2.setPosition(250,200 );

        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //outputLabel.setText("Press a Button");
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    host.setScreen(new fightingStageScreen(host));
                return true;
            }
        });
        gameStage.addActor(button2);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    }
}
