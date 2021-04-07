package com.mygdx.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.*;
import com.mygdx.game.screens.*;


public class thinkButton extends Actor {

    private final Texture texture;
    playerActor player;

    public thinkButton() {
        texture = new Texture("shield_icon1.png");

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(80, 320, getWidth(), getHeight());

        player = level1.player;

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            player.thinkAction();
            return true;
        }
    }
}
