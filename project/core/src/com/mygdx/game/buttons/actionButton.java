package com.mygdx.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.*;
import com.mygdx.game.screens.*;

/**
 * Button for the players basic attack. Has a texture and an inputlistener.
 *
 * <p>This class will be created in each level. When the texture is pressed, this calls the players hitAction method
 * which is the basic attack in our game. </p>
 */
public class actionButton extends Actor {

    private final Texture playTexture;
    playerActor player;

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

    public void draw(Batch batch, float alpha) {
        batch.draw(playTexture, this.getX(), this.getY(), getWidth(), getHeight());

    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (!playerActor.playerActionDone && enemyActor.allowPlayerAttack) {
                player.hitAction();
            }
            return true;
        }
    }
}
