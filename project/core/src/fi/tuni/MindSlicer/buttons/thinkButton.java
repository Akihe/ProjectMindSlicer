package fi.tuni.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.enemyActor;
import fi.tuni.MindSlicer.playerActor;
import fi.tuni.MindSlicer.screens.level1;
import fi.tuni.MindSlicer.screens.level2;
import fi.tuni.MindSlicer.screens.level3;


/**
 * Button for the players buff. Has a texture and an inputlistener.
 *
 * <p>An object of this class will be created in each level. This is an actor that is added to a stage in each level. When the texture is pressed, this calls the players thinkAction method
 *  which is the buffing move in our game.</p>
 */

public class thinkButton extends Actor {

    private final Texture texture;
    playerActor player;

    /**
     * Texture and position of the button is set on the constructor.
     * Level index is fetched to see in which level this button is used.
     */

    public thinkButton() {
        texture = new Texture("coffee_cup.png");

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(80, 320, getWidth(), getHeight());

        if (defaultValues.levelInd == 1) {
            player = level1.player;
        } else if (defaultValues.levelInd == 2) {
            player = level2.player;
        } else if (defaultValues.levelInd == 3) {
            player = level3.player;
        }

        addListener(new PlayerListener());
    }
    /**
     * Draws the texture.
     * @param batch
     * @param alpha
     */

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    /**
     * Inputlistener is created to read when the player touches the texture.
     */

    class PlayerListener extends InputListener {

        /**
         * Touching the texture calls the players thinkAction.
         *
         * <p>A Check is performed here to see if the enemy has finished his action. This prevents the player from
         * pressing the button repeatedly.</p>
         */

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (!playerActor.playerActionDone && enemyActor.allowPlayerAttack) {
                player.thinkAction();
            }
            return true;
        }
    }
}
