package fi.tuni.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import javax.xml.soap.Text;

import fi.tuni.MindSlicer.Main;
import fi.tuni.MindSlicer.buttons.returnButton;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.playerActor;


public class settings implements Screen {

    static Main host;
    SpriteBatch batch;
    private Stage gameStage;
    private Texture BACKGROUND;
    static Skin skin;
    private Window window;
    private Table table;
    private Window resetWindow;

    private returnButton returnbutton;
    String languagebtn;
    CheckBox musicCheckBox;

    public settings(final Main host) {
        this.host = host;
        skin = host.skin;
        languagebtn = Main.getLevelText("languagebtn");

        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH, Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        BACKGROUND = new Texture("mainmenu_screen.png");

        returnbutton = new returnButton(50f, 50f, "Settings");
        gameStage.addActor(returnbutton);
        languageButton();
        saveResetButton();
        musicOnOff();
        createInfoTable();
        saveResetDialog();
    }

    public static void setMainMenuScreen() {
        host.setScreen(new mainMenuScreen(host, skin));
    }

    private void languageButton() {
        TextButton textbtn;
        textbtn = new TextButton(languagebtn, skin);

        textbtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Main.finnish) {
                    Main.finnish = false;
                } else {
                    Main.finnish = true;
                }
                gameStage.clear();
                host.setScreen(new settings(host));
            }
        });

        textbtn.setBounds(250f, 200f, 280f, 50f);
        gameStage.addActor(textbtn);
    }

    public void infoButton() {
        TextButton infoOpener;
        infoOpener = new TextButton("Info", skin);

        infoOpener.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(true);
            }
        });

        infoOpener.setPosition(350f, 260f);
        gameStage.addActor(infoOpener);
    }

    public void saveResetButton() {
        TextButton saveReset;
        saveReset = new TextButton(Main.getLevelText("saveResetButton"), skin);

        saveReset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetWindow.setVisible(true);
                Gdx.app.log("asd","asd");
            }
        });
        saveReset.setPosition(300f, 330f);
        gameStage.addActor(saveReset);
    }

    public void saveResetDialog() {

        resetWindow = new Window(Main.getLevelText("applicationHeader"), skin, "default");

        Label text = new Label(Main.getLevelText("saveResetText"), skin);

        TextButton yes = new TextButton(Main.getLevelText("saveResetYes"), skin);
        yes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.prefs.clear();
                playerActor.MONEY = defaultValues.startingMoney;
                defaultValues.currentAttack = defaultValues.playerDefaultAttack;
                defaultValues.currentDefence = defaultValues.playerDefaultDefence;
                defaultValues.firstSaveDone = true;
                defaultValues.level1Defeated = false;
                defaultValues.level2Defeated = false;
                defaultValues.level3Defeated = false;
                Main.save();
                resetWindow.setVisible(false);
            }
        });

        TextButton no = new TextButton(Main.getLevelText("saveResetNo"), skin);
        no.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetWindow.setVisible(false);
            }
        });

        resetWindow.add(text).colspan(2).center();
        resetWindow.row().pad(5f);
        resetWindow.add(yes);
        resetWindow.add(no);
        resetWindow.pack();
        resetWindow.setPosition(180,150);
        resetWindow.setMovable(false);
        resetWindow.setVisible(false);

        gameStage.addActor(resetWindow);
    }

    public void createInfoTable() {
        infoButton();

        table = new Table();
        table.setSize(Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        window = new Window("", skin);
        window.setSize(table.getWidth(), table.getHeight());

        Image attack = new Image(new Texture("bubble.png"));
        Label attackText = new Label(Main.getLevelText("AttackText"), skin);

        Image buff = new Image(new Texture("coffee_cup.png"));
        Label buffText = new Label(Main.getLevelText("buffText"), skin);

        Image shield = new Image(new Texture("shield_icon1.png"));
        Label shieldText = new Label(Main.getLevelText("shieldText"), skin);

        Image heal = new Image(new Texture("sweet_health.png"));
        Label healText = new Label(Main.getLevelText("healText"), skin);

        Image upgradeMachine = new Image(new Texture("foodmachine.png"));
        Label upgradeText = new Label(Main.getLevelText("upgradeText"), skin);
        upgradeMachine.setScale(0.8f);

        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);
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
        window.add(upgradeMachine);
        window.add(upgradeText);
        window.row();
        window.add(close).colspan(2).center();
        window.setMovable(false);
        window.setModal(true);

        table.setVisible(false);
        table.add(window);

        gameStage.addActor(table);
    }

    public void musicOnOff() {
        musicCheckBox = new CheckBox("", skin);
        musicCheckBox.setBounds(350f, 120f, musicCheckBox.getWidth(), musicCheckBox.getHeight());

        musicCheckBox.setChecked(defaultValues.musicOn);

        musicCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!musicCheckBox.isChecked()) {
                    defaultValues.musicOn = false;
                    Main.menuMusic.stop();
                } else if (musicCheckBox.isChecked()) {
                    defaultValues.musicOn = true;
                    Main.menuMusic.play();
                }
            }
        });
        gameStage.addActor(musicCheckBox);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(BACKGROUND,0,0, Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
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
        batch.dispose();
        gameStage.dispose();
    }
}
