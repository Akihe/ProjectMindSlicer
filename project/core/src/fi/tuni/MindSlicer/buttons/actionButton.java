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
 * Button for the players basic attack. Has a texture and an inputlistener.
 *
 * <p>An object of this class will be created in each level. This is an actor that is added to a stage in each level. When the texture is pressed, this calls the players hitAction method
 *  which is the basic attack in our game.</p>
 */
public class actionButton extends Actor {

    private final Texture playTexture;
    playerActor player;

    /**
     * Texture and position of the button is set on the constructor.
     * Level index is fetched to see in which level this button is used.
     */
    public actionButton() {
        playTexture = new Texture("bubble.png");

        setWidth(playTexture.getWidth());
        setHeight(playTexture.getHeight());
        setBounds(20, 230, getWidth(), getHeight());

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
        batch.draw(playTexture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    /**
     * Inputlistener is created to read when the player touches the texture.
     */
    class PlayerListener extends InputListener {

        /**
         * Touching the texture calls the players hitAction.
         *
         * <p>A Check is performed here to see if the enemy has finished his action. This prevents the player from
         * pressing the button repeatedly.</p>
         */
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (!playerActor.playerActionDone && enemyActor.allowPlayerAttack) {
                player.hitAction();
            }
            return true;
        }
    }
}
