package fi.tuni.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import fi.tuni.MindSlicer.main;
import fi.tuni.MindSlicer.buttons.actionButton;
import fi.tuni.MindSlicer.buttons.healButton;
import fi.tuni.MindSlicer.buttons.settingsIngameButton;
import fi.tuni.MindSlicer.buttons.shieldButton;
import fi.tuni.MindSlicer.buttons.thinkButton;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.enemyActor;
import fi.tuni.MindSlicer.kidActor;
import fi.tuni.MindSlicer.playerActor;

/**
 * The class for level 1. This is where stuff are combined and made into a level.
 *
 * <p>Alot of the classes will be combined here to build a stage that is our fighting stage.</p>
 */
public class level1 implements Screen {

    /**
     * Comes from constuctor.
     */
    main host;

    /**
     * Background image.
     */
    private Texture BACKGROUND;

    private Skin skin;

    /**
     * Fetched from the properties files.
     */
    private String winner;

    private actionButton actionbutton;
    private thinkButton thinkbutton;
    private shieldButton shieldButton;
    private healButton healbutton;
    private settingsIngameButton settingsingame;
    private kidActor kid;
    public static playerActor player;
    public static enemyActor enemy;


    /**
     * Using a boolean to display winning dialog only once.
     */
    boolean winScreenShown = false;

    private Dialog openDialog;
    private Dialog winDialog;

    /**
     * Variable that is used to count seconds.
     */
    private float timeSinceAttack = 0;

    public static Label playerTurn;

    private final Stage gameStage;

    /**
     * Everything is built in the constructor and added to a new stage in this screen.
     *
     * @param host will always be from the Main class
     */
    public level1(main host) {
        main.save();
        defaultValues.levelInd = 1;
        this.host = host;
        skin = host.skin;


        gameStage = new Stage(new StretchViewport(main.WORLD_WIDTH, main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);

        BACKGROUND = new Texture("taustakoulu.png");

        main.menuMusic.stop();
        if (defaultValues.musicOn) {
            main.fightMusic.play();
        }

        winner = main.getLevelText("winner1");

        playerTurn = new Label("Its your turn!", skin, "chilanka-header", "RGBA_0_0_0_255");
        playerTurn.setPosition(320f, main.WORLD_HEIGHT/1.5f);
        playerTurn.setVisible(false);

        player = new playerActor(1);
        enemy = new enemyActor(1);
        actionbutton = new actionButton();
        thinkbutton = new thinkButton();
        shieldButton = new shieldButton();
        healbutton = new healButton();
        kid = new kidActor(1);
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

    /**
     * This method gives the player a pop-up message that tells them "its your turn!" at right times
     */
    public static void playerTurnTeller() {
        if(enemyActor.allowPlayerAttack) {
            playerTurn.setVisible(true);
            playerTurn.addAction(Actions.sequence(Actions.alpha(0)
                    , Actions.fadeIn(2f), Actions.fadeOut(2f)));
        }
    }

    /**
     * Story dialog
     *
     * <p>Here we are creating a dialog that gives the player some background of the level. This will be shown when entering the screen
     *  And it has a button that closes the window. When the button is pressed, turnteller is called the first time to let the player know its their turn first</p>
     */
    private void openingDialog() {
        String introduceKid = main.getLevelText("kid1");

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
        openDialog.setPosition(main.WORLD_WIDTH/10f, main.WORLD_HEIGHT/4f);
        openDialog.setMovable(false);
        gameStage.addActor(openDialog);
    }

    /**
     * Creates a dialog that will be shown when the fight is won.
     *
     * <p>This dialog has a text which congratulates the winning player. Position is somewhere around the middle.
     * Has one button which closes the window and gives the player a reward of 200 coins and saves the game.</p>
     */
    public void winPopup() {

        winDialog = new Dialog("Success!", skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    host.setScreen(new levelSelect(host));
                    player.MONEY += 200;
                    main.save();
                }
            }
        };

        winDialog.text(winner);
        winDialog.button("Ok", true); //sends "true" as the result
        winDialog.pack();
        winDialog.setPosition(main.WORLD_WIDTH/10f, main.WORLD_HEIGHT/4f);
        winDialog.setVisible(false);
        winDialog.setMovable(false);
        gameStage.addActor(winDialog);
    }

    /**
     * This checks the players moves and does enemies moves after.
     *
     * <p>Once the boolean playerActionDone is over and the enemy has more than 0 health,
     *  The game will calculate seconds based on a timer inside the player class, that is changed depending on which
     *  skill the player uses and how much time it takes.
     *  After these are done, we are choosing a random attack for the enemy to use and rendering it. After this we set the
     *  modifiers back to defaults to prepare for the next turn.</p>
     */
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

    /**
     * Will be checking players and the enemies HP.
     *
     * <p>This handles the HP check to see if you won or lost the fight. If the players HP goes to 0, this will change you to a whole new screen that just says game over.
     *  If the enemies HP goes to 0, the enemy texture gets removed and we we are adding a kid Actor to the stage that appears slowly.
     *  After a short delay you get a popup window that has a short story about the fight and information about earned coins.</p>
     */
    public void loseWinCheck() {

        //Enemy HP check, If enemy HP goes to 0 or under, you win the game and the fight ends.
        if(enemy.ENEMY_HEALTH <= 0){
            defaultValues.level1Defeated = true;
            enemy.enemyDie();   //Enemy texture fades out
            kid.appear();   //Kid texture fades in
            gameStage.getActors().removeValue(settingsingame, true); //We wanted to remove the settings button here as a way to prevent it from being pressed as its not needed anymore.

            float delay = 2; //delay to run method below, in seconds.
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameStage.getActors().removeValue(enemy, true); //Removing enemy value to stop rendering it on the background
                    player.resetPlayer();   //Resetting players texture to default.

                }
            }, delay);

            if (!winScreenShown) {  //this is set to false by default, using a boolean to prevent the winning window being copied to the screen multiple times.
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        winDialog.setVisible(true); //Showing the winning window popup
                        winScreenShown = true;  //Set to true so this gets performed only once
                    }
                }, delay*2); //Using the same delay variable set above, so * 2 = 4 seconds
            }                             //This means the dialog appears 4 seconds after the enemy HP goes to 0 or less.
        }

        //Here we simply change to a new screen if the players health goes to 0 or less (player loses).
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

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(BACKGROUND,0,0, main.WORLD_WIDTH, main.WORLD_HEIGHT);
        gameStage.getBatch().end();

        fight();
        loseWinCheck();

        gameStage.act();
        gameStage.draw();

    }


    /**
     * Resize method updates the viewport dimensions, mainly for desktop / emulator purposes.
     *
     * <p>Without the resize method, enlarging or decreasing the window size would make the buttons unclickable.
     *    The textures would appear at a different place than where the clickable area is located at.</p>
     * @param width comes from the game engine, window size
     * @param height comes from the game engine, window size
     */
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
        main.menuMusic.dispose();
        main.fightMusic.dispose();
        skin.dispose();
    }
}
