package com.mygdx.MindSlicer.screens;

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
import com.mygdx.MindSlicer.Main;
import com.mygdx.MindSlicer.buttons.statsPlusMinus;


public class lounge implements Screen {

    static com.mygdx.MindSlicer.Main host;
    private Stage gameStage;

    private Texture backgroundTexture;
    private TextureRegion flippedPlayer;


    private Image background;


    com.mygdx.MindSlicer.buttons.returnButton returnBtn;
    com.mygdx.MindSlicer.buttons.statsButton statsBtn;

    String enter;
    Skin skin;

    int attackValue;
    int defenceValue;

    public static Table table;

    Label attackLabel;
    Label moneyLabel;
    Label defenceLabel;
    Dialog dialog;

    public lounge(com.mygdx.MindSlicer.Main host){
        this.host = host;

        flippedPlayer = new TextureRegion(new Texture("playercharacter.png"));
        flippedPlayer.flip(true, false);

        enter = com.mygdx.MindSlicer.Main.getLevelText("Enter");
        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH, com.mygdx.MindSlicer.Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        backgroundTexture = new Texture("tlounge.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        returnBtn = new com.mygdx.MindSlicer.buttons.returnButton(50f,50f, "LevelUP");
        gameStage.addActor((returnBtn));

        statsBtn = new com.mygdx.MindSlicer.buttons.statsButton();
        gameStage.addActor(statsBtn);

        skin = host.skin;

        upgradeTable();
    }

    public void updateStats() {
        attackValue = com.mygdx.MindSlicer.defaultValues.currentAttack;
        defenceValue = com.mygdx.MindSlicer.defaultValues.currentDefence;

        attackLabel.setText("Attack : " + attackValue + "  ");
        moneyLabel.setText("Coins : " + com.mygdx.MindSlicer.playerActor.MONEY);
        defenceLabel.setText("Defence : " + defenceValue + "  ");
    }

    public void upgradeTable() {
        Drawable background = skin.getDrawable("dialog");
        attackValue = com.mygdx.MindSlicer.defaultValues.currentAttack;
        defenceValue = com.mygdx.MindSlicer.defaultValues.currentDefence;

        attackLabel = new Label(" Attack : " + attackValue, skin);
        defenceLabel = new Label(" Defence : " + defenceValue, skin);
        moneyLabel = new Label(" Coins : " + com.mygdx.MindSlicer.playerActor.MONEY, skin);

        moneyLabel.setWrap(false);
        moneyLabel.setSize(400f,20f);

        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);
                com.mygdx.MindSlicer.Main.save();
            }
        });

        Container<Table> tableContainer = new Container<Table>();
        tableContainer.setSize(300f, com.mygdx.MindSlicer.Main.WORLD_HEIGHT / 2f);
        tableContainer.setPosition(230f, com.mygdx.MindSlicer.Main.WORLD_HEIGHT / 4f);
        tableContainer.fill();

        table = new Table(skin);

        com.mygdx.MindSlicer.buttons.statsPlusMinus attackPlus = new com.mygdx.MindSlicer.buttons.statsPlusMinus("attackPlus");
        com.mygdx.MindSlicer.buttons.statsPlusMinus attackMinus = new com.mygdx.MindSlicer.buttons.statsPlusMinus("attackMinus");
        com.mygdx.MindSlicer.buttons.statsPlusMinus defencePlus = new statsPlusMinus("defencePlus");
        com.mygdx.MindSlicer.buttons.statsPlusMinus defenceMinus = new com.mygdx.MindSlicer.buttons.statsPlusMinus("defenceMinus");

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

    public void entryPopup() {
        //Dialog dialog is initialized on the top
        //boolean value for showing popup is changed to true in the render call
        Skin skin = new Skin(Gdx.files.internal("skin.json"));
        dialog = new Dialog(com.mygdx.MindSlicer.Main.getLevelText("welcome"), skin) {
            public void result(Object obj) {
                if(obj.equals(true)){
                    dialog.setVisible(false);
                }
            }};
        dialog.text(enter);
        dialog.button("Okay", true); //sends "true" as the result
        dialog.pack();
        dialog.setPosition(230f, com.mygdx.MindSlicer.Main.WORLD_HEIGHT/4f);
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
        gameStage.getBatch().draw(backgroundTexture,0,0, com.mygdx.MindSlicer.Main.WORLD_WIDTH, com.mygdx.MindSlicer.Main.WORLD_HEIGHT);
        gameStage.getBatch().draw(flippedPlayer,400f,40f, flippedPlayer.getRegionWidth(), flippedPlayer.getRegionHeight());
        gameStage.getBatch().end();

        gameStage.act();
        gameStage.draw();
        if (!com.mygdx.MindSlicer.defaultValues.LoungeEntry){
            entryPopup();
            com.mygdx.MindSlicer.defaultValues.LoungeEntry = true;
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
