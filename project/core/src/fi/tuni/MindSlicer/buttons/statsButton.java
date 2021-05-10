package fi.tuni.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fi.tuni.MindSlicer.screens.lounge;

/**
 * Class for the teachers' lounge's food machine
 * <p>This class provides the parameters and textures for the upgrade machine</p>
 */
public class statsButton extends Actor {

    private final Texture texture;

    public statsButton() {
        texture = new Texture("foodmachine.png");

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(200, 130f, getWidth(), getHeight());

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());

    }

    /**
     * Inputlistener is created to read when the player touches the texture.
     */


    class PlayerListener extends InputListener {

        /**
         * inputlister that opens stat window
         * @param event
         * @param x
         * @param y
         * @param pointer
         * @param button
         * @return
         * <p>Pressing this button shows the table where the player can manage their money and stats</p>
         */

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            lounge.table.setVisible(true);
            return true;
        }
    }
}
