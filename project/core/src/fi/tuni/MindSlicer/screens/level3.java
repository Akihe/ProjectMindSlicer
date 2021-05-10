package fi.tuni.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import fi.tuni.MindSlicer.Main;
import fi.tuni.MindSlicer.buttons.settingsIngameButton;
import fi.tuni.MindSlicer.buttons.actionButton;
import fi.tuni.MindSlicer.buttons.healButton;
import fi.tuni.MindSlicer.buttons.shieldButton;
import fi.tuni.MindSlicer.buttons.thinkButton;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.enemyActor;
import fi.tuni.MindSlicer.kidActor;
import fi.tuni.MindSlicer.playerActor;

/**
 * Level 3 fighting stage, please check level 1 for more information.
 *
 * <p>Level 3 class doesn't really differ from level 1 part from the background.
 *    All the differences regarding the fights are written in other classes like in enemyActor,
 *    in which we give attack, defence and HP values based on where the object is created in (constructor usage).</p>
 */
public class level3 implements Screen {

    Main host;
    SpriteBatch batch;
    private Texture BACKGROUND;

    private actionButton actionbutton;
    private thinkButton thinkbutton;
    private shieldButton shieldButton;
    private healButton healbutton;
    private settingsIngameButton settingsingame;
    kidActor kid;
    boolean winScreenShown = false;
    Dialog openDialog;
    Dialog winDialog;

    Skin skin;
    String winner;

    float timeSinceAttack = 0;

    public static Label playerTurn;

    private final Stage gameStage;
    public static playerActor player;
    public static enemyActor enemy;

    public level3(Main host) {
        Main.save();
        defaultValues.levelInd = 3;
        this.host = host;
        skin = host.skin;


        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH, Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        BACKGROUND = new Texture("taustakolme.png");


        Main.menuMusic.stop();
        if (defaultValues.musicOn) {
            Main.fightMusic.play();
        }

        winner = Main.getLevelText("winner3");

        playerTurn = new Label("Its your turn!", skin, "chilanka-header", "RGBA_0_0_0_255");
        playerTurn.setPosition(320f, Main.WORLD_HEIGHT/1.5f);
        playerTurn.setVisible(false);

        player = new playerActor(3);
        enemy = new enemyActor(3);
        actionbutton = new actionButton();
        thinkbutton = new thinkButton();
        shieldButton = new shieldButton();
        healbutton = new healButton();
        kid = new kidActor(3);
        settingsingame = new settingsIngameButton(host, skin, gameStage);

        gameStage.addActor(player);
        gameStage.addActor(enemy);
        gameStage.addActor(actionbutton);
        gameStage.addActor(thinkbutton);
        gameStage.addActor(shieldButton);
        gameStage.addActor(healbutton);
        gameStage.addActor(kid);
        gameStage.addActor(settingsingame);
        gameStage.addActor(playerTurn);

        winPopup();
        openingDialog();
    }

    public static void playerTurnTeller() {
        if(enemyActor.allowPlayerAttack) {
            playerTurn.setVisible(true);
            playerTurn.addAction(Actions.sequence(Actions.alpha(0)
                    , Actions.fadeIn(2f), Actions.fadeOut(2f)));
        }
    }

    private void openingDialog() {
        String introduceKid = Main.getLevelText("kid3");

        openDialog = new Dialog("", skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    openDialog.setVisible(false);
                    playerTurnTeller();
                }
            }};
        openDialog.text(introduceKid);
        openDialog.button("Ok", true); //sends "true" as the result
        openDialog.pack();
        openDialog.setPosition(Main.WORLD_WIDTH/10f, Main.WORLD_HEIGHT/4f);
        openDialog.setMovable(false);
        gameStage.addActor(openDialog);
    }

    public void winPopup() {

        winDialog = new Dialog("Success!", skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    host.setScreen(new levelSelect(host));
                    player.MONEY += 300;
                    Main.save();
                }
            }};

        winDialog.text(winner);
        winDialog.button("Ok", true); //sends "true" as the result
        winDialog.pack();
        winDialog.setPosition(Main.WORLD_WIDTH/10f, Main.WORLD_HEIGHT/4f);
        winDialog.setVisible(false);
        winDialog.setMovable(false);
        gameStage.addActor(winDialog);
    }

    public void fight() {

        //Enemy attacks only after the player has attacked, and DeltaTime counts a delay for the enemies move, based on how long the players invidual attack lasts.
        if(player.playerActionDone && enemy.ENEMY_HEALTH > 0){
            timeSinceAttack += Gdx.graphics.getDeltaTime();
            if (timeSinceAttack > player.enemyAttacksAfter) {
                enemy.randomAttack();
                enemy.chooseAttack();
                timeSinceAttack = 0;
                player.enemyAttacksAfter = 0;
                player.resetPlayer();
            }
        }
    }

    public void loseWinCheck() {

        if(enemy.ENEMY_HEALTH <= 0){
            defaultValues.level3Defeated = true;
            enemy.enemyDie();
            kid.appear();
            gameStage.getActors().removeValue(settingsingame, true);

            float delay = 2;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameStage.getActors().removeValue(enemy, true);
                    player.resetPlayer();

                }
            }, delay);

            if (!winScreenShown) {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        winDialog.setVisible(true);
                        winScreenShown = true;
                    }
                }, delay*2);
            }
        }

        if(player.PLAYER_HEALTH <= 0){
            player.resetStats();
            host.setScreen(new GameOverScreen(host));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        defaultValues.levelInd=2;
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(BACKGROUND,0,0, Main.WORLD_WIDTH, Main.WORLD_HEIGHT);
        gameStage.getBatch().end();

        fight();
        loseWinCheck();

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
        Main.menuMusic.dispose();
        skin.dispose();
    }
}
