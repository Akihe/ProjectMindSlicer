package fi.tuni.MindSlicer.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fi.tuni.MindSlicer.Main;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.screens.mainMenuScreen;

/**
 * A button for entering the levelselect
 *
 * <p>This one has a texture and an Inputlistener. When the texture is pressed, it will run the touchdown method which takes you to a new screen</p>
 */

public class playButton extends Actor {

    private final Texture texture;

    public playButton() {
        texture = new Texture(Gdx.files.internal(Main.getLevelText("start")));

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(275, 110f, getWidth(), getHeight());

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

        class PlayerListener extends InputListener {

            /**
             * Touching the texture calls the setPlayScreen() method.
             *
             * <p>a method that calls the mainMenusScreen's method for setting the appropriate Screen.</p>
             */

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            mainMenuScreen.setPlayScreen();
            defaultValues.firstSaveDone = true;
            Main.save();
            return true;
        }
    }
}
