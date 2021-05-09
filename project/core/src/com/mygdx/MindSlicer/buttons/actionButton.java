package com.mygdx.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.MindSlicer.enemyActor;
import com.mygdx.MindSlicer.screens.level3;

/**
 * Button for the players basic attack. Has a texture and an inputlistener.
 *
 * <p>This class will be created in each level. When the texture is pressed, this calls the players hitAction method
 * which is the basic attack in our game. Calling th</p>
 */
public class actionButton extends Actor {

    private final Texture playTexture;
    com.mygdx.MindSlicer.playerActor player;

    public actionButton() {
        playTexture = new Texture("bubble.png");

        setWidth(playTexture.getWidth());
        setHeight(playTexture.getHeight());
        setBounds(20, 230, getWidth(), getHeight());

        if (com.mygdx.MindSlicer.defaultValues.levelInd == 1) {
            player = com.mygdx.MindSlicer.screens.level1.player;
        } else if (com.mygdx.MindSlicer.defaultValues.levelInd == 2) {
            player = com.mygdx.MindSlicer.screens.level2.player;
        } else if (com.mygdx.MindSlicer.defaultValues.levelInd == 3) {
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
            if (!com.mygdx.MindSlicer.playerActor.playerActionDone && enemyActor.allowPlayerAttack) {
                player.hitAction();
            }
            return true;
        }
    }
}
