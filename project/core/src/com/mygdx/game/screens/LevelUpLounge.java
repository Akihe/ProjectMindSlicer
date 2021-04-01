package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.buttons.*;
import com.mygdx.game.*;


public class LevelUpLounge implements Screen {

    static Main host;
    SpriteBatch batch;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;
    returnButton returnBtn;
    statsButton statsBtn;
    Image image;

    public LevelUpLounge(Main host){
        this.host = host;
        batch = host.batch;

        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        backgroundTexture = new Texture("Lounge.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        returnBtn = new returnButton(100f,100f, "LevelUP");
        gameStage.addActor((returnBtn));

        statsBtn = new statsButton();
        gameStage.addActor(statsBtn);

        Skin skin = new Skin(Gdx.files.internal("test-skin.json"));

        Label nameLabel = new Label("Name:", skin);
        TextField nameText = new TextField("", skin);
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("", skin);

        Table table = new Table(skin);
        table.add(nameLabel);
        table.add(nameText).width(100);
        table.row();
        table.add(addressLabel);
        table.add(addressText).width(100);
        table.setFillParent(true);

        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGB565);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();

        Image plus = new Image(new Texture("plussa.png"));
        Image minus = new Image(new Texture("miinus.png"));
        table.add(plus, minus);

        //table.setBackground( );
        gameStage.addActor(table);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture,0,0,Main.WORLD_WIDTH,Main.WORLD_HEIGHT);
        gameStage.getBatch().end();


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

    }
}
