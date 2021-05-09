package com.mygdx.MindSlicer.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.MindSlicer.Main;
import com.mygdx.MindSlicer.screens.mainMenuScreen;

public class settingsIngameButton extends Actor {

    private final Texture texture;

    com.mygdx.MindSlicer.Main host;
    Skin skin;
    private Stage stage;
    Container<Table> tableContainer;
    Table table;
    Stage oldStage;
    CheckBox musicCheckBox;

    Table infoTable;
    Window window;

    public settingsIngameButton(com.mygdx.MindSlicer.Main host, Skin skin1, Stage gameStage) {
        texture = new Texture("asetusnappi.png");
        skin = skin1;
        oldStage = gameStage;
        this.host = host;

        stage = new Stage(gameStage.getViewport());

        setWidth(texture.getWidth()/4f);
        setHeight(texture.getHeight()/4f);
        setBounds(730, 410, getWidth(), getHeight());

        settingsTable();
        addListener(new PlayerListener());

    }

    public void draw(Batch batch, float alpha) {

        stage.act();
        stage.draw();

        stage.getBatch().begin();
        stage.getBatch().draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
        stage.getBatch().end();
    }

    public void infoButton() {
        TextButton infoOpener;
        infoOpener = new TextButton("help", skin);

        infoOpener.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoTable.setVisible(true);
            }
        });

        infoOpener.setPosition(350f, 280f);
        table.add(infoOpener);
    }

    public void createInfoTable() {
        infoButton();

        infoTable = new Table();
        infoTable.setSize(com.mygdx.MindSlicer.Main.WORLD_WIDTH, com.mygdx.MindSlicer.Main.WORLD_HEIGHT);
        window = new Window("", skin);
        window.setSize(infoTable.getWidth(), infoTable.getHeight());

        Image attack = new Image(new Texture("bubble.png"));
        Label attackText = new Label(com.mygdx.MindSlicer.Main.getLevelText("AttackText"), skin);

        Image buff = new Image(new Texture("coffee_cup.png"));
        Label buffText = new Label(com.mygdx.MindSlicer.Main.getLevelText("buffText"), skin);

        Image shield = new Image(new Texture("shield_icon1.png"));
        Label shieldText = new Label(com.mygdx.MindSlicer.Main.getLevelText("shieldText"), skin);

        Image heal = new Image(new Texture("sweet_health.png"));
        Label healText = new Label(com.mygdx.MindSlicer.Main.getLevelText("healText"), skin);

        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoTable.setVisible(false);
            }
        });

        window.add(attack);
        window.add(buff);
        window.row();
        window.add(attackText);
        window.add(buffText);
        window.row();
        window.add(shield);
        window.add(heal);
        window.row();
        window.add(shieldText);
        window.add(healText);
        window.row();
        window.add(close).colspan(2).center();
        window.setMovable(false);

        infoTable.setVisible(false);
        infoTable.add(window);

    }

    public void settingsTable() {

        Drawable background = skin.getDrawable("dialog");

        tableContainer = new Container<Table>();
        tableContainer.setSize(com.mygdx.MindSlicer.Main.WORLD_WIDTH / 2f, com.mygdx.MindSlicer.Main.WORLD_HEIGHT / 1.5f);
        tableContainer.setPosition(com.mygdx.MindSlicer.Main.WORLD_WIDTH / 4f, com.mygdx.MindSlicer.Main.WORLD_HEIGHT / 4f);
        tableContainer.fillX();

        table = new Table(skin);

        table.setBackground(background);

        TextButton backToMain = new TextButton(com.mygdx.MindSlicer.Main.getLevelText("backToMain"), skin);
        backToMain.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                host.setScreen(new mainMenuScreen(host, skin));
            }
        });

        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);
                Gdx.input.setInputProcessor(oldStage);
            }
        });

        musicCheckBox = new CheckBox("", skin);
        musicCheckBox.setChecked(com.mygdx.MindSlicer.defaultValues.musicOn);

        musicCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!musicCheckBox.isChecked()) {
                    com.mygdx.MindSlicer.defaultValues.musicOn = false;
                    com.mygdx.MindSlicer.Main.fightMusic.stop();
                } else if (musicCheckBox.isChecked()) {
                    com.mygdx.MindSlicer.defaultValues.musicOn = true;
                    Main.fightMusic.play();
                }
            }
        });

        table.add(backToMain);
        table.row().pad(5f);
        table.add(musicCheckBox);
        table.row().pad(5f);

        createInfoTable();
        table.row().pad(5f);

        table.add(close);

        table.setFillParent(true);
        table.setVisible(false);

        tableContainer.setActor(table);
        stage.addActor(tableContainer);
        stage.addActor(infoTable);
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
