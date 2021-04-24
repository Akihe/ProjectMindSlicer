package com.mygdx.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.*;

public class settingsIngameButton extends Actor {

    private final Texture texture;
    Skin skin;
    public static Stage stage;
    soundOnOff soundonoff;
    Container<Table> tableContainer;
    Table table;
    Stage oldStage;

    public settingsIngameButton(Skin skin1, Stage gameStage) {
        texture = new Texture("asetusnappi.png");
        skin = skin1;
        oldStage = gameStage;

        stage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));

        setWidth(texture.getWidth()/4f);
        setHeight(texture.getHeight()/4f);
        setBounds(730, 410, getWidth(), getHeight());

        settingsTable();
        addListener(new PlayerListener());

        soundonoff = new soundOnOff();
    }

    public void draw(Batch batch, float alpha) {
        stage.act();
        stage.draw();
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());

    }

    public void settingsTable() {

        Drawable background = skin.getDrawable("dialog4");

        tableContainer = new Container<Table>();
        tableContainer.setSize(Main.WORLD_WIDTH / 2f, Main.WORLD_HEIGHT / 2f);
        tableContainer.setPosition(Main.WORLD_WIDTH / 4f, Main.WORLD_HEIGHT / 4f);
        tableContainer.fillX();

        table = new Table(skin);

        table.setBackground(background);

        playButton returni = new playButton();
        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);
                Gdx.input.setInputProcessor(oldStage);
            }
        });

        table.add(returni);
        table.row();

        table.setFillParent(true);

        table.setVisible(false);
        table.setDebug(true);
        table.add(close);
        tableContainer.setActor(table);
        stage.addActor(tableContainer);
    }


        class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            table.setVisible(true);
            Gdx.input.setInputProcessor(stage);
            return true;
        }
    }
}
