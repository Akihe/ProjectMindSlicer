package com.mygdx.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.MindSlicer.defaultValues;
import com.mygdx.MindSlicer.playerActor;

public class statsPlusMinus extends Actor {

    private Texture texture;
    String usage;


    /**
     * This class is used for creating buttons which increase or reduce chosen values
     * at the "teachers lounge"
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

    class PlayerListener extends InputListener {
        int cost = 50;

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            /**
             * Chooses the stat which will be increased or decreased based on the parameter given when creating a button using this class.
             * Prevents the player from going below default values and checks the times bought and can u afford the cost.
             */
            if (usage.equals("attackPlus") && com.mygdx.MindSlicer.playerActor.MONEY >= cost) {
                com.mygdx.MindSlicer.defaultValues.currentAttack += 5;
                com.mygdx.MindSlicer.playerActor.MONEY -= cost;
                com.mygdx.MindSlicer.playerActor.statPointsBought++;

            } else if (usage.equals("attackMinus") && com.mygdx.MindSlicer.defaultValues.currentAttack > com.mygdx.MindSlicer.defaultValues.playerDefaultAttack) {
                com.mygdx.MindSlicer.defaultValues.currentAttack -= 5;
                com.mygdx.MindSlicer.playerActor.MONEY += cost;
                com.mygdx.MindSlicer.playerActor.statPointsBought--;

            } else if (usage.equals("defencePlus") && com.mygdx.MindSlicer.playerActor.MONEY >= cost) {
                com.mygdx.MindSlicer.defaultValues.currentDefence += 2;
                com.mygdx.MindSlicer.playerActor.MONEY -= cost;
                com.mygdx.MindSlicer.playerActor.statPointsBought++;

            } else if (usage.equals("defenceMinus") && com.mygdx.MindSlicer.defaultValues.currentDefence > com.mygdx.MindSlicer.defaultValues.playerDefaultDefence) {
                defaultValues.currentDefence -= 2;
                com.mygdx.MindSlicer.playerActor.MONEY += cost;
                playerActor.statPointsBought--;

            }
            return true;
        }
    }
}
