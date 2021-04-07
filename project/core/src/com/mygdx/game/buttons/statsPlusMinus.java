package com.mygdx.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.defaultValues;
import com.mygdx.game.playerActor;

public class statsPlusMinus extends Actor {

    private Texture texture;
    String usage;


    /**
     * This class is used for creating buttons which increase or reduce chosen values
     * at the "teachers lounge"
     * @param Usage What is the button used for, "attackPlus", "defenceMinus" etc.
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
            if (usage.equals("attackPlus") && playerActor.MONEY >= cost) {
                defaultValues.currentAttack += 10;
                playerActor.MONEY -= cost;
                playerActor.statPointsBought++;

            } else if (usage.equals("attackMinus") && playerActor.statPointsBought > 0 && playerActor.PLAYER_ATK > defaultValues.playerAttack) {
                defaultValues.currentAttack -= 10;
                playerActor.MONEY += cost;
                playerActor.statPointsBought--;

            } else if (usage.equals("defencePlus") && playerActor.MONEY >= cost) {
                defaultValues.currentDefence += 5;
                playerActor.MONEY -= cost;
                playerActor.statPointsBought++;

            } else if (usage.equals("defenceMinus") && playerActor.statPointsBought > 0 && playerActor.PLAYER_DEF > defaultValues.playerDefence) {
                defaultValues.currentDefence -= 5;
                playerActor.MONEY += cost;
                playerActor.statPointsBought--;
            }
            return true;
        }
    }
}
