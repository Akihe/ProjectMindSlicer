package fi.tuni.MindSlicer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import fi.tuni.MindSlicer.main;
import fi.tuni.MindSlicer.buttons.levelButtons;
import fi.tuni.MindSlicer.buttons.loungeButton;
import fi.tuni.MindSlicer.buttons.returnButton;
import fi.tuni.MindSlicer.defaultValues;

/**
 * A class for creating the level selection screen
 * <p>This Screen has dialogs, a Stage, a background, multiple constructed buttons and textures for completed stages</p>
 */

public class levelSelect implements Screen {

    static main host;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;

    Dialog dialog;
    Dialog tutorialText;
    Dialog endgame;
    static Skin skin;

    String open;
    String tutorial;
    String gameBeaten;

    private levelButtons levelButton1;
    private levelButtons levelButton2;
    private levelButtons levelButton3;

    private returnButton returnBtn;
    private loungeButton loungeButton;

    private Texture completed;

    /**
     * constructor for levelselect
     * @param host
     * <p>set host to this host. Gets texte with Mains methods for dialogs. Checks if music should be played. Has checkers, so the game won't show certain dialogues unnecessarily.</p>
     */
    public levelSelect (final main host) {
        this.host = host;

        skin = host.skin;
        gameStage = new Stage(new StretchViewport(main.WORLD_WIDTH, main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);
        open = main.getLevelText("startingDialog");
        tutorial = main.getLevelText("tutorial");
        gameBeaten= main.getLevelText("completed");
        completed = new Texture("checked.png");

        main.fightMusic.stop();
        if (defaultValues.musicOn) {
            main.menuMusic.play();
        }

        defaultValues.levelInd = 0;

        backgroundTexture = new Texture("mainmenu_screen.png");
        background = new Image(backgroundTexture);
        background.setPosition(0, 0);

        levelButton1 = new levelButtons(160f, 150f, 1);
        gameStage.addActor(levelButton1);

        levelButton2 = new levelButtons(350f, 150f, 2);
        gameStage.addActor(levelButton2);

        levelButton3 = new levelButtons(540f, 150f, 3);
        gameStage.addActor(levelButton3);

        returnBtn = new returnButton(50f,50f, "LevelSelect");
        gameStage.addActor((returnBtn));

        loungeButton = new loungeButton();
        gameStage.addActor(loungeButton);

        tutorialDialog();
        openingDialog();
        completedDialog();


        if (!defaultValues.introShown) {
            dialog.setVisible(true);
            defaultValues.introShown = true;
        }
        if(defaultValues.level1Defeated && defaultValues.level2Defeated && defaultValues.level3Defeated && defaultValues.introShown && !defaultValues.completionShown){
            endgame.setVisible(true);
            defaultValues.completionShown = true;
        }
    }

    /**
     * method for starting dialog
     * <p>Creates a new dialog. Its invisible as a default, but will be shown according to above requirements. When button is pressed, hides dialog, and shows the next dialog.</p>
     */
    private void openingDialog() {

        dialog = new Dialog(main.getLevelText("applicationHeader"), skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    dialog.setVisible(false);
                    tutorialText.setVisible(true);
                }
            }
        };
        dialog.text(open);
        dialog.button("Ok",true); //sends "true" as the result
        dialog.pack();
        dialog.setPosition(100,20);
        dialog.setVisible(false);
        dialog.setMovable(false);
        gameStage.addActor(dialog);
    }

    /**
     * method for tutorial dialog
     * <p>Creates a new dialog. Its invisible as a default, but will be shown according to above requirements. When button is pressed, hides dialog.</p>
     */

    private void tutorialDialog() {

        tutorialText = new Dialog(main.getLevelText("tutorialHeader"), skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    tutorialText.setVisible(false);
                }
            }
        };
        tutorialText.text(tutorial);
        tutorialText.button("Ok", true); //sends "true" as the result
        tutorialText.pack();
        tutorialText.setPosition(100, 120);
        tutorialText.setMovable(false);
        tutorialText.setVisible(false);
        gameStage.addActor(tutorialText);
    }

    /**
     * method for completion dialog
     * <p>Creates a new dialog. Its invisible as a default, but will be shown according to above requirements. When button is pressed, hides dialog, and shows the next dialog.</p>
     */

    private void completedDialog() {

        endgame = new Dialog(main.getLevelText("completedHeader"), skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    endgame.setVisible(false);
                }
            }
        };
        endgame.text(gameBeaten);
        endgame.button("Ok",true); //sends "true" as the result
        endgame.pack();
        endgame.setPosition(180,140);
        endgame.setVisible(false);
        endgame.setMovable(false);
        gameStage.addActor(endgame);
    }

    /**
     * methods for setting specific levels
     * <p>These methods are used in corresponding buttons to set the desired screen.</p>
     */
    public static void setLevel1() {
        host.setScreen(new level1(host));
    }

    public static void setLevel2() {
        host.setScreen(new level2(host));
    }

    public static void setLevel3() {
        host.setScreen(new level3(host));
    }

    public static void setLounge() {
        host.setScreen(new lounge(host));
    }

    public static void setMainMenu() {
        host.setScreen(new mainMenuScreen(host, skin));
    }

    @Override
    public void show() {
    }


    /**
     * render for levelselect.
     * @param delta
     * <p>Has checkers for tracking which stages are cleared, and updated textures accordingly</p>
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture, 0,0, main.WORLD_WIDTH, main.WORLD_HEIGHT);

        if (defaultValues.level1Defeated) {
            gameStage.getBatch().draw(completed, 250, 230, completed.getWidth(), completed.getHeight());
        }
        if (defaultValues.level2Defeated) {
            gameStage.getBatch().draw(completed, 440, 230, completed.getWidth(), completed.getHeight());

        }
        if (defaultValues.level3Defeated) {
            gameStage.getBatch().draw(completed, 630, 230, completed.getWidth(), completed.getHeight());
        }

        gameStage.getBatch().end();

        gameStage.act();
        gameStage.draw();

    }

    /**
     * resixe method for the screen
     * @param width
     * @param height
     * <p>Consistently updates screen size when changed</p>
     */
    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, false);
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

    /**
     * dispose method for levelselect
     * <p>Disposes needless assets</p>
     */
    @Override
    public void dispose() {
        gameStage.dispose();
        skin.dispose();
    }
}
