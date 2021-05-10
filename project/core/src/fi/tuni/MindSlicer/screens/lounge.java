package fi.tuni.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import fi.tuni.MindSlicer.Main;
import fi.tuni.MindSlicer.buttons.statsPlusMinus;
import fi.tuni.MindSlicer.buttons.returnButton;
import fi.tuni.MindSlicer.buttons.statsButton;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.playerActor;

/**
 * Screen for the teachers lounge where the player can upgrade stats.
 *
 * <p>This is just a screen with some images and one imageButton. Clicking the button opens up
 *    a window that has buttons for upgrading the players Attack and Defence stats.</p>
 */
public class lounge implements Screen {

    static Main host;
    private Stage gameStage;

    private Texture backgroundTexture;
    private TextureRegion flippedPlayer;

    private Image background;

    returnButton returnBtn;
    statsButton statsBtn;

    String enter;
    Skin skin;

    int attackValue;
    int defenceValue;

    public static Table table;

    Label attackLabel;
    Label moneyLabel;
    Label defenceLabel;
    Dialog dialog;

    /**
     * Constructor creates the buttons and textures that are used.
     *
     * @param host Comes from the Main class.
     */
    public lounge(Main host){
        this.host = host;

        flippedPlayer = new TextureRegion(new Texture("playercharacter.png"));
        flippedPlayer.flip(true, false);

        enter = Main.getLevelText("Enter");
        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH, Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        backgroundTexture = new Texture("tlounge.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        returnBtn = new returnButton(50f,50f, "LevelUP");
        gameStage.addActor((returnBtn));

        statsBtn = new statsButton();
        gameStage.addActor(statsBtn);

        skin = host.skin;

        upgradeTable();
    }

    /**
     * Used to update the labels to display correct information in real time.
     */
    public void updateStats() {
        attackValue = defaultValues.currentAttack;
        defenceValue = defaultValues.currentDefence;

        attackLabel.setText("Attack : " + attackValue + "  ");
        moneyLabel.setText("Coins : " + playerActor.MONEY);
        defenceLabel.setText("Defence : " + defenceValue + "  ");
    }

    /**
     * A Table that contains all the buttons for upgrading the players stats.
     *
     * <p>This creates a table that contains labels for your attack and defence values,
     * a label that tells you how much money you have and 4 imageButtons to increase or decrease your stats.
     * A container is used to contol the size of the table. The table is hidden and will be changed to be visible
     * when pressing the drinking machine imageButton.</p>
     */
    public void upgradeTable() {
        Drawable background = skin.getDrawable("dialog");
        attackValue = defaultValues.currentAttack;
        defenceValue = defaultValues.currentDefence;

        attackLabel = new Label(" Attack : " + attackValue, skin);
        defenceLabel = new Label(" Defence : " + defenceValue, skin);
        moneyLabel = new Label(" Coins : " + playerActor.MONEY, skin);

        moneyLabel.setWrap(false);
        moneyLabel.setSize(400f,20f);

        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);
                Main.save();
            }
        });

        Container<Table> tableContainer = new Container<Table>();
        tableContainer.setSize(300f, Main.WORLD_HEIGHT / 2f);
        tableContainer.setPosition(230f, Main.WORLD_HEIGHT / 4f);
        tableContainer.fill();

        statsPlusMinus attackPlus = new statsPlusMinus("attackPlus");
        statsPlusMinus attackMinus = new statsPlusMinus("attackMinus");
        statsPlusMinus defencePlus = new statsPlusMinus("defencePlus");
        statsPlusMinus defenceMinus = new statsPlusMinus("defenceMinus");

        table = new Table(skin);

        table.setBackground(background);

        table.add(moneyLabel).colspan(3).center();
        table.row().pad(5f);
        table.add(attackMinus, attackLabel, attackPlus);
        table.row();
        table.add(defenceMinus, defenceLabel, defencePlus);
        table.row();
        table.add(close).colspan(3).center();

        table.setFillParent(true);
        table.setVisible(false);

        tableContainer.setActor(table);

        gameStage.addActor(tableContainer);
    }

    /**
     * A popup dialog that gives you information about the room.
     *
     * <p>This popup will only be shown on your first entry.</p>
     */
    public void entryPopup() {
        //Dialog dialog is initialized on the top
        //boolean value for showing popup is changed to true in the render call
        Skin skin = host.skin;
        dialog = new Dialog(Main.getLevelText("welcome"), skin) {
            public void result(Object obj) {
                if(obj.equals(true)){
                    dialog.setVisible(false);
                }
            }};
        dialog.text(enter);
        dialog.button("Okay", true); //sends "true" as the result
        dialog.pack();
        dialog.setPosition(230f, Main.WORLD_HEIGHT/4f);
        dialog.setMovable(false);
        gameStage.addActor(dialog);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateStats();

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture,0,0, Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        gameStage.getBatch().draw(flippedPlayer,400f,40f, flippedPlayer.getRegionWidth(), flippedPlayer.getRegionHeight());
        gameStage.getBatch().end();

        gameStage.act();
        gameStage.draw();

        if (!defaultValues.loungeEntry){ //Popup will only be shown on your first entry.
            entryPopup();
            defaultValues.loungeEntry = true;
        }
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
