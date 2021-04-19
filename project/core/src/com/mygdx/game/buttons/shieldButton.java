package com.mygdx.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.defaultValues;
import com.mygdx.game.enemyActor;
import com.mygdx.game.playerActor;
import com.mygdx.game.screens.level1;
import com.mygdx.game.screens.level2;

public class shieldButton extends Actor {

    private final Texture texture;
    playerActor player;

    public shieldButton() {
        texture = new Texture("shield.png");

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(170, 320, getWidth(), getHeight());

        if (defaultValues.levelInd == 1) {
            player = level1.player;
        } else if (defaultValues.levelInd == 2) {
            player = level2.player;
        }

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (!playerActor.playerActionDone && enemyActor.allowPlayerAttack) {
                player.superShield();
            }
            return true;
        }
    }
}

