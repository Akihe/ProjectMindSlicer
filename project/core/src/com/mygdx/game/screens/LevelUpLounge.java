package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
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

    int holder;

    public static Table table;

    Label attackLabel;
    Label moneyLabel;

    public void updateStats() {
        attackLabel.setText(" Attack value : " + holder);
        moneyLabel.setText("" + playerActor.MONEY);
    }

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

        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGB565);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();



        Skin skin = new Skin(Gdx.files.internal("test-skin.json"));

        Drawable background = skin.getDrawable("dialog");
        Gdx.app.log("background", "korkeus" + background.getBottomHeight());
        holder = playerActor.PLAYER_ATK;

        attackLabel = new Label(" Attack value : " + holder, skin);
        Label defenceLabel = new Label(" Defence upgrade ", skin);

        moneyLabel = new Label("Coins : " + playerActor.MONEY, skin);


        Container<Table> tableContainer = new Container<Table>();
        tableContainer.setSize(Main.WORLD_WIDTH / 2, Main.WORLD_HEIGHT / 2);
        tableContainer.setPosition(Main.WORLD_WIDTH / 4, Main.WORLD_HEIGHT / 4);
        tableContainer.fillX();

        table = new Table(skin);

        statsPlusMinus attackPlus = new statsPlusMinus("attackPlus");
        statsPlusMinus attackMinus = new statsPlusMinus("attackMinus");

        Image plus = new Image(new Texture("plussa.png"));
        Image minus = new Image(new Texture("miinus.png"));
        Image plus2 = new Image(new Texture("plussa.png"));
        Image minus2 = new Image(new Texture("miinus.png"));

        table.setBackground(background);

        table.add(moneyLabel);
        table.row();
        table.add(attackMinus, attackLabel, attackPlus);
        table.row();
        table.add(minus2, defenceLabel, plus2);

        table.setFillParent(true);

        //table.debugAll();

        table.setVisible(false);

        tableContainer.setActor(table);
        gameStage.addActor(tableContainer);
        Gdx.app.log("background", "korkeus" + background.getBottomHeight());

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        holder = playerActor.PLAYER_ATK;

        updateStats();


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
        gameStage.dispose();
    }
}