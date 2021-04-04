package com.mygdx.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.playerActor;

public class statsPlusMinus extends Actor {

    private Texture texture;
    String usage;
    int timesPressed = 0;


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

            if (usage.equals("attackPlus") && playerActor.MONEY >= cost) {
                playerActor.PLAYER_ATK += 10;
                playerActor.MONEY -= cost;
                timesPressed++;
                Gdx.app.log("timespressed", "" + timesPressed);
            } else if (timesPressed > 0 && usage.equals("attackMinus") ) {
                playerActor.PLAYER_ATK -= 10;
                playerActor.MONEY += cost;
                timesPressed--;
            } else if (usage.equals("defencePlus")) {

            } else if (usage.equals("defenceMinus")) {

            }
            return true;
        }
    }


}
