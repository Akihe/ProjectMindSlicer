package fi.tuni.MindSlicer.buttons;

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
import fi.tuni.MindSlicer.Main;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.screens.mainMenuScreen;

/**
 * a button that is created in each level for information to the player
 * <p>creates a table that shows information, text and textures. Checkbox for configuring music</p>
 */

public class settingsIngameButton extends Actor {

    private final Texture texture;

    Main host;
    Skin skin;
    private Stage stage;
    Container<Table> tableContainer;
    Table table;
    Stage oldStage;
    CheckBox musicCheckBox;

    Table infoTable;
    Window window;

    public settingsIngameButton(Main host, Skin skin1, Stage gameStage) {
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

    /**
     * method for creating a help button
     * <p>When clicked, shows the created info table</p>
     */

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

    /**
     * method for creating the info table
     * <p>A table, that compiles information and images for the players attacks. When button is clicked, the table is closed</p>
     */
    public void createInfoTable() {
        infoButton();

        infoTable = new Table();
        infoTable.setSize(Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        window = new Window("", skin);
        window.setSize(infoTable.getWidth(), infoTable.getHeight());

        Image attack = new Image(new Texture("bubble.png"));
        Label attackText = new Label(Main.getLevelText("AttackText"), skin);

        Image buff = new Image(new Texture("coffee_cup.png"));
        Label buffText = new Label(Main.getLevelText("buffText"), skin);

        Image shield = new Image(new Texture("shield_icon1.png"));
        Label shieldText = new Label(Main.getLevelText("shieldText"), skin);

        Image heal = new Image(new Texture("sweet_health.png"));
        Label healText = new Label(Main.getLevelText("healText"), skin);

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
        tableContainer.setSize(Main.WORLD_WIDTH / 2f, Main.WORLD_HEIGHT / 1.5f);
        tableContainer.setPosition(Main.WORLD_WIDTH / 4f, Main.WORLD_HEIGHT / 4f);
        tableContainer.fillX();

        table = new Table(skin);

        table.setBackground(background);

        TextButton backToMain = new TextButton(Main.getLevelText("backToMain"), skin);
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
        musicCheckBox.setChecked(defaultValues.musicOn);

        musicCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!musicCheckBox.isChecked()) {
                    defaultValues.musicOn = false;
                    Main.fightMusic.stop();
                } else if (musicCheckBox.isChecked()) {
                    defaultValues.musicOn = true;
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
        /**
         * Listener for the setting button
         * @param event
         * @param x
         * @param y
         * @param pointer
         * @param button
         * @return
         * <p>When pressed, shows the settings info table</p>
         */
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            table.setVisible(true);
            Gdx.input.setInputProcessor(stage);
            return true;
        }
    }
}
