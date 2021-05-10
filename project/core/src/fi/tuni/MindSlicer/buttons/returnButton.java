package fi.tuni.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fi.tuni.MindSlicer.screens.level1;
import fi.tuni.MindSlicer.screens.levelSelect;
import fi.tuni.MindSlicer.screens.mainMenuScreen;
import fi.tuni.MindSlicer.screens.settings;

/**
 * A button for returning to previous screen
 *
 * <p>This one has a texture and an Inputlistener. When the texture is pressed, it will run the touchdown method which takes you to the last screen</p>
 */

public class returnButton extends Actor {

    private final Texture texture;
    private String position;

    public returnButton(float xPos, float yPos, String screen) {
        position = screen;

        texture = new Texture("arrow.png");

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(xPos, yPos, getWidth(), getHeight());

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        /**
         * Touching the texture calls the desired method.
         * @param event
         * @param x
         * @param y
         * @param pointer
         * @param button
         * @return
         * <p>position is used to know, where the player is, and where the player will be taken</p>
         */

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (position.equals("Settings")) {
                settings.setMainMenuScreen();

            } else if (position.equals("LevelSelect")) {
                levelSelect.setMainMenu();

            } else if (position.equals("LevelUP")) {
                mainMenuScreen.setPlayScreen();
            }
            return true;
        }
    }
}
