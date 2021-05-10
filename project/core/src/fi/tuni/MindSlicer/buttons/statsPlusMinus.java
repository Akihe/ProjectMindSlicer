package fi.tuni.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.playerActor;

/**
 * This class is used for creating buttons which increase or reduce chosen values at the "teachers lounge".
 *
 * <p>This one class is used for all 4 different buttons, based on the usage string given for constructor.
 *    The "usage" string is used for the texture and to determine what pressing the button does.</p>
 */
public class statsPlusMinus extends Actor {

    private Texture texture;
    String usage;

    /**
     * In the constructor we are giving it a texture and a playerlistener.
     * @param Usage What value is the button used to decrease or increase for, "attackPlus", "defenceMinus" etc.
     */
    public statsPlusMinus(String Usage) {

        usage = Usage;

        if (usage.equals("attackPlus") || usage.equals("defencePlus")) {
            texture = new Texture("plussa.png");
        } else if (usage.equals("attackMinus") || usage.equals("defenceMinus")) {
            texture = new Texture("miinus.png");
        }

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());

        addListener(new statsPlusMinus.PlayerListener());
    }

    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY());
    }

    /**
     * Chooses the stat which will be increased or decreased based on the parameter given when creating a button using this class.
     * Prevents the player from going below default values and checks the times bought and can u afford the cost.
     */
    class PlayerListener extends InputListener {
        int cost = 50;

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            if (usage.equals("attackPlus") && playerActor.MONEY >= cost) {
                defaultValues.currentAttack += 5;
                playerActor.MONEY -= cost;
                playerActor.statPointsBought++;

            } else if (usage.equals("attackMinus") && defaultValues.currentAttack > defaultValues.playerDefaultAttack) {
                defaultValues.currentAttack -= 5;
                playerActor.MONEY += cost;
                playerActor.statPointsBought--;

            } else if (usage.equals("defencePlus") && playerActor.MONEY >= cost) {
                defaultValues.currentDefence += 2;
                playerActor.MONEY -= cost;
                playerActor.statPointsBought++;

            } else if (usage.equals("defenceMinus") && defaultValues.currentDefence > defaultValues.playerDefaultDefence) {
                defaultValues.currentDefence -= 2;
                playerActor.MONEY += cost;
                playerActor.statPointsBought--;

            }
            return true;
        }
    }
}
