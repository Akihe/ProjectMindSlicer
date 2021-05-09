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
import fi.tuni.MindSlicer.Main;
import fi.tuni.MindSlicer.buttons.levelButtons;
import fi.tuni.MindSlicer.buttons.loungeButton;
import fi.tuni.MindSlicer.buttons.returnButton;
import fi.tuni.MindSlicer.defaultValues;


public class levelSelect implements Screen {

    static Main host;
    private Stage gameStage;
    private Texture backgroundTexture;
    private Image background;

    Dialog dialog;
    Dialog tutorialText;
    static Skin skin;

    String open;
    String tutorial;

    private levelButtons levelButton1;
    private levelButtons levelButton2;
    private levelButtons levelButton3;

    private returnButton returnBtn;
    private loungeButton loungeButton;

    private Texture completed;


    public levelSelect (final Main host) {
        this.host = host;

        skin = host.skin;
        gameStage = new Stage(new StretchViewport(Main.WORLD_WIDTH, Main.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(gameStage);
        open = Main.getLevelText("startingDialog");
        completed = new Texture("checked.png");

        Main.fightMusic.stop();
        if (defaultValues.musicOn) {
            Main.menuMusic.play();
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
        if (!defaultValues.introShown) {
            dialog.setVisible(true);
            defaultValues.introShown = true;
        }
    }

    private void openingDialog() {

        dialog = new Dialog(Main.getLevelText("applicationHeader"), skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    tutorialText.setVisible(true);
                }
            }
        };
        dialog.text(open);
        dialog.button("Ok",true); //sends "true" as the result
        dialog.pack();
        dialog.setPosition(70,50);
        dialog.setVisible(false);
        dialog.setMovable(false);
        gameStage.addActor(dialog);
        }
    private void tutorialDialog() {

        tutorialText = new Dialog(Main.getLevelText("tutorialHeader"), skin, "default") {
            public void result(Object obj) {
                if (obj.equals(true)) {
                    tutorialText.setVisible(false);
                }
            }
        };
        tutorialText.text(tutorial);
        tutorialText.button("Ok", true); //sends "true" as the result
        tutorialText.pack();
        tutorialText.setPosition(100, 50);
        tutorialText.setMovable(false);
        gameStage.addActor(tutorialText);
    }

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

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(backgroundTexture, 0,0, Main.WORLD_WIDTH, Main.WORLD_HEIGHT);

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

    @Override
    public void dispose() {
        gameStage.dispose();
        skin.dispose();
    }
}
