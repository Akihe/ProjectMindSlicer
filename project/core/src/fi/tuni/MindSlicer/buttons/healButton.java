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
 * Button for the players heal action. Has a texture and an inputlistener.
 *
 *  * <p>An object of this class will be created in each level. This is an actor that is added to a stage in each level. When the texture is pressed, this calls the players heal method
 *  *  which is our healing skill.</p>
 */
public class healButton extends Actor{

    private final Texture texture;
    playerActor player;

    /**
     * Texture and position of the button is set on the constructor.
     * Level index is fetched to see in which level this button is used.
     */
    public healButton() {
        texture = new Texture("sweet_health.png");
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(235, 230, getWidth(), getHeight());

        if (defaultValues.levelInd == 1) {
            player = level1.player;
        } else if (defaultValues.levelInd == 2) {
            player = level2.player;
        } else if (defaultValues.levelInd == 3) {
            player = level3.player;
        }

        //Adding the listener to this actor. This listener is created below.
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
         * Touching the texture calls the players heal method.
         *
         * <p>A Check is performed here to see if the enemy has finished his action. This prevents the player from
         * pressing the button repeatedly.</p>
         */
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (!playerActor.playerActionDone && enemyActor.allowPlayerAttack) {
                player.heal();
            }
            return true;
        }
    }
}

