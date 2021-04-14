package com.mygdx.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.*;
import com.mygdx.game.screens.*;

public class settingsIngameButton extends Actor {

    private final Texture texture;
    playerActor player;
    Skin skin;
    public static Table table;
    public static Stage stage;
    soundOnOff soundonoff;
    Container<Table> tableContainer;

    public settingsIngameButton(Skin skin1) {
        texture = new Texture("asetusnappi.png");
        skin = skin1;

        stage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));

        setWidth(texture.getWidth()/4f);
        setHeight(texture.getHeight()/4f);
        setBounds(730, 410, getWidth(), getHeight());

        player = level1.player;
      //  settingsTable();
        addListener(new PlayerListener());

        soundonoff = new soundOnOff();
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());

        stage.act();
        stage.draw();
    }
/*
    public void settingsTable() {

        Drawable background = skin.getDrawable("dialog4");

        tableContainer = new Container<Table>();
        tableContainer.setSize(Main.WORLD_WIDTH / 2f, Main.WORLD_HEIGHT / 2f);
        tableContainer.setPosition(Main.WORLD_WIDTH / 4f, Main.WORLD_HEIGHT / 4f);
        tableContainer.fillX();

        table = new Table(skin);

        table.setBackground(background);

        playButton returni = new playButton();
        returnButton returnbutton = new returnButton(0,0,"ingameSettings");

        table.add(returni);
        table.row();


        table.setFillParent(true);

        table.setVisible(false);
        table.setDebug(true);
        table.add(returnbutton);
        tableContainer.setActor(table);
        stage.addActor(tableContainer);
    }

 */


        class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            level1.table.setVisible(true);
            //Gdx.input.setInputProcessor(stage);
            return true;
        }
    }
}
