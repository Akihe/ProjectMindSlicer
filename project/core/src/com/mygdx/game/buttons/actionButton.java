package com.mygdx.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.*;
import com.mygdx.game.screens.*;

public class actionButton extends Actor {

    private final Texture playTexture;
    playerActor player;

    public actionButton() {
        playTexture = new Texture("wooden_sword.png");

        setWidth(playTexture.getWidth());
        setHeight(playTexture.getHeight());
        setBounds(10, 300, getWidth(), getHeight());

        player = level1.player;

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(playTexture, this.getX(), this.getY(), getWidth(), getHeight());

    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (!playerActor.playerActionDone) {
                player.hitAction();
            }
            return true;
        }
    }
}
