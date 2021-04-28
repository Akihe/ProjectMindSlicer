package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.buttons.*;
import com.mygdx.game.*;


public class lounge implements Screen {

    static Main host;
    SpriteBatch batch;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;
    returnButton returnBtn;
    statsButton statsBtn;

    Stage stage;
    String enter;
    Skin skin;

    int attackValue;
    int defenceValue;

    public static Table table;

    Label attackLabel;
    Label moneyLabel;
    Label defenceLabel;
    Dialog dialog;

    public void updateStats() {
        attackValue = defaultValues.currentAttack;
        defenceValue = defaultValues.currentDefence;

        attackLabel.setText("Attack : " + attackValue + "  ");
        moneyLabel.setText("Coins : " + playerActor.MONEY);
        defenceLabel.setText("Defence : " + defenceValue + "  ");
    }

    public lounge(Main host){
        this.host = host;
        batch = host.batch;

        enter = Main.getLevelText("Enter");
        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH,Main.WORLD_HEIGHT));
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

    public void upgradeTable() {
        Drawable background = skin.getDrawable("dialog");
        attackValue = defaultValues.currentAttack;
        defenceValue = defaultValues.currentDefence;

        attackLabel = new Label(" Attack : " + attackValue, skin);
        defenceLabel = new Label(" Defence : " + defenceValue, skin);

        moneyLabel = new Label(" Coins : " + playerActor.MONEY, skin);

        Container<Table> tableContainer = new Container<Table>();
        tableContainer.setSize(Main.WORLD_WIDTH / 2f, Main.WORLD_HEIGHT / 2f);
        tableContainer.setPosition(Main.WORLD_WIDTH / 4f, Main.WORLD_HEIGHT / 4f);
        tableContainer.fill();

        table = new Table(skin);

        statsPlusMinus attackPlus = new statsPlusMinus("attackPlus");
        statsPlusMinus attackMinus = new statsPlusMinus("attackMinus");
        statsPlusMinus defencePlus = new statsPlusMinus("defencePlus");
        statsPlusMinus defenceMinus = new statsPlusMinus("defenceMinus");

        table.setBackground(background);

        table.add(moneyLabel).center();
        table.row().pad(5f);
        table.add(attackMinus, attackLabel, attackPlus);
        table.row();
        table.add(defenceMinus, defenceLabel, defencePlus);

        table.setFillParent(true);

        //table.debugAll();

        table.setVisible(false);

        tableContainer.setActor(table);

        gameStage.addActor(tableContainer);
    }

    public void entryPopup() {
        //Dialog dialog is initialized on the top
        //boolean value for showing popup is changed to true in the render call
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("skin.json"));
         dialog = new Dialog("Welcome", skin, "default") {
            public void result(Object obj) {
                if(obj.equals(true)){
                    dialog.setVisible(false);
                }

                Gdx.app.log("nappi ", "nappi" + obj);
            }
        };
        dialog.text(enter);
        dialog.button("Okay", true); //sends "true" as the result
        dialog.pack();
        dialog.setPosition(Main.WORLD_WIDTH/4f, Main.WORLD_HEIGHT/4f);
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
        gameStage.getBatch().draw(backgroundTexture,0,0,Main.WORLD_WIDTH,Main.WORLD_HEIGHT);
        gameStage.getBatch().end();

        gameStage.act();
        gameStage.draw();
        if (defaultValues.LoungeEntry==false){
            entryPopup();
            defaultValues.LoungeEntry=true;
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
