package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class mainMenuScreen implements Screen {

    static Main host;
    SpriteBatch batch;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;

    playButton playbutton;
    settingsButton settingsbutton;

/*
    private Stage backStage;
    ExtendViewport backViewport;
*/

    public mainMenuScreen(final Main host) {
        this.host = host;
        batch = host.batch;

        gameStage = new Stage(new FitViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT), batch);
        Gdx.input.setInputProcessor(gameStage);


        backgroundTexture = new Texture("mainmenu_screen.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);
/*
        backViewport = new ExtendViewport(Main.WORLD_WIDTH, Main.WORLD_HEIGHT);

        backStage = new Stage();
        backStage.setViewport(backViewport);
        backStage.addActor(background);
        gameStage.addActor(background);

*/

        playbutton = new playButton();
        gameStage.addActor(playbutton);

        settingsbutton = new settingsButton();
        gameStage.addActor(settingsbutton);
/*
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        ImageButton button2 = new ImageButton(mySkin);
        button2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Garmfiel.png"))));
        button2.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Garmfiel.png"))));
        button2.setPosition(250,200 );

        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                host.setScreen(new fightingStageScreen(host));
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        gameStage.addActor(button2);
*/
    }

    public static void setPlayScreen() {
        host.setScreen(new fightingStageScreen(host));
    }

    public static void setSettingsScreen() {
        host.setScreen(new settingScreen(host));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture, 0,0, Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        gameStage.getBatch().end();

        //backStage.act();
        gameStage.act();

        //backStage.draw();
        gameStage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, false);
        //background.setPosition(0, 0);

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
